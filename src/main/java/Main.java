import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import com.lev.medication.Medication;

public class Main {
    public static void main(String[] args) {
        final MedicationService medicationService = new MedicationServiceMapImpl();

        get("/medications/statistics", (request, response) -> {
            response.type("application/json");

            HashMap<String, Integer> medicationsStatistics = getMedicationsStatistics(medicationService);

            return new Gson().toJson(medicationsStatistics);
        });

        post("/medications", (request, response) -> {
            response.type("application/json");

            postMedications(request.body(), medicationService);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/*", (request, response) -> {
            response.type("application/json");
            response.status(403);
            return "This endpoint does not exist";
        });
    }

    public static HashMap<String, Integer> getMedicationsStatistics(MedicationService medicationService) {
        return medicationService.getStatistics();
    }

    public static void postMedications(String json, MedicationService medicationService) {

        String[] medicationStrings = separateMedicationStrings(json);

        // split up each string and create a medicine model and add to hash
        for (String medicationString : medicationStrings) {
            Medication medication = medicationStringToMedicationObject(medicationString);

            try {
                if (!Medication.validate(medication)) {
                    throw new Exception("medicationString format not valid");
                }
                medicationService.addMedicine(medication);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static String[] separateMedicationStrings(String json) {
        String[] medicationStrings = new String[0];

        if (json.contains("[")) {
            try {
                MedicationStringsList medicationStringsList = new Gson().fromJson(json, MedicationStringsList.class);
                List<String> medicationList = medicationStringsList.getMedicationStrings();
                medicationStrings = medicationList.stream().toArray(String[]::new);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("medicationStrings json format not valid");
            }
        } else {
            try {
                MedicationStringsString medicationStringsString = new Gson().fromJson(json, MedicationStringsString.class);
                medicationStrings = medicationStringsString.getMedicationStringsString().split(";");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("medicationStrings json format not valid");
            }
        }

        return medicationStrings;
    }

    public static Medication medicationStringToMedicationObject(String medicationString) {
        String[] medicationAttributes = medicationString.split("_");
        String medicationId = medicationAttributes[0];
        String bottleSize = medicationAttributes[1];
        int dosageCount = Integer.parseInt(medicationAttributes[2]);
        // generate a uniqueId, needed so that statistics can keep track of multiple inputs of the same medicine
        // by preventing overwriting Medicine with the same medicationId
        int id = System.identityHashCode(medicationId);

        String formattedJson = String.format("{\"id\":\"%s\", \"medicationId\": \"%s\", \"bottleSize\": \"%s\", \"dosageCount\": %s}", id, medicationId, bottleSize, dosageCount);
        Medication medication = new Gson().fromJson(formattedJson, Medication.class);

        return medication;
    }
}
