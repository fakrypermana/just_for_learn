package id.teknologi.teknologiid.model;

import java.util.List;

public class DetileThread {

    //deklarasi
    private int id;
    private int upvote;
    private String commenter_name;
    private String commenter_url_photo;
    private String comment;


    //get data
    public int getId() {
        return id;
    }

    public int getUpvote() {
        return upvote;
    }

    public String getCommenter_name() {
        return commenter_name;
    }

    public String getCommenter_url_photo() {
        return commenter_url_photo;
    }

    public String getComment() {
        return comment;
    }
}
