package com.justin.social.RetrofitUtils.DataBean;


/**
 * Created by Justinliu on 2018/1/29.
 */

public class SoundCloudMusic {

    private int id;

    private int duration;

    private long original_content_size;

    private String title;

    private String stream_url;

    private String artwork_url;

    private UserConfig user;

    public String getUserName() {
        if (user != null)
            return user.getUsername();
        return "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream_url() {
        return stream_url;
    }

    public void setStream_url(String stream_url) {
        this.stream_url = stream_url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getOriginal_content_size() {
        return original_content_size;
    }

    public void setOriginal_content_size(long original_content_size) {
        this.original_content_size = original_content_size;
    }

    public String getArtwork_url() {
        return artwork_url;
    }

    public void setArtwork_url(String artwork_url) {
        this.artwork_url = artwork_url;
    }

    public UserConfig getUser() {
        return user;
    }

    public void setUser(UserConfig user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SoundCloudMusic{" +
                "title='" + title + '\'' +
                ", uri=" + stream_url +
                ", artwork_url='" + artwork_url + '\'' +
                '}';
    }


    public SoundCloudMusic(int id, int duration, long original_content_size, String title, String stream_url, String artwork_url, UserConfig user) {
        this.id = id;
        this.duration = duration;
        this.original_content_size = original_content_size;
        this.title = title;
        this.stream_url = stream_url;
        this.artwork_url = artwork_url;
        this.user = user;
    }
}
