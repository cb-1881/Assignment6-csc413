public class TestCustomerAddressDAO {
    public static void main(String[] args) {
        CustomerAddressDAO dao = new CustomerAddressDAO();

       // Create a new address with the correct parameter order
CustomerAddressDTO newAddress = new CustomerAddressDTO(123, "Main St", "Anytown", "CA", 90210, 22);

        // Add the address
        System.out.println("Adding new address...");
        dao.addCustomerAddress(newAddress);

        // Retrieve the address
        System.out.println("Retrieving address...");
        CustomerAddressDTO retrievedAddress = dao.getCustomerAddress(1);
        if (retrievedAddress != null) {
            System.out.println("Retrieved: " + retrievedAddress.getStreetName() + ", " + retrievedAddress.getCity());
        } else {
            System.out.println("No address found.");
        }

        // Update the address
        System.out.println("Updating address...");
        retrievedAddress.setCity("Newtown");
        dao.updateCustomerAddress(retrievedAddress);

        // Retrieve updated address
        retrievedAddress = dao.getCustomerAddress(1);
        System.out.println("Updated Address: " + retrievedAddress.getCity());

        // List all addresses
        System.out.println("Listing all addresses...");
        for (CustomerAddressDTO address : dao.getAllCustomerAddresses()) {
            System.out.println(address.getStreetName() + " " + address.getCity());
        }

        // Delete the address
        System.out.println("Deleting address...");
        dao.deleteCustomerAddress(1);
        
        // Check if deleted
        retrievedAddress = dao.getCustomerAddress(1);
        if (retrievedAddress == null) {
            System.out.println("Address successfully deleted.");
        }
    }
}
