package br.edu.fatec.sjc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculableStrategyTest {

    @Test
    void lambdaImplementationShouldCalculateAndReturnValue() {
        CalculableStrategy<Double> doubleValue = value -> value * 2;

        Double result = doubleValue.calculateValue(21.0);

        assertEquals(42.0, result);
    }

    @Test
    void anonymousClassImplementationShouldCalculateAndReturnValue() {
        CalculableStrategy<Integer> increment = new CalculableStrategy<Integer>() {
            @Override
            public Integer calculateValue(Integer value) {
                return value + 1;
            }
        };

        Integer result = increment.calculateValue(9);

        assertEquals(10, result);
    }

    @Test
    void implementationShouldPropagateNullPointerExceptionWhenValueIsNull() {
        CalculableStrategy<Double> rejectNull = value -> {
            if (value == null) {
                throw new NullPointerException();
            }
            return value;
        };

        assertThrows(NullPointerException.class, () -> rejectNull.calculateValue(null));
    }
}
