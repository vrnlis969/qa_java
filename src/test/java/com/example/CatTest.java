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
    private Feline felineMock;  // Мокаем Feline, который реализует Predator

    @Test
    void getSound_ShouldReturnMeow() {
        // Arrange
        Cat cat = new Cat(felineMock);

        // Act & Assert
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void getFood_ShouldCallPredatorEatMeat() throws Exception {
        // Arrange
        List<String> expectedFood = List.of("Курица", "Говядина");
        when(felineMock.eatMeat()).thenReturn(expectedFood);
        Cat cat = new Cat(felineMock);

        // Act
        List<String> food = cat.getFood();

        // Assert
        assertEquals(expectedFood, food);
        verify(felineMock, times(1)).eatMeat();
    }

    @Test
    void getFood_WhenPredatorThrowsException_ShouldPropagateException() throws Exception {
        // Arrange
        when(felineMock.eatMeat()).thenThrow(new Exception("Нет еды"));
        Cat cat = new Cat(felineMock);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, cat::getFood);
        assertEquals("Нет еды", exception.getMessage());
    }

    @Test
    void constructor_ShouldAcceptFelineAsPredator() {
        // Act (просто создание объекта без исключения)
        Cat cat = new Cat(felineMock);

        // Assert
        assertNotNull(cat);
        // Проверяем, что можем вызвать метод без ошибок
        assertDoesNotThrow(cat::getSound);
    }
}