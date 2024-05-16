import java.util.Date;

public class Transaction {
    private Date createDate;
    private String tranType;
    private double amount;
    private String summary;
    private int accountId;

    public Transaction(Date createDate, String tranType, double amount, String summary, int accountId) {
        this.createDate = createDate;
        this.tranType = tranType;
        this.amount = amount;
        this.summary = summary;
        this.accountId = accountId;
    }

    // Getters
    public Date getCreateDate() { return createDate; }
    public String getTranType() { return tranType; }
    public double getAmount() { return amount; }
    public String getSummary() { return summary; }
    public int getAccountId() { return accountId; }
}
