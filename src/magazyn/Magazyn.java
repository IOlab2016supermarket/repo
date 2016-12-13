/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

import wspolne.Produkt;
import baza.BazaDanych;
import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mateusz
 */
public class Magazyn {
    
    private ArrayList<Magazynier> magazynierzy;
    private ArrayList<Produkt> produkty;
    private Statement statement;
    
    public Magazyn() 
    {
        magazynierzy = new ArrayList<Magazynier>();
        produkty = new ArrayList<Produkt>();
    }
    
    //Metody do zarządzania magazynem
    
    public void wczytajProduktyZBazy(Connection connection) 
    {
        try
        {
            Statement st = (Statement) connection.createStatement();
            st.executeQuery("SELECT * FROM produkt");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("idProdukt");
                String nazwa = rs.getString("nazwaProduktu");
                float waga = rs.getFloat("waga");
                int dlugoscGwarancji = rs.getInt("dlugoscGwarancji");
                Date dataWaznosci = rs.getDate("dataWaznosci");
                float cenaZakupu = rs.getFloat("cenaZakupu");
                float cenaSprzedazy = rs.getFloat("cenaSprzedazy");
                float cenaPromocyjna = rs.getFloat("cenaPromocyjna");
                int ilosc = rs.getInt("ilosc");
                int nr_regalu = rs.getInt("nr_regalu");
                int nr_polki = rs.getInt("nr_polki");
                int nr_miejsca = rs.getInt("nr_miejsca");
                int punkty = rs.getInt("punkty");
                
                produkty.add(new Produkt(id, nazwa, dlugoscGwarancji, cenaZakupu, cenaSprzedazy, cenaPromocyjna, waga, ilosc, dataWaznosci,nr_regalu, nr_polki, nr_miejsca));
            } 
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void wczytajMagazynierowZBazy(Connection connection) 
    {
        try
        {
            Statement st = (Statement) connection.createStatement();
            st.executeQuery("SELECT * FROM magazynier");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("idMagazynier");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                magazynierzy.add(new Magazynier(id, imie, nazwisko));
            } 
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void dodajProdukt(Produkt produkt, Connection connection)
    {
        produkty.add(produkt);
        try
        {
            String query = "";
            query = "INSERT INTO produkt VALUES (" + produkt.pobierzId() + ", '" + produkt.pobierzNazwe() + "', " + produkt.pobierzWage() + ", " + produkt.pobierzDlugoscGwarancji()
                                                    + ", " + produkt.pobierzDateWaznosci() + ", " + produkt.pobierzCeneZakupu() + ", " + produkt.pobierzCeneSprzedazy()+ ", " 
                                                    + produkt.pobierzCenePromocyjna() + ", " + produkt.pobierzIlosc() + ", " + produkt.pobierzDateWaznosci()+  ", " 
                                                    + produkt.pobierzNrRegalu() +  ", " + produkt.pobierzNrMiejsca() + ", " + produkt.pobierzNrPolki() +  ", " 
                                                    + produkt.pobierzIloscPunktow() +  ");";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void dodajProdukt(int id_produktu, String nazwa, int dlugoscGwarancji, float cenaZakupu, float cenaSprzedazy, float cenaPromocyjna, float waga, int ilosc, Date dataWaznosci, int nrRegalu, int nrPolki, int nrMiejsca, Connection connection)
    {
        Produkt produkt = new Produkt(id_produktu, nazwa, dlugoscGwarancji, cenaZakupu, cenaSprzedazy, cenaPromocyjna, waga, ilosc, dataWaznosci,nrRegalu, nrPolki, nrMiejsca);
        produkty.add(produkt);
        try
        {
            String query = "";
            query = "INSERT INTO produkt VALUES (" + produkt.pobierzId() + ", '" + produkt.pobierzNazwe() + "', " + produkt.pobierzWage() + ", " + produkt.pobierzDlugoscGwarancji()
                                                    + ", " + produkt.pobierzDateWaznosci() + ", " + produkt.pobierzCeneZakupu() + ", " + produkt.pobierzCeneSprzedazy()+ ", " 
                                                    + produkt.pobierzCenePromocyjna() + ", " + produkt.pobierzIlosc() + ", " + produkt.pobierzDateWaznosci()+  ", " 
                                                    + produkt.pobierzNrRegalu() +  ", " + produkt.pobierzNrMiejsca() + ", " + produkt.pobierzNrPolki() +  ", " 
                                                    + produkt.pobierzIloscPunktow() +  ");";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void usunProdukt(Produkt produkt, Connection connection)
    {
        produkty.remove(produkt);
        try
        {
            String query = "";
            query = "DELETE FROM produkt WHERE idProdukt=" + produkt.pobierzId() + " AND nazwaProduktu='" + produkt.pobierzNazwe() + "';";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void usunProdukt(int idProduktu, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkty.remove(produkt);
                try
        {
            String query = "";
            query = "DELETE FROM produkt WHERE idProdukt=" + produkt.pobierzId() + " AND nazwaProduktu='" + produkt.pobierzNazwe() + "';";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
            }
        }
    }
    
    public Produkt pobierzProdukt(int idProduktu)
    {
        Produkt retProdukt = null;
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {  
                retProdukt = new Produkt(produkt); /////////// ?????????????????????????????????????????????
            }
        }
        return retProdukt;
    }
    
    public ArrayList<Produkt> pobierzProdukty()
    {
        return produkty;
    }
    
    public ArrayList<Magazynier> pobierzMagazynierow()
    {
        return magazynierzy;
    }
    
    public Produkt szukajPorduktu(int idProduktu)
    {
        Produkt retProdukt = null;
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                retProdukt = new Produkt(produkt);
            }
        }
        return retProdukt;
    }
    
    public float zliczCeneSprzedazy()
    {
        float cena = 0;
        for(Produkt produkt : produkty)
        {
            cena += produkt.pobierzCeneSprzedazy();
        }
        return cena;
    }
    
    public float zliczCeneZakupu()
    {
        float cena = 0;
        for(Produkt produkt : produkty)
        {
            cena += produkt.pobierzCeneZakupu();
        }
        return cena;
    }
    
    public float zliczCenePromocyjna()
    {
        float cena = 0;
        for(Produkt produkt : produkty)
        {
            cena += produkt.pobierzCenePromocyjna();
        }
        return cena;
    }
    
    //Metody do zarządzania produktami
    
    public void edytujNazweProduktu(int idProduktu, String nazwa, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawNazwe(nazwa);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET nazwaProduktu='"+ nazwa +"' WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujNazweProduktu(Produkt produkt, String nazwa, Connection connection)
    {
        produkt.ustawNazwe(nazwa);
        try
        {
            String query = "";
            query = "UPDATE produkt SET nazwaProduktu='"+ nazwa +"' WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void edytujDlugoscGwarancjiProduktu(int idProduktu, int dlugoscGwarancji, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawDlugoscGwarancji(dlugoscGwarancji);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET dlugoscGwarancji="+ dlugoscGwarancji +" WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujDlugoscGwarancjiProduktu(Produkt produkt, int dlugoscGwarancji, Connection connection)
    {
        produkt.ustawDlugoscGwarancji(dlugoscGwarancji);
        try
        {
            String query = "";
            query = "UPDATE produkt SET dlugoscGwarancji="+ dlugoscGwarancji +" WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void edytujCeneZakupuProduktu(int idProduktu, float cenaZakupu, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawCeneZakupu(cenaZakupu);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET cenaZakupu="+ cenaZakupu +" WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujCeneZakupuProduktu(Produkt produkt, float cenaZakupu, Connection connection)
    {
        produkt.ustawCeneZakupu(cenaZakupu);
        try
        {
            String query = "";
            query = "UPDATE produkt SET cenaZakupu="+ cenaZakupu +" WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();

        }
        catch(SQLException e) 
        {
            e.printStackTrace();

        }
    }
    
    public void edytujCeneSprzedazyProduktu(int idProduktu, float cenaSprzedazy, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawCeneSprzedazy(cenaSprzedazy);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET cenaSprzedazy="+ cenaSprzedazy +" WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujCeneSprzedazyProduktu(Produkt produkt, float cenaSprzedazy, Connection connection)
    {
        produkt.ustawCeneSprzedazy(cenaSprzedazy);
        try
        {
            String query = "";
            query = "UPDATE produkt SET cenaSprzedazy="+ cenaSprzedazy +" WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void edytujCenePromocyjnaProduktu(int idProduktu, float cenaPromocyjna, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawCenePromocyjna(cenaPromocyjna);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET cenaPromocyjna="+ cenaPromocyjna +" WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujCenePromocyjnaProduktu(Produkt produkt, float cenaPromocyjna, Connection connection)
    {
        produkt.ustawCenePromocyjna(cenaPromocyjna);
        try
        {
            String query = "";
            query = "UPDATE produkt SET cenaPromocyjna="+ cenaPromocyjna +" WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void edytujWageProduktu(int idProduktu, float waga, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawWage(waga);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET waga="+ waga +" WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujWageProduktu(Produkt produkt, float waga, Connection connection)
    {
        produkt.ustawWage(waga);
        try
        {
            String query = "";
            query = "UPDATE produkt SET waga="+ waga +" WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void edytujIloscProduktu(int idProduktu, int ilosc, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawIlosc(ilosc);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET ilosc="+ ilosc +" WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujIloscProduktu(Produkt produkt, int ilosc, Connection connection)
    {
        produkt.ustawIlosc(ilosc);
        try
        {
            String query = "";
            query = "UPDATE produkt SET ilosc="+ ilosc +" WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void edytujNrRegaluProduktu(int idProduktu, int nrRegalu, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawNrRegalu(nrRegalu);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET nr_regalu="+ nrRegalu +" WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujNrRegaluProduktu(Produkt produkt, int nrRegalu, Connection connection)
    {
        produkt.ustawNrRegalu(nrRegalu);
        try
        {
            String query = "";
            query = "UPDATE produkt SET nr_regalu="+ nrRegalu +" WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void edytujNrPolkiProduktu(int idProduktu, int nrPolki, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawNrPolki(nrPolki);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET nr_polki="+ nrPolki +" WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujNrPolkiProduktu(Produkt produkt, int nrPolki, Connection connection)
    {
        produkt.ustawNrPolki(nrPolki);
        try
        {
            String query = "";
            query = "UPDATE produkt SET nr_polki="+ nrPolki +" WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void edytujNrMiejscaProduktu(int idProduktu, int nrMiejsca, Connection connection)
    {
        for(Produkt produkt : produkty)
        {
            if(produkt.pobierzId() == idProduktu)
            {
                produkt.ustawNrMiejsca(nrMiejsca);
                try
                {
                    String query = "";
                    query = "UPDATE produkt SET nr_miejsca="+ nrMiejsca +" WHERE idProdukt=" + idProduktu + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujNrMiejscaProduktu(Produkt produkt, int nrMiejsca, Connection connection)
    {
        produkt.ustawNrMiejsca(nrMiejsca);
        try
        {
            String query = "";
            query = "UPDATE produkt SET ne_miejsca="+ nrMiejsca +" WHERE idProdukt=" + produkt.pobierzId() + ";";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        } 
    }   
    
    //Metody do zarządzania magazynierami
    
    public void dodajMagazyniera(Magazynier magazynier, Connection connection)
    {
        magazynierzy.add(magazynier);
        try
        {
            String query = "";
            query = "INSERT INTO magazynier VALUES (" + magazynier.pobierzId() + ", '" + magazynier.pobierzImie() + "', '" + magazynier.pobierzNazwisko() + "'); ";
                                                   
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void dodajMagazyniera(int id, String imie, String nazwisko, Connection connection)
    {
        Magazynier magazynier = new Magazynier(id, imie, nazwisko);
        magazynierzy.add(magazynier);
        try
        {
            String query = "";
            query = "INSERT INTO magazynier VALUES (" + magazynier.pobierzId() + ", '" + magazynier.pobierzImie() + "', '" + magazynier.pobierzNazwisko() + "'); ";
                                                   
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void usunMagazyniera(Magazynier magazynier, Connection connection)
    {
        magazynierzy.remove(magazynier);
        try
        {
            String query = "";
            query = "DELETE FROM magazynier WHERE idMagazynier=" + magazynier.pobierzId() + " AND imie='" + magazynier.pobierzImie() + "';";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //connection.close();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void usunMagazyniera(int idMagazyniera, Connection connection)
    {
        for(Magazynier magazynier : magazynierzy)
        {
            if(magazynier.pobierzId() == idMagazyniera)
            {
                magazynierzy.remove(magazynier);
                try
                {
                    String query = "";
                    query = "DELETE FROM magazynier WHERE idMagazynier=" + magazynier.pobierzId() + " AND imie='" + magazynier.pobierzImie() + "';";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujImieMagazyniera(Magazynier magazynier, String imie, Connection connection)
    {
        magazynier.ustawImie(imie);
        try
                {
                    String query = "";
                    query = "UPDATE magazynier SET imie="+ imie +" WHERE idMagazynier=" + magazynier.pobierzId() + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
    }
    
    public void edytujImieMagazyniera(int id, String imie, Connection connection)
    {
        for(Magazynier magazynier : magazynierzy)
        {
            if(magazynier.pobierzId() == id)
            {
                magazynier.ustawImie(imie);
                try
                {
                    String query = "";
                    query = "UPDATE magazynier SET imie="+ imie +" WHERE idMagazynier=" + id + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void edytujNazwiskoMagazyniera(Magazynier magazynier, String nazwisko, Connection connection)
    {
        magazynier.ustawNazwisko(nazwisko);
        try
                {
                    String query = "";
                    query = "UPDATE magazynier SET nazwisko="+ nazwisko +" WHERE idMagazynier=" + magazynier.pobierzId() + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
        
    }
    
    public void edytujNazwiskoMagazyniera(int id, String nazwisko, Connection connection)
    {
        for(Magazynier magazynier : magazynierzy)
        {
            if(magazynier.pobierzId() == id)
            {
                magazynier.ustawNazwisko(nazwisko);
                try
                {
                    String query = "";
                    query = "UPDATE magazynier SET nazwisko="+ nazwisko +" WHERE idMagazynier=" + id + ";";
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    //connection.close();
                }
                catch(SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
//    public static void main(String [] args) throws SQLException
//    {
//        BazaDanych.polacz();
//        BazaDanych.getPolaczenie().ping();
//    }
    
}
