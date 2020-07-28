package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import lombok.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>TODO insert documentation for this class</p>
 *
 * @author Rainer Meindl, rainer.meindl@fh-hagenberg.at, 28.07.2020
 */
public class CsvProcessorConfigBuilder {
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
