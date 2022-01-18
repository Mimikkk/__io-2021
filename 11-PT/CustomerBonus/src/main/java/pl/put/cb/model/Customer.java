package pl.put.cb.model;

public class Customer {
  private final double spendings;
  private final int id;

  public Customer(int id, double spendings) {
    this.id = id;
    this.spendings = spendings;
  }

  public Integer Id() {
    return id;
  }
  public Double Spendings() {
    return spendings;
  }
}
