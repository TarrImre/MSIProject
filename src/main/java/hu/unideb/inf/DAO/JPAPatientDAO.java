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

    public boolean cardnumberAlreadyExists(int cardNumber) {
        TypedQuery<Patient> query = entityManager.createQuery("from Patient p where p.cardNumber = :cardNumber",Patient.class).setParameter("cardNumber", cardNumber);
        query.setParameter("cardNumber",cardNumber);

        List<Patient> ret = query.getResultList();

        if (ret.size() > 0) return true;

        return false;
    }


    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
