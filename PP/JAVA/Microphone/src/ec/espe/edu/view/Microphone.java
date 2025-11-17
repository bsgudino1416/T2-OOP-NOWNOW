package ec.espe.edu.view;

/**
 *
 * @author Bryan Gudino, KNOWLEDGE ENCAPSULATE, @ESPE
 */
public class Microphone {
    private String id;
    private String brand;
    private String model;
    private double price;

    public Microphone(String id, String brand, String model, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    // Convert object to JSON-like structure
    public String toJson() {
        return String.format(
            "{\"id\":\"%s\", \"brand\":\"%s\", \"model\":\"%s\", \"price\": %.2f}",
            id, brand, model, price
        );
    }

    // Getters
    public String getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPrice() { return price; }
}
