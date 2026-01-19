package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    private Feline felineMock;

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void constructorValidSexShouldSetHasManeCorrectly(String sex, boolean expectedHasMane) throws Exception {
        // Arrange - подготовка
        // (мок уже создан)

        // Act - действие
        Lion lion = new Lion(sex, felineMock);

        // Assert - проверка
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @ParameterizedTest
    @CsvSource({
            "Мужчина",
            "Женщина",
            "Unknown",
            "' '"
    })
    void constructorInvalidSexShouldThrowException(String invalidSex) {
        // Arrange - подготовка
        // (мок уже создан)

        // Act - действие
        Exception exception = null;
        try {
            new Lion(invalidSex, felineMock);
        } catch (Exception e) {
            exception = e;
        }

        // Assert - проверка
        assertNotNull(exception);
    }

    @ParameterizedTest
    @CsvSource({
            "Мужчина",
            "Женщина",
            "Unknown",
            "' '"
    })
    void constructorInvalidSexShouldHaveCorrectMessage(String invalidSex) {
        // Arrange - подготовка
        // (мок уже создан)

        // Act - действие
        Exception exception = null;
        try {
            new Lion(invalidSex, felineMock);
        } catch (Exception e) {
            exception = e;
        }

        // Assert - проверка
        assertTrue(exception.getMessage().contains("допустимые значения пола"));
    }

    @Test
    void getKittensShouldReturnExpectedValue() throws Exception {
        // Arrange - подготовка
        when(felineMock.getKittens()).thenReturn(3);
        Lion lion = new Lion("Самка", felineMock);

        // Act - действие
        int kittens = lion.getKittens();

        // Assert - проверка
        assertEquals(3, kittens);
    }

    @Test
    void getKittensShouldCallFelineGetKittensOnce() throws Exception {
        // Arrange - подготовка
        when(felineMock.getKittens()).thenReturn(3);
        Lion lion = new Lion("Самка", felineMock);

        // Act - действие
        lion.getKittens();

        // Assert - проверка
        verify(felineMock, times(1)).getKittens();
    }

    @Test
    void getFoodShouldReturnExpectedFood() throws Exception {
        // Arrange - подготовка
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(felineMock.eatMeat()).thenReturn(expectedFood);
        Lion lion = new Lion("Самка", felineMock);

        // Act - действие
        List<String> food = lion.getFood();

        // Assert - проверка
        assertEquals(expectedFood, food);
    }

    @Test
    void getFoodShouldCallFelineEatMeatOnce() throws Exception {
        // Arrange - подготовка
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(felineMock.eatMeat()).thenReturn(expectedFood);
        Lion lion = new Lion("Самка", felineMock);

        // Act - действие
        lion.getFood();

        // Assert - проверка
        verify(felineMock, times(1)).eatMeat();
    }

    @Test
    void getFoodShouldThrowExceptionWhenFelineThrows() throws Exception {
        // Arrange - подготовка
        when(felineMock.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));
        Lion lion = new Lion("Самец", felineMock);

        // Act - действие
        Exception exception = null;
        try {
            lion.getFood();
        } catch (Exception e) {
            exception = e;
        }

        // Assert - проверка
        assertNotNull(exception);
    }

    @Test
    void getFoodExceptionShouldHaveCorrectMessage() throws Exception {
        // Arrange - подготовка
        when(felineMock.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));
        Lion lion = new Lion("Самец", felineMock);

        // Act - действие
        Exception exception = null;
        try {
            lion.getFood();
        } catch (Exception e) {
            exception = e;
        }

        // Assert - проверка
        assertEquals("Ошибка получения еды", exception.getMessage());
    }

    @Test
    void doesHaveManeShouldReturnTrueForMale() throws Exception {
        // Arrange - подготовка
        Lion lion = new Lion("Самец", felineMock);

        // Act - действие
        boolean hasMane = lion.doesHaveMane();

        // Assert - проверка
        assertTrue(hasMane);
    }

    @Test
    void doesHaveManeShouldReturnFalseForFemale() throws Exception {
        // Arrange - подготовка
        Lion lion = new Lion("Самка", felineMock);

        // Act - действие
        boolean hasMane = lion.doesHaveMane();

        // Assert - проверка
        assertFalse(hasMane);
    }
}