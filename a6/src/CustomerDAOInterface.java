
import java.util.List;
//import java.sql.*;


    public interface CustomerDAOInterface {
    void addCustomer(Customer customer);
    Customer getCustomer(String id);
    void updateCustomer(Customer customer);
    void deleteCustomer(String id);
    List<Customer> getAllCustomers();
   // List<CustomerDTO> findCustomersByLastName(String lastName); // New method
    List<CustomerDTO> findCustomersByCity(String city);  // New method
    public List<BankAccount> findBankAccountsByCustomerId(int customerId);
}
