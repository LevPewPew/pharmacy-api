# Adnuntius Coding Challenge by Levente Toth

### General Information
View repo on GitHub at https://github.com/LevPewPew/pharmacy-api

Thanks for the challenge and for taking a look at my implementation, any feedback is appreciated, especially as the
first time I have ever seen any Java was in order to complete this challenge! I had never used a statically typed
language for web dev before, so having to learn something new to complete this challenge Java seemed like an obvious
choice.

### Running instructions

1. clone the repo `git clone git@github.com:LevPewPew/pharmacy-api.git`
2. open repo/project in IntelliJ
3. to use app, Run Main.main() (filepath: src/main/java/com/lev/pharmacy/Main.java)
4. use postman (or similar tool) to do manual testing
    - POST http://localhost:4567/medications with either of the 2 JSON formats of medicationStrings in the json request 
    body
    - GET http://localhost:4567/medications/statistics
    - POST http://localhost:4567/anyrandomletters to get a 403 response for a endpoint that do not exist
5. to perform unit tests, Run MedicationTest (filepath: src/test/java/com/lev/medication/MedicationTest.java)

