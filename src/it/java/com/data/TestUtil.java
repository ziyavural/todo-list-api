package com.data;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtil {

    public static String readFile(String templateFileName) throws IOException {
        final ClassPathResource classPathResource = new ClassPathResource(templateFileName);
        return new String(Files.readAllBytes(Paths.get(classPathResource.getURI())));
    }
}
