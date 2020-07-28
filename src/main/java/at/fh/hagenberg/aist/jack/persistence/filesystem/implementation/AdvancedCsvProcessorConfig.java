package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>Configuration definition for {@link at.fh.hagenberg.aist.jack.persistence.filesystem.implementation.AdvancedCsvProcessor}.</p>
 *
 * @author Rainer Meindl, rainer.meindl@fh-hagenberg.at, 22.06.2020
 */
@Builder(access = AccessLevel.PACKAGE)
public class AdvancedCsvProcessorConfig {

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

    @Builder.Default
    private final HeaderConfig headerConfig = new HeaderConfig();
    @Builder.Default
    private final ColumnConfig columnConfig = new ColumnConfig();
}
