package id.teknologi.teknologiid.model;

public class RelatedPekerjaan {

    private int id;
    private String name;
    private int id_company;
    private String description;
    private String desc_long;
    private int req_employee;
    private String created_at;
    private String updated_at;
    private String skills;
    private String location;
    private int salary_min;
    private int salary_max;
    private int status;
    private String slug;
    private int views;
    private int point_min;
    private String date_exp;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getId_company() {
        return id_company;
    }

    public String getDescription() {
        return description;
    }

    public String getDesc_long() {
        return desc_long;
    }

    public int getReq_employee() {
        return req_employee;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
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

    public int getStatus() {
        return status;
    }

    public String getSlug() {
        return slug;
    }

    public int getViews() {
        return views;
    }

    public int getPoint_min() {
        return point_min;
    }

    public String getDate_exp() {
        return date_exp;
    }
}
