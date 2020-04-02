package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import at.fh.hagenberg.aist.jack.general.function.TriConsumer;
import at.fh.hagenberg.aist.jack.persistence.filesystem.CSVReader;
import at.fh.hagenberg.aist.jack.persistence.filesystem.CSVWriter;
import at.fh.hagenberg.aist.seshat.Logger;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Created by Christoph Praschl on 17/01/2020</p>
 * <p>Class for accessing domain classes in CSV files. Allows to read and write .csv files containing domain types</p>
 *
 * @author Christoph Praschl christoph.praschl@fh-hagenberg.at
 */
public class CSVProcessor<T> implements CSVReader<T>, CSVWriter<T> {
    private static Logger logger = Logger.getInstance(CSVProcessor.class);
    protected char separator;
    protected List<String> columnDefinition;
    protected BiFunction<T, List<String>, List<String>> elementToFunc;
    protected BiFunction<List<String>, List<String>, T> columnsToElementFunc;

    /**
     * Creates a CSVPointProcessor with given seperator and coloumn definition
     *
     * @param separator            separator symbol used in csv file
     * @param columnDefinition     column definition of the csv file (can be null for reading if the CSV file contains a header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param elementToColumnFunc  function to convert the elements to a csv line. First parameter == current element; second parameter == columnDefinition; Returns the list of strings representing the columns in the csv file
     * @param columnsToElementFunc function to convert the string columns of a csv line to a element
     */
    protected CSVProcessor(char separator, List<String> columnDefinition, BiFunction<T, List<String>, List<String>> elementToColumnFunc, BiFunction<List<String>, List<String>, T> columnsToElementFunc) {
        this.separator = separator;
        this.columnDefinition = columnDefinition;
        this.elementToFunc = elementToColumnFunc;
        this.columnsToElementFunc = columnsToElementFunc;
    }


    protected CSVProcessor(char separator, List<String> columnDefinition) {
        this(separator, columnDefinition, null, null);
    }

    /**
     * Factory method for creating a CSVProcessor object for writing CSV files;
     *
     * @param separator           separator symbol used in csv file
     * @param columnDefinition    column definition of the csv file (can be null for reading if the CSV file contains a header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param elementToColumnFunc function to convert the elements to a csv line which should be written. First parameter == current element; second parameter == columnDefinition; Returns the list of strings representing the columns in the csv file
     * @param <O>                 element type
     * @return CSV Processor
     */
    public static <O> CSVWriter<O> getWriter(char separator, List<String> columnDefinition,
                                             @NonNull BiFunction<O, List<String>, List<String>> elementToColumnFunc) {
        return getProcessor(separator, columnDefinition, elementToColumnFunc, null);
    }

    /**
     * Factory method for creating a CSVProcessor object for reading CSV files with a fieldconsumer
     *
     * @param separator        separator symbol used in csv file
     * @param columnDefinition column definition of the csv file (can be null for reading if the CSV file contains a header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param elementSupplier  supplies the initial object which will be modified using the given fieldConsumer
     * @param fieldConsumer    consumes the supplied object (created with elementSupplier), the actual column value (second parameter) and the associated column name (third parameter). Should modify the supplied object using the given column
     * @param <O>              element type
     * @return CSV Processor
     */
    public static <O> CSVReader<O> getReader(char separator, List<String> columnDefinition,
                                             @NonNull Supplier<O> elementSupplier,
                                             @NonNull TriConsumer<O, String, String> fieldConsumer) {
        return getProcessor(separator, columnDefinition, null, elementSupplier, fieldConsumer);
    }

    /**
     * Factory method for creating a CSVProcessor object for reading CSV files
     * This method requires a lot of manual handling using the BiFunctions. Use {@link CSVProcessor#getReader(char, List, Supplier, TriConsumer)} for a more convenient way.
     *
     * @param separator            separator symbol used in csv file
     * @param columnDefinition     column definition of the csv file (can be null for reading if the CSV file contains a header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param columnsToElementFunc function to convert the read string columns of a csv line to a element
     * @param <O>                  element type
     * @return CSV Processor
     */
    public static <O> CSVReader<O> getReader(char separator, List<String> columnDefinition,
                                             @NonNull BiFunction<List<String>, List<String>, O> columnsToElementFunc) {
        return new CSVProcessor<>(separator, columnDefinition, null, columnsToElementFunc);
    }

