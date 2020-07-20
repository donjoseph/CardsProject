package config;

public enum JokerStatus {

    JOKER_STATUS_TRUE(true),
    JOKER_STATUS_FALSE(false),
    JOKER_STATUS_NULL(null);

    private Boolean status;

    JokerStatus(Boolean status){
        this.status = status;
    }

    public Boolean getStatus(){
        return status;
    }
}
