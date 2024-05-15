import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Homepage extends JFrame {
    public Homepage() {
        setTitle("Management System Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);  // Center the frame

        // Create buttons for various management functionalities
        JButton btnEmployee = new JButton("Employee Management");
        JButton btnCustomer = new JButton("Customer Management");
        JButton btnAccount = new JButton("Account Management");
        JButton btnTransaction = new JButton("Transaction Management");
        JButton btnSearch = new JButton("Search Customers by City"); // New button for city search

        // Set layout and add buttons to the frame
        setLayout(new GridLayout(5, 1, 10, 10)); // Adjusted for 5 rows
        add(btnEmployee);
        add(btnCustomer);
        add(btnAccount);
        add(btnTransaction);
        add(btnSearch); // Add the search button to the grid

        // Add action listeners to buttons
        btnEmployee.addActionListener(e -> Windows.EmployeeWindow());
        btnCustomer.addActionListener(e -> Windows.customerWindow());
        btnAccount.addActionListener(e -> Windows.accountWindow());
        btnTransaction.addActionListener(e -> Windows.transactionWindow());
        btnSearch.addActionListener(e -> openSearchPage()); // Listener for opening the search window
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
