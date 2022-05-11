package fr.ul.miage.projet_gi;

import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

//TO DO : verification regex inscriptiton
public class ClientTest {

    @Test
    public void testInscription(){
        Client client = new Client(1,"Chelh", "Sofiane", "15 rue de Paris", "0649071969", "chelh.sofiane@gmail.com", "1234-1234-1234-1234");
        assertThat(client.getEmail()).isEqualTo("chelh.sofiane@gmail.com");
    }
}
