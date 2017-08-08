package fr.noeldupuis.demo.users;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class UserEntity {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue()
    private long id;

    @Column(name= "NAME")
    private String name;

    @Column(name= "FIRST_NAME")
    private String firstName;

    @Column(name= "PASSWORD")
    private String password;

    @Column(name= "EMAIL", unique=true)
    private String email;

    @Column(name= "CLASSE")
    private String classe;
    @Column(name= "DROITS")
    private String droits;

    public UserEntity() {
    }

    public UserEntity(String name, String firstName, String password, String email, String classe) {
        this.name = name;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.classe = classe;
        this.droits = Droits.ROLE_NON_VALIDE.toString();
    }

    public Droits getDroits() {
        return Droits.valueOf(droits);
    }

    public void setDroits(Droits droits) {
        this.droits = droits.toString();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}

