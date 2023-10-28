
public class Car implements Comparable<Car>, CarInterface {
  private String brand;
  private String model;
  private int year;
  private double price;
  private double mileage;

  public Car(double price, String brand, String model, int year, double mileage) {
    this.price = price;
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.mileage = mileage;
  }


  @Override
  public String Brand() {
    return brand;
  }

  @Override
  public String Model() {
    return model;
  }

  @Override
  public int Year() {
    return year;
  }

  @Override
  public double Price() {
    return price;
  }

  @Override
  public double Mileage() {
    return mileage;
  }


  @Override
  public int compareTo(Car o) {
    if (this.mileage == o.mileage)
      return 0;
    return this.mileage > o.mileage ? 1 : -1;
  }

  @Override
  public String toString() {
    return "[Brand: " + this.brand + ", Model: " + this.model + ", Mileage: " + this.mileage + "]";
  }

}
