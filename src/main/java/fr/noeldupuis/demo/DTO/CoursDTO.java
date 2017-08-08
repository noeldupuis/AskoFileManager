package fr.noeldupuis.demo.DTO;

import fr.noeldupuis.demo.fileManager.Status;

public class CoursDTO {

    private long id;
    private String name;
    private String description;
    private String path;
    private UserDTO author;
    private Status statut;

    public CoursDTO(long id, String name, String description, String path, UserDTO author, Status statut) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
        this.author = author;
        this.statut = statut;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public Status getStatut() {
        return statut;
    }

    public void setStatut(Status statut) {
        this.statut = statut;
    }
}

