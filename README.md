# Лабораторна робота №2 — Gradle та Java Collections Framework

**Дисципліна:** Технології розробки на платформі Java 
**Тема:** Збірка проєкту за допомогою Gradle та робота з Java Collections Framework
**Виконала:** Студентка групи ІС-41 Малик Поліна

## Розрахунок варіанта

Для обчислення варіанту було використано порядковий номер у списку групи: **N = 11**.

```
V = N mod 3 = 11 mod 3 = 2
```

Таким чином отримали **Варіант V = 2** — частотний аналізатор тексту (`WordCounter`).

## Опис проєкту

Клас `ua.kpi.comsys.collections.WordCounter` обробляє список рядків тексту:

| Метод | Результат |
|-------|-----------|
| `Map<String, Integer> countWords(List<String> textLines)` | слово → кількість входжень |
| `Set<String> getUniqueWords(List<String> textLines)` | множина унікальних слів |

Правила обробки:

- словом вважається послідовність буквено-цифрових символів латиниці (`[A-Za-z0-9]+`), усе інше — роздільники;
- усі слова приводяться до нижнього регістру;
- порожні слова ігноруються (так само як порожні рядки та елементи `null` усередині списку);
- якщо `textLines == null`, обидва методи кидають `IllegalArgumentException("textLines cannot be null")`.

Використано лише стандартні колекції `java.util.*` (`HashMap`, `HashSet`, `ArrayList`).

### Структура

```
gradle/libs.versions.toml      Version Catalog (JUnit 5)
build.gradle.kts               конфігурація збірки
settings.gradle.kts
gradlew, gradlew.bat, gradle/wrapper/   Gradle Wrapper 8.14.3
src/main/java/ua/kpi/comsys/collections/WordCounter.java
src/test/java/ua/kpi/comsys/collections/WordCounterTest.java
```

## Запуск тестів

Потрібен JDK 21+ (Gradle встановлювати не треба — використовується wrapper).

```bash
./gradlew build     # збірка + тести
./gradlew test      # лише тести
```

У Windows: `gradlew.bat test`. HTML-звіт тестів: `build/reports/tests/test/index.html`.
