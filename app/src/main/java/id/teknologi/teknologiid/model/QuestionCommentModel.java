package id.teknologi.teknologiid.model;

public class QuestionCommentModel {

    private int id;
    private int id_user;
    private int id_answer;
    private String comment;
    private int status;
    private int upvote;
    private String created_at;
    private String updated_at;
    private String user_url_photo;
    private String user_name;

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_answer() {
        return id_answer;
    }

    public String getComment() {
        return comment;
    }

    public int getStatus() {
        return status;
    }

    public int getUpvote() {
        return upvote;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getUser_url_photo() {
        return user_url_photo;
    }

    public String getUser_name() {
        return user_name;
    }
}
