package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection _Conn = null;
		Statement _St = null;
		ResultSet _Rs = null;
		
		try {
			_Conn = DB.getConnection();
			_St = _Conn.createStatement();
			
			_Rs = _St.executeQuery("Select * From Department");			
			while (_Rs.next()) {
				System.out.println(_Rs.getInt("Id")+", "+_Rs.getString("Name"));
			}
		} 
		catch (SQLException e) {
				e.printStackTrace();
		}
		finally {
			DB.closeResultSet(_Rs);
			DB.closeStatement(_St);
			DB.closeConnection();			
		}
	}
}
