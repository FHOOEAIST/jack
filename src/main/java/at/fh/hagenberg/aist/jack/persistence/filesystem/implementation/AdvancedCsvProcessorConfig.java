package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * <p>Configuration definition for {@link at.fh.hagenberg.aist.jack.persistence.filesystem.implementation.AdvancedCsvProcessor}.</p>
 *
 * @author Rainer Meindl, rainer.meindl@fh-hagenberg.at, 22.06.2020
 */
@Builder
@Getter
public class AdvancedCsvProcessorConfig {
    @Builder.Default
    private final List<String> charactersToRemove = List.of();
    @Builder.Default
    private final Map<String, String> charactersToReplace = Map.of();


}
