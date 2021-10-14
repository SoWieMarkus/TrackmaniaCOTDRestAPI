package markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.videos;

import java.util.ArrayList;
import java.util.List;

public class Videos {

    private List<YoutubeVideo> items;

    public Videos() {
        this.items = new ArrayList<>();
    }

    public List<YoutubeVideo> getItems() {
        return items;
    }

    public void setItems(List<YoutubeVideo> items) {
        this.items = items;
    }
}
