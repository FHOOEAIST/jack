package science.aist.jack.persistence.filesystem.implementation;

import science.aist.jack.string.StringUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Extension of the {@link ReflectionCSVProcessor}. It is advanced as it takes an {@link AdvancedCsvProcessorConfig}
 * in order to configure the normalisation of the column headers to allow improved mapping of csv files to objects</p>
 *
 * @author Rainer Meindl
 */
public class AdvancedCsvProcessor<T> extends ReflectionCSVProcessor<T> {

    private AdvancedCsvProcessorConfig config;

    /**
     * Constructor of a AdvancedCsvProcessor for domain types with simple-typed properties only (int, double, float,
     * long, boolean, char, Integer, Float, Double, Long, String, Character, Boolean)
     *
     * @param separator          separator used in the csv file
     * @param columnDefinition   columnDefinition of the csv file (can be null for reading if the CSV file contains a
     *                           header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param clazz              clazz of domain type
     * @param csvProcessorConfig Configuration for this class. Defines how the header normalization should be handled
     */
    public AdvancedCsvProcessor(char separator, List<String> columnDefinition, Class<T> clazz,
                                AdvancedCsvProcessorConfig csvProcessorConfig) {
        this(separator, columnDefinition, clazz);
        this.config = csvProcessorConfig;
    }

    /**
     * Constructor of a AdvancedCsvProcessor for domain types with simple-typed properties only (int, double,
     * float, long, boolean, char, Integer, Float, Double, Long, String, Character, Boolean). The configuration is set
     * to a default, meaning only whitespaces of the column headers are trimmed and concatenated into one "word"
     *
     * @param separator        separator used in the csv file
     * @param columnDefinition columnDefinition of the csv file (can be null for reading if the CSV file contains a
     *                         header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param clazz            clazz of domain type
     */
    public AdvancedCsvProcessor(char separator, List<String> columnDefinition, Class<T> clazz) {
        super(separator, columnDefinition, clazz);
        this.config = CsvProcessorConfigBuilder.builder()
                .headerCharactersToRemove(List.of(" "))
                .build();
    }

    /**
     * Normalizes the headers according to the {@link AdvancedCsvProcessorConfig}. It first removes the strings defined
     * in the config.charactersToRemove, then replaces the strings defined in config.charactersToReplace.
     *
     * @param columnHeader the extracted column headers to be used to map them to object fields
     * @return normalized column headers as define in the {@link AdvancedCsvProcessorConfig}. Should allow mapping
     * of the values to objects.
     */
    @Override
    protected List<String> normalizeColumnDefinition(List<String> columnHeader) {
        return columnHeader.stream()
                .map(String::strip)
                .map(s -> StringUtils.removeAll(s,
                        config.getHeaderCharactersToRemove()))
                .map(s -> StringUtils.replaceAll(s,
                        config.getHeaderCharactersToReplace()))
                .collect(Collectors.toList());

    }

    @Override
    protected List<String> normalizeRow(List<String> csvLine) {
        return csvLine.stream()
                .map(String::strip)
                .map(stringToCheck -> StringUtils.removeAll(stringToCheck, config.getContentCharactersToRemove()))
                .map(s -> StringUtils.replaceAll(s, config.getContentCharactersToReplace()))
                .collect(Collectors.toList());
    }
}
