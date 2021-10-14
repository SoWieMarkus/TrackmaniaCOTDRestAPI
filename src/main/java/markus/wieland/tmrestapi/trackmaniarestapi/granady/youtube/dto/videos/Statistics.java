package markus.wieland.tmrestapi.trackmaniarestapi.granady.youtube.dto.videos;

public class Statistics {

    private String viewCount;
    private String likeCount;
    private String dislikeCount;
    private String commentCount;
    private String favoriteCount;

    public Long getLikes(){
        return Long.parseLong(likeCount);
    }
    public Long getDislikes(){
        return Long.parseLong(dislikeCount);
    }

    public Long getViews(){
        return Long.parseLong(viewCount);
    }

    public Long getComments(){
        return Long.parseLong(commentCount);
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(String dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
}
