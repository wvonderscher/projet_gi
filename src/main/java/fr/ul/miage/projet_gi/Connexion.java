package fr.ul.miage.projet_gi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connexion {
    public static Connection getConnexion() {
        String url = "jdbc:mysql://localhost:3306/projetGL?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            return con;
        }catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

