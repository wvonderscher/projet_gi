package fr.ul.miage.projet_gi;

import java.sql.*;

public class Connexion {
        public static Connection getConnexion() {
            String url = "jdbc:mysql://localhost:3306/projet_gl&serverTimezone=UTC";
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
  /*  public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3308/projet_gl";
        String username = "root";
        String password = "";

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            Statement statement = con.createStatement();

            //statement.execute()

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Client");

            while (resultSet.next()){
                System.out.println("id:  "+ resultSet.getInt("idClient"));
                System.out.println("nom:  "+ resultSet.getInt("nom"));
                System.out.println("prenom:  "+ resultSet.getInt("prenom"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }*/
}
