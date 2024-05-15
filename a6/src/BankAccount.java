public class BankAccount {
    private int accountNumber;
    private int customerId;
    private double balance;
    private String createDate;
    private String lastUpdateDate;
    private String accountType;
    private float overdraftLimit;
    private float interestRate;

    public BankAccount(int accountNumber, int customerId, double balance, String createDate, String lastUpdateDate, String accountType, float overdraftLimit, float interestRate) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.accountType = accountType;
        this.overdraftLimit = overdraftLimit;
        this.interestRate = interestRate;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public float getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(float overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    // Getters and setters as needed
}
