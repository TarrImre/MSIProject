package hu.unideb.inf.Modell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testSetRadius() {
        User user = new User();
        user.setRadius(true);
        assertTrue(user.getRadius());
    }


    @Test
    void testGetRadius() {
        assertFalse((new User()).getRadius());
    }


    @Test
    void testGetRadius2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRadius(true);
        user.setTheme("Theme");
        user.setUsername("janedoe");
        assertTrue(user.getRadius());
    }


    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setId(1);
        actualUser.setPassword("iloveyou");
        actualUser.setTheme("Theme");
        actualUser.setUsername("janedoe");
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals(1, actualUser.getId());
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals("Theme", actualUser.getTheme());
        assertEquals("janedoe", actualUser.getUsername());
    }
}

