package id.teknologi.teknologiid.utils;

/**
 * Created by galihgasur on 10/9/17.
 */

public class Constant {
    public static final String EMAIL = "email";
    public static final String WORKSHOP_ID = "workshop_id";

    public enum WorkshopType {
        CAR, MOTOR, ALL
    }
    public enum FollowType {
        FOLLOWING, FOLLOWER
    }
    
    public static String getWorkshopType(WorkshopType type){
        switch (type){
            case CAR:
                return "1";
            case MOTOR:
                return "2";
        }
        return "";
    }
    public static String getFollowType(FollowType type){
        switch (type){
            case FOLLOWING:
                return "following";
            case FOLLOWER:
                return "follower";
        }
        return "";
    }
}
