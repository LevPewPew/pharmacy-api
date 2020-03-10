import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import java.util.List;

public class Main {
    public static void main(String[] args) {
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

            // create an array of Strings out of the json, try each possible format
            String[] medicationStrings = new String[0];
            try {
                MedicationStringsString medicationStringsString = new Gson().fromJson(request.body(), MedicationStringsString.class);
                medicationStrings = medicationStringsString.getMedicationStringsString().split(";");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            try {
                MedicationStringsList medicationStringsList = new Gson().fromJson(request.body(), MedicationStringsList.class);
                List<String> medicationList = medicationStringsList.getMedicationStrings();
                medicationStrings = medicationList.stream().toArray(String[]::new);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            // split up each string and create a medicine model and add to hash
            for (String string : medicationStrings) {
                String[] medicationAttributes = string.split("_");
                String id = medicationAttributes[0];
                String bottleSize = medicationAttributes[1];
                int dosageCount = Integer.parseInt(medicationAttributes[2]);

                String json = String.format("{\"id\":\"%s\", \"bottleSize\": \"%s\", \"dosageCount\": %s}", id, bottleSize, dosageCount);
                Medication medication = new Gson().fromJson(json, Medication.class);
                medicationService.addMedicine(medication);
            }

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });
    }
}
