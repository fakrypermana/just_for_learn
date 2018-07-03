package id.teknologi.teknologiid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostNewThread {


    //deklarasi
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("post")
    @Expose
    private String post;

    @SerializedName("id_user")
    @Expose
    private int id_user;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("url_cover")
    @Expose
    private String url_cover;

    //getter
    public String getTitle() {
        return title;
    }

    public String getPost() {
        return post;
    }

    public int getId_user() {
        return id_user;
    }

    public String getSlug() {
        return slug;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getId() {
        return id;
    }

    public String getUrl_cover() {
        return url_cover;
    }
}
