package fr.ul.miage.projet_gi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String numeroCarte;

    public static Client inscription(int id,String nom, String prenom, String adresse, String telephone, String email, String numeroCarte) {
        if(nom.length() < 3) {
            System.out.println("Veuillez saisir un nom supérieur à 3 caractères!");
            return null;
        }
        if(prenom.length() < 3) {
            System.out.println("Veuillez saisir un prénom supérieur à 3 caractères!");
            return null;
        }
        if(adresse.length() < 5) {
            System.out.println("Veuillez saisir un adresse supérieur à 5 caractères!");
            return null;
        }
        if(!(telephone.length() == 10)) {
            System.out.println("Veuillez saisir un numéro de téléphone valide! (10 numéros)");
            return null;
        }
        if(email.length() < 3) {
            System.out.println("Veuillez saisir un mail valide! (xx@yy.zz)");
            return null;
        }
        if(numeroCarte.length() < 8) {
            System.out.println("Veuillez saisir une carte bancaire valide! (1234-5678-9123-4567");
            return null;
        }
        Connection con = Connexion.getConnexion();
        try {
            Statement insert = con.createStatement();
            if(insert.executeUpdate("INSERT INTO client(idClient,nom,prenom,adresse,telephone,email,numeroCarte) VALUES (1 + ',' + nom + ',' + prenom + ',' +adresse+ ',' +telephone+ ',' +email+ ',' +numeroCarte)") == 1) {
                System.out.println("Inscription validée!");
                con.close();
                return new Client(id,nom,prenom,adresse,telephone,email,numeroCarte);
            }else {
                System.out.println("Problème lors de l'inscription");
            }
        } catch (Exception e) {
            System.out.println("Problème lors de l'inscription --> " + e);
            return null;
        }
        return null;
    }
    public static Client connexion(String nom, String prenom) {
        Connection con = Connexion.getConnexion();
        try {
            Statement select = con.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM client WHERE nom = \""+nom+"\" AND prenom = \""+prenom+"\"");
            while(rs.next()) {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }
            return null;
        }catch(Exception e) {
            System.out.println("Problème lors de la connexion --> " + e);
            return null;
        }
    }
    public Client(String nom, String prenom, String adresse, String telephone, String email) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }
    public Client(int id, String nom, String prenom, String adresse, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }
    public Client(int id, String nom, String prenom, String adresse, String telephone, String email, String numeroCarte) {
        this.id = this.id;
        this.nom = this.nom;
        this.prenom = this.prenom;
        this.adresse = this.adresse;
        this.telephone = this.telephone;
        this.email = this.email;
        this.numeroCarte = this.numeroCarte;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNumeroCarte() {
        return numeroCarte;
    }
    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }
    public String toString() {
        return "Nom : " + nom + " Prénom : " +prenom + " Adresse : " + adresse + " Numéro téléphone : " + telephone;
    }
}
