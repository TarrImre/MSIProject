package hu.unideb.inf.Modell;

import java.time.LocalDate;

public class Model {

    private Patient patient;

    public Model() {

        this.patient = new Patient();
    }

    public Patient getPatient() {
        return patient;
    }
}
