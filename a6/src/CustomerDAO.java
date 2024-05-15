import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerDAOInterface {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/CS413";
        String user = "root";
        String password = "Rootroot123!";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO bankcustomer (id, first_name, last_name, email, phone, sex, birthday) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getId());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.setString(6, customer.getSex());
            stmt.setDate(7, new Date(customer.getBirthday().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CustomerDTO> findCustomersByCity(String city) {
        List<CustomerDTO> customers = new ArrayList<>();
        String sql = "SELECT c.*, a.streetnum, a.streetname, a.city, a.state, a.zip, a.cusid " +
                     "FROM bankcustomer c " +
                     "JOIN customeraddress a ON c.id = a.cusid " +
                     "WHERE LOWER(a.city) = LOWER(?);";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, city.toLowerCase());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CustomerAddress address = new CustomerAddress(
                    rs.getInt("cusid"),
                    rs.getInt("streetnum"),
                    rs.getString("streetname"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getInt("zip")
                );
                customers.add(new CustomerDTO(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("sex"),
                    rs.getDate("birthday"),
                    address
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    @Override
    public Customer getCustomer(String id) {
        String sql = "SELECT * FROM bankcustomer WHERE id = ?";
        Customer customer = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new Customer(sql, sql, sql, sql, sql, sql, null);
                customer.setId(rs.getString("id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setSex(rs.getString("sex"));
                customer.setBirthday(rs.getDate("birthday"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE bankcustomer SET first_name = ?, last_name = ?, email = ?, phone = ?, sex = ?, birthday = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getSex());
            stmt.setDate(6, new Date(customer.getBirthday().getTime()));
            stmt.setString(7, customer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(String id) {
        String sql = "DELETE FROM bankcustomer WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM bankcustomer";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer customer = new Customer(sql, sql, sql, sql, sql, sql, null);
                customer.setId(rs.getString("id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setSex(rs.getString("sex"));
                customer.setBirthday(rs.getDate("birthday"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    public void saveAddress(CustomerAddress address) {
        String sql = "UPDATE customeraddress SET streetnum = ?, streetname = ?, city = ?, state = ?, zip = ? WHERE cusid = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, address.getStreetNum());
            stmt.setString(2, address.getStreetName());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getState());
            stmt.setInt(5, address.getZip());
            stmt.setInt(6, address.getId());  // Assuming cusid is used as the identifier for the address

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating address failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred while updating address: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<BankAccount> findBankAccountsByCustomerId(int customerId) {
        List<BankAccount> accounts = new ArrayList<>();
        String sql = "SELECT * FROM bankaccount WHERE cust_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BankAccount account = new BankAccount(
                    rs.getInt("acct_num"),
                    rs.getInt("cust_id"),
                    rs.getDouble("balance"),
                    rs.getString("create_date"),
                    rs.getString("last_update_date"),
                    rs.getString("acct_type"),
                    rs.getFloat("od_limit"),
                    rs.getFloat("int_rate")
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
    

    public void updateBankAccount(BankAccount account) throws SQLException {
        String sql = "UPDATE bankaccount SET balance = ?, acct_type = ?, od_limit = ?, int_rate = ? WHERE acct_num = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getAccountType());
            stmt.setFloat(3, account.getOverdraftLimit());
            stmt.setFloat(4, account.getInterestRate());
            stmt.setInt(5, account.getAccountNumber());
    
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating account failed, no rows affected.");
            }
        }
    }
    

}
