package ru.job4j.lombok;

import lombok.*;

import java.util.List;

@Builder(builderMethodName = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
public class Permission {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    @Singular("rule")
    private List<String> rules;
}
