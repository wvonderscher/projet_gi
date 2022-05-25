package fr.ul.miage.projet_gi;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

@DisplayName("Test de l'inscription")
public class InscriptionTest {
    @DisplayName("Test de l'inscription avec mauvais nom")
    @Test
    public void testInscriptionMauvaisNom(){
        assertFalse(Client.traitementDonneesInscription("","test","test","0123456789","test@test.fr","testtest","0000-1111-2222-3333"));
    }

    @DisplayName("Test de l'inscription avec mauvais prénom")
    @Test
    public void testInscriptionMauvaisPrenom(){
        assertFalse(Client.traitementDonneesInscription("test","","test","0123456789","test@test.fr","testtest","0000-1111-2222-3333"));
    }

    @DisplayName("Test de l'inscription avec mauvais adresse")
    @Test
    public void testInscriptionMauvaisAdresse(){
        assertFalse(Client.traitementDonneesInscription("test","test","","0123456789","test@test.fr","testtest","0000-1111-2222-3333"));
    }

    @DisplayName("Test de l'inscription avec mauvais email")
    @Test
    public void testInscriptionMauvaisEmail(){
        assertFalse(Client.traitementDonneesInscription("test","test","test","0123456789","testtest.fr","testtest","0000-1111-4444-5555"));
    }

    @DisplayName("Test de l'inscription avec mauvais password")
    @Test
    public void testInscriptionMauvaisMotDePasse(){
        assertFalse(Client.traitementDonneesInscription("test","test","test","0123456789","test@test.fr","","0000-1111-4444-5555"));
    }

    @DisplayName("Test de l'inscription avec mauvais numéro de téléphone")
    @Test
    public void testInscriptionMauvaisNumero(){
        assertFalse(Client.traitementDonneesInscription("test","test","test","012345789","test@test.fr","testtest","0000-1111-2222-3333"));
    }

    @DisplayName("Test de l'inscription avec mauvais numéro de carte")
    @Test
    public void testInscriptionMauvaisNumeroCarte(){
        assertFalse(Client.traitementDonneesInscription("test","test","test","0123456789","test@test.fr","testtest","dadada"));
    }
}
