package br.edu.fatec.sjc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StackFullExceptionTest {

    @Test
    void shouldBeAnException() {
        StackFullException exception = new StackFullException();

        assertTrue(exception instanceof Exception);
    }

    @Test
    void shouldBeThrowableAndCatchable() {
        assertThrows(StackFullException.class, () -> {
            throw new StackFullException();
        });
    }
}
