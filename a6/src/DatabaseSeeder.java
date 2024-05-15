import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSeeder {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/CS413";
        String user = "root";
        String password = "Rootroot123!";

        try {
            insertBankAccounts(url, user, password);
            insertBankCustomers(url, user, password);
            insertCustomerAddresses(url, user, password);
            System.out.println("Data successfully inserted into all tables.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertBankAccounts(String url, String user, String password) throws SQLException {
        String sql = "INSERT INTO bankaccount (cust_id, balance, create_date, last_update_date, acct_type, od_limit, int_rate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[][] bankAccounts = {
            {1, 2500, "2021-12-12", "2023-12-12", "CH", 2000, 3.2},
            {2, 3500, "2022-12-12", "2024-01-12", "SV", 1400, 2.2},
            {3, 4500, "2021-12-12", "2024-12-01", "SV", 1800, 1.2},
            {4, 5500, "2022-12-12", "2024-07-02", "SV", 1700, 4.0},
            {5, 6500, "2021-12-12", "2024-08-03", "SV", 1200, 2.0},
            {6, 7500, "2022-12-12", "2023-09-03", "CH", 1100, 1.7},
            {7, 8500, "2021-12-12", "2023-10-07", "CH", 800, 1.4},
            {8, 9500, "2022-12-12", "2023-12-04", "CH", 700, 2.9}
        };
        executeInsert(url, user, password, sql, bankAccounts);
    }

    private static void insertBankCustomers(String url, String user, String password) throws SQLException {
        String sql = "INSERT INTO bankcustomer (first_name, last_name, email, phone, sex, birthday, addressid) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[][] bankCustomers = {
            {"John", "Doe", "jdoe@SFSU.edu", "415-555-1212", "Male", "1960-12-07", 2},
            {"Alexander", "Hamilton", "ahamilton@SFSU.edu", "415-555-1213", "Male", "1898-12-08", 3},
            {"Franklin", "Roosevelt", "froosevelt@SFSU.edu", "415-555-1214", "Male", "1886-12-07", 4},
            {"Thomas", "Jefferson", "tjefferson@SFSU.edu", "415-555-1215", "Male", "1855-12-07", 5},
            {"Johnny", "Appleseed", "jappleseed@SFSU.edu", "415-555-1313", "Male", "1923-03-03", 6},
            {"Ben", "Franklin", "bfranklin@SFSU.edu", "415-555-1314", "Male", "1912-03-03", 7},
            {"Herb", "Hoover", "hhoover@SFSU.edu", "415-555-1315", "Male", "1913-03-03", 8},
            {"Andrew", "Jackson", "ajackson@SFSU.edu", "415-555-1316", "Male", "1916-03-03", 9},
            {"Sarah", "Connor", "sconnor@SFSU.edu", "415-555-1317", "Female", "1917-03-03", 1},
            {"Hillary", "Clinton", "hclinton@SFSU.edu", "415-555-1320", "Female", "1942-03-03", 2}
        };
        executeInsert(url, user, password, sql, bankCustomers);
    }

    private static void insertCustomerAddresses(String url, String user, String password) throws SQLException {
        String sql = "INSERT INTO customeraddress (streetnum, streetname, city, state, zip, cusid) VALUES (?, ?, ?, ?, ?, ?)";
        Object[][] customerAddresses = {
            {1212, "Johnson St", "Fremont", "CA", 94536, 1},
            {1214, "Smith St", "San Mateo", "CA", 94537, 2},
            {1215, "Tyler St", "South San Francisco", "CA", 94538, 3},
            {1216, "Dallas St", "San Mateo", "CA", 94539, 4},
            {1217, "Village St", "Milpitas", "CA", 94531, 5},
            {1218, "Apple St", "Los Altos", "CA", 94532, 6},
            {1219, "Banana St", "Fremont", "CA", 94533, 7},
            {1220, "Kayle St", "Hayward", "CA", 94534, 8},
            {1221, "Victory St", "San Leandro", "CA", 94540, 9}
        };
        executeInsert(url, user, password, sql, customerAddresses);
    }

    private static void executeInsert(String url, String user, String password, String sql, Object[][] data) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Object[] entry : data) {
                for (int i = 0; i < entry.length; i++) {
                    pstmt.setObject(i + 1, entry[i]);
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();
        }
    }
}
