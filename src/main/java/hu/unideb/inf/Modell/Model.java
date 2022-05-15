package hu.unideb.inf.Modell;

public class Model {

    private final Patient patient;

    private final User user;

    public Model() {

        this.patient = new Patient();

        this.user = new User();
    }

    public Patient getPatient() {
        return patient;
    }

    public User getUser() { return user; }
}
