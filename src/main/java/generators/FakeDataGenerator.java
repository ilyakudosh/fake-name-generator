package generators;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import java.util.Locale;

public class FakeDataGenerator {
    private final String locale;

    private int generationsCount;

    private float errorProbability;

    public FakeDataGenerator(String[] args) {
        String argumentLocale;

        try {
            argumentLocale = args[0];
        } catch (Exception error) {
            argumentLocale = "";
        }

        try {
            this.generationsCount = Integer.parseInt(args[1]);
        } catch (Exception error) {
            System.out.println("Use default generating count 0");
        }

        try {
            this.errorProbability = Float.parseFloat(args[2]);
        } catch (Exception error) {
            System.out.println("Use default errorProbability 0");
        }

        switch(argumentLocale) {
            case "ru_RU": this.locale = "ru";
                break;
            case "en_US": this.locale = "en_US";
                break;
            case "be_BY": this.locale = "by";
                break;
            default:
                System.out.println("No support locale used default - by");
                this.locale = "by";
        }
    }

    public void generateFakeData() {
        Faker faker = new Faker(new Locale(locale));

        for (int i = 0; i < generationsCount; i++) {
            Address fakerAddress = faker.address();

            String addressWithIndex = fakerAddress.fullAddress().replaceAll("######", fakerAddress.zipCode());

            String fakeUserData = faker.name().fullName() + ',' + addressWithIndex;

            ErrorsInStringGenerator errorsInStringGenerator = new ErrorsInStringGenerator(fakeUserData, errorProbability);

            String fakeDataWithErrors = errorsInStringGenerator.getStringWithError();

            System.out.println(fakeDataWithErrors);
        }
    }
}
