/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.persistence.filesystem.implementation;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Builder class for {@link AdvancedCsvProcessorConfig}. Has one static build method that takes all relevant
 * parameters for the config</p>
 *
 * <h2>Usage</h2>
 * <code>
 * AdvancedCsvProcessorConfig config = CsvProcessorConfigBuilder.builder()<br>
 * .colCharactersToRemove(List.of("\""))<br>
 * .build());
 * </code>
 *
 * @author Rainer Meindl
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvProcessorConfigBuilder {
    /**
     * builds a new config using the given parameters
     *
     * @param headerCharactersToRemove  which characters should be removed from the csv header
     * @param headerCharactersToReplace which characters should be replaced with what in the csv header
     * @param colCharactersToRemove     which characters should be removed from the csv content
     * @param colCharactersToReplace    which characters should be replaced with what from the csv content
     * @return an {@link AdvancedCsvProcessorConfig} initialized with the given parameters
     */
    @Builder
    private static AdvancedCsvProcessorConfig newConfig(List<String> headerCharactersToRemove,
                                                        Map<String, String> headerCharactersToReplace,
                                                        List<String> colCharactersToRemove,
                                                        Map<String, String> colCharactersToReplace) {
        return AdvancedCsvProcessorConfig.builder()
                .headerConfig(AdvancedCsvProcessorConfig.HeaderConfig.builder()
                        .charactersToRemove(checkListForNull(headerCharactersToRemove))
                        .charactersToReplace(checkMapForNull(headerCharactersToReplace))
                        .build())
                .columnConfig(AdvancedCsvProcessorConfig.ColumnConfig.builder()
                        .charactersToRemove(checkListForNull(colCharactersToRemove))
                        .charactersToReplace(checkMapForNull(colCharactersToReplace))
                        .build())
                .build();
    }

    private static Map<String, String> checkMapForNull(Map<String, String> map) {
        return map == null ? new HashMap<>() : map;
    }

    private static List<String> checkListForNull(List<String> list) {
        return list == null ? new ArrayList<>() : list;

    }
}
