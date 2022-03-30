package hu.unideb.inf.DAO;

import hu.unideb.inf.Modell.Patient;

import java.util.List;

public interface PatientDAO extends AutoCloseable{

    public void savePatient(Patient patient);

    public void deletePatient(Patient patient);

    public void updatePatient(Patient patient);

    public List<Patient> getPatients();


}
