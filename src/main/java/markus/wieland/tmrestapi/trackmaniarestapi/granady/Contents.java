package markus.wieland.tmrestapi.trackmaniarestapi.granady;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.models.COTD;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.twitch.TwitchVideo;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.videos.YoutubeVideo;

import java.util.List;

public class Contents {

    private List<YoutubeVideo> youtubeContent;
    private List<TwitchVideo> twitchContent;
    private List<COTD> cotds;

    public List<YoutubeVideo> getYoutubeContent() {
        return youtubeContent;
    }

    public void setYoutubeContent(List<YoutubeVideo> youtubeContent) {
        this.youtubeContent = youtubeContent;
    }

    public List<TwitchVideo> getTwitchContent() {
        return twitchContent;
    }

    public void setTwitchContent(List<TwitchVideo> twitchContent) {
        this.twitchContent = twitchContent;
    }

    public List<COTD> getCotds() {
        return cotds;
    }

    public void setCotds(List<COTD> cotds) {
        this.cotds = cotds;
    }
}
