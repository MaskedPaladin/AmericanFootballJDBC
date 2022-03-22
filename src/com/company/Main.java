package com.company;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String driver = "org.postgresql.Driver";

        String url = "jdbc:postgresql://172.26.120.83:5432/futbol_americano";
        String user = "postgres";
        String pass = "admin";

        Connection con = DriverManager.getConnection(url, user, pass);

        Class.forName(driver);

	    // write your code here
        Menu menu = new Menu();
        int option = menu.mainMenu();
        if (option == 3){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM player");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("--------------------------------------------------------");
                System.out.print("id_player: " + rs.getInt("id_player"));
                System.out.print("\nname: " + rs.getString("name"));
                System.out.print("\nsurname1: " + rs.getString("surname1"));
                System.out.print("\nsurname2: " + rs.getString("surname2"));
                System.out.print("\nbirthdate: " + rs.getDate("birthdate"));
                System.out.print("\nactive: " + rs.getBoolean("active"));
                System.out.println("\n--------------------------------------------------------");
            }

        }
        con.close();
    }
}
