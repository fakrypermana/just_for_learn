package id.teknologi.teknologiid.model.pekerjaan;

public class Company {

    //variable company
    private int id;
    private String name;
    private String company_name;
    private String email;
    private String address;
    private String phone_number;
    private String short_bio;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getShort_bio() {
        return short_bio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setShort_bio(String short_bio) {
        this.short_bio = short_bio;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public String getLink() {
        return link;
    }

    private String url_photo;
    private String link;
}
