import static spark.Spark.*;

import org.apache.log4j.BasicConfigurator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        BasicConfigurator.configure();

        get("/hello", (req, res) -> {
            System.out.println("hello world!!!!!");
            return "Hello World";
        });

        get("/medication", (req, res) -> {
            System.out.println("here is some medication info");

            JSONObject employeeDetails = new JSONObject();
            employeeDetails.put("firstName", "Lokesh");
            employeeDetails.put("lastName", "Gupta");
            JSONArray employeeList = new JSONArray();
            employeeList.add(employeeDetails);

            try (FileWriter file = new FileWriter("employees.json")) {
                file.write(employeeList.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "here is some medication info";
        });

        post("/medication", (req, res) -> {
            System.out.println("thank you for this medication info");
            System.out.println(req.body());
            System.out.println(req.queryParams("medicationStrings"));
            return "thank you for this medication info";
        });
    }
}
