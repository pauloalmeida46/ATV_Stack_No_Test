package br.edu.fatec.sjc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomStackTest {

    private static final int LIMIT = 3;

    private CustomStack<Double> cut;

    @BeforeEach
    void setUp() {
        cut = new CustomStack<>(LIMIT, value -> value);
    }

    @Test
    void shouldStartEmptyWithZeroSize() {
        assertTrue(cut.isEmpty());
        assertEquals(0, cut.size());
    }

    @Test
    void pushShouldAddElementToTheStack() throws StackFullException {
        cut.push(10.0);

        assertFalse(cut.isEmpty());
        assertEquals(1, cut.size());
        assertEquals(10.0, cut.top());
    }

    @Test
    void pushShouldStoreTheValueReturnedByTheCalculableStrategy() throws StackFullException {
        CalculableStrategy<Double> calculableStrategy = mock(CalculableStrategy.class);
        when(calculableStrategy.calculateValue(5.0)).thenReturn(50.0);
        CustomStack<Double> stackWithStrategy = new CustomStack<>(LIMIT, calculableStrategy);

        stackWithStrategy.push(5.0);

        assertEquals(50.0, stackWithStrategy.top());
        verify(calculableStrategy).calculateValue(5.0);
    }

    @Test
    void pushShouldThrowStackFullExceptionWhenLimitIsReached() throws StackFullException {
        for (int i = 0; i < LIMIT; i++) {
            cut.push((double) i);
        }

        assertThrows(StackFullException.class, () -> cut.push(99.0));
        assertEquals(LIMIT, cut.size());
    }

    @Test
    void pushShouldAllowPushingAgainAfterAPop() throws StackFullException, StackEmptyException {
        for (int i = 0; i < LIMIT; i++) {
            cut.push((double) i);
        }
        cut.pop();

        cut.push(99.0);

        assertEquals(LIMIT, cut.size());
        assertEquals(99.0, cut.top());
    }

    @Test
    void popShouldRemoveAndReturnTheLastPushedElement() throws StackFullException, StackEmptyException {
        cut.push(1.0);
        cut.push(2.0);

        Double popped = cut.pop();

        assertEquals(2.0, popped);
        assertEquals(1, cut.size());
        assertEquals(1.0, cut.top());
    }

    @Test
    void popShouldThrowStackEmptyExceptionWhenStackIsEmpty() {
        assertThrows(StackEmptyException.class, () -> cut.pop());
    }

    @Test
    void popShouldThrowStackEmptyExceptionAfterAllElementsWerePopped() throws StackFullException, StackEmptyException {
        cut.push(1.0);
        cut.pop();

        assertThrows(StackEmptyException.class, () -> cut.pop());
    }

    @Test
    void isEmptyShouldReturnTrueWhenThereAreNoElements() {
        assertTrue(cut.isEmpty());
    }

    @Test
    void isEmptyShouldReturnFalseWhenThereAreElements() throws StackFullException {
        cut.push(1.0);

        assertFalse(cut.isEmpty());
    }

    @Test
    void topShouldReturnTheLastElementWithoutRemovingIt() throws StackFullException {
        cut.push(1.0);
        cut.push(2.0);

        assertEquals(2.0, cut.top());
        assertEquals(2, cut.size());
    }

    @Test
    void sizeShouldReflectTheNumberOfPushesAndPops() throws StackFullException, StackEmptyException {
        assertEquals(0, cut.size());

        cut.push(1.0);
        cut.push(2.0);
        assertEquals(2, cut.size());

        cut.pop();
        assertEquals(1, cut.size());
    }
}
