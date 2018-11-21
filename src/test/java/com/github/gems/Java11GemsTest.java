package com.github.gems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static java.util.function.Predicate.not;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ConstantConditions")
class Java11GemsTest {

    @Test
    void lambdaTypeInference() {
        // Doesn't compile with java 10 (use sourceCompatibility to mock the java version)
        Function<String, String> abc = (var string) -> string + " ";
    }

    @Test
    void multilineString() {
        var multiline = "This\r\nis a\r\nmultiline\r\nstring";

        multiline.lines()
                .map(l -> "@ : " + l)
                .forEach(System.out::println);
    }

    @Test
    void checkString() {
        assertTrue("  ".isBlank());

        assertFalse("  ".isEmpty());
    }

    @Test
    void repeatString() {
        assertEquals("azerty", "azerty".repeat(1));

        assertEquals("azertyazerty", "azerty".repeat(2));
    }

    @Test
    void createPath() {
        // before
        Paths.get("");

        // now
        Path.of("");
    }

    @Test
    void readFile() throws IOException {
        Files.readString(Path.of(""));

        Files.writeString(Path.of(""), "");
    }

    @Test
    void collectionToArray() {
        List<String> list = List.of("blabla");

        // before
        Object[] objects = list.toArray();
        String[] strings_0 = list.toArray(new String[0]);
        String[] strings_size = list.toArray(new String[list.size()]);

        // now
        strings_size = list.toArray(String[]::new);
    }

    @Test
    void optional() {
        Optional<String> optional = Optional.of("value");

        // before
        assertFalse(!optional.isPresent());

        // now
        assertFalse(optional.isEmpty());
    }

    @Test
    void negatePredicate() {
        // before
        Stream.of("a", "b", "", "c")
                // statically import `Predicate.not`
                .filter(s -> !s.isBlank())
                .forEach(System.out::println);

        // now
        Stream.of("a", "b", "", "c")
                // statically import `Predicate.not`
                .filter(not(String::isBlank))
                .forEach(System.out::println);
    }
}
