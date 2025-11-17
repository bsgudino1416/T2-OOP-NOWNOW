package ec.espe.edu.utils;

import ec.espe.edu.view.Microphone;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Bryan
 */
public class FileManager {
    private static final String JSON_FILE = "data/microphones.json";

    public static void saveToFile(ArrayList<Microphone> microphones) {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            try (FileWriter writer = new FileWriter(JSON_FILE)) {
                writer.write("[\n");
                for (int i = 0; i < microphones.size(); i++) {
                    writer.write("  " + microphones.get(i).toJson());
                    if (i < microphones.size() - 1) writer.write(",");
                    writer.write("\n");
                }
                writer.write("]");
            }

            System.out.println("Datos guardados correctamente en " + JSON_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Microphone> loadFromFile() {
        ArrayList<Microphone> list = new ArrayList<>();

        try {
            File file = new File(JSON_FILE);
            if (!file.exists()) {
                System.out.println("No se encontró archivo, iniciando vacío.");
                return list;
            }

            StringBuilder json = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    json.append(line);
                }
            }

            String content = json.toString().trim();
            // Remove array brackets
            if (content.startsWith("[")) content = content.substring(1);
            if (content.endsWith("]")) content = content.substring(0, content.length() - 1);
            content = content.trim();
            if (content.isEmpty()) return list;

            // Split using "}," but add back the brace if it was removed
            String[] items = content.split("\\},\\s*");
            for (String item : items) {
                item = item.trim();
                if (!item.endsWith("}")) item = item + "}";
                // Remove braces and split by comma between fields
                item = item.replaceAll("^\\{", "").replaceAll("\\}$", "").trim();
                String[] fields = item.split(",\\s*");

                String id = fields[0].split(":", 2)[1].replace("\"", "").trim();
                String brand = fields[1].split(":", 2)[1].replace("\"", "").trim();
                String model = fields[2].split(":", 2)[1].replace("\"", "").trim();
                double price = Double.parseDouble(fields[3].split(":", 2)[1].trim());

                list.add(new Microphone(id, brand, model, price));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
