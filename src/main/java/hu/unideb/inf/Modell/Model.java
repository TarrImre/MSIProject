package hu.unideb.inf.Modell;

import java.time.LocalDate;

public class Model {

    private Patient patient;

    private User user;

    public Model() {

        this.patient = new Patient();

        this.user = new User();
    }

    public Patient getPatient() {
        return patient;
    }

    public User getUser() { return user; }
}
