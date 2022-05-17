package fr.ul.miage.projet_gi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@DisplayName("Test Vehicule")
public class VehiculeTest {

	@DisplayName("Test methode getVehiculeId lorsque le vehicule existe deja")
	@Test
	void plaqueExiste() {

	}
	
	
	@DisplayName("Test methode getVehiculeId lorsque le vehicule existe pas")
	@Test
	void plaqueExistePas() {
		assertThat(Vehicule.getVehiculeId("ZZ-999-ZZ")).isEqualTo(-1);
	}
	
	@DisplayName("Test methode validePlaque : quans la plaque est invalide")
	@Test
	void plaqueIncorrect() {
		assertThat(Vehicule.validePlaque("AA-000-A")).isFalse();
		assertThat(Vehicule.validePlaque("")).isFalse();
		assertThat(Vehicule.validePlaque(null)).isFalse();
		assertThat(Vehicule.validePlaque("-1")).isFalse();
		assertThat(Vehicule.validePlaque("AA-000-AA ")).isFalse();
		assertThat(Vehicule.validePlaque(" AA-000-AA")).isFalse();
		assertThat(Vehicule.validePlaque("14-000-AA")).isFalse();
	}
	
	
	@DisplayName("Test methode validePlaque : quans la plaque est valide")
	@Test
	void plaqueCorrect() {
		assertThat(Vehicule.validePlaque("AA-000-AA")).isTrue();
		assertThat(Vehicule.validePlaque("DU-426-ZG")).isTrue();
		assertThat(Vehicule.validePlaque("bg-426-ze")).isTrue();
		assertThat(Vehicule.validePlaque("bg-426-ze")).isTrue();
		
	}
	
}
