package markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.videos;

import java.util.HashMap;
import java.util.List;

public class Details {

    private String publishedAt;
    private String channelId;
    private String title;
    private String description;
    private HashMap<String, Thumbnail> thumbnails;
    private String channelTitle;
    private List<String> tags;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(HashMap<String, Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
