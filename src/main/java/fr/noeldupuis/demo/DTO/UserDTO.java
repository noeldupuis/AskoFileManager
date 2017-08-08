package fr.noeldupuis.demo.DTO;

import fr.noeldupuis.demo.users.Droits;

public class UserDTO {

    private String name;
    private String firstName;
    private String email;
    private String classe;
    private Droits droits;

    public UserDTO(String name, String firstName, String email, String classe, Droits droits) {
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.classe = classe;
        this.droits = droits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Droits getDroits() {
        return droits;
    }

    public void setDroits(Droits droits) {
        this.droits = droits;
    }
}
