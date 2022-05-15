package hu.unideb.inf.Modell;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String username;
    private String password;

    private String theme;

    private boolean radius;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public void setTheme(String theme) {this.theme = theme;}

    public String getTheme() {return theme;}

    public void setRadius(Boolean Radius) {this.radius = Radius;}

    public Boolean getRadius() {return radius;}

}
