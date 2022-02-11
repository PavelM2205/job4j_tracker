package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {

    @Override
    public int askInt(String question) {
        boolean inValid = true;
        int value = -1;
        do {
            try {
                value = super.askInt(question);
                inValid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (inValid);
        return value;
    }
}
