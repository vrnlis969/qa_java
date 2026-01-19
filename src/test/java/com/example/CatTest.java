package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Mock
    private Feline felineMock;

    @Test
    void getSoundShouldReturnMeow() {
        // Arrange - подготовка
        Cat cat = new Cat(felineMock);

        // Act - действие
        String sound = cat.getSound();

        // Assert - проверка
        assertEquals("Мяу", sound);
    }

    @Test
    void getFoodShouldReturnExpectedFood() throws Exception {
        // Arrange - подготовка
        List<String> expectedFood = List.of("Курица", "Говядина");
        when(felineMock.eatMeat()).thenReturn(expectedFood);
        Cat cat = new Cat(felineMock);

        // Act - действие
        List<String> food = cat.getFood();

        // Assert - проверка
        assertEquals(expectedFood, food);
    }

    @Test
    void getFoodShouldCallPredatorEatMeatOnce() throws Exception {
        // Arrange - подготовка
        List<String> expectedFood = List.of("Курица", "Говядина");
        when(felineMock.eatMeat()).thenReturn(expectedFood);
        Cat cat = new Cat(felineMock);

        // Act - действие
        cat.getFood();

        // Assert - проверка
        verify(felineMock, times(1)).eatMeat();
    }

    @Test
    void getFoodShouldThrowExceptionWhenPredatorThrows() throws Exception {
        // Arrange - подготовка
        when(felineMock.eatMeat()).thenThrow(new Exception("Нет еды"));
        Cat cat = new Cat(felineMock);

        // Act - действие
        Exception exception = null;
        try {
            cat.getFood();
        } catch (Exception e) {
            exception = e;
        }

        // Assert - проверка
        assertNotNull(exception);
    }

    @Test
    void getFoodExceptionShouldHaveCorrectMessage() throws Exception {
        // Arrange - подготовка
        when(felineMock.eatMeat()).thenThrow(new Exception("Нет еды"));
        Cat cat = new Cat(felineMock);

        // Act - действие
        Exception exception = null;
        try {
            cat.getFood();
        } catch (Exception e) {
            exception = e;
        }

        // Assert - проверка
        assertEquals("Нет еды", exception.getMessage());
    }

    @Test
    void constructorShouldNotThrowException() {
        // Arrange - подготовка
        // (мок уже создан через аннотацию @Mock)

        // Act - действие
        Cat cat = new Cat(felineMock);

        // Assert - проверка
        assertNotNull(cat);
    }
}