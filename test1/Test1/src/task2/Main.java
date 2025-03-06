package task2;
// Задание 2: Обработка текста.
// Реализовать программу, которая:
// 1. Принимает на вход английский текст произвольной длины.
// 2. Обрабатывает текст, убирая знаки препинания и приводя все слова к нижнему регистру.
// 3. Выводит список всех уникальных слов, сгруппированных по первой букве.
// 4. Слова должны быть отсортированы по алфавиту внутри каждой группы.

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Ввод английского текста произвольной длины.
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n{2,}");
        System.out.println("Enter your text:");
        String text = scanner.next("[A-Za-z,.!?;:—\"'()\\[\\]\\s]+");
        scanner.close();

        // Обработка текста: удаление знаков препинания и приведение всех слов к нижнему регистру.
        String textProcessed = new StringBuilder(text.replaceAll("\n", " ")
                .replaceAll("[,.!?;:—\"'()\\[\\]]", "").toLowerCase()).toString();
        System.out.print("Processed text:\n" + textProcessed + "\n\n");

        // Получение списка всех уникальных слов.
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(textProcessed.split(" ")));
        System.out.println("All the unique words in the entered text:\n" + String.join(" | ", uniqueWords) + "\n");

        // Сортировка всех уникальных слов и группировка по первой букве.
        Map<String, List<String>> groupedUniqueWords = new TreeMap<>(uniqueWords.stream().toList().stream().sorted()
                .collect(Collectors.groupingBy(string -> string.substring(0, 1).toUpperCase())));
        // Вывод результата сортировки и группировки.
        System.out.println("All the unique words sorted and grouped by the first letter:");
        groupedUniqueWords.forEach((k, v) -> System.out.printf("%s: %s\n", k, String.join(" ", v)));
    }
}