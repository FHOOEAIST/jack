package at.fh.hagenberg.aist.jack.persistence.filesystem;

import java.io.File;
import java.util.List;

/**
 * <p>Created by Christoph Praschl on 25/02/2020</p>
 * <p>Interface for writing elements to a CSV file</p>
 *
 * @author Christoph Praschl christoph.praschl@fh-hagenberg.at
 */
public interface CSVWriter<T> {
    /**
     * Method for writing the given elements to a csv file (without the column definition)
     *
     * @param targetFile target file where to write the elements
     * @param elements   to be written
     * @return true iff elements are written successfully
     */
    default boolean write(File targetFile, List<T> elements) {
        return write(targetFile, elements, false);
    }

    /**
     * Method for writing the given elements to a csv file
     *
     * @param targetFile            target file where to write the elements
     * @param elements              to be written
     * @param writeColumnDefinition flag which signals if the column definition should be written in the beginning
     * @return true iff elements are written successfully
     */
    boolean write(File targetFile, List<T> elements, boolean writeColumnDefinition);
}
