package com.lev.pharmacy;

import static spark.Spark.get;
import static spark.Spark.post;
import com.google.gson.Gson;
import java.util.HashMap;
import com.lev.medication.*;
import com.lev.httpRequest.*;

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

    private static HashMap<String, Integer> getMedicationsStatistics(MedicationService medicationService) {
        return medicationService.getStatistics();
    }

    private static void postMedications(String json, MedicationService medicationService) {

        String[] medicationStrings = Medication.separateMedicationStrings(json);

        // split up each string and create a medicine model and add to hash
        for (String medicationString : medicationStrings) {
            try {
                Medication medication = Medication.medicationStringToMedicationObject(medicationString);
                if (!Medication.validate(medication)) {
                    throw new Exception("medicationString format not valid");
                }
                medicationService.addMedicine(medication);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
