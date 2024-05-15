import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomerAddressEditor extends JFrame {
    private JTextField streetNumField, streetNameField, cityField, stateField, zipField;
    private JButton updateButton;
    private CustomerAddress address;

    public CustomerAddressEditor(CustomerAddress address) {
        this.address = address;
        initializeUI();
        setTitle("Update Address");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initializeUI() {
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Street Number:"));
        streetNumField = new JTextField(String.valueOf(address.getStreetNum()), 10);
        add(streetNumField);

        add(new JLabel("Street Name:"));
        streetNameField = new JTextField(address.getStreetName(), 10);
        add(streetNameField);

        add(new JLabel("City:"));
        cityField = new JTextField(address.getCity(), 10);
        add(cityField);

        add(new JLabel("State:"));
        stateField = new JTextField(address.getState(), 10);
        add(stateField);

        add(new JLabel("Zip:"));
        zipField = new JTextField(String.valueOf(address.getZip()), 10);
        add(zipField);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this::updateAddress);
        add(updateButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveAddress());
        add(saveButton);
    }


    private void saveAddress() {
        try {
            address.setStreetNum(Integer.parseInt(streetNumField.getText()));
            address.setStreetName(streetNameField.getText());
            address.setCity(cityField.getText());
            address.setState(stateField.getText());
            address.setZip(Integer.parseInt(zipField.getText()));

            CustomerDAO dao = new CustomerDAO();
            dao.saveAddress(address);

            JOptionPane.showMessageDialog(this, "Address updated successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format in fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to update the address: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateAddress(ActionEvent e) {
        // Update address fields based on user input
        address.setStreetNum(Integer.parseInt(streetNumField.getText()));
        address.setStreetName(streetNameField.getText());
        address.setCity(cityField.getText());
        address.setState(stateField.getText());
        address.setZip(Integer.parseInt(zipField.getText()));
        JOptionPane.showMessageDialog(this, "Address Updated Successfully!");
    }
}
