package com.company;
import java.sql.*;
import java.util.Scanner;

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
        if (option == 1){
            Scanner sc = new Scanner(System.in);
            System.out.println("Name\nsurname1\nsurname2\nbirthdate\nactive");
            String name = sc.next();
            String surname1 = sc.next();
            String surname2 = sc.next();
            String birthdate = sc.next();
            Boolean active = sc.nextBoolean();
            PreparedStatement ps = con.prepareStatement("INSERT INTO player(name, surname1, surname2, birthdate, active) VALUES("+"'"+name+"',"+"'"+surname1+"',"+"'"+surname2+"',"+"TO_DATE("+"'"+birthdate+"'"+",'dd/mm/yyyy'"+")"+","+active+")");
            ps.executeUpdate();
        }
        if (option == 2){
            Scanner sc = new Scanner(System.in);
            System.out.println("Name\nbowls_won");

            String name = sc.next();
            int bowls_won = sc.nextInt();

            PreparedStatement ps = con.prepareStatement("INSERT INTO team(name, bowls_won) VALUES("+"'"+name+"'"+", "+bowls_won+")");
            ps.executeUpdate();
        }
        if (option == 3){
            PreparedStatement players = con.prepareStatement("SELECT * FROM player");
            ResultSet rsplayers = players.executeQuery();
            while (rsplayers.next()){
                System.out.println("--------------------------------------------------------");
                System.out.print("id_player: " + rsplayers.getInt("id_player"));
                System.out.print("\nname: " + rsplayers.getString("name"));
                System.out.print("\nsurname1: " + rsplayers.getString("surname1"));
                System.out.print("\nsurname2: " + rsplayers.getString("surname2"));
                System.out.print("\nbirthdate: " + rsplayers.getDate("birthdate"));
                System.out.print("\nactive: " + rsplayers.getBoolean("active"));
                System.out.println("\n--------------------------------------------------------");
            }
            PreparedStatement ps = con.prepareStatement("SELECT * FROM team");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("--------------------------------------------------------");
                System.out.print("id_team: " + rs.getInt("id_team"));
                System.out.print("\nname: " + rs.getString("name"));
                System.out.println("\nbowls_won: " + rs.getInt("bowls_won"));
                System.out.println("\n--------------------------------------------------------");
            }
        }
        if (option == 4){
            PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE player");
            ps.executeQuery();
        }
        con.close();
    }
}
