import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        get("/hello", (req, res) -> {
            System.out.println("hello world!!!!!");
            return "Hello World";
        });

        get("/medication", (req, res) -> {
            System.out.println("here is some medication info");
            return "here is some medication info";
        });

        post("/medication", (req, res) -> {
            System.out.println("thank you for this medication info");
            return "thank you for this medication info";
        });
    }
}
