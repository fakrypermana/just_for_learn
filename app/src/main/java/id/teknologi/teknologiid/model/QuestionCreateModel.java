package id.teknologi.teknologiid.model;

import java.util.List;

public class QuestionCreateModel {

            private String title;
            private int id_category;
            private String question;
            private int id_user;
            private String slug;
            private String updated_at;
            private String created_at;
            private int id;
            private List<String> tags;

    public String getTitle() {
        return title;
    }

    public int getId_category() {
        return id_category;
    }

    public String getQuestion() {
        return question;
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

    public List<String> getTags() {
        return tags;
    }
}
