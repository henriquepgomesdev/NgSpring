package org.example.utils;

import jakarta.persistence.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;
import org.reflections.Reflections;

public class LiquibaseYamlGenerator {

    private static final String BASE_PACKAGE = "org.example.casamento.convidado.domain"; // Altere para o pacote das suas entidades
    private static final String AUTHOR = "henrique"; // Autor do changelog

    public static void main(String[] args) {
        generateYaml("changelog.yaml");
    }

    public static void generateYaml(String fileName) {
        Reflections reflections = new Reflections(BASE_PACKAGE);
        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("databaseChangeLog:\n");

            for (Class<?> entity : entities) {
                generateChangeSet(entity, writer);
            }

            System.out.println("Changelog YAML gerado com sucesso em: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateChangeSet(Class<?> entity, FileWriter writer) throws IOException {
        Table tableAnnotation = entity.getAnnotation(Table.class);
        String tableName = (tableAnnotation != null) ? tableAnnotation.name() : entity.getSimpleName().toLowerCase();
        String sequenceName = "seq_" + tableName;

        writer.write("  - changeSet:\n");
        writer.write("      id: create-table-" + tableName + "\n");
        writer.write("      author: " + AUTHOR + "\n");
        writer.write("      preConditions:\n");
        writer.write("        - onFail: MARK_RAN\n");
        writer.write("          not:\n");
        writer.write("            tableExists:\n");
        writer.write("              tableName: " + tableName + "\n");
        writer.write("      changes:\n");
        writer.write("        - createTable:\n");
        writer.write("            tableName: " + tableName + "\n");
        writer.write("            columns:\n");

        for (Field field : entity.getDeclaredFields()) {
            processField(field, writer);
        }

        if (hasSequence(entity)) {
            writer.write("        - createSequence:\n");
            writer.write("            sequenceName: " + sequenceName + "\n");
            writer.write("            incrementBy: 1\n");
            writer.write("            startValue: 1\n");
        }

        writer.write("\n");
    }

    private static void processField(Field field, FileWriter writer) throws IOException {
        Column columnAnnotation = field.getAnnotation(Column.class);
        Id idAnnotation = field.getAnnotation(Id.class);
        GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);

        String columnName = (columnAnnotation != null && !columnAnnotation.name().isEmpty()) ? columnAnnotation.name() : field.getName();
        String columnType = mapType(field.getType());

        writer.write("              - column:\n");
        if (idAnnotation != null) {
            writer.write("                  autoIncrement: true\n");
            writer.write("                  constraints:\n");
            writer.write("                    nullable: false\n");
            writer.write("                    primaryKey: true\n");
            writer.write("                    primaryKeyName: " + getTableName(field) + "_pkey\n");
        }
        writer.write("                  name: " + columnName + "\n");
        writer.write("                  type: " + columnType + "\n");
    }

    private static String getTableName(Field field) {
        Class<?> entityClass = field.getDeclaringClass();
        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        return (tableAnnotation != null && !tableAnnotation.name().isEmpty())
                ? tableAnnotation.name()
                : entityClass.getSimpleName().toLowerCase();
    }

    private static String mapType(Class<?> type) {
        if (type.equals(Long.class) || type.equals(Integer.class)) return "BIGINT";
        if (type.equals(String.class)) return "VARCHAR(255)";
        if (type.equals(java.math.BigDecimal.class)) return "DECIMAL(19,4)";
        if (type.equals(java.time.LocalDate.class)) return "DATE";
        if (type.equals(java.time.LocalDateTime.class)) return "TIMESTAMP";
        return "VARCHAR(255)"; // Default
    }

    private static boolean hasSequence(Class<?> entity) {
        for (Field field : entity.getDeclaredFields()) {
            if (field.isAnnotationPresent(GeneratedValue.class) && field.isAnnotationPresent(SequenceGenerator.class)) {
                return true;
            }
        }
        return false;
    }
}