    /**
     * Factory method for creating a CSVProcessor object for writing and reading CSV files
     * This method requires a lot of manual handling using the BiFunctions. Use {@link CSVProcessor#getProcessor(char, List, BiFunction, Supplier, TriConsumer)} for a more convenient way.
     *
     * @param separator            separator symbol used in csv file
     * @param columnDefinition     column definition of the csv file (can be null for reading if the CSV file contains a header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param elementToColumnFunc  function to convert the elements to a csv line which should be written. First parameter == current element; second parameter == columnDefinition; Returns the list of strings representing the columns in the csv file
     * @param columnsToElementFunc function to convert the read string columns of a csv line to a element
     * @param <O>                  element type
     * @return CSV Processor
     */
    public static <O> CSVProcessor<O> getProcessor(char separator, List<String> columnDefinition,
                                                   BiFunction<O, List<String>, List<String>> elementToColumnFunc,
                                                   BiFunction<List<String>, List<String>, O> columnsToElementFunc) {
        if (elementToColumnFunc == null && columnsToElementFunc == null) {
            throw new IllegalArgumentException("At least one of the parameters elementToColumnFunc and columnsToElementFunc must not be null. Otherwise the given processor can neither read nor write csv files");
        }

        return new CSVProcessor<>(separator, columnDefinition, elementToColumnFunc, columnsToElementFunc);
    }

    /**
     * Factory method for creating a CSVProcessor object for writing and reading CSV files with a fieldconsumer
     *
     * @param separator           separator symbol used in csv file
     * @param columnDefinition    column definition of the csv file (can be null for reading if the CSV file contains a header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param elementToColumnFunc function to convert the elements to a csv line which should be written. First parameter == current element; second parameter == columnDefinition; Returns the list of strings representing the columns in the csv file
     * @param elementSupplier     supplies the initial object which will be modified using the given fieldConsumer
     * @param fieldConsumer       consumes the supplied object (created with elementSupplier), the actual column value (second parameter) and the associated column name (third parameter). Should modify the supplied object using the given column
     * @param <O>                 element type
     * @return CSV Processor
     */
    public static <O> CSVProcessor<O> getProcessor(char separator, List<String> columnDefinition,
                                                   BiFunction<O, List<String>, List<String>> elementToColumnFunc,
                                                   Supplier<O> elementSupplier,
                                                   TriConsumer<O, String, String> fieldConsumer) {

        BiFunction<List<String>, List<String>, O> func = (splittedLines, cDefinition) -> {
            O t = elementSupplier.get();
            for (int i = 0; i < cDefinition.size(); i++) {
                fieldConsumer.accept(t, splittedLines.get(i), cDefinition.get(i));
            }
            return t;
        };
        return getProcessor(separator, columnDefinition, elementToColumnFunc, func);
    }

    private static List<String> split(String string, String delim) {
        return Stream.of(string.replace(delim, " " + delim + " ").split(delim))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Method for reading the given csv file and converting the lines to the specific element type using the given {@link BiFunction}
     *
     * @param csvFile                  source file to read
     * @param containsColumnDefinition flag which signals if the file contains a column definition
     * @param useFileColumnDefinition  flag if the column definition in the csv file should be used instead of the definition in the constructor (only used if containsColumndefinition == true)
     * @return the read elements
     */
    @SneakyThrows
    public List<T> read(File csvFile, boolean containsColumnDefinition, boolean useFileColumnDefinition) {
        if (columnsToElementFunc == null) {
            throw new IllegalStateException("lineToElementFunc must not be null");
        }

        List<T> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), StandardCharsets.UTF_8))) {
            String st;
            List<String> fileColumndefinition = new ArrayList<>();
            if (containsColumnDefinition) {
                String s = reader.readLine();
                for (String s1 : split(s, String.valueOf(separator))) {
                    fileColumndefinition.add(s1.trim());
                }

                if (!useFileColumnDefinition) {
                    for (String column : columnDefinition) {
                        if (!fileColumndefinition.contains(column)) {
                            throw new IllegalStateException("CSV File doesnt contain the required column " + column + " in column definition " + fileColumndefinition);
                        }
                    }
                }
            }
            while ((st = reader.readLine()) != null) {
                List<String> splittedLine = split(st, String.valueOf(separator));
                result.add(columnsToElementFunc.apply(splittedLine, useFileColumnDefinition ? fileColumndefinition : columnDefinition));
            }
        }

        return result;
    }

    /**
     * Method for writing the given elements to a csv file
     *
     * @param targetFile            target file where to write the elements
     * @param elements              to be written
     * @param writeColumnDefinition flag which signals if the column definition should be written in the beginning
     * @return true iff elements are written successfully
     */
    public boolean write(File targetFile, List<T> elements, boolean writeColumnDefinition) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(targetFile), StandardCharsets.UTF_8)) {
            if (writeColumnDefinition) {
                writeLine(writer, columnDefinition);
            }

            for (T element : elements) {
                writeLine(writer, elementToFunc.apply(element, columnDefinition));
            }
        } catch (IOException e) {
            logger.error("Could not write file", e);
            return false;
        }
        return true;
    }

    private void writeLine(OutputStreamWriter writer, List<String> columns) throws IOException {
        for (String s : columns) {
            writeColumn(writer, s);
        }
        writer.write(System.lineSeparator());
    }

    private void writeColumn(OutputStreamWriter writer, String s) throws IOException {
        writer.write(s);
        writer.write(separator);
    }
}
