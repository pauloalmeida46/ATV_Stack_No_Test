package br.edu.fatec.sjc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StackEmptyExceptionTest {

    @Test
    void shouldBeAnException() {
        StackEmptyException exception = new StackEmptyException();

        assertTrue(exception instanceof Exception);
    }

    @Test
    void shouldBeThrowableAndCatchable() {
        assertThrows(StackEmptyException.class, () -> {
            throw new StackEmptyException();
        });
    }
}
