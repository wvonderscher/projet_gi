package fr.ul.miage.projet_gi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Borne {
    private int id;
    private String etat;
    private int tempsAttente;
    public static void changeTempsAttente() throws Exception{
        afficheBorne();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir l'identifiant de la borne sur laquel vous voulez changer le temps d'attente : ");
        int idBorne = sc.nextInt();
        System.out.println("Veuillez saisir le nouveau temps d'attente pour cette borne (en minutes) : ");
        int tempsAttente = sc.nextInt();
        Connection con = Connexion.getConnexion();
        Statement updateBorne = con.createStatement();
        updateBorne.executeUpdate("UPDATE Borne SET tempsAttente ="+ tempsAttente +" WHERE idBorne = " + idBorne + ";");
        System.out.println("Changement effectué!");
        con.close();
    }
    public static void afficheBorne() throws Exception {
        Connection con = Connexion.getConnexion();
        Statement selectBorne = con.createStatement();
        ResultSet rs = selectBorne.executeQuery("SELECT * FROM Borne");
        System.out.println("Voici les bornes : ");
        while(rs.next()){
            System.out.println("Numéro de la borne : " + rs.getInt(1));
            System.out.println("Etat de la borne : " + rs.getString(2));
            if(rs.getString(3) == null){
                System.out.println("Aucun temps d'attente renseigné pour cette borne");
            }else{
                System.out.println("Temps d'attente : " + rs.getInt(3) + " minutes");
            }
        }
        con.close();
    }
    public Borne(int id, String etat, int tempsAttente) {
        this.id = id;
        this.etat = etat;
        this.tempsAttente = tempsAttente;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public int getTempsAttente() {
        return tempsAttente;
    }
    public void setTempsAttente(int tempsAttente) {
        this.tempsAttente = tempsAttente;
    }
}
