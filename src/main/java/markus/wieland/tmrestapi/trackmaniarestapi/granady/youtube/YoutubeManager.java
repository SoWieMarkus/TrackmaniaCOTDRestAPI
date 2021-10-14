package markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube;

import markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.abstractvideos.AbstractVideo;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.abstractvideos.AbstractVideos;
import markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.videos.YoutubeVideo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class YoutubeManager {

    private LocalDateTime lastQuery;
    private List<YoutubeVideo> tempVideos;

    public YoutubeManager(){
        this.lastQuery = LocalDateTime.now();
        this.tempVideos = new ArrayList<>();
    }

    public String getIdListAsString(AbstractVideos abstractVideos) {
        List<String> videoIds = new ArrayList<>();
        for (AbstractVideo abstractVideo : abstractVideos) {
            videoIds.add(abstractVideo.getId().getVideoId());
        }
        return String.join(",", videoIds);
    }

    public boolean canRefreshQuery(){
        LocalDateTime now = LocalDateTime.now();
        return now.minusMinutes(20).isAfter(lastQuery);
    }

    public void update(List<YoutubeVideo> youtubeVideos) {
        this.tempVideos = youtubeVideos;
        lastQuery = LocalDateTime.now();
    }

    public List<YoutubeVideo> getTempVideos() {
        return tempVideos;
    }
}
