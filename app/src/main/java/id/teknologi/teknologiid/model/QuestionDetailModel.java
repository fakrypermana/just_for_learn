package id.teknologi.teknologiid.model;

import java.util.List;

public class QuestionDetailModel {
            private int id;
            private int id_user;
            private String title;
            private String slug;
            private String question;
            private boolean is_answered;
            private int status;
            private int id_category;
            private int upvote;
            private String created_at;
            private String updated_at;
            private int views;
            private List<String>tags;

    public int getId(){
        return id;
    }
    public int getId_user(){
        return id_user;
    }
    public String getTitle(){
        return title;
    }
    public String getSlug(){
        return slug;
    }
    public String getQuestion(){
        return question;
    }
    public boolean getIs_answered(){
        return is_answered;
    }
    public int getStatus(){
        return status
    }
    public int getId_category(){
        return id_category;
    }
    public int getUpvote(){
        return upvote
    }
    public String getCreated_at(){
        return created_at;
    }
    public String getUpdated_at(){
        return updated_at;
    }
    public int getViews(){
        return views;
    }
    public List<String> getTagstags(){
        return tags;
    }

}
