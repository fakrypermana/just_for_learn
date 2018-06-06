package id.teknologi.teknologiid.model;

import java.util.List;

public class Pekerjaan {

    //Pekerjaan
    private int id;
    private String description;
    private String name;
    private String photo;
    private int req_employee;
    private String location;
    private int salary_min;
    private int salary_max;
    private String slug;
    private int views;
    private String poin_min;
    private int status;
    private String date_exp;

    //Detail Pekerjaan
    private String desc_long;
    private int id_company;
    private String skills;
    private List<String> tags;
    private List<Company> company;


    //getter pekerjaan list
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public int getReq_employee() {
        return req_employee;
    }

    public String getSkills() {
        return skills;
    }

    public String getLocation() {
        return location;
    }

    public int getSalary_min() {
        return salary_min;
    }

    public int getSalary_max() {
        return salary_max;
    }

    public String getSlug() {
        return slug;
    }

    public int getViews() {
        return views;
    }

    public String getPoin_min() {
        return poin_min;
    }

    public int getStatus() {
        return status;
    }

    public String getDate_exp() {
        return date_exp;
    }


    //getter detail pekerjaan
    public String getDesc_long() {
        return desc_long;
    }

    public int getId_company() {
        return id_company;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<Company> getCompany() {
        return company;
    }
}
