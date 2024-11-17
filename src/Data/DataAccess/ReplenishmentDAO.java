package Data.DataAccess;

import Model.Shared.ReplenishmentRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ReplenishmentDAO class handles the data access for replenishment requests.
 * This includes loading, saving, and managing the data from a CSV file.
 */
public class ReplenishmentDAO {
    private static final String FILE_PATH = "src/Data/Assets/Replenishment_Restock.csv";

    /**
     * Loads all replenishment requests from the CSV file.
     * 
     * @return A list of replenishment requests.
     */
    public List<ReplenishmentRequest> loadAll() {
        List<ReplenishmentRequest> requestList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 4) {
                    System.err.println("Skipping malformed row: " + line);
                    continue;
                }
                try {
                    String requestID = values[0];
                    String inventoryName = values[1];
                    int requestedQuantity = Integer.parseInt(values[2]);
                    String status = values[3];

                    requestList.add(new ReplenishmentRequest(requestID, inventoryName, requestedQuantity, status));
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing numeric value in row: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading replenishment requests: " + e.getMessage());
        }
        return requestList;
    }

    /**
     * Saves a replenishment request to the CSV file.
     * Updates an existing request if found, or adds a new one if it does not exist.
     * 
     * @param request The replenishment request to save.
     */
    public void save(ReplenishmentRequest request) {
        List<ReplenishmentRequest> requestList = loadAll();
        boolean exists = false;

        for (int i = 0; i < requestList.size(); i++) {
            if (requestList.get(i).getRequestID().equals(request.getRequestID())) {
                requestList.set(i, request); // Update existing request
                exists = true;
                break;
            }
        }

        if (!exists) {
            requestList.add(request);
        }

        saveAll(requestList);
    }

    /**
     * Saves all replenishment requests to the CSV file.
     * 
     * @param requestList A list of replenishment requests to save.
     */
    private void saveAll(List<ReplenishmentRequest> requestList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            writer.write("Request ID,Inventory Name,Requested Quantity,Status");
            writer.newLine();
            for (ReplenishmentRequest request : requestList) {
                writer.write(formatRequestCSVLine(request));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving replenishment requests: " + e.getMessage());
        }
    }

    /**
     * Formats a replenishment request as a CSV line.
     * 
     * @param request The replenishment request to format.
     * @return A CSV-formatted string representation of the replenishment request.
     */
    private String formatRequestCSVLine(ReplenishmentRequest request) {
        return String.join(",",
                request.getRequestID(),
                request.getInventoryName(),
                String.valueOf(request.getRequestedQuantity()),
                request.getStatus()
        );
    }

    /**
     * Generates the next request ID in the format "R##", where ## is the next number.
     * 
     * @return The next available request ID.
     */
    public String generateNextRequestID() {
        List<ReplenishmentRequest> requestList = loadAll();
        int maxID = 0;

        for (ReplenishmentRequest request : requestList) {
            String id = request.getRequestID();
            if (id.startsWith("R")) {
                try {
                    int numericPart = Integer.parseInt(id.substring(1));
                    if (numericPart > maxID) {
                        maxID = numericPart;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid request ID: " + id);
                }
            }
        }

        return String.format("R%02d", maxID + 1);
    }

    /**
     * Finds a specific replenishment request by request ID.
     * 
     * @param id The request ID to find.
     * @param searchKey Optional search key (not used in the current implementation).
     * @return The replenishment request if found, or null if not found.
     */
    public ReplenishmentRequest find(String id, String searchKey) {
        List<ReplenishmentRequest> requestList = loadAll();
        for (ReplenishmentRequest request : requestList) {
            if (request.getRequestID().equalsIgnoreCase(id.trim())) {
                return request;
            }
        }
        return null;
    }

    /**
     * Deletes a replenishment request by request ID.
     * 
     * @param id The request ID to delete.
     * @param searchKey Optional search key (not used in the current implementation).
     */
    public void delete(String id, String searchKey) {
        // TODO: Implementation required to delete a request by ID.
    }
}
