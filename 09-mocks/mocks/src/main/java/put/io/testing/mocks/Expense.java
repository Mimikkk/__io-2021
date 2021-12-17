package put.io.testing.mocks;

public class Expense {
    private String title;
    private String category;
    private long amount;

    Expense() {}

    static Expense of(long amount) {
        return new Expense(amount);
    }

    static Expense of(long amount, String category) {
        return new Expense(amount, category);
    }

    Expense(long amount) {
        this.amount = amount;
    }

    Expense(long amount, String category) {
        this.amount = amount;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
