package logowanie;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import baza.BazaDanych;

public class Logowanie {
	
	static String login = "root";
	static String haslo = "";
	static String url = "jdbc:mysql://localhost:3306/baza_supermarket";
	
	public static void rejestracja() throws SQLException {
	//Connection conn = (Connection) DriverManager.getConnection(url+baza, login, password);
	
	BazaDanych.polacz();
	
	Connection conn = BazaDanych.setPolaczenie((Connection) DriverManager.getConnection(url,login ,haslo ));
    Statement st = (Statement) conn.createStatement();
 
    try {
        ResultSet rs = st.executeQuery("select count(*) FROM logowanie WHERE login='"+login+"'");
        while(rs.next()){
          if(rs.getInt(1)==0){
           
			  Statement st2 = (Statement) conn.createStatement();
              st2.executeUpdate("INSERT logowanie VALUES ('root','')");
              System.out.println("Uzytkownik zostal dodany do Bazy!");
               
          }
     
        }
        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();System.out.println("Uwaga! Problem z wczytaniem danych");
     
    }
    conn.close();
  } 
	public static void zaloguj() throws SQLException{
		
		
		
		Statement st = (Statement) BazaDanych.setPolaczenie((Connection) DriverManager.getConnection(url,login ,haslo )).createStatement();
		ResultSet rs = st.executeQuery("select count(*) FROM logowanie WHERE login='"+login+"'");
		
		
		while(rs.next()){
	          if(rs.getInt(1)==0){
	        	  System.out.println("uzytkownik nie znajduje sie w bazie");
	          }
	          else{
	        	  System.out.println("uzytkownik znajduje sie w bazie");
	        	  BazaDanych.polacz();
	          }
	          
		}      
		
	}
}


