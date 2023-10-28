import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;

import java.util.List;

public class BackendTests {

  // ==================================== Backend Tests =========================

  @Test
  public void testReadDataFile() {
    BackendInterface backend = new BackendPlaceholder();
    assertDoesNotThrow(() -> backend.readDataFile("src/USA_cars_datasets.csv"));
  }

  @Test
  public void testCarWithMinMileage() {
    BackendInterface backend = new BackendPlaceholder();
    List<Car> cars = backend.carWithMinMileage(1);
    assertNotNull(cars);
    assertTrue(cars.isEmpty());
  }

  @Test
  public void testCarsAtThreshold() {
    BackendInterface backend = new BackendPlaceholder();
    List<Car> cars = backend.carsAtThreshold(1);
    assertNotNull(cars);
    assertTrue(cars.isEmpty());
  }

  @Test
  public void testCarBrand() {
    Car car = new Car(1, "Audi", "R8", 2023, 0);
    assertTrue(car.Brand().equals("Audi"));

  }

  @Test
  public void testCarPrice() {
    Car car = new Car(1, "Audi", "R8", 2023, 0);
    assertTrue(car.Price() == 1);
  }


  // Implement at least two test cases for your partner's code in your ROLETest.java, where
  // ROLE is replaced by your role within this project: BackendDeveloper, or FrontendDeveloper.

  // ================================ Integration Tests from frontend developer ===========================
  @Test
  public void testBackendCarWithMinMileage() {
    BackendInterface backend = new BackendPlaceholder();

    // Add a car with mileage less than 1 to the backend (if not already in the test data)

    List<Car> cars = backend.carWithMinMileage(1);

    // Assert that the list of cars is not null
    assertNotNull(cars);
  }

  @Test
  public void testBackendCarsAtThreshold() {
    BackendInterface backend = new BackendPlaceholder();

    // Add a car with mileage greater than or equal to 1 to the backend (if not already in the test data)

    List<Car> cars = backend.carsAtThreshold(1);

    // Assert that the list of cars is not null
    assertNotNull(cars);
  }
}

