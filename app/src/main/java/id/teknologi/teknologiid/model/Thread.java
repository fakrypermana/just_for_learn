package id.teknologi.teknologiid.model;

import java.util.List;

public class Thread {

    //Variabel di Thread
    private int id;
    private String title;
    private String slug;
    private String url_cover;
    private String created_at;
    private String updated_at;
    private int upvote;
    private int views;
    private int status;
    private String user_name;
    private String user_url_photo;
    private String user_work;
    private List<String> topics;
    private List<ThreadCommentModel> comments;

    //inisiasi variable di DetileThread
    private int id_user;
    private int post;

    //getter di Thread
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getSlug() {
        return slug;
    }
    public String getUrl_cover() {
        return url_cover;
    }
    public String getCreated_at() {
        return created_at;
    }
    public String getUpdated_at() {
        return updated_at;
    }
    public int getUpvote() { return upvote; }
    public int getViews() { return views; }
    public int getStatus() { return status; }
    public String getUsername() { return user_name; }
    public String getUser_url_photo() { return user_url_photo; }
    public String getUser_work() { return user_work; }
    public List<String> getTopics() { return topics; }
    public List<ThreadCommentModel> getComments() {
        return comments;
    }

    //getter di DetileThread
    public int getId_user() {
        return id_user;
    }
    public int getPost() {
        return post;
    }

}


