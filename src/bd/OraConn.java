package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OraConn {
	
	private static Connection connection = null;
	
	private static SQLException ex;
	
	public int getErrorCode() { return ex.getErrorCode(); }
	public String getExceptionMessage() { return ex.getMessage(); }
	
	public static Connection getConnection() {
		return connection;
	}

	public OraConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Brak sterownika Oracle JDBC.");
		}
		System.out.println("Sterownik Oracle JDBC został zarejestrowany.");
		return;
	}
	
	public static void open(String login, String passwd) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf", login, passwd);
		} catch (SQLException exc) {
			ex = exc;
			throw exc;
		}
		System.out.println("Połączenie z bazą ora3inf zostało otwarte.");
		return;
	}
	
	public static void close() throws SQLException {
		try {
			connection.close();
			System.out.println("Połączenie z bazą ora3inf zostało zamknięte.");
		} catch (SQLException exc) {
			ex = exc;
			throw exc;
		}
	}

}
