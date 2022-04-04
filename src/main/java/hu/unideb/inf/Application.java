package hu.unideb.inf;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hu.unideb.inf.DAO.FilePatientDAO;
import hu.unideb.inf.DAO.JPAPatientDAO;
import hu.unideb.inf.DAO.PatientDAO;
import hu.unideb.inf.Modell.*;
import org.h2.tools.Server;

public class Application{
    public static void main(String[] args) throws SQLException {
        startDatabase();
        try(PatientDAO aDAO = new JPAPatientDAO();){
            Patient p = new Patient();
            p.setName("lala");
            p.setAge(90);
            p.setCity("debrecen");
            aDAO.savePatient(p);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
}
