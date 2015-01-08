package com.teamdev.arseniuk;

import com.teamdev.arseniuk.impl.ExpressionReader;

public interface CalculationParser {
    public Token parse(ExpressionReader reader);
}
