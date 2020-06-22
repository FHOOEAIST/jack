package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>TODO insert documentation for this class</p>
 *
 * @author Rainer Meindl, rainer.meindl@fh-hagenberg.at, 22.06.2020
 */
public class AdvancedCsvProcessor<T> extends ReflectionCSVProcessor<T> {
    /**
     * Constructor of a ReflectionCSVProcessor for domain types with simple-typed properties only (int, double, float, long, boolean, char, Integer, Float, Double, Long, String, Character, Boolean)
     *
     * @param separator        separator used in the csv file
     * @param columnDefinition columnDefinition of the csv file (can be null for reading if the CSV file contains a header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param clazz            clazz of domain type
     */
    public AdvancedCsvProcessor(char separator, List<String> columnDefinition, Class<T> clazz) {
        super(separator, columnDefinition, clazz);
    }

    @Override
    protected List<String> normalizeColumnDefinition(List<String> columnHeader) {
        return columnHeader.stream()
                .map(String::strip)
                .map(s -> Arrays.stream(s.split(" "))
                        .reduce(String::concat))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
