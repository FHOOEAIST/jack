package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import at.fh.hagenberg.aist.jack.string.StringUtils;
import lombok.*;
import org.springframework.core.io.ClassPathResource;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

/**
 * <p>TODO insert documentation for this class</p>
 *
 * @author Rainer Meindl, rainer.meindl@fh-hagenberg.at, 22.06.2020
 */
public class AdvancedCsvProcessorTest {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class TestData {
        private int valid;
        private String invalidStuff;
        private int crazyShit;
    }

    @SneakyThrows
    @Test
    public void testInvalidHeaders() {
        // given
        AdvancedCsvProcessor<TestData> csvProcessor = new AdvancedCsvProcessor<>(',',
                null, TestData.class);
        ClassPathResource resource = new ClassPathResource("advancedTest.csv");
        File file = resource.getFile();
        var expected = List.of(new TestData(45, "abc", 1),
                new TestData(73, "def", 5),
                new TestData(99, "ghi", -1));

        // when
        var result = csvProcessor.read(file, true, true);

        // then
        Assert.assertEquals(result, expected);
    }

    @SneakyThrows
    @Test
    public void testInvalidHeadersCustomConfig() {
        // given
        AdvancedCsvProcessor<TestData> csvProcessor = new AdvancedCsvProcessor<>(',',
                null, TestData.class, AdvancedCsvProcessorConfig.builder()
                .charactersToRemove(List.of(' ', '(', ')'))
                .build());

        ClassPathResource resource = new ClassPathResource("advancedCustomTest.csv");
        File file = resource.getFile();
        var expected = List.of(new TestData(45, "abc", 1),
                new TestData(73, "def", 5),
                new TestData(99, "ghi", -1));

        // when
        var result = csvProcessor.read(file, true, true);

        // then
        Assert.assertEquals(result, expected);
    }
}
