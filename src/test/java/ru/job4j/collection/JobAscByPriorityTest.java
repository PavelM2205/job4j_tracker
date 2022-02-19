package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class JobAscByPriorityTest {

    @Test
    public void compare() {
        List<Job> jobs = Arrays.asList(
                new Job("A", 2),
                new Job("C", 4),
                new Job("B", 1)
        );
        List<Job> expected = Arrays.asList(
                new Job("B", 1),
                new Job("A", 2),
                new Job("C", 4)
        );
        Collections.sort(jobs, new JobAscByPriority());
        assertEquals(expected, jobs);
    }
}