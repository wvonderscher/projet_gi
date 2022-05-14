package fr.ul.miage.projet_gi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vehicule {
 private int id;
 private String marque;
 private String immatriculation;
 private static final Pattern VALID_IMMATRICULATION_REGEX = Pattern.compile("^[A-Z]{2}[-][0-9]{3}[-][A-Z]{2}$",Pattern.CASE_INSENSITIVE);
 
 
 public Vehicule(int id, String marque, String immatriculation) {
	 this.id = id;
	 this.marque = marque;
	 this.immatriculation = immatriculation;
 }
 
 public Vehicule(String marque, String immatriculation) {
	 this.marque = marque;
	 this.immatriculation = immatriculation;
 }
 
 
 public Vehicule(int id, String immatriculation) {
	 this.id = id;
	 this.immatriculation = immatriculation;
 }
 
 
 public static int getVehiculeId(String plaque) {
     try {
    	Connection con = Connexion.getConnexion();
     	Statement select = con.createStatement();
			ResultSet rs = select.executeQuery("SELECT * FROM vehicule where immatriculation = \""+plaque+"\" ");
			if(rs.isBeforeFirst()) {
				rs.next();
				int id = rs.getInt(1);
				con.close();
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
 }
 
 public static boolean validePlaque(String plaque) {
 	Matcher matcher = VALID_IMMATRICULATION_REGEX.matcher(plaque);
 	return matcher.find();
 }


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getMarque() {
	return marque;
}

public void setMarque(String marque) {
	this.marque = marque;
}

public String getImmatriculation() {
	return immatriculation;
}

public void setImmatriculation(String immatriculation) {
	this.immatriculation = immatriculation;
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}
