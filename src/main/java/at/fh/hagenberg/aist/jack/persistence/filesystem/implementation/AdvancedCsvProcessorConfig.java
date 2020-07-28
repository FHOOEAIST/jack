package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

/**
 * <p>Configuration definition for {@link at.fh.hagenberg.aist.jack.persistence.filesystem.implementation.AdvancedCsvProcessor}.</p>
 *
 * @author Rainer Meindl, rainer.meindl@fh-hagenberg.at, 22.06.2020
 */
@Builder(access = AccessLevel.PACKAGE)
@Getter
public class AdvancedCsvProcessorConfig {

    public Collection<String> getHeaderCharactersToRemove() {
        return headerConfig != null ? headerConfig.charactersToRemove : new ArrayList<>();
    }

    public Map<String, String> getHeaderCharactersToReplace() {
        return headerConfig != null ? headerConfig.charactersToReplace : new HashMap<>();
    }

    @Builder
    static class ColumnConfig {
        @Builder.Default
        private final List<String> charactersToRemove = List.of();
        @Builder.Default
        private final Map<String, String> charactersToReplace = Map.of();
    }

    @Builder
    static class HeaderConfig {
        @Builder.Default
        private final List<String> charactersToRemove = List.of();
        @Builder.Default
        private final Map<String, String> charactersToReplace = Map.of();
    }

    @Builder.Default
    private final HeaderConfig headerConfig = null;
    @Builder.Default
    private final ColumnConfig columnConfig = null;
}
