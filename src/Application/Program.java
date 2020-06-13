package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.dbIntegrityExeption;

public class Program {

	public static void main(String[] args) {
		
		Connection _conn = null;
		PreparedStatement _st = null;
		
		try {
			_conn = DB.getConnection();
			_st = _conn.prepareStatement("Delete From Department"
					+" Where"
					+" (Id=?)");
			
			_st.setInt(1, 2);
			
			int RowsAffected = _st.executeUpdate();
			
			System.out.println("Done! "+RowsAffected);
		}
		catch (SQLException e){
			throw new dbIntegrityExeption(e.getMessage());
		}
		finally {
			DB.closeStatement(_st);
			DB.closeConnection();
		}
	}
}
