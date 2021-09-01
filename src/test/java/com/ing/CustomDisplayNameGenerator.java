package com.ing;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

class CustomDisplayNameGenerator {
    static class ReplaceCamelCase extends DisplayNameGenerator.Standard {

        public String generateDisplayNameForClass(Class<?> testClass) {
            return super.generateDisplayNameForClass(testClass);
        }

        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return super.generateDisplayNameForNestedClass(nestedClass);
        }

        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return this.convertCamelCaseIntoHumanReadable(testMethod.getName());
        }

        private String convertCamelCaseIntoHumanReadable(String name) {
            return String.join(" ",
                    name.split("(?<=[A-Z])(?=[A-Z][a-z])|(?<=[^A-Z])(?=[A-Z])|(?<=[A-Za-z])(?=[^A-Za-z])"))
                    .toLowerCase()
                    .replace("given", "GIVEN")
                    .replace("when", "WHEN")
                    .replace("then", "THEN");
        }
    }
}
