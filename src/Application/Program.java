package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.dbException;
import db.dbIntegrityExeption;

public class Program {

	public static void main(String[] args) {
		
		Connection _conn = null;
		Statement _st = null;
		
		try {
			_conn = DB.getConnection();
			_st = _conn.createStatement();
			
			_conn.setAutoCommit(false);
			
			int Rows1 = _st.executeUpdate("Update Seller"
			+ " SET BaseSalary = 2090"
			+" Where (DepartmentId = 1)" );
			
			//int x = 1;
			//if (x<2) {
			//	throw new SQLException("Fake Error");
			//}
			
			int Rows2 = _st.executeUpdate("Update Seller"
					+ " SET BaseSalary = 3090"
					+" Where (DepartmentId = 2)" );
			
			_conn.commit();
			
			System.out.println("R1: "+Rows1);
			System.out.println("R2: "+Rows2);
		}
		catch (SQLException e){
			try {
				_conn.rollback();
				throw new dbException("Transation Rollback! "+e.getMessage());
			} catch (SQLException e1) {
				throw new dbException("Erro Triel to Rollback! "+e.getMessage());
			}
		}
		finally {
			DB.closeStatement(_st);
			DB.closeConnection();
		}
	}
}
