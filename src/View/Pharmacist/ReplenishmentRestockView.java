package View.Pharmacist;

import Model.Shared.ReplenishmentRequest;
import java.util.List;

public class ReplenishmentRestockView {
    public void printReplenishmentRequests(String title, List<ReplenishmentRequest> requests) {
        System.out.println("\n--- " + title + " ---");
        if (requests.isEmpty()) {
            System.out.println("No requests found.");
            return;
        }

        System.out.printf("%-15s %-20s %-20s %-15s%n", "Request ID", "Inventory Name", "Requested Qty", "Status");
        System.out.println("-------------------------------------------------------------------");
        for (ReplenishmentRequest req : requests) {
            System.out.printf("%-15s %-20s %-20d %-15s%n",
                    req.getRequestID(),
                    req.getInventoryName(),
                    req.getRequestedQuantity(),
                    req.getStatus());
        }
    }
}
