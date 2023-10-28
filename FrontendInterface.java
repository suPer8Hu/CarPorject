public interface FrontendInterface {
    
    /**
     * Constructor for the frontend, the constructor should passed a backendInterface and a userInput as arguments;
     * @param backendInterface reference to the backend interface
     * @param snd the scanner base on system.in
     */

    /**
     * Starts the main command loop for user interaction.
     */
    void run();
    
    /**
     * Describe the main menu.
     * @return 
     */
    int mainMenu();
    
    /**
     * Command to specify and load a datafile.
     */
    void commandLoadData(String fileName);
    
    /**
     * Command to list vehicles with the lowest mileage in the dataset.
     */
    void commandLowestMileageVehicles(int min); 
    
    /**
     * Command to list vehicles with mileage at or above a specified threshold
     */
    void commandAllCarAboveThreshold(int threshold);
}
