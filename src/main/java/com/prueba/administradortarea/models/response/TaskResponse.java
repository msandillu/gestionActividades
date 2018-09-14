package com.prueba.administradortarea.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaskResponse {

    private Integer id;
    private String name;
    private String description;
    private Integer creationUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date creationDate;

    public TaskResponse(){
    }

    public TaskResponse(Integer id, String name, String description, Integer creationUser, Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationUser = creationUser;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(Integer creationUser) {
        this.creationUser = creationUser;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
