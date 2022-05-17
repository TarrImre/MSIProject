package hu.unideb.inf.DAO;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hu.unideb.inf.Modell.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class FileUserDAOTest {

    @Test
    void testConstructor() throws Exception {
        FileUserDAO actualFileUserDAO = new FileUserDAO();
        actualFileUserDAO.close();
        assertTrue(actualFileUserDAO.getUsers().isEmpty());
    }

    @Test
    void testConstructor2() {
        assertTrue((new FileUserDAO()).getUsers().isEmpty());
    }

    @Test
    void testValidate() {
        assertFalse((new FileUserDAO()).validate("janedoe", "iloveyou"));
    }

    @Test
    void testUsernameAlreadyExists() {
        assertFalse((new FileUserDAO()).usernameAlreadyExists("janedoe"));
    }

    @Test
    void testEmailAlreadyExists() {
        assertFalse((new FileUserDAO()).emailAlreadyExists("jane.doe@example.org"));
    }
}

