package id.teknologi.teknologiid.model;

public class Pekerjaan {
    private int id;
    private String description;
    private String name;
    private int req_employee;
    private String skills;
    private String location;
    private int salary_min;
    private int salary_max;
    private String slug;
    private int views;
    private String poin_min;
    private int status;
    private String date_exp;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
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
}
