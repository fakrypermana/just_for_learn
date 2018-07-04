package id.teknologi.teknologiid.model;

import com.google.gson.annotations.SerializedName;

public class Topic {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


    @Override
    public String toString(){
        return
                "SemuadosenItem{" +
                        "nama = '" + name + '\'' +
                        ",id = '" + id +
                        "}";
    }
}
