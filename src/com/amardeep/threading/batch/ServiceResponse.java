package com.amardeep.threading.batch;

public class ServiceResponse {
    public ServiceResponse(String name, long id){
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String name;
    private long id;

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
