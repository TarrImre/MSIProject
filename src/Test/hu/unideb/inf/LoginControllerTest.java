package hu.unideb.inf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.h2.tools.Server;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LoginControllerTest {


    @Test
    void testMD5Encryption() throws Exception {
        assertEquals("e9c20d9b237aecc65de77a491061be5", LoginController.MD5Encryption("27c7cf400229103e00c6d8830029e29b"));
    }
}

