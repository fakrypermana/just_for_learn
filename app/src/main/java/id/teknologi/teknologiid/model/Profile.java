package id.teknologi.teknologiid.model;

public class Profile {

    private int id;
    private String name;
    private String email;
    private String adress;
    private String phone_number;
    private String short_bio;
    private int work;   //masih belum jelas pembagiannya
    private int point;
    private int idrank;
    private String username;
    private String url_photo;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getShort_bio() {
        return short_bio;
    }

    public int getWork() {
        return work;
    }

    public int getPoint() {
        return point;
    }

    public int getIdrank() {
        return idrank;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl_photo() {
        return url_photo;
    }
}
