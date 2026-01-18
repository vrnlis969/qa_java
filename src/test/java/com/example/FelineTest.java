package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    private final Feline feline = new Feline();

    @Test
    void eatMeat_ShouldReturnMeatList() throws Exception {
        // Act
        List<String> food = feline.eatMeat();

        // Assert
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);
    }

    @Test
    void getFamily_ShouldReturnFelidae() {
        // Act & Assert
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    void getKittens_NoArguments_ShouldReturnOne() {
        // Act & Assert
        assertEquals(1, feline.getKittens());
    }

    // Параметризованный тест для разного количества котят
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 5, 10})
    void getKittens_WithArgument_ShouldReturnSameNumber(int kittensCount) {
        // Act & Assert
        assertEquals(kittensCount, feline.getKittens(kittensCount));
    }
    @Test
    void eatMeat_ShouldNotReturnEmptyList() throws Exception {
        List<String> food = feline.eatMeat();
        assertFalse(food.isEmpty());
    }

}