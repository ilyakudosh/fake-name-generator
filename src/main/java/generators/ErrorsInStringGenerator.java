package generators;

import java.util.Random;

public class ErrorsInStringGenerator {
    private final StringBuilder stringWithError;

    private final String[] errorTypesList = { "addSymbol", "deleteSymbol", "switchSymbol" };

    private float errorProbability = 0;

    Random rand = new Random();

    ErrorsInStringGenerator(String string, float errorProbability){
        this.stringWithError = new StringBuilder(string);
        this.errorProbability = errorProbability;
    }

    private void deleteSymbol() {
        stringWithError.deleteCharAt(rand.nextInt(stringWithError.length()));
    }

    private void addSymbol() {
        char randomChar = stringWithError.charAt(rand.nextInt(stringWithError.length()));

        stringWithError.insert(rand.nextInt(stringWithError.length()), randomChar);
    }

    private void switchSymbol() {
        int randomPosition = rand.nextInt(stringWithError.length() - 1);
        char randomChar = stringWithError.charAt(randomPosition);

        int neighborPosition = randomPosition + 1;
        char neighborChar = stringWithError.charAt(randomPosition + 1);

        stringWithError.setCharAt(neighborPosition, randomChar);
        stringWithError.setCharAt(randomPosition, neighborChar);
    }

    private int getErrorsOnStringCount() {
        int errorsOnStringCount;

        if (errorProbability < 1) {
            int errorProbabilityPercent = (int) (1 / errorProbability);
            boolean val = rand.nextInt(errorProbabilityPercent) == 0;

            errorsOnStringCount = val ? 1 : 0;
        } else {
            errorsOnStringCount = (int) errorProbability;
        }

        return errorsOnStringCount;
    }

    private void generateErrorsInString() {
        int errorOnStringCount = getErrorsOnStringCount();

        for (int i = 0; i < errorOnStringCount; i++) {
            int errorTypeIndex = rand.nextInt(errorTypesList.length);

            switch (errorTypesList[errorTypeIndex]){
                case "addSymbol": this.addSymbol();
                    break;
                case "deleteSymbol": this.deleteSymbol();
                    break;
                case "switchSymbol": this.switchSymbol();
                    break;
            }
        }
    }

    public String getStringWithError() {
        this.generateErrorsInString();

        return stringWithError.toString();
    }
}
