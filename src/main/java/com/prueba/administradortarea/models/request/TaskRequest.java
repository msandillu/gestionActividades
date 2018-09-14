package com.prueba.administradortarea.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRequest {

    @NotNull(message = "name is required")
    @JsonProperty("name")
    @Valid
    @Size(max=100, message = "The length of the name is too long")
    @Size(min=1, message = "The length of the name is too short")
    private String name;

    @NotNull(message = "description is required")
    @JsonProperty("description")
    @Valid
    @Size(max=100, message = "The length of the description is too long")
    @Size(min=1, message = "The length of the description is too short")
    private String description;

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


}
