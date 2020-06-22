package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import at.fh.hagenberg.aist.jack.string.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>TODO insert documentation for this class</p>
 *
 * @author Rainer Meindl, rainer.meindl@fh-hagenberg.at, 22.06.2020
 */
public class AdvancedCsvProcessor<T> extends ReflectionCSVProcessor<T> {

    private AdvancedCsvProcessorConfig config;

    /**
     * Constructor of a ReflectionCSVProcessor for domain types with simple-typed properties only (int, double, float, long, boolean, char, Integer, Float, Double, Long, String, Character, Boolean)
     *
     * @param separator          separator used in the csv file
     * @param columnDefinition   columnDefinition of the csv file (can be null for reading if the CSV file contains a header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param clazz              clazz of domain type
     * @param csvProcessorConfig
     */
    public AdvancedCsvProcessor(char separator, List<String> columnDefinition, Class<T> clazz,
                                AdvancedCsvProcessorConfig csvProcessorConfig) {
        this(separator, columnDefinition, clazz);
        this.config = csvProcessorConfig;
    }

    public AdvancedCsvProcessor(char separator, List<String> columnDefinition, Class<T> clazz) {
        super(separator, columnDefinition, clazz);
        this.config = AdvancedCsvProcessorConfig.builder()
                .charactersToRemove(List.of(' '))
                .build();
    }

    @Override
    protected List<String> normalizeColumnDefinition(List<String> columnHeader) {
        return columnHeader.stream()
                .map(String::strip)
                .map(s -> StringUtils.removeAll(s,
                        config.getCharactersToRemove().stream()
                                .map(String::valueOf)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
