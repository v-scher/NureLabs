package com.teamdev.arseniuk;

import java.util.HashMap;
import java.util.Set;

import static com.teamdev.arseniuk.Operation.Associativity.LEFT;
import static com.teamdev.arseniuk.Operation.Associativity.RIGHT;

public enum Operation {

    ADDITION("+", 2, LEFT),

    SUBTRACTION("-", 2, LEFT),

    MULTIPLICATION("*", 3, LEFT),

    DIVISION("/", 3, LEFT),

    INVOLUTION("^", 4, RIGHT);


    enum Associativity {
        LEFT, RIGHT
    }


    private String value;
    private int priority;
    private Associativity associativity;

    private static final HashMap<String, Operation> lookup = new HashMap<>();

    static {
        for (Operation operation : Operation.values())
            lookup.put(operation.getValue(), operation);
    }

    Operation(String value, int priority, Associativity associativity) {
        this.value = value;
        this.priority = priority;
        this.associativity = associativity;
    }

    public int getPriority() {
        return priority;
    }

    public String getValue() {
        return value;
    }

    public Associativity getAssociativity() {
        return associativity;
    }

    public static Operation get(String symbol) {
        return lookup.get(symbol);
    }

    public static Set<String> getPresentations() {
        return lookup.keySet();
    }
}
