package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CountFunctionTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        CountFunction function = new CountFunction();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertEquals(expected, result);
    }

    @Test
    public void whenSquareFunctionThenSquareResult() {
        CountFunction function = new CountFunction();
        List<Double> result = function.diapason(5, 8, x -> Math.pow(x, 2) + 3);
        List<Double> expected = Arrays.asList(28D, 39D, 52D);
        assertEquals(expected, result);
    }

    @Test
    public void whenDegreeFunctionThenDegreeResult() {
        CountFunction function = new CountFunction();
        List<Double> result = function.diapason(5, 8, x -> Math.pow(2, x));
        List<Double> expected = Arrays.asList(32D, 64D, 128D);
        assertEquals(expected, result);
    }
}