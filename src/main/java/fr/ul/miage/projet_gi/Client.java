package fr.ul.miage.projet_gi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String motdepasse;
    private String numeroCarte;
    private boolean admin;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void inputDonneesInscription(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir votre nom : ");
        String nom = sc.nextLine();
        System.out.println("Veuillez saisir votre prénom : ");
        String prenom = sc.nextLine();
        System.out.println("Veuillez saisir votre adresse : ");
        String adresse = sc.nextLine();
        System.out.println("Veuillez saisir votre numéro de téléphone : ");
        String telephone = sc.nextLine();
        System.out.println("Veuillez saisir votre adresse mail : ");
        String email = sc.nextLine();
        System.out.println("Veuillez saisir votre mot de passe : ");
        String motdepasse = sc.nextLine();
        System.out.println("Veuillez saisir votre numéro de carte bancaire : ");
        String numeroCarte = sc.nextLine();
        traitementDonneesInscription(nom,prenom,adresse,telephone,email,motdepasse,numeroCarte);
    }

    public static boolean traitementDonneesInscription(String nom, String prenom, String adresse, String telephone, String email, String motdepasse, String numeroCarte){
        if(nom.length() < 2 ){
            System.out.println("Veuillez saisir un nom supérieur à 1 caractères !");
            return false;
        }
        if(prenom.length() < 2){
            System.out.println("Veuillez saisir un prénom supérieur à 1 caractères!");
            return false;
        }
        if(adresse.length() < 3){
            System.out.println("Veuillez saisir une adresse supérieur à 2 caractères!");
            return false;
        }
        if(telephone.length() != 10){
            System.out.println("Veuillez saisir un numéro de téléphone valide (10 numéros)");
            return false;
        }
        if(!valideEmail(email)) {
            System.out.println("Veuillez saisir un mail valide pour vous inscrire! Format : xx@yy.zz");
            return false;
        }
        if(motdepasse.length() < 6){
            System.out.println("Veuillez saisir un mot de passe supérieur à 5 caractères!");
            return false;
        }
        if(numeroCarte.length() != 19){
            System.out.println("Veuillez saisir un numéro de carte valide!");
            return false;
        }
        return outputDonneesInscription(nom,prenom,adresse,telephone,email,motdepasse,numeroCarte);
    }

    public static boolean outputDonneesInscription(String nom, String prenom, String adresse, String telephone, String email, String motdepasse, String numeroCarte){
        Connection con = Connexion.getConnexion();
        try {
            Statement insert = con.createStatement();
            if(insert.executeUpdate("INSERT INTO client(nom,prenom,adresse,telephone,email,motdepasse,numeroCarte) VALUES (\""+nom+"\",\""+prenom+"\",\""+adresse+"\",\""+telephone+"\",\""+email+"\",\""+motdepasse+"\",\""+numeroCarte+"\");") == 1) {
                System.out.println("Inscription validée! Veuillez vous connecter!");
                con.close();
                return true;
            }else {
                System.out.println("Problème lors de l'inscription");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Problème lors de l'inscription --> " + e);
            return false;
        }
    }

    public static Client inputDonneesConnexion(){
        Client client = new Client();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir votre adresse email : ");
        String email = sc.nextLine();
        System.out.println("Veuillez saisir votre mot de passe : ");
        String motDePasse = sc.nextLine();
        client.setEmail(email);
        client.setMotdepasse(motDePasse);
        return traitementDonneesConnexion(client);
    }

    public static Client traitementDonneesConnexion(Client client){
        boolean valide = true;
        if(client.motdepasse.equals("")){
            System.out.println("Veuillez saisir un mot de passe!");
            valide = false;
        }
        if(!valideEmail(client.email)) {
            System.out.println("Veuillez saisir un mail valide pour vous connecter! Format : xx@yy.zz");
            valide = false;
        }
        if(valide){
            return outputDonneesConnexion(client);
        }else{
            return null;
        }
    }

    public static Client outputDonneesConnexion(Client client){
        Connection con = Connexion.getConnexion();
        try{
            Statement select = con.createStatement();
            ResultSet rs = select.executeQuery("SELECT *, COUNT(*) as recordCount FROM client WHERE email = \""+client.email+"\" AND motdepasse = \""+client.motdepasse+"\"");
            rs.next();
            int count = rs.getInt("recordCount");
            if(count == 1){
                System.out.println("Connected!");
                int id = rs.getInt(1);
                return new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getBoolean(9));
            }else{
                System.out.println("Mauvais identifiant!");
                return null;
            }
        }catch(Exception e){
            System.out.println("Problème! " + e);
            return null;
        }
    }

    /**
     * Ajouter un véhicule pour le client connecté 
     */
    public void ajouterVehicule(String plaque, int vehiculeId) {
    	Connection con = Connexion.getConnexion();
    	String sortie = "";
    		if(vehiculeId == -1) {
    			//vehicule n'existe pas
    			try {
					Statement insertVehicule = con.createStatement();
					insertVehicule.executeUpdate("insert into vehicule (marque, immatriculation) values (\"inconnu\", \""+plaque+"\")");
					Vehicule.ajoutLienClientVehicule(vehiculeId, this.id);
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}else {
    			//vehicule existe
    			try {
					Statement select = con.createStatement();
					ResultSet rs = select.executeQuery("SELECT * from clientpossedevehicule where idClient = "+this.id+" AND idVehicule ="+vehiculeId+"");
					if(!rs.isBeforeFirst()) {
						Vehicule.ajoutLienClientVehicule(vehiculeId, this.id);
						con.close();
					}else {
						System.out.println("Vous avez déjà ajouté ce véhicule");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		} 	
    		
    } 
    
    
    public void reserverBorne() {
    	//user entre : date recharge et durée
    	//verifier que date > today
    	//verifier que borne disponble/occupé est dispo l'horaire 
    	//inscrire le type
    	
    }

    public void inputDonneesAdminGetInformationClient(){
        Client client = new Client();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom de l'utilisateur que vous recherchez : ");
        client.nom = sc.nextLine();
        System.out.println("Veuillez saisir le prénom de l'utilisateur que vous recherchez : ");
        client.prenom = sc.nextLine();
        traitementDonneesAdminGetInformationClient(client);
    }

    public void traitementDonneesAdminGetInformationClient(Client client){
        boolean valide = true;
        if(client.nom.equals("")){
            System.out.println("Veuillez saisir un nom pour la recherche d'utilisateur!");
            valide = false;
        }
        if(client.prenom.equals("")){
            System.out.println("Veuillez saisir un prénom pour la recherche d'utilisateur!");
            valide = false;
        }
        if(valide){
            outputAdminGetInformationClient(client);
        }
    }

    /**
     * Méthode permettant d'afficher les informations d'un client s'il existe
     * @param client
     */
    public void outputAdminGetInformationClient(Client client){
        if(!this.isAdmin()){
            System.out.println("Vous devez être admin pour accéder à cette fonctionnalité!");
        }else{
            Connection con = Connexion.getConnexion();
            try{
                Statement select = con.createStatement();
                ResultSet rs = select.executeQuery("SELECT *, COUNT(*) as recordCount FROM client WHERE nom = \""+client.nom+"\" AND prenom = \""+client.prenom+"\"");
                rs.next();
                int count = rs.getInt("recordCount");
                if(count == 1){
                    System.out.println("la");
                    System.out.println("Id du client : " + rs.getString(1));
                    System.out.println("Nom du client : " + rs.getString(2));
                    System.out.println("Prénom du client : " + rs.getString(3));
                    System.out.println("Adresse du client : " + rs.getString(4));
                    System.out.println("Téléphone du client : " + rs.getString(5));
                    System.out.println("Email du client : " + rs.getString(6));
                    System.out.println("Mot de passe du client : " + rs.getString(7));
                    System.out.println("Numéro de carte du client : " + rs.getString(8));
                    if(rs.getBoolean(9)) System.out.println("Ce client est administrateur");
                    else System.out.println("Ce client n'est pas administrateur");
                }else{
                    System.out.println("Ce client n'existe pas!");
                }
            }catch(Exception e){
                System.out.println("Problème ! " + e);
            }
        }
    }

    public void modificationInformations(boolean modificationEnCours) throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection con = Connexion.getConnexion();
        while (modificationEnCours){
            messageInformationAModifier();
            int choix = sc.nextInt();
            switch (choix){
                case 1 :
                    Scanner scannerNom = new Scanner(System.in);
                    System.out.println("Votre nom actuel : " + this.nom);
                    System.out.println("Saisissez votre nouveau nom : ");
                    String nom = scannerNom.nextLine();
                    Statement updateNom = con.createStatement();
                    updateNom.executeUpdate("UPDATE Client SET nom = \""+nom+"\" WHERE idClient = "+this.id+"");
                    System.out.println("Changement effectué!");
                    break;
                case 2 :
                    Scanner scannerPrenom = new Scanner(System.in);
                    System.out.println("Votre prenom actuel : " + this.prenom);
                    System.out.println("Saisissez votre nouveau prenom : ");
                    String prenom = scannerPrenom.nextLine();
                    Statement updatePrenom = con.createStatement();
                    updatePrenom.executeUpdate("UPDATE Client SET prenom = \""+prenom+"\" WHERE idClient = "+this.id+"");
                    System.out.println("Changement effectué!");
                    break;
                case 3 :
                    Scanner scannerAdresse = new Scanner(System.in);
                    System.out.println("Votre adresse actuel : " + this.adresse);
                    System.out.println("Saisissez votre nouveau adresse : ");
                    String adresse = scannerAdresse.nextLine();
                    Statement updateAdresse = con.createStatement();
                    updateAdresse.executeUpdate("UPDATE Client SET adresse = \""+adresse+"\" WHERE idClient = "+this.id+"");
                    System.out.println("Changement effectué!");
                    break;
                case 4:
                    Scanner scannerTelephone = new Scanner(System.in);
                    System.out.println("Votre telephone actuel : " + this.telephone);
                    System.out.println("Saisissez votre nouveau telephone : ");
                    String telephone = scannerTelephone.nextLine();
                    Statement updateTelephone = con.createStatement();
                    updateTelephone.executeUpdate("UPDATE Client SET telephone = \""+telephone+"\" WHERE idClient = "+this.id+"");
                    System.out.println("Changement effectué!");
                    break;
                case 5:
                    modificationEnCours = false;
                    break;
            }
        }
    }

    public void adminAfficherTarifs() {
        if (!this.isAdmin()) {
            System.out.println("Vous devez être admin pour accéder à cette fonctionnalité!");
        } else {
            System.out.println("Les frais actuels sont de : ");
            Connection con = Connexion.getConnexion();
            try {
                Statement select = con.createStatement();
                ResultSet rs = select.executeQuery("select * from frais LIMIT 1;\n");
                rs.next();
                System.out.println("Frais de réservation : " + rs.getString(1) + " €");
                System.out.println("Frais hors réservation : " + rs.getString(2) + " €");
                System.out.println("Frais de non présentation : " + rs.getString(3) + " €");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void adminModifierTarifs() {
        if (!this.isAdmin()) {
            System.out.println("Vous devez être admin pour accéder à cette fonctionnalité!");
        } else {
            System.out.println("------ MODIFICATION DES FRAIS ------");
            System.out.println("1 - Pour modifier les frais de réservation");
            System.out.println("2 - Pour modifier les frais hors réservation");
            System.out.println("3 - Pour modifier les frais de non présentation");
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez choisir le numéro dont vous souhaitez modifier les frais : ");
            int nbChoix = sc.nextInt();
            while (nbChoix < 1 || nbChoix > 3){
                System.out.println("Veuillez choisir un nombre entre 1 et 3");
                nbChoix = sc.nextInt();
            }
            System.out.println("Quel est le nouveau montant pour le frais ?");
            float newFrais = sc.nextFloat();
            while (newFrais <= 0 ) {
                System.out.println("Veuillez entrer un nombre strictement positif : ");
                newFrais = sc.nextFloat();
            }
            Connection con = Connexion.getConnexion();
            if (nbChoix == 1) {
                try {
                    Statement select = con.createStatement();
                    int rs = select.executeUpdate("UPDATE frais set fraisReservation ="+ newFrais +";");
                    System.out.println("------ FRAIS MIS A JOUR ------");
                    adminAfficherTarifs();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (nbChoix == 2) {
                try {
                    Statement select = con.createStatement();
                    int rs = select.executeUpdate("UPDATE frais set fraisHorsReservation ="+ newFrais +";");
                    System.out.println("------ FRAIS MIS A JOUR ------");
                    adminAfficherTarifs();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (nbChoix == 3) {
                try {
                    Statement select = con.createStatement();
                    int rs = select.executeUpdate("UPDATE frais set fraisNonPresentation ="+ newFrais +";");
                    System.out.println("------ FRAIS MIS A JOUR ------");
                    adminAfficherTarifs();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    
    
    private void messageInformationAModifier(){
        System.out.println("Quelle information voulez vous modifier ? ");
        System.out.println("1 - Nom\n2 - Prénom\n3 - Adresse\n4 - Téléphone\n5 - Quitter");
    }

    public Client(){

    }

    public Client(String nom, String prenom, String adresse, String telephone, String email, boolean admin) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.admin = admin;
    }
    public Client(String nom, String prenom, String adresse, String telephone, String email, String numeroCarte, boolean admin) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.numeroCarte = numeroCarte;
        this.admin = admin;
    }
    public Client(int id, String nom, String prenom, String adresse, String telephone, String email, boolean admin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.admin = admin;
    }
    public Client(int id, String nom, String prenom, String adresse, String telephone, String email, String numeroCarte, boolean admin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.numeroCarte = numeroCarte;
        this.admin = admin;
    }
    public Client(int id, String nom, String prenom, String adresse, String telephone, String email, String motdepasse, String numeroCarte, boolean admin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.motdepasse = motdepasse;
        this.numeroCarte = numeroCarte;
        this.admin = admin;
    }
    public Client(String nom, String prenom, String adresse, String telephone, String email, String motdepasse, String numeroCarte) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.motdepasse = motdepasse;
        this.numeroCarte = numeroCarte;
        this.admin = false;
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

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getNumeroCarte() {
        return numeroCarte;
    }
    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String toString() {
        return "Nom : " + nom + " Prénom : " +prenom + " Adresse : " + adresse + " Numéro téléphone : " + telephone;
    }
    public static boolean valideEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }   
    
}