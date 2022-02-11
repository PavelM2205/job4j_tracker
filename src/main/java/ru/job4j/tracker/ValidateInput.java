package ru.job4j.tracker;

public class ValidateInput implements Input {

    private final Output out;
    private final Input input;

    public ValidateInput(Output out, Input input) {
        this.out = out;
        this.input = input;
    }

    @Override
    public String askStr(String question) {
        return input.askStr(question);
    }

    @Override
    public int askInt(String question) {
        boolean inValid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question);
                inValid = false;
            } catch (NumberFormatException nfe) {
                out.println("Please enter validate data again.");
            }
        } while (inValid);
        return value;
    }
}
