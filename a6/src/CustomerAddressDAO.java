import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerAddressDAO implements CustomerAddressDAOInterface {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/CS413";
        String user = "root";
        String password = "Rootroot123!";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void addCustomerAddress(CustomerAddressDTO address) {
        String sql = "INSERT INTO customeraddress (streetnum, streetname, city, state, zip, cusid) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, address.getStreetNum());
            stmt.setString(2, address.getStreetName());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getState());
            stmt.setInt(5, address.getZip());
            stmt.setInt(6, address.getCusId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CustomerAddressDTO getCustomerAddress(int cusId) {
        String sql = "SELECT * FROM customeraddress WHERE cusid = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cusId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CustomerAddressDTO(
                    rs.getInt("streetnum"),
                    rs.getString("streetname"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getInt("zip"),
                    rs.getInt("cusid")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCustomerAddress(CustomerAddressDTO address) {
        String sql = "UPDATE customeraddress SET streetnum = ?, streetname = ?, city = ?, state = ?, zip = ? WHERE cusid = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, address.getStreetNum());
            stmt.setString(2, address.getStreetName());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getState());
            stmt.setInt(5, address.getZip());
            stmt.setInt(6, address.getCusId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomerAddress(int cusId) {
        String sql = "DELETE FROM customeraddress WHERE cusid = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cusId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CustomerAddressDTO> getAllCustomerAddresses() {
        List<CustomerAddressDTO> addresses = new ArrayList<>();
        String sql = "SELECT * FROM customeraddress";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                addresses.add(new CustomerAddressDTO(
                    rs.getInt("streetnum"),
                    rs.getString("streetname"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getInt("zip"),
                    rs.getInt("cusid")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }
}
