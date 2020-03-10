import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import org.apache.log4j.BasicConfigurator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        BasicConfigurator.configure();

        final MedicineService medicineService = new MedicineServiceMapImpl();

        get("/medicines", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(medicineService.getMedicines())));
        });

        get("/medicines/:id", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(medicineService.getMedicine(request.params(":id")))));
        });
        
        post("/medicines", (request, response) -> {
            response.type("application/json");

            Medicine medicine = new Gson().fromJson(request.body(), Medicine.class);
            medicineService.addMedicine(medicine);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });
    }
}
