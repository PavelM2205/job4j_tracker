package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class JobDescByPriorityTest {

    @Test
    public void whenDiscByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("A", 2),
                new Job("C", 4),
                new Job("B", 1)
        );
        List<Job> expected = Arrays.asList(
                new Job("C", 4),
                new Job("A", 2),
                new Job("B", 1)
        );
        Collections.sort(jobs, new JobDescByPriority());
        assertEquals(expected, jobs);
    }
}