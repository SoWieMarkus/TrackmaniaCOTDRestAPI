package markus.wieland.tmrestapi.trackmaniarestapi.granady.twitch;

import java.util.ArrayList;
import java.util.List;

public class TwitchVideos {
    private List<TwitchVideo> data;

    public TwitchVideos() {
        this.data = new ArrayList<>();
    }

    public List<TwitchVideo> getData() {
        return data;
    }

    public void setData(List<TwitchVideo> data) {
        this.data = data;
    }
}
