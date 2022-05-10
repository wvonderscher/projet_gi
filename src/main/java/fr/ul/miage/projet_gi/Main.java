package fr.ul.miage.projet_gi;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean appEnFonction = true;
		int operation;
		
		while(appEnFonction) {
			System.out.println("-------------------------------");
			System.out.println("Parc de recharge, que souhaitez-vous faire?");
			System.out.println("1 - Se connecter\n2 - Quitter");
			operation = scannerInt();
			
			switch(operation) {
			case 1:
				//connexion
				break;
			case 2:
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
