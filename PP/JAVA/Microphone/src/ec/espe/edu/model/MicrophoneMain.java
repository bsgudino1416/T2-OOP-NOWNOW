package ec.espe.edu.model;

import ec.espe.edu.view.Microphone;
import ec.espe.edu.utils.FileManager;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Bryan
 */
public class MicrophoneMain {

    public static void main(String[] args) {
        System.out.println("Microphone Manager");

        ArrayList<Microphone> microphones = FileManager.loadFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add microphone");
            System.out.println("2. Show list");
            System.out.println("3. Save in JSON");
            System.out.println("4. Exit");
            System.out.print("Option: ");

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Invalid option.");
                continue;
            }

            int option;
            try {
                option = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Brand: ");
                    String brand = scanner.nextLine();

                    System.out.print("Model: ");
                    String model = scanner.nextLine();

                    System.out.print("Price: ");
                    double price = 0.0;
                    try {
                        String priceStr = scanner.nextLine();
                        price = Double.parseDouble(priceStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price. Aborting add.");
                        break;
                    }

                    microphones.add(new Microphone(id, brand, model, price));
                    System.out.println("Microphone added.");
                    break;

                case 2:
                    if (microphones.isEmpty()) {
                        System.out.println("No microphones in list.");
                    } else {
                        for (Microphone mic : microphones) {
                            System.out.println(mic.getId() + " - " + mic.getBrand() + " - " + mic.getModel() + " - $" + mic.getPrice());
                        }
                    }
                    break;

                case 3:
                    FileManager.saveToFile(microphones);
                    break;

                case 4:
                    System.out.println("Exit...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
