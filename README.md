# Adnuntius Coding Challenge by Levente Toth

### General Information

### Running instructions

1. clone the repo `git clone git@github.com:LevPewPew/pharmacy-api.git`
2. open repo/project in IntelliJ
3. run Main.main() (filepath: src/main/java/com/lev/pharmacy/Main.java)
4. use postman (or similar tool) to do manual testing
    - POST http://localhost:4567/medications with either of the 2 JSON formats of medicationStrings in the json request body
    - GET http://localhost:4567/medications/statistics
    - POST http://localhost:4567/anyrandomletters to get a 403 response for a endpoint that do not exist