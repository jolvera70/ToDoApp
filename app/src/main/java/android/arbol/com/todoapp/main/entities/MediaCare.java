package android.arbol.com.todoapp.main.entities;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Jorge on 01/09/2017.
 */

public class MediaCare {

    private String mediaId;
    private String mediaName;
    private Map<String,Boolean> objects;
    private Map<String,String> images;
    private Map<String,String> videos;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public Map<String, Boolean> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, Boolean> objects) {
        this.objects = objects;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    public Map<String, String> getVideos() {
        return videos;
    }

    public void setVideos(Map<String, String> videos) {
        this.videos = videos;
    }
}
