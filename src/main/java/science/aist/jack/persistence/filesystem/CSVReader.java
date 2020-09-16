package science.aist.jack.persistence.filesystem;


import java.io.File;
import java.util.List;

/**
 * <p>Interface for a CSV file reader</p>
 *
 * @author Christoph Praschl
 * @since 1.0
 */
public interface CSVReader<T> {
    /**
     * Method for reading the given csv file (without a column definition) and converting the lines to the specific element type
     *
     * @param csvFile source file to read
     * @return the read elements
     */
    default List<T> read(File csvFile) {
        return read(csvFile, false, false);
    }

    /**
     * Method for reading the given csv file (without a column definition) and converting the lines to the specific element type
     *
     * @param csvFile                  source file to read
     * @param containsColumndefinition flag which signals if the file contains a column definition which will be ignored
     * @return the read elements
     */
    default List<T> read(File csvFile, boolean containsColumndefinition) {
        return read(csvFile, containsColumndefinition, false);
    }

    /**
     * Method for reading the given csv file and converting the lines to the specific element type
     *
     * @param csvFile                  source file to read
     * @param containsColumndefinition flag which signals if the file contains a column definition
     * @param useFileColumnDefinition  flag if the column definition in the csv file should be used instead of a given one (only used if containsColumndefinition == true)
     * @return the read elements
     */
    List<T> read(File csvFile, boolean containsColumndefinition, boolean useFileColumnDefinition);
}