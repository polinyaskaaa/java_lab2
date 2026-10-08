package ua.kpi.comsys.collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {

    private final WordCounter wc = new WordCounter();

    @Test
    @DisplayName("countWords: null throws exception")
    void testCountWordsNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> wc.countWords(null));
        assertEquals("textLines cannot be null", ex.getMessage());
    }

    @Test
    @DisplayName("countWords: empty list")
    void testCountWordsEmpty() {
        Map<String, Integer> res = wc.countWords(new ArrayList<>());
        assertTrue(res.isEmpty());
    }

    @Test
    @DisplayName("countWords: simple text")
    void testCountWordsSimple() {
        List<String> text = List.of("i like java", "java is cool");
        Map<String, Integer> res = wc.countWords(text);

        assertEquals(5, res.size());
        assertEquals(2, res.get("java"));
        assertEquals(1, res.get("i"));
        assertEquals(1, res.get("like"));
        assertEquals(1, res.get("cool"));
    }

    @Test
    @DisplayName("countWords: ignores case")
    void testCountWordsCase() {
        Map<String, Integer> res = wc.countWords(List.of("Test test TEST"));

        assertEquals(1, res.size());
        assertEquals(3, res.get("test"));
    }

    @Test
    @DisplayName("countWords: punctuation and spaces")
    void testCountWordsPunctuation() {
        Map<String, Integer> res = wc.countWords(List.of("hi, how are you?", "hi!  fine\tthanks"));

        assertEquals(2, res.get("hi"));
        assertEquals(1, res.get("you"));
        assertEquals(1, res.get("fine"));
        assertEquals(1, res.get("thanks"));
        assertEquals(6, res.size());
    }

    @Test
    @DisplayName("countWords: digits are part of word")
    void testCountWordsDigits() {
        Map<String, Integer> res = wc.countWords(List.of("lab2 Lab2 lab3 2025"));

        assertEquals(2, res.get("lab2"));
        assertEquals(1, res.get("lab3"));
        assertEquals(1, res.get("2025"));
    }

    @Test
    @DisplayName("countWords: empty lines and null in list")
    void testCountWordsEmptyLines() {
        List<String> text = new ArrayList<>();
        text.add("");
        text.add("   ");
        text.add("???");
        text.add(null);
        text.add("test");

        Map<String, Integer> res = wc.countWords(text);

        assertEquals(1, res.size());
        assertEquals(1, res.get("test"));
    }

    @Test
    @DisplayName("getUniqueWords: null throws exception")
    void testUniqueWordsNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> wc.getUniqueWords(null));
        assertEquals("textLines cannot be null", ex.getMessage());
    }

    @Test
    @DisplayName("getUniqueWords: empty list")
    void testUniqueWordsEmpty() {
        assertTrue(wc.getUniqueWords(new ArrayList<>()).isEmpty());
    }

    @Test
    @DisplayName("getUniqueWords: no duplicates, lower case")
    void testUniqueWords() {
        Set<String> res = wc.getUniqueWords(List.of("one two three", "Two three FOUR"));

        assertEquals(4, res.size());
        assertTrue(res.contains("one"));
        assertTrue(res.contains("two"));
        assertTrue(res.contains("four"));
        assertFalse(res.contains("FOUR"));
    }

    @Test
    @DisplayName("getUniqueWords: empty lines and null in list")
    void testUniqueWordsEmptyLines() {
        List<String> text = new ArrayList<>();
        text.add(null);
        text.add("...");
        text.add("Java java");

        Set<String> res = wc.getUniqueWords(text);

        assertEquals(Set.of("java"), res);
    }

    @Test
    @DisplayName("both methods return same words")
    void testSameWords() {
        List<String> text = List.of("java is cool, java is fun", "Java 21");

        assertEquals(wc.countWords(text).keySet(), wc.getUniqueWords(text));
    }
}
