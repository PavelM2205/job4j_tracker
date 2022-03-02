package ru.job4j.map;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class College {
    private final Map<Student, Set<Subject>> students;

    public College(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Optional<Student> findByAccount(String account) {
        Optional<Student> result = Optional.empty();
        for (Student s : students.keySet()) {
            if (s.getAccount().equals(account)) {
                result = Optional.of(s);
                break;
            }
        }
        return result;
    }

    public Optional<Subject> findBySubjectName(String account, String name) {
        Optional<Subject> result = Optional.empty();
        Optional<Student> s = findByAccount(account);
        if (s.isPresent()) {
            for (Subject sub : students.get(s.get())) {
                if (name.equals(sub.getName())) {
                    result = Optional.of(sub);
                    break;
                }
            }
        }
        return result;
    }
}
