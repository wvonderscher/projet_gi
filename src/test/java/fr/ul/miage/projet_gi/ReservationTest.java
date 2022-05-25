package fr.ul.miage.projet_gi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@DisplayName("Test Reservation")
public class ReservationTest {

	@DisplayName("Test methode valideFormatDateReservation : quand le format de la date n'est pas valide")
	@Test
	public void formatDateEchec() {
		assertThat(Reservation.valideFormatDateReservation("bonjour")).isFalse();
		assertThat(Reservation.valideFormatDateReservation("01*25/2000")).isFalse();
		assertThat(Reservation.valideFormatDateReservation(null)).isFalse();
		assertThat(Reservation.valideFormatDateReservation("0")).isFalse();
		assertThat(Reservation.valideFormatDateReservation("")).isFalse();
	}
	
	@Test
	public void formatDateOK() {
		assertThat(Reservation.valideFormatDateReservation("01/01/2000 05:02")).isTrue();
		assertThat(Reservation.valideFormatDateReservation("32/09/1999 09:10")).isTrue();
		assertThat(Reservation.valideFormatDateReservation("99/18/1000 18:30")).isTrue();
	}
	
	
	@Test 
	public void dateReservationOK() {
		assertThat(Reservation.verifierDateReservation("32/05/2022 10:00")).isTrue();
		assertThat(Reservation.verifierDateReservation("01/05/2030 01:25")).isTrue();
	}
	
	@Test 
	public void dateReservationEchec() {
		assertThat(Reservation.verifierDateReservation("01/02/2020 12:")).isFalse();
		assertThat(Reservation.verifierDateReservation("01/02/2022")).isFalse();
		assertThat(Reservation.verifierDateReservation("01/022020")).isFalse();
		assertThat(Reservation.verifierDateReservation(null)).isFalse();
	}

}
