package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("Insert into Seller " + "(Name,Email,BirthDate,BaseSalary,DepartmentId)"
					+ " Values " + "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Carl Purple");
			st.setString(2, "Carl@Gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1955").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);

			int RowsAffected = st.executeUpdate();
			
			if(RowsAffected >0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int Id = rs.getInt(1);
					System.out.println("Rows Affected:"+ RowsAffected + ", Id:"+Id);
				}
				
			} else {
				System.out.println("No Rows Affected:");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
