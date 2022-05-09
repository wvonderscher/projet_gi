package fr.ul.miage.projet_gi;

public class Main {
	public static void main(String[] args) throws Exception {
		Client client = Client.connexion("test", "test");
		System.out.println(client.toString());
	}
}
