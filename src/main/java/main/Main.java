package main;

import generators.FakeDataGenerator;

public class Main {
    public static void main(String[] args) {
        long time = System.nanoTime();

        FakeDataGenerator fakeDataGenerator = new FakeDataGenerator(args);
        fakeDataGenerator.generateFakeData();

        time = System.nanoTime() - time;
        System.out.printf("Elapsed %,9.3f ms\n", time/1_000_000.0);
    }
}