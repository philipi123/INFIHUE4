package hausuebung_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class CSV {

	public static Connection createConnection() {

		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}


	public static void main(String args[]) {
		try {
			Connection c = createConnection();	
			Personen.dropPersonen(c);
			Personen.createTablePersonen(c);

			File f = new File("/Users/philip/Desktop/HTL/SWP-Greinöcker/ECLIPSE/Infi/src/hausuebung_3/leute.csv");
			Scanner s = new Scanner(f);
			String string = ""; 

			while (s.hasNextLine()) {
				string = s.nextLine();
				String[] str = string.split(";");
				String sql = "insert into personen (vorname, nachname, wohnort) values (?, ?, ?);"; 
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, str[0]);
				stmt.setString(2, str[1]);
				stmt.setString(3, str[2]);
				stmt.executeUpdate();
				stmt.close();
			}
			s.close();
			Personen.show(c);
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}