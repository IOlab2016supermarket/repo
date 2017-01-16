package superclasses;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import main.Common;

public abstract class DataManager {
	final private static String DB_DRIVER = "com.mysql.jdbc.Driver";
	final private static String DB_URL = "jdbc:mysql://localhost:3306/baza_gui";
	final private static String DB_USER = "root";
	final private static String DB_PASSWORD = "root";
	
	protected ResultSet rs;
	
	protected abstract Entity getEntityByFields() throws SQLException;
	protected abstract void addEntity(Entity ent);
	protected abstract void updateEntity(Entity ent);
	protected abstract void deleteEntity(int id);
	
	public abstract ArrayList<Entity> getEntityList();
	
	private static Connection getDBConnection() {
	    Connection dbConnection = null;
	    try {
	        Class.forName(DB_DRIVER);
	    } catch (ClassNotFoundException e) {
	    	Common.showErrorMessage(null, e.getMessage());
	    }
	    try {
	        dbConnection = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        return dbConnection;
	    } catch (SQLException e) {
	    	Common.showErrorMessage(null, e.getMessage());
	    }
	    return dbConnection;
	}
	
	protected void executeUpdate(String sql) {
		Connection dbConnection = null;
	    Statement st = null;
		
		try {
	        dbConnection = getDBConnection();
	        st = (Statement) dbConnection.createStatement();
	        st.executeUpdate(sql);
	    } catch (SQLException e) {
	    	Common.showErrorMessage(null, e.getMessage());
	    } finally {
	    	try { dbConnection.close(); } catch(SQLException se) {se.printStackTrace();}
            try { st.close(); } catch(SQLException se) {se.printStackTrace();}
            try { rs.close(); } catch(SQLException se) {se.printStackTrace();}
	    }

	}
	
	protected ArrayList<Entity> getListFromRS(String sql) {
		Connection dbConnection = null;
	    Statement st = null;
	    rs = null;
	    
		ArrayList<Entity> list = new ArrayList<Entity>();
		
		try {
	        dbConnection = getDBConnection();
	        st = (Statement) dbConnection.createStatement();
	        rs = st.executeQuery(sql);
	        
	        if (rs != null) {
				try {
					while (rs.next()) {
					 	list.add( getEntityByFields() );
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        
	    } catch (SQLException e) {
	    	Common.showErrorMessage(null, e.getMessage());
	    } finally {
	    	try { dbConnection.close(); } catch(SQLException se) {se.printStackTrace();}
            try { st.close(); } catch(SQLException se) {se.printStackTrace();}
            try { rs.close(); } catch(SQLException se) {se.printStackTrace();}
	    }
		
		return list;
	}
	
	protected int getResultSetSize(String sql) {
		Connection dbConnection = null;
	    Statement st = null;
	    rs = null;
	    int size = 0;
		
		try {
	        dbConnection = getDBConnection();
	        st = (Statement) dbConnection.createStatement();
	        rs = st.executeQuery(sql);
	        
	        if (rs != null)
	        	if (rs.last())
	        		  size = rs.getRow();
	        
	    } catch (SQLException e) {
	    	Common.showErrorMessage(null, e.getMessage());
	    } finally {
	    	try { dbConnection.close(); } catch(SQLException se) {se.printStackTrace();}
            try { st.close(); } catch(SQLException se) {se.printStackTrace();}
            try { rs.close(); } catch(SQLException se) {se.printStackTrace();}
	    }
		
		return size;
	}
	
	protected Entity getFirstFromRS(String sql) {
		ArrayList<Entity> list = getListFromRS(sql);
		return list.size() == 0 ? null : list.get(0);
	}
	
	protected Entity getFromTableByID(String table, int id) {
		String sql = String.format("SELECT * FROM %s WHERE id LIKE '%d'", table, id);
		return getFirstFromRS(sql);
	}
	
	protected void deleteFromTableByID(String table, int id) {
		String sql = String.format("DELETE FROM %s WHERE id=%d", table, id);
		executeUpdate(sql);
	}
	
	protected Date getDate(String field) {
		Date dt = null;
		try {
			dt = rs.getDate(field);
		} catch (Exception e) {}
		return dt;
	}
	
	protected String getString(String field) {
		String s = "";
		try {
			s = rs.getString(field);
		} catch (Exception e) {}
		return s;
	}
	
	protected int getInt(String field) {
		int n = 0;
		try {
			n = rs.getInt(field);
		} catch (Exception e) {}
		return n;
	}
	
	protected float getFloat(String field) {
		float f = 0;
		try {
			f = rs.getFloat(field);
		} catch (Exception e) {}
		return f;
	}
	
	protected String sqlDateFormat(Date d, boolean nullMode) {
		String res;
		if (d == null)
			res = nullMode ? "NULL" : "'0000-00-00'"; 
		else
			res = "'" + (new SimpleDateFormat("yyyy-MM-dd")).format(d) + "'";
		return res;
	}
	
}