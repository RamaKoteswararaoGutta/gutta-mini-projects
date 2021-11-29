package jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTxnMgmt {

	public static void main(String[] args) {

		String connectionUrl = "jdbc:mysql://localhost:3306/gutta?serverTimezone=UTC";

		try (Connection conn = DriverManager.getConnection(connectionUrl, "spring", "spring@123");
				Statement stmt = conn.createStatement()) {

			// JDBC level txm mgmt by making auto commit as false
			conn.setAutoCommit(false);

			stmt.executeUpdate("insert into bookings values(105,'ccc')");
			stmt.executeUpdate("insert into bookings values(106,'dddddd')"); // there is name constraint as name length must be <= 5

			// the following commits once every thing goes right till the end
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}