package markus.wieland.tmrestapi.trackmaniarestapi.granady;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.CotdManager;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.twitch.Stream;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.twitch.Streams;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.twitch.TwitchManager;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.twitch.TwitchVideos;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.abstractvideos.AbstractVideos;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.videos.Videos;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.YoutubeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class GranaDyController {

    private RestTemplate restTemplate;
    private YoutubeManager youtubeManager;
    private TwitchManager twitchManager;
    private CotdManager cotdManager;

    public GranaDyController(@Autowired RestTemplate restTemplate, YoutubeManager youtubeManager, TwitchManager twitchManager, CotdManager cotdManager) {
        this.restTemplate = restTemplate;
        this.youtubeManager = youtubeManager;
        this.twitchManager = twitchManager;
        this.cotdManager = cotdManager;
    }

    @GetMapping("/granady/youtube")
    public Videos getLatestYoutubeVideos() {
        AbstractVideos abstractVideos = restTemplate.getForObject("https://www.googleapis.com/youtube/v3/search?part=snippet,id&channelId=UCK8aWc1IQ_XJhNJ4O-OGvnQ&maxResults=20&order=date&type=video&key=AIzaSyBny7ZLFp67PztM1NX9g3nF4A9-9DT0uB0",
                AbstractVideos.class);
        if (abstractVideos == null) return new Videos();
        String ids = youtubeManager.getIdListAsString(abstractVideos);
        return restTemplate.getForObject("https://www.googleapis.com/youtube/v3/videos?part=contentDetails,snippet,statistics&id=" + ids + "&key=AIzaSyBny7ZLFp67PztM1NX9g3nF4A9-9DT0uB0",
                Videos.class);
    }

    @GetMapping("/granady/twitch")
    public TwitchVideos getLatestTwitchVideos() {
        TwitchVideos twitchVideos = restTemplate.exchange("https://api.twitch.tv/helix/videos?user_id=451024164",
                HttpMethod.GET, twitchManager.getHeader(), TwitchVideos.class).getBody();
        return twitchVideos == null ? new TwitchVideos() : twitchVideos;
    }

    @GetMapping("/granady/twitch/stream")
    public Streams getIsLive(){
        return restTemplate.exchange("https://api.twitch.tv/helix/streams?user_id=451024164",
                HttpMethod.GET, twitchManager.getHeader(), Streams.class).getBody();
    }

    @GetMapping("/granady/latest")
    public Contents getLatestContent(){
        Contents contents = new Contents();
        try {
            if (twitchManager.canRefreshQuery()) twitchManager.update(getLatestTwitchVideos().getData());
            contents.setTwitchContent(twitchManager.getTempVideos());
        } catch (Exception e) {
            e.printStackTrace();
            contents.setTwitchContent(new ArrayList<>());
        }

        try {
            if (youtubeManager.canRefreshQuery()) youtubeManager.update(getLatestYoutubeVideos().getItems());
            contents.setYoutubeContent(youtubeManager.getTempVideos());
        } catch (Exception e) {
            e.printStackTrace();
            contents.setYoutubeContent(new ArrayList<>());
        }

        contents.setCotds(cotdManager.getLatestCOTD());
        return contents;
    }
}
