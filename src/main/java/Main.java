import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        BasicConfigurator.configure();

        final MedicationService medicationService = new MedicationServiceMapImpl();

        get("/medications", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(medicationService.getMedicines())));
        });

        get("/medications/:id", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(medicationService.getMedicine(request.params(":id")))));
        });

        post("/medications", (request, response) -> {
            response.type("application/json");
            System.out.println(request.body());

            MedicationsStrings medicationsStrings = new Gson().fromJson(request.body(), MedicationsStrings.class);

            String[] medicationAttributes = medicationsStrings.getMedicationsStrings().split("_");
            String id = medicationAttributes[0];
            String bottleSize = medicationAttributes[1];
            int dosageCount = Integer.parseInt(medicationAttributes[2]);

            String json = String.format("{\"id\":\"%s\", \"bottleSize\": \"%s\", \"dosageCount\": %s}", id, bottleSize, dosageCount);
            Medication medication = new Gson().fromJson(json, Medication.class);
            medicationService.addMedicine(medication);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });
    }
}
