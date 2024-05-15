import java.util.List;

public interface CustomerAddressDAOInterface {
    void addCustomerAddress(CustomerAddressDTO address);
    CustomerAddressDTO getCustomerAddress(int id);
    void updateCustomerAddress(CustomerAddressDTO address);
    void deleteCustomerAddress(int id);
    List<CustomerAddressDTO> getAllCustomerAddresses();
}
