package se.remit.core.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Video item model
 */
public class Video {

    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("sources")
    @Expose
    private List<String> sources = null;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("image-480x270")
    @Expose
    private String smallImage;
    @SerializedName("image-780x1200")
    @Expose
    private String largeImage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("studio")
    @Expose
    private String studio;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

}