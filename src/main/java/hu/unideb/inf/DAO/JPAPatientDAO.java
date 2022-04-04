package hu.unideb.inf.DAO;

import hu.unideb.inf.Modell.Patient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPAPatientDAO implements PatientDAO{

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void savePatient(Patient patient) {
        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deletePatient(Patient patient) {
        entityManager.getTransaction().begin();
        entityManager.remove(patient);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updatePatient(Patient patient) {
        savePatient(patient);
    }

    @Override
    public List<Patient> getPatients() {
        TypedQuery<Patient> query = entityManager.createQuery(
                "SELECT patient FROM Patient patient", Patient.class);
        List<Patient> patients = query.getResultList();
        return patients;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
