package com.github.gems;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Java11GemsTest {

    @Test
    void lambdaTypeInference() {
        // Doesn't compile with java 10 (use sourceCompatibility to mock the java version)
        Function<String, String> append = (var string) -> string + " ";
    }

    @Test
    void multilinesString() {
        var multiline = "This\r\nis a\r\nmultiline\r\nstring";

        multiline.lines()
                .map(line -> "// " + line)
                .forEach(System.out::println);
    }

    @Test
    void isBlankCheck() {
        assertTrue("  ".isBlank());
    }
}
