
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FrontendDeveloperTests {

// ============================== Frontend tests =====================================
    /**
     * This test method checks if the 'run' method runs without errors.
     */
    @Test
    public void testRunApp() {
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new FrontendPlaceholder(backend);

        // Simply call the 'run' method and check for any exceptions
        assertDoesNotThrow(() -> frontend.run());
    }

    @TempDir
    File tempDir;
    /**
     * This test method verifies that the 'commandLoadData' method successfully loads data from a file.
     * It does so by creating a temporary data file in the @TempDir provided by JUnit 5. The 'commandLoadData'
     * method is then called to load data from the test file. Finally, the test checks if the test data file exists
     * to confirm that the data was successfully loaded.
     * @throws IOException if there are issues with file creation or loading data.
     */
    @Test
    public void testCommandLoadData() throws IOException {
        // Create a temporary test data file
        File testDataFile = new File(tempDir, "USA_cars_datasets.csv");
        testDataFile.createNewFile();
        // Initialize the backend and frontend
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new FrontendPlaceholder(backend);
        // Load data using the 'commandLoadData' method
        frontend.commandLoadData(testDataFile.getPath());
        // Verify if the test data file exists
        assertTrue(testDataFile.exists());
    }


    /**
     * This test method checks if the 'commandLowestMileageVehicles' method runs without errors.
     */
    @Test
    public void testCommandLowestMileageVehicles() {
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new FrontendPlaceholder(backend);
        // Simply call the method and check for any exceptions
        assertDoesNotThrow(() -> frontend.commandLowestMileageVehicles(10000));
    }

    /**
     * Test frontend commandAllCarAboveThreshold, a empty list should be returned when min is too
     * little
     */
    @Test
    public void testExit() {
        String input = "out/USA_cars_datasets.csv";
        TextUITester tester = new TextUITester(input);
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new FrontendPlaceholder(backend);

        frontend.commandLoadData(input);
        frontend.commandLowestMileageVehicles(-1);

        String output = tester.checkOutput();
        assertTrue(output.contains("Total 0 records found:"));
    }

    /**
     * This test method checks if the 'commandAllCarAboveThreshold' method runs without errors.
     */
    @Test
    public void testCommandAllCarAboveThreshold() {
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new FrontendPlaceholder(backend);

        // Simply call the method and check for any exceptions
        assertDoesNotThrow(() -> frontend.commandAllCarAboveThreshold(100000));
    }


    // ================== Integration Tests ===================================


    /**
     * Test the interaction between the frontend and the backend on loading data.
     */
    @Test
    public void testIntegrationLoadData() throws IOException {
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new FrontendPlaceholder(backend);

        // Simply call the 'commandLoadData' method and check for any exceptions
        assertDoesNotThrow(() -> frontend.commandLoadData("testFile.csv"));
    }

    /**
     * Test the interaction between the frontend and the backend on listing vehicles with the lowest mileage.
     */
    @Test
    public void testIntegrationLowestMileageVehicles() {
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new FrontendPlaceholder(backend);

        // Simply call the 'commandLowestMileageVehicles' method and check for any exceptions
        assertDoesNotThrow(() -> frontend.commandLowestMileageVehicles(10000));
    }
}
