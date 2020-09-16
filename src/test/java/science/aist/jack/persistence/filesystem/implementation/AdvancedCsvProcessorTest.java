package science.aist.jack.persistence.filesystem.implementation;

import lombok.*;
import org.springframework.core.io.ClassPathResource;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

public class AdvancedCsvProcessorTest {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class TestData {
        private int valid;
        private String invalidStuff;
        private int fancyInt;
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
    public void testInvalidContentCustomConfig() {
        // given
        AdvancedCsvProcessor<TestData> csvProcessor = new AdvancedCsvProcessor<>(',',
                null, TestData.class, CsvProcessorConfigBuilder.builder()
                .colCharactersToRemove(List.of("\""))
                .build());

        ClassPathResource resource = new ClassPathResource("advancedCustomTestContent.csv");
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
    public void testInvalidContentCustomConfigWithReplace() {
        // given
        AdvancedCsvProcessor<TestData> csvProcessor = new AdvancedCsvProcessor<>(',',
                null, TestData.class, CsvProcessorConfigBuilder.builder()
                .colCharactersToRemove(List.of("\""))
                .colCharactersToReplace(Map.of("a", "b"))
                .build());

        ClassPathResource resource = new ClassPathResource("advancedCustomTestContent.csv");
        File file = resource.getFile();
        var expected = List.of(new TestData(45, "bbc", 1),
                new TestData(73, "def", 5),
                new TestData(99, "ghi", -1));

        // when
        var result = csvProcessor.read(file, true, true);

        // then
        Assert.assertEquals(result, expected);
    }

    @SneakyThrows
    @Test
    public void testInvalidContentAndHeadersCustomConfig() {
        // given
        AdvancedCsvProcessor<TestData> csvProcessor = new AdvancedCsvProcessor<>(',',
                null, TestData.class, CsvProcessorConfigBuilder.builder()
                .headerCharactersToRemove(List.of(" ", "(", ")"))
                .colCharactersToRemove(List.of("\""))
                .build());

        ClassPathResource resource = new ClassPathResource("advancedCustomTestMixed.csv");
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
    public void testInvalidContentAndHeadersCustomConfigWithReplace() {
        // given
        AdvancedCsvProcessor<TestData> csvProcessor = new AdvancedCsvProcessor<>(',',
                null, TestData.class, CsvProcessorConfigBuilder.builder()
                .headerCharactersToRemove(List.of(" ", "(", ")"))
                .headerCharactersToReplace(Map.of("(", "", ")", ""))
                .colCharactersToRemove(List.of("\""))
                .colCharactersToReplace(Map.of("a", "b", "d", "1"))
                .build());

        ClassPathResource resource = new ClassPathResource("advancedCustomTestMixed.csv");
        File file = resource.getFile();
        var expected = List.of(new TestData(45, "bbc", 1),
                new TestData(73, "1ef", 5),
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
                null, TestData.class, CsvProcessorConfigBuilder.builder()
                .headerCharactersToRemove(List.of(" ", "(", ")"))
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

    @SneakyThrows
    @Test
    public void testInvalidHeadersCustomConfigWithReplace() {
        // given
        AdvancedCsvProcessor<TestData> csvProcessor = new AdvancedCsvProcessor<>(',',
                null, TestData.class, CsvProcessorConfigBuilder.builder()
                .headerCharactersToRemove(List.of(" ", "(", ")"))
                .headerCharactersToReplace(Map.of("(", "", ")", ""))
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
