package hu.unideb.inf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.stage.Stage;

import javax.accessibility.AccessibleResourceBundle;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class UserRegistrationControllerTest {


    @Test
    void testIsValidEmailAddress() {
        assertTrue((new UserRegistrationController()).isValidEmailAddress("jane.doe@example.org"));
        assertFalse((new UserRegistrationController()).isValidEmailAddress("foo"));
    }

    @Test
    void testConstructor() throws MalformedURLException {
        UserRegistrationController actualUserRegistrationController = new UserRegistrationController();
        URL toURLResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
        AccessibleResourceBundle accessibleResourceBundle = new AccessibleResourceBundle();
        actualUserRegistrationController.initialize(toURLResult, accessibleResourceBundle);
        assertEquals("", toURLResult.getAuthority());
        assertEquals("file:/C:/Users/dobiv/AppData/Local/Temp/test.txt", toURLResult.toExternalForm());
        assertEquals("file", toURLResult.getProtocol());
        assertEquals(-1, toURLResult.getPort());
        assertEquals("/C:/Users/dobiv/AppData/Local/Temp/test.txt", toURLResult.getPath());
        assertEquals("", toURLResult.getHost());
        assertEquals("/C:/Users/dobiv/AppData/Local/Temp/test.txt", toURLResult.getFile());
        assertEquals(-1, toURLResult.getDefaultPort());
        assertEquals(80, accessibleResourceBundle.getContents().length);
    }


    @Test
    void testIsValidPassword() {
        assertFalse(UserRegistrationController.isValidPassword("iloveyou"));
        assertFalse(UserRegistrationController.isValidPassword(null));
        assertTrue(UserRegistrationController.isValidPassword("Password42"));
        assertFalse(UserRegistrationController.isValidPassword("iloveyou"));
        assertFalse(UserRegistrationController.isValidPassword(null));
        assertTrue(UserRegistrationController.isValidPassword("Password42"));
    }


    @Test
    void testMD5Encryption() throws Exception {
        assertEquals("e9c20d9b237aecc65de77a491061be5",
                UserRegistrationController.MD5Encryption("27c7cf400229103e00c6d8830029e29b"));
    }


    @Test
    void testIsValidUsername() {
        assertTrue(UserRegistrationController.isValidUsername("Name"));
        assertFalse(UserRegistrationController.isValidUsername(null));
    }
}

