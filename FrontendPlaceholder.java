import java.util.List;
import java.util.Scanner;

public class FrontendPlaceholder implements FrontendInterface {
  private final BackendInterface backend;
  private final Scanner userInput = new Scanner(System.in);

  public FrontendPlaceholder(BackendInterface backend) {
    this.backend = backend;
  }

  @Override
  public void run() {
    int input = 0;
    while (input != 4) {

      input = mainMenu();

      switch (input) {
        case 1:
          System.out.print("Please give the file name: ");
          String fileName = userInput.nextLine();
          commandLoadData(fileName);
          break;
        case 2:
          System.out.print("Please give the minumum mileage: ");
          int min = userInput.nextInt();
          commandLowestMileageVehicles(min);
          break;
        case 3:
          System.out.print("Please give the threshold: ");
          int threshold = userInput.nextInt();
          commandAllCarAboveThreshold(threshold);
          break;
        case 4:
          System.out.println("Exiting the app...");
          break;
        default:
          System.out.println("Invalid command. Please enter a valid command.");
          break;
      }
    }
  }

  @Override
  public int mainMenu() {
    // Print your main menu options here
    System.out.println("1. Load Data");
    System.out.println("2. List Lowest Mileage Vehicles");
    System.out.println("3. List Vehicles Above Threshold");
    System.out.println("4. Exit");

    // Read user input
    int choice = userInput.nextInt();
    userInput.nextLine();

    return choice;
  }

  @Override
  public void commandLoadData(String fileName) {
    System.out.println("================================================================");
    System.out.println("Readed " + backend.readDataFile(fileName) + " cars' data");
    System.out.println("================================================================\n");
  }

  @Override
  public void commandLowestMileageVehicles(int min) {
    List<Car> res = backend.carWithMinMileage(min);
    System.out.println("================================================================");
    System.out.println("Total " + res.size() + " records found:");
    System.out.println(res);
    System.out.println("================================================================\n");
  }

  @Override
  public void commandAllCarAboveThreshold(int threshold) {
    List<Car> res = backend.carsAtThreshold(threshold);
    System.out.println("================================================================");
    System.out.println("Total " + res.size() + " records found:");
    System.out.println(res);
    System.out.println("================================================================\n");
  }
  public static void main(String[] args) {
    BackendInterface backend = new BackendPlaceholder();
    FrontendInterface frontend = new FrontendPlaceholder(backend);

    frontend.run();
  }
}

