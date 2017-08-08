package fr.noeldupuis.demo.fileManager;

import fr.noeldupuis.demo.users.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CoursEntity {

    @Id
    @Column(name = "COURS_ID")
    @GeneratedValue()
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UPLOAD_DATE")
    private Date date;

    @Column(name = "PATH", length = 1000)
    private String path;

    @Column(name = "DESCRIPTION", length = 3000)
    private String description;

    @Column(name = "STATUS")
    private String status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity auteur;

    public CoursEntity() {}

    public CoursEntity(String name, String description, UserEntity auteur) {
        this.name = name;
        this.date = new Date();
        this.description = description;
        this.auteur = auteur;
        this.path = "";
        this.status = Status.PROPOSE.toString();
    }

    public Status getStatus() {
        return Status.valueOf(this.status);
    }

    public void setStatus(Status status) {
        this.status = status.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getAuteur() {
        return auteur;
    }

    public void setAuteur(UserEntity auteur) {
        this.auteur = auteur;
    }
}
