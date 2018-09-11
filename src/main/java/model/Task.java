package model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "NUMERIC(19,0)")
    private Integer id;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    private Integer creationUser;

    @Column(name = "CREATIONDATE")
    private Date creationDate;

    public Task(@NotNull String name, @NotNull String description, Integer creationUser, Date creationDate) {
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
