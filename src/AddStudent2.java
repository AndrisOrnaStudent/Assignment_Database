import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddStudent2 {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	public static void main(String[] args) 
	{
		init_db();  // open the connection to the database
		try
		{
			
			ResultSet rs =  stmt.executeQuery("SELECT * FROM students");

			 while (rs.next()) 
			 { 
				 int empid = rs.getInt(1); 
				 String firstname = rs.getString("firstName"); 
				 String lastname = rs.getString("lastName");
				 String dateOfBirth = rs.getString("dateOfBirth");
				 String registered = rs.getString("registered");
				 
			 System.out.println(empid + " " + firstname + " " + lastname + " " + dateOfBirth + " " + registered + " " ); }
			 // Inserting new student
			 String str = 
						"INSERT INTO student VALUES(null,?, ?, ?, ?, ?)";

					PreparedStatement pstmt = con.prepareStatement(str);
					pstmt.setString(1, "Joe");
					pstmt.setString(2, "Bloggs");
					pstmt.setString(3, "1979-26-03");
					pstmt.setString(4, "Business");
					

					// Returns number of rows inserted
					int rows = pstmt.executeUpdate();
					System.out.print("Number of rows updated");


		}
		catch (SQLException sqle)
		{
			System.out.println("Error: failed to get number of records");
		}
		try
		{
			con.close();
		}
		catch (SQLException sqle)
		{
			System.out.println("Error: failed to close the database");
		}
	}
	public static void init_db()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/assignment_data_baseSQL?useTimezone=true&serverTimezone=UTC";
			con = DriverManager.getConnection(url, "root", "password");
			stmt = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Error: Failed to connect to database\n" + e.getMessage());// if table not found, check for String url.
		}
		
	}

	
}
