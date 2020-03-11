import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final MedicationService medicationService = new MedicationServiceMapImpl();

        get("/medications/statistics", (request, response) -> {
            response.type("application/json");

            HashMap<String, Integer> allData = medicationService.getStatistics();

            return new Gson().toJson(allData);
        });

        post("/medications", (request, response) -> {
            response.type("application/json");

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
                String medicationId = medicationAttributes[0];
                String bottleSize = medicationAttributes[1];
                int dosageCount = Integer.parseInt(medicationAttributes[2]);
                // generate a uniqueId, needed so that statistics can keep track of multiple inputs of the same medicine
                // by preventing overwriting Medicine with the same medicationId
                int id = System.identityHashCode(medicationId);

                String json = String.format("{\"id\":\"%s\", \"medicationId\": \"%s\", \"bottleSize\": \"%s\", \"dosageCount\": %s}", id, medicationId, bottleSize, dosageCount);
                Medication medication = new Gson().fromJson(json, Medication.class);
                try {
                    if (!Medication.validate(medication)) {
                        throw new Exception("medicationStrings format not valid");
                    }
                    medicationService.addMedicine(medication);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/*", (request, response) -> {
            response.type("application/json");
            response.status(403);
            return "This endpoint does not exist";
        });
    }
}
