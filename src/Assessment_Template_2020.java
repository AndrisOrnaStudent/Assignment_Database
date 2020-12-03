
import java.sql.*;
import java.util.Scanner;

// REMEMBER to edit the name of the database in the init_db() method
// You'll also need to change the password if you're not using 'admin' for your MySQL password
public class Assessment_Template_2020
{
    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        init_db();  // open the connection to the database

        int menuChoice = 0; // variable used to store main menu choice
        final int STOP_APP = 7; //value from menu that is used to quit the application

        while (menuChoice != STOP_APP)
        {
            displayMainMenu(); //display the primary menu
            if (in.hasNextInt())
            {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice)
                {
                    case 1:
                        displayAllStudents(); //The code for this method is already done for you below
                        break;
                    case 2:
                        displayParticularStudent(); //You need to code this method below
                        break;
                    case 3:
                        addNewStudent(); //You need to code this method below
                        break;
                    case 4:
                        deleteStudent(); //You need to code this method below
                        break;
                    case 5:
                        registerStudent(); //You need to code this method below
                        break;
                    case 6:
                        editStudent(); //You need to code this method below
                        break;
                    case 7:
                        System.out.println("Program is closing...");
                        cleanup_resources();  // close the connection to the database when finished program
                        break;
                    default:
                        System.out.println("You entered an invalid choice, please try again...");
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid choice, please try again...");
            }
        }
    }

    public static void displayAllStudents()
    {
        //1: Query the database for all students
        //2: Display the result set in an appropriate manner
        String str = "Select * from students";

        try
        {
            rs = stmt.executeQuery(str);
            System.out.printf("\n%-12s %-15s %-20s %-10s\n", "Student ID", "First Name", "Last Name", "Date of Birth");
            while (rs.next())
            {
                int studentId = rs.getInt("student_id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String dob = rs.getString("dateOfBirth");

                System.out.printf("%-12d %-15s %-20s %-10s\n", studentId, firstName, lastName, dob);
            }

        }
        catch (SQLException sqle)
        {
            System.out.println("Error: failed to display all students.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }


    public static void displayParticularStudent()
    {
        //Your code should go here:
        //1: You need to get the user to select an existing student first - display all the existing students and
        //   let the user select a studentId that they wish to display.

        //String str = "Select * from students";
        displayAllStudents();
        System.out.print(" Please enter student ID:");
        int student_id = in.nextInt();
        String str = "select count(*) from student where student_id = " + student_id;

        try
        {
            rs = stmt.executeQuery(str);
            rs.next();

            System.out.printf("\n%-12s %-15s %-20s %-10s\n", "Student ID", "First Name", "Last Name", "Date of Birth");
            while (rs.next())
            {
                int studentId = rs.getInt("student_id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String dob = rs.getString("dateOfBirth");

                System.out.printf("%-12d %-15s %-20s %-10s\n", studentId, firstName, lastName, dob);
            }

        }
        catch (SQLException sqle)
        {
            System.out.println("Error: failed to display all students.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
        //2: Ensure that the studentId they selected exists (query the database for a count of students with that studentId,
        //    and if the count comes back as 0, then it doesn't exist; if its a 1 then it does.
        //3: Query the database for the student and all its modules
        //4: Display the result set in an appropriate manner


    }


    public static void addNewStudent()
    {
        //Your code should go here:
        //1: Get the required data from the user (i.e. the student data) and validate the data if needs be
        //2: Insert the data in the database
    }


    public static void deleteStudent()
    {
        //Your code should go here:
        //1. You need to get the user to select an existing student first - display all the existing students and
        //   let the user select a studentId that they wish to delete.
        //2: Ensure that the studentId they selected exists (query the database for a count of students with that studentId -
        //   if the count comes back as 0, then it doesn't exist; if its a 1 then it does.
        //3: Delete any reference to the student in the attends table
        //4: Delete the selected student from the student table

        displayAllStudents();
    }


    public static void registerStudent()
    {
        //Your code should go here:
        //1: Get the required data from the user (i.e. the student and module ids)  and validate the data if needs be
        //   (You should print out a list of students and modules to the user first)
        //2: Insert the data in the database
        //String str = select student.student_id a"
    }


    public static void editStudent()
    {
        //Your code should go here:
        //1: Get the required data from the user (i.e. the student data) and validate the data if needs be
        //2: Ensure the student exists as you've done previously
        //3: Edit the selected student
    }



    public static void displayMainMenu()
    {
        System.out.println("\nMain Menu");
        System.out.println("1: Display all students");
        System.out.println("2: Display a particular student's modules ");
        System.out.println("3: Create a new student ");
        System.out.println("4: Delete a student ");
        System.out.println("5: Register a student ");
        System.out.println("6: Edit a student's details ");
        System.out.println("7: Exit application\n");
        System.out.print("Enter your choice: ");
    }

    public static void cleanup_resources()
    {
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
            System.out.println("Error: Failed to connect to database\n" + e.getMessage());
        }
    }
}
