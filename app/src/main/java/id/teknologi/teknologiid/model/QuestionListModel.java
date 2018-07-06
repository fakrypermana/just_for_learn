package id.teknologi.teknologiid.model;

import java.util.List;

public class QuestionListModel {

//    QUESTION LIST
    private int id;
    private int id_user;
    private String title;
    private String slug;
    private boolean is_answered;
    private int status;
    private int id_category;
    private int upvote;
    private String created_at;
    private String updated_at;
    private int views;
    private String user_name;
    private String user_url_photo;
    private String user_work;
    private List<String> tags;

    private int answer_count;

//    QUESTION LIST GETTER

    public int getId(){
        return id;
    }
    public int getId_user(){
        return id_user;
    }
    public String getTitle(){
        return title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getSlug() {
        return slug;
    }
    public boolean getIs_Answered(){
        return is_answered;
    }

    public int getStatus() {
        return status;
    }

    public int getId_category() {
        return id_category;
    }

    public int getUpvote() {
        return upvote;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getViews() {
        return views;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_url_photo() {
        return user_url_photo;
    }

    public String getUser_work() {
        return user_work;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public List<String> getTags() {
        return tags;
    }

}

