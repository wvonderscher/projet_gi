package fr.ul.miage.projet_gi;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import static org.assertj.core.api.Assertions.*;

@DisplayName("Test de client")
public class ClientTest {
    @Test //TO DO regex,...
    public void inscription(){
        Client c = new Client(1, "Chelh", "Sofiane", "116 avenue de Nancy", "0649071969", "chelh.sofiane@gmail.com");
        assertThat(c.getEmail()).isEqualTo("chelh.sofiane@gmail.com");
    }

    @Test //TO DO regex,...
    public void connexion(){
        Client c = new Client(1, "Chelh", "Sofiane", "116 avenue de Nancy", "0649071969", "chelh.sofiane@gmail.com");
        assertThat(c.getEmail()).isEqualTo("chelh.sofiane@gmail.com");
      }
}
