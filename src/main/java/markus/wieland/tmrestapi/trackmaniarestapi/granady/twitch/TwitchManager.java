package markus.wieland.tmrestapi.trackmaniarestapi.granady.twitch;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TwitchManager {

    private LocalDateTime lastQuery;
    private List<TwitchVideo> tempVideos;

    public TwitchManager() {
        this.lastQuery = LocalDateTime.now().minusMinutes(5);
        this.tempVideos = new ArrayList<>();
    }

    public boolean canRefreshQuery() {
        LocalDateTime now = LocalDateTime.now();
        return now.minusMinutes(2).isAfter(lastQuery);
    }

    public void update(List<TwitchVideo> twitchVideos) {
        this.tempVideos = twitchVideos;
        lastQuery = LocalDateTime.now();
    }

    public List<TwitchVideo> getTempVideos() {
        return tempVideos;
    }

    public HttpEntity getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer z92zkjtgzzjyj3hzn3fmjgawlq1f4w");
        headers.set("client-id", "bm4lmkfbwezvoaycc8sp7a25ux0lt4");
        return new HttpEntity(headers);
    }

}
