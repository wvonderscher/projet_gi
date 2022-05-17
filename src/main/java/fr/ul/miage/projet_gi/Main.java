package fr.ul.miage.projet_gi;

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
				if(client == null) client = Client.connexion();
				else System.out.println("Vous êtes déjà connecté!");
				break;
			case 2:
				if(client == null) Client.inscription();
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
		    	String plaque = sc.nextLine();
				client.ajouterVehicule(plaque);
				break;
			case 6:
				client.modificationInformations(true);
				break;
			case 901:
				client.adminGetInfoClient();
				break;
			case 902:
				client.adminAfficherTarifs();
			break;
			case 903:
				client.adminModifierTarifs();
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
			System.out.println("3 - Se déconnecter\n4 - Quitter\n5 - Ajouter véhicule\n901 - Voir les informations d'un client\n902 - Afficher les frais actuels\n903 - Modifier les frais actuels");
		}else{
			System.out.println("3 - Se déconnecter\n4 - Quitter\n5 - Ajouter véhicule\n6 - Modifier mes informations");
		}
	}
	public static void afficheMenuNonConnecte(){
		System.out.println("-------------------------------");
		System.out.println("Parc de recharge, que souhaitez-vous faire?");
		System.out.println("1 - Se connecter\n2 - Inscription\n4 - Quitter");
	}
}
