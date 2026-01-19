package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    private final Feline feline = new Feline();

    @Test
    void eatMeatShouldReturnMeatList() throws Exception {
        // Arrange - подготовка (в данном случае объект уже создан)

        // Act - действие: вызываем метод eatMeat()
        List<String> food = feline.eatMeat();

        // Assert - проверка: сравниваем результат с ожидаемым
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);
    }

    @Test
    void getFamilyShouldReturnFelidae() {
        // Arrange - подготовка (объект уже создан)

        // Act - действие: вызываем метод getFamily()
        String family = feline.getFamily();

        // Assert - проверка
        assertEquals("Кошачьи", family);
    }

    @Test
    void getKittensNoArgumentsShouldReturnOne() {
        // Arrange - подготовка

        // Act - действие: вызываем метод getKittens() без аргументов
        int kittens = feline.getKittens();

        // Assert - проверка: должно вернуться 1
        assertEquals(1, kittens);
    }

    @Test
    void eatMeatShouldNotReturnEmptyList() throws Exception {
        // Arrange - подготовка

        // Act - действие
        List<String> food = feline.eatMeat();

        // Assert - проверка: список не должен быть пустым
        assertFalse(food.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 5, 10})
    void getKittensWithArgumentShouldReturnSameNumber(int kittensCount) {
        // Arrange - подготовка

        // Act - действие: вызываем метод getKittens() с аргументом
        int result = feline.getKittens(kittensCount);

        // Assert - проверка: результат должен быть равен переданному числу
        assertEquals(kittensCount, result);
    }
}