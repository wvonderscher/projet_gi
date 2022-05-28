package fr.ul.miage.projet_gi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static Client client = null;
	public static void main(String[] args) throws Exception {
		boolean appEnFonction = true;
		int operation;


		while(appEnFonction) {
			if(client == null) afficheMenuNonConnecte();
			else afficheMenuConnecte();
			operation = scannerInt();
			
			switch(operation) {
			case 1:
				if(client == null) client = Client.inputDonneesConnexion();
				else System.out.println("Vous êtes déjà connecté!");
				break;
			case 2:
				if(client == null) Client.inputDonneesInscription();
				else System.out.println("Vous êtes connecté! Vous ne pouvez pas vous ré-inscrire!");
				break;
			case 3:
				client = null;
				System.out.println("Vous êtes deconnecté!");
				break;
			case 4:
				System.out.println("Au revoir !");
				appEnFonction = false;
				break;
			case 5:
		    	Scanner sc = new Scanner(System.in);
		    	System.out.println("Veuillez écrire la plaque du véhicule(format : AA-000-AA)");
		    	String plaque = sc.nextLine().toUpperCase();
		    	if(Vehicule.validePlaque(plaque)) {
		    		int vehiculeId = Vehicule.getVehiculeId(plaque);
		    		client.ajouterVehicule(plaque, vehiculeId);
		    	}
		    	else {
		    		System.out.println("La plaque n'est pas au bon format");
		    	}
				break;
			case 6:
				client.modificationInformations(true);
				break;
			case 7:
				Scanner scReservation = new Scanner(System.in);
				System.out.println("Saisissez la date de début de votre reservation au format : dd/mm/yyyy hh:mm");
				String dateReservation = scReservation.nextLine();
				System.out.println("Saisissez la duree de reservation : 1h ou 2h ");
				int dureeReservation = scReservation.nextInt();
				if(Reservation.valideFormatDateReservation(dateReservation) && Reservation.verifierDateReservation(dateReservation) && (dureeReservation == 1 || dureeReservation ==2)) {
					dateReservation = dateReservation.concat(":00");
					SimpleDateFormat dateType =new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					Date datedeb = dateType.parse(dateReservation);
					Calendar datefin = Calendar.getInstance();
					datefin.setTime(datedeb);
					datefin.add(Calendar.HOUR, dureeReservation);
		        	Timestamp timestampdd = new Timestamp(datedeb.getTime());
		        	Timestamp timestampdf= new Timestamp(datefin.getTime().getTime());
					int idBorne = Borne.getBorneDisponibleId(timestampdd, timestampdf);
					if(idBorne != -1) {
						client.reserverBorne(timestampdd, timestampdf, idBorne);
						System.out.println("réservation effectué à la borne : "+idBorne);
					}else {
						System.out.println("Aucune borne n'est disponible");
					}

				}else {
					System.out.println("Erreur dans la date ou la durée proposée");
				}
				
				
				
				break;
			case 901:
				client.inputDonneesAdminGetInformationClient();
				break;
			case 902:
				client.adminAfficherTarifs();
				break;
			case 903:
				client.adminModifierTarifs();
				break;
			case 904:
				Borne.changeTempsAttente();
				break;
			case 999:
				if (client.deleteAccount() == true){
					client.deleteAccount();
					client = null;
					System.out.println("Suppression réussie !");
					System.out.println("Vous n'êtes plus connecté!");
				}
				else
					client.deleteAccount();
				break;
			default:
				System.out.println("Opération inconnue!");
				break;
			}
		}
	}
	
	public static int scannerInt() {
		Scanner sc = new Scanner(System.in);
		while(!sc.hasNextInt()) {
			sc.nextLine();
			System.out.println("entier requis");
		}
		int res = sc.nextInt();
		while(res < 0) {
			System.out.println("entier ne peut pas être négatif");
			res = sc.nextInt();
		}
		return res;
	}
	public static void afficheMenuConnecte(){
		System.out.println("-------------------------------");
		System.out.println("Parc de recharge, que souhaitez-vous faire?");
		if(client.isAdmin()){
			System.out.println("3 - Se déconnecter\n4 - Quitter\n5 - Ajouter véhicule\n901 - Voir les informations d'un client\n902 - Afficher les frais actuels\n903 - Modifier les frais actuels\n904 - Modifier temps attente bornes");
		}else{
			System.out.println("3 - Se déconnecter\n4 - Quitter\n5 - Ajouter véhicule\n6 - Modifier mes informations\n7 - Reserver une borne\n999 - Supprimer mes données (irréversible)");
		}
	}
	public static void afficheMenuNonConnecte(){
		System.out.println("-------------------------------");
		System.out.println("Parc de recharge, que souhaitez-vous faire?");
		System.out.println("1 - Se connecter\n2 - Inscription\n4 - Quitter");
	}
}
