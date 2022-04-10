package hu.unideb.inf.DAO;

import hu.unideb.inf.Modell.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePatientDAO implements PatientDAO{

    private List<Patient> patients;

    public FilePatientDAO(){
        try(FileInputStream fis = new FileInputStream("patients.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);) {
            patients = (List<Patient>)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            patients = new ArrayList<>();
        }
    }

    public void serialize(){
        try (FileOutputStream fos = new FileOutputStream("patient.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(patients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePatient(Patient patient) {
        if(!patients.contains(patient)){
            patients.add(patient);
            serialize();
        }
    }

    @Override
    public void deletePatient(Patient patient) {
        if(patients.contains(patient)){
            patients.add(patient);
            serialize();
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        if (patients.contains(patient)) {
            patients.remove(patient);
            patients.add(patient);
            serialize();
        }else{
            patients.add(patient);
            serialize();
        }
    }

    @Override
    public List<Patient> getPatients() {
        return patients;
    }

    @Override
    public void close() throws Exception {

    }
}
