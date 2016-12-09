package dostawa;
import wspolne.Zamowienie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Eksport {
	
	private Statement statement;
	
	public void zapiszZamowienia(Zamowienie zamowienie, Connection connection)
	{
		
	}
	
	public void zapiszDostawce(Dostawca dostawca, Connection connection) throws SQLException
	{
		String query = "";
		query = "INSERT INTO dostawcy VALUES ("+dostawca.pobierzId()+", '" + dostawca.pobierzNazwe() + "', '" + dostawca.pobierzAdres() +"');";
		statement = (Statement) connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        //connection.close();
	}
	
	public List<Dostawca> wczytajDostawcow(Connection connection) throws SQLException
	{
		List <Dostawca> dostawcy = new ArrayList<>();
		String query = "";
		query = "SELECT * FROM dostawcy";
		statement = (Statement) connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		int id = 0;
		String nazwa = null,adres = null;
		while(result.next())
		{
			id=result.getInt(1);
			nazwa=result.getString(2);
			adres=result.getString(3);
			dostawcy.add(new Dostawca(id,nazwa,adres));
		}
        statement.close();
        //connection.close();
		return dostawcy;
	}
}
