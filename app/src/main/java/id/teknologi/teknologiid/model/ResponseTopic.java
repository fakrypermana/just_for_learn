package id.teknologi.teknologiid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTopic {

    @SerializedName("topic")
    private List<Topic> topics;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public void setSemuadosen(List<Topic> topics){
        this.topics = topics;
    }

    public List<Topic> getTopics(){
        return topics;
    }

    public void setError(boolean error){
        this.error = error;
    }

    public boolean isError(){
        return error;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "ResponseTopic{" +
                        "topics = '" + topics + '\'' +
                        ",error = '" + error + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
