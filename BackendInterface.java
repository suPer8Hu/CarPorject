import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BackendInterface {
  /**
   * This method is to read data from the CSV file
   * 
   * @param file This is the string that holds the name of the CSV file that contains the car
   *             information
   * @return
   * @throws IOException
   * @throws FileNotFoundException
   */
  public int readDataFile(String file);

  /**
   * This method retrieves the car with minimum mileage
   * 
   * @param minMileage This holds the minimum mileage that is in the RBT
   */
  public List<Car> carWithMinMileage(double minMileage);

  /**
   * This method retrieves cars at certain mileage
   * 
   * @param threshold This holds is the number that defines what mileage we want to set to limit
   *                  what cars get returned in a list
   */
  public List<Car> carsAtThreshold(double threshold);
}
