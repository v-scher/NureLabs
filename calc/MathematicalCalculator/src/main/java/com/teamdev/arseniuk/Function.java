package com.teamdev.arseniuk;

import java.util.HashMap;
import java.util.Set;

public enum Function {

    SQRT("sqrt"),
    MIN("min"),
    MAX("max"),
    SUM("sum");

    private final String presentation;

    Function(String presentation) {
        this.presentation = presentation;
    }

    private static final HashMap<String, Function> lookup = new HashMap<>();

    static {
        for (Function function : Function.values())
            lookup.put(function.getPresentation(), function);
    }

    public String getPresentation() {
        return presentation;
    }

    public static Function get(String presentation) {
        return lookup.get(presentation);
    }

    public static Set<String> getPresentations() {
        return lookup.keySet();
    }


}
