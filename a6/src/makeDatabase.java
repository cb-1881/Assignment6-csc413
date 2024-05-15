import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class makeDatabase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/CS413";
        String user = "root"; 
        String password = "Rootroot123!"; 

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Dropping and Creating Database
            stmt.executeUpdate("DROP DATABASE IF EXISTS CS413");
            stmt.executeUpdate("CREATE DATABASE CS413");
            stmt.executeUpdate("USE CS413");

            // Creating tables and inserting data
            stmt.executeUpdate("CREATE TABLE admin (userid VARCHAR(40), pwd VARCHAR(45))");
            stmt.executeUpdate("INSERT INTO admin(userid, pwd) VALUES ('kmehta', 'password')");

            stmt.executeUpdate("CREATE TABLE bankcustomer (id INT PRIMARY KEY AUTO_INCREMENT, first_name VARCHAR(45), last_name VARCHAR(45), email VARCHAR(40), phone VARCHAR(20), sex VARCHAR(10), birthday VARCHAR(20), addressid INT)");
           // stmt.executeUpdate("LOAD DATA INFILE 'bankcustomer.csv' INTO TABLE bankcustomer FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 LINES (first_name, last_name, email, phone, sex, birthday, addressid)");

            stmt.executeUpdate("CREATE TABLE customeraddress (streetnum INT, streetname VARCHAR(45), city VARCHAR(40), state VARCHAR(5), zip INT, cusid INT)");
          //  stmt.executeUpdate("LOAD DATA INFILE 'customeraddress.csv' INTO TABLE customeraddress FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 LINES (streetnum, streetname, city, state, zip, cusid)");

            stmt.executeUpdate("CREATE TABLE bankaccount (acct_num INT PRIMARY KEY AUTO_INCREMENT, cust_id INT, balance FLOAT, create_date VARCHAR(40), last_update_date VARCHAR(40), acct_type VARCHAR(5), od_limit FLOAT, int_rate FLOAT)");
            //stmt.executeUpdate("LOAD DATA INFILE 'bankaccount.csv' INTO TABLE bankaccount FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 LINES (cust_id, balance, create_date, last_update_date, acct_type, od_limit, int_rate)");

            stmt.executeUpdate("CREATE TABLE accountransaction (create_date DATETIME, tran_type VARCHAR(45), amount FLOAT, summary VARCHAR(40), acct_id INT)");
            //stmt.executeUpdate("LOAD DATA INFILE 'accountransaction.csv' INTO TABLE accountransaction FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 LINES (create_date, tran_type, amount, summary, acct_id)");

            // Fetch and display bankcustomer data to confirm
            ResultSet rs = stmt.executeQuery("SELECT * FROM bankcustomer");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
