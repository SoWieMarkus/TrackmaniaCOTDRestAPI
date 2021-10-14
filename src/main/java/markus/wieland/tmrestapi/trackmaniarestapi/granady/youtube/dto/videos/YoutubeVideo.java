package markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.videos;

import markus.wieland.tmrestapi.trackmaniarestapi.granady.Content;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeVideo extends Content {

    private ContentDetails contentDetails;
    private Statistics statistics;
    private Details snippet;

    public ContentDetails getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(ContentDetails contentDetails) {
        this.contentDetails = contentDetails;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Details getSnippet() {
        return snippet;
    }

    public void setSnippet(Details snippet) {
        this.snippet = snippet;
    }

    public String getUrl() {
        return "https://www.youtube.com/watch?v=" + getId();
    }




}
