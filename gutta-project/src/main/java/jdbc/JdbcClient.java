package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class JdbcClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

		String sqlSelectAllPersons = "SELECT * FROM person";
		String connectionUrl = "jdbc:mysql://localhost:3306/gutta?serverTimezone=UTC";

		try (Connection conn = DriverManager.getConnection(connectionUrl, "spring", "spring@123");
				PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long id = rs.getLong("ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				Date bdate = rs.getDate("BDATE");

				// do something with the extracted data...
				System.out.println(id + " " + firstName + " " + lastName + " " + sdf.format(bdate));
			}
		} catch (SQLException e) {
			// handle the exception
		}
	}

}
