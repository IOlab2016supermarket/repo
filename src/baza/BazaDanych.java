package baza;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BazaDanych {

	private static Connection polaczenie;

	public static void polacz() {

		BazaDanych.setPolaczenie(null);
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/baza_supermarket";
			
			BazaDanych.polaczenie = (Connection) DriverManager.getConnection(url, "root", "");
			//BazaDanych.setPolaczenie((Connection) DriverManager.getConnection(url, "pracownik", "Pracownik0."));

			JOptionPane.showMessageDialog(null, " uda³o polaczyc siê z baza danych.");
			System.out.println("Uda³o polaczyc siê z baza danych.");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Nie uda³o polaczyc siê z baza danych.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void rozlacz() {
		try {
			if (BazaDanych.getPolaczenie() != null) {
				BazaDanych.getPolaczenie().close();
				System.out.println("Rozlaczenie z baza danych powiod³o siê.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static Connection getPolaczenie() {
		return BazaDanych.polaczenie;
	}

	public static Connection setPolaczenie(Connection polaczenie) {
		return BazaDanych.polaczenie = polaczenie;
	}
}