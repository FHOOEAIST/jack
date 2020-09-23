/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.persistence.filesystem;

import java.io.File;
import java.util.List;

/**
 * <p>Interface for writing elements to a CSV file</p>
 *
 * @author Christoph Praschl
 * @since 2.0
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
