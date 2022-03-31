package hausuebung_3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Personen {
	public static void createTablePersonen(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists personen(vorname varchar(20), nachname varchar(20), wohnort varchar(20))";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void dropPersonen(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "drop table if exists personen;";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void show(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "select vorname, nachname, wohnort from personen;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				String wohnort = rs.getString("wohnort");

				System.out.printf("%s \t %2s \t %s \n", vorname, nachname, wohnort);
				System.out.println();
				//System.out.printf("Vorname: " + vorname + " Nachname: " + nachname + " Wohnort: " + wohnort);

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
