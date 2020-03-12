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

        get("/medications/statistics", (req, res) -> {
            res.type("application/json");

            HashMap<String, Integer> medicationsStatistics = getMedicationsStatistics(medicationService);

            return new Gson().toJson(medicationsStatistics);
        });

        post("/medications", (req, res) -> {
            res.type("application/json");

            try {
                postMedications(req.body(), medicationService);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        "all medicationStrings succesfully submitted!"));
            } catch (Exception ex) {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
                        ex.getMessage().concat(" medicationString format not valid")));
            }
        });

        get("/*", (req, res) -> {
            res.type("application/json");
            res.status(403);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
                    " This endpoint does not exist"));
        });
    }

    private static HashMap<String, Integer> getMedicationsStatistics(MedicationService medicationService) {
        return medicationService.getStatistics();
    }

    private static void postMedications(String json, MedicationService medicationService) throws Exception {
        String[] medicationStrings = Medication.separateMedicationStrings(json);

        // split up each string and create a medicine model and add to hash
        for (String medicationString : medicationStrings) {
            Medication medication = Medication.medicationStringToMedicationObject(medicationString);
            if (!Medication.validate(medication)) {
                throw new Exception("medicationString format not valid");
            }
        }

        // in separate for loop so that if one medication string is invalid then none of the strings are added, better
        // design for the use to get rejected like this so they don't accidentally double up on medication inputs when
        // when resubmitting after fixing invalid strings
        for (String medicationString : medicationStrings) {
            Medication medication = Medication.medicationStringToMedicationObject(medicationString);
            medicationService.addMedication(medication);
        }
    }
}
