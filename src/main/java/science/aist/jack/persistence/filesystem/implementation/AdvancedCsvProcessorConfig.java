package science.aist.jack.persistence.filesystem.implementation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>Configuration definition for {@link AdvancedCsvProcessor}.</p>
 * <p>Use {@link CsvProcessorConfigBuilder} to create a configuration</p>
 *
 * <h2>Usage</h2>
 * <code>
 * AdvancedCsvProcessorConfig config = CsvProcessorConfigBuilder.builder()<br>
 * .colCharactersToRemove(List.of("\""))<br>
 * .build());
 * </code>
 *
 * @author Rainer Meindl
 */
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvancedCsvProcessorConfig {

    @Builder.Default
    private final HeaderConfig headerConfig = new HeaderConfig();
    @Builder.Default
    private final ColumnConfig columnConfig = new ColumnConfig();

    public Collection<String> getHeaderCharactersToRemove() {
        return headerConfig.charactersToRemove;
    }

    public Map<String, String> getHeaderCharactersToReplace() {
        return headerConfig.charactersToReplace;
    }

    public Collection<String> getContentCharactersToRemove() {
        return columnConfig.charactersToRemove;
    }

    public Map<String, String> getContentCharactersToReplace() {
        return columnConfig.charactersToReplace;
    }

    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class ColumnConfig {
        @Builder.Default
        private final Collection<String> charactersToRemove = List.of();
        @Builder.Default
        private final Map<String, String> charactersToReplace = Map.of();
    }

    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class HeaderConfig {
        @Builder.Default
        private final Collection<String> charactersToRemove = List.of();
        @Builder.Default
        private final Map<String, String> charactersToReplace = Map.of();
    }
}
