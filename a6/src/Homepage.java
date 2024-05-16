import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Homepage extends JFrame {
    public Homepage() {
        setTitle("Management System Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);  // Center the frame

         //Create buttons for various management functionalities
      //  JButton btnEmployee = new JButton("Employee Management");
      //  JButton btnCustomer = new JButton("Customer Management");
        JButton btnAccount = new JButton("Account Management");
        //JButton btnTransaction = new JButton("Transaction Management");
        JButton btnShowAllCustomers = new JButton("Show All Customers"); // New button for showing all customers
        JButton btnSearch = new JButton("Search Customers by City"); // New button for city search

        // Set layout and add buttons to the frame
        setLayout(new GridLayout(6, 1, 10, 10)); // Adjusted for 6 rows
        //add(btnEmployee);
        //add(btnCustomer);
        add(btnAccount);
        //add(btnTransaction);
        add(btnShowAllCustomers); // Add the new button to the grid
        add(btnSearch);
        // Add action listeners to buttons
        //btnEmployee.addActionListener(e -> Windows.EmployeeWindow());
        //btnCustomer.addActionListener(e -> Windows.customerWindow());
        btnAccount.addActionListener(e -> Windows.accountWindow());
       // btnTransaction.addActionListener(e -> Windows.transactionWindow());
        btnShowAllCustomers.addActionListener(e -> openAllCustomersPage()); // Listener for opening the all customers window
        btnSearch.addActionListener(e -> openSearchPage());
    }

    private void openAllCustomersPage() {
        EventQueue.invokeLater(() -> {
            JFrame frame = new AllCustomersPage(); // Create the all customers page frame
            frame.setVisible(true); // Set it to visible
        });
    }

    private void openSearchPage() {
        EventQueue.invokeLater(() -> {
            JFrame frame = new SearchPage(); // Create the search page frame
            frame.setVisible(true); // Set it to visible
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Homepage().setVisible(true));
    }
}
