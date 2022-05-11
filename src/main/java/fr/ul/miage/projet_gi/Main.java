package fr.ul.miage.projet_gi;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean appEnFonction = true;
		int operation;
		Client client = null;
		
		while(appEnFonction) {
			System.out.println("-------------------------------");
			System.out.println("Parc de recharge, que souhaitez-vous faire?");
			System.out.println("1 - Se connecter\n2 - Inscription\n3 - Quitter");
			operation = scannerInt();
			
			switch(operation) {
			case 1:
				if(client == null) client = Client.connexion();
				else System.out.println("Vous êtes déjà connecté!");
				break;
			case 2:
				Client.inscription();
				break;
			case 3:
				System.out.println("Au revoir !");
				appEnFonction = false;
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
}
