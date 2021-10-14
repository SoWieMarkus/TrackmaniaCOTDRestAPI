package markus.wieland.tmrestapi.trackmaniarestapi.granady.twitch;

import java.util.ArrayList;
import java.util.List;

public class Streams {

    protected List<Stream> data;

    public Streams() {
        this.data = new ArrayList<>();
    }

    public List<Stream> getData() {
        return data;
    }

    public void setData(List<Stream> data) {
        this.data = data;
    }
}
