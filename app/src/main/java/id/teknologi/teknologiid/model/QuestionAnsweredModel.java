package id.teknologi.teknologiid.model;

import java.security.PublicKey;
import java.util.List;

public class QuestionAnsweredModel {
            private int id;
            private int id_user;
            private int id_question;
            private String answer;
            private int status;
            private int upvote;
            private String created_at;
            private String updated_at;
            private int block;
            private String user_name;
            private String user_url_photo;
            private List<QuestionCommentModel> comments;

    public int getId(){
        return id;
    }
    public int getId_user(){
        return id_user;
    }
    public int getId_question(){
        return id_question;
    }
    public String getAnswer(){
        return answer;
    }
    public int getStatus(){
        return status;
    }
    public int getUpvote(){
        return upvote;
    }
    public String getCreated_at(){
        return created_at;
    }
    public String getUpdated_at(){
        return updated_at;
    }
    public int getBlock(){
        return block;
    }
    public String getUser_name(){
        return user_name;
    }
    public String getUser_url_photo(){
        return user_url_photo;
    }

    public List<QuestionCommentModel> getComments() {
        return comments;
    }
}
