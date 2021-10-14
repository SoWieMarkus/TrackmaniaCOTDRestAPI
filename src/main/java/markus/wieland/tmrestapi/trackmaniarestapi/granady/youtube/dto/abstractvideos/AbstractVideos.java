package markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.abstractvideos;


import java.util.Iterator;
import java.util.List;

public class AbstractVideos implements Iterable<AbstractVideo> {

    private List<AbstractVideo> items;

    public List<AbstractVideo> getItems() {
        return items;
    }

    @Override
    public Iterator<AbstractVideo> iterator() {
        return items.iterator();
    }
}
