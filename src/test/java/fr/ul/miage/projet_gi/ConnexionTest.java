package fr.ul.miage.projet_gi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

@DisplayName("Test de connexion")
public class ConnexionTest {

    @DisplayName("Test de connexion avec mauvais nom")
    @Test
    public void testConnexionMdpVide(){
        Client client = new Client();
        client.setEmail("totsdsdo@live.fr");
        client.setMotdepasse("");
        assertNull(Client.traitementDonneesConnexion(client));
    }

    @DisplayName("Test de connexion avec un email dans le mauvais format")
    @Test
    public void testConnexionMauvaisEmail(){
        Client client = new Client();
        client.setEmail("totsdsd");
        client.setMotdepasse("motdepasse");
        assertNull(Client.traitementDonneesConnexion(client));
    }

}
