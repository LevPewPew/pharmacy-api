import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

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

//            Gson testGson = new Gson().fromJson(request.body(), );
//            System.out.println(testGson);

            Medication medication = new Gson().fromJson(request.body(), Medication.class);
            medicationService.addMedicine(medication);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });
    }
}
