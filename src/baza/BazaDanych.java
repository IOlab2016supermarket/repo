package baza;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BazaDanych {

	private static Connection polaczenie;

	public static void polacz() {

		BazaDanych.polaczenie = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/baza_supermarket";
			BazaDanych.polaczenie = (Connection) DriverManager.getConnection(url, "pracownik", "Pracownik0.");

			System.out.println("Uda�o po��czyc si� z baz� danych.");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Nie uda�o po��czyc si� z baz� danych.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void rozlacz() {
		try {
			if (BazaDanych.polaczenie != null) {
				BazaDanych.polaczenie.close();
				System.out.println("Roz��czenie z baz� danych powiod�o si�.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static Connection getPolaczenie() {
		return BazaDanych.polaczenie;
	}
}
