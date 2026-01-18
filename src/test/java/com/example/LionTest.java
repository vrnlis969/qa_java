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

@ExtendWith(MockitoExtension.class)  // Включаем поддержку Mockito
class LionTest {

    @Mock
    private FelineInterface felineMock;  // Создаем мок интерфейса FelineActions

    // Параметризованный тест для конструктора Lion
    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void constructor_ValidSex_ShouldSetHasManeCorrectly(String sex, boolean expectedHasMane) throws Exception {
        // Act
        Lion lion = new Lion(sex, felineMock);

        // Assert
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    // Параметризованный тест для невалидных значений пола
    @ParameterizedTest
    @CsvSource({
            "Мужчина",
            "Женщина",
            "Unknown",
            "' '"
    })
    void constructor_InvalidSex_ShouldThrowException(String invalidSex) {
        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion(invalidSex, felineMock);
        });

        assertTrue(exception.getMessage().contains("допустимые значения пола"));
    }

    @Test
    void getKittens_ShouldCallFelineGetKittens() throws Exception {
        // Arrange
        when(felineMock.getKittens()).thenReturn(3);  // Настраиваем мок
        Lion lion = new Lion("Самка", felineMock);

        // Act
        int kittens = lion.getKittens();

        // Assert
        assertEquals(3, kittens);
        verify(felineMock, times(1)).getKittens();  // Проверяем вызов метода
    }

    @Test
    void getFood_ShouldCallFelineEatMeat() throws Exception {
        // Arrange
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(felineMock.eatMeat()).thenReturn(expectedFood);
        Lion lion = new Lion("Самка", felineMock);

        // Act
        List<String> food = lion.getFood();

        // Assert
        assertEquals(expectedFood, food);
        verify(felineMock, times(1)).eatMeat();
    }

    @Test
    void getFood_WhenFelineThrowsException_ShouldPropagateException() throws Exception {
        // Arrange
        when(felineMock.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));
        Lion lion = new Lion("Самец", felineMock);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, lion::getFood);
        assertEquals("Ошибка получения еды", exception.getMessage());
    }
    @Test
    void doesHaveMane_ShouldReturnConsistentValue() throws Exception {
        Lion lion = new Lion("Самец", felineMock);
        // Дважды вызываем - должно возвращать одинаковое значение
        assertTrue(lion.doesHaveMane());
        assertTrue(lion.doesHaveMane());
    }
}