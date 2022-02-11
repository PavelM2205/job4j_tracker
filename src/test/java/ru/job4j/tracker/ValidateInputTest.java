package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {"one", "1"});
        ValidateInput input = new ValidateInput(out, in);
        int select = input.askInt("Enter menu:");
        assertThat(select, is(1));
    }

    @Test
    public void whenManyValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {"1", "2", "3"});
        ValidateInput input = new ValidateInput(out, in);
        int select = input.askInt("Enter menu:");
        assertThat(select, is(1));
    }

    @Test
    public void whenNegativeNumberInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {"-1"});
        ValidateInput input = new ValidateInput(out, in);
        int select = input.askInt("Enter menu:");
        assertThat(select, is(-1));
    }
}