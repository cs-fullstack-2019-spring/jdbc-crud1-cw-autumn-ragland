import javax.print.DocFlavor;
import java.sql.*;
public class Main {

    private final static String url = "jdbc:postgresql://localhost:5432/19_05_20_pm_cw";
    private final static String user = "student";
    private final static String password = "C0d3Cr3w";

    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void ex1(String fname, String lname, String position, int salary){
        String SQL =
                "INSERT INTO employee (employee_fname, employee_lname, employee_position, employee_salary) "+
                " VALUES (?,?,?,?)";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)){
                pstmt.setString(1,fname);
                pstmt.setString(2,lname);
                pstmt.setString(3,position);
                pstmt.setInt(4,salary);
                ResultSet rs = pstmt.executeQuery();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void ex2(){
        String SQL =
                "SELECT * " + "FROM employee";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)){

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print("Name: " + rs.getString(2));
                System.out.print(" " + rs.getString(3));
                System.out.print(" Position: " + rs.getString(4));
                System.out.println(" Salary: $" + rs.getString(5));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void ex3(String position){
        String SQL =
                "SELECT * " + "FROM employee " + "WHERE employee_position = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setString(1,position);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print("Name: " + rs.getString(2));
                System.out.print(" " + rs.getString(3));
                System.out.println(" Salary: $" + rs.getString(5));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void ex4(int salary){
        String SQL =
                "SELECT * " + "FROM employee " + "WHERE employee_salary > ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setInt(1,salary);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print("Name: " + rs.getString(2));
                System.out.print(" " + rs.getString(3));
                System.out.print(" Position: " + rs.getString(4));
                System.out.println(" Salary: $" + rs.getString(5));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void ex5(int salary, String lname){
        String SQL =
                "UPDATE employee SET employee_salary = ?"+
                "WHERE employee_lname = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setInt(1,salary);
            pstmt.setString(2,lname);
            ResultSet rs = pstmt.executeQuery();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void ex6(String lname){
        String SQL =
                "DELETE FROM employee "+
                "WHERE employee_lname = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setString(1,lname);
            ResultSet rs = pstmt.executeQuery();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
//        ex1("Ellen", "Musk", "DEVELOPER", 38000);
        ex2();
//        ex3("DEVELOPER");
//        ex4(50000);
//        ex1("Thomas", "Tank", "ENGINEER", 75000);
//        ex5(42000, "Musk");
//        ex6("Sham");
    }
}