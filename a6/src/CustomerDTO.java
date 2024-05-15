import java.util.Date;

public class CustomerDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String sex;
    private Date birthday;
    private CustomerAddress address;  // Reference to CustomerAddress

    public CustomerDTO(int id, String firstName, String lastName, String email, String phone, String sex, Date birthday, CustomerAddress address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public CustomerAddress getAddress() {
        return address;
    }

    public void setAddress(CustomerAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        String cityDisplay = address != null ? address.getCity() : "Unknown City";
        return firstName + " " + lastName + " - " + cityDisplay;
    }
}
