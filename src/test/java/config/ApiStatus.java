package config;

public enum ApiStatus {

    SUCCESS(200),
    SERVER_ERROR(500),
    BAD_REQUEST(400);

    private Integer status;

    ApiStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return status;
    }

}
