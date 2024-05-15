import java.util.List;

public class TestFindCustomersByLastName {

        public static void main(String[] args) {
            CustomerDAO customerDao = new CustomerDAO();
    
            // Search for customers in a specific city
            String searchCity = "San Mateo";  // Modify as per your test data
            List<CustomerDTO> customers = customerDao.findCustomersByCity(searchCity);
    
            // Output the results
            System.out.println("Customers in " + searchCity + ":");
            if (customers.isEmpty()) {
                System.out.println("No customers found in the city " + searchCity);
            } else {
                for (CustomerDTO customer : customers) {
                    System.out.println("ID: " + customer.getId() + ", Name: " + customer.getFirstName() + " " + customer.getLastName() +
                                       ", Email: " + customer.getEmail() + ", Phone: " + customer.getPhone());
                }
            }
        }
    
    
}
