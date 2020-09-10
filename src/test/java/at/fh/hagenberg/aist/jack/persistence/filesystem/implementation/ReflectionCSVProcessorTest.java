package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import at.fh.hagenberg.aist.jack.math.MathUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>Created by Christoph Praschl on 24/01/2020</p>
 * <p>Test class for {@link ReflectionCSVProcessor}</p>
 *
 * @author Christoph Praschl
 */
public class ReflectionCSVProcessorTest {

    @Test
    public void testRead() throws IOException {
        // given
        ReflectionCSVProcessor<Point> reader = new ReflectionCSVProcessor<>(';', Arrays.asList("x", "y", "z"), Point.class);
        ClassPathResource resource = new ClassPathResource("points.csv");
        File file = resource.getFile();

        // when
        List<Point> read = reader.read(file, true);

        // then
        Assert.assertEquals(read.size(), 3);

        Assert.assertTrue(read.contains(new Point(0.5155, 0.88489, 0.568949)));
        Assert.assertTrue(read.contains(new Point(5.45646, 8.197997, 6.18916)));
        Assert.assertTrue(read.contains(new Point(2.9873, 1, 6464.234)));
    }

    @Test
    public void testWrite() throws IOException {
        // given
        List<Point> points = Arrays.asList(new Point(0.5155, 0.88489, 0.568949),
                new Point(5.45646, 8.197997, 6.18916),
                new Point(2.9873, 1, 6464.234));

        Path file = Files.createTempFile("someTestFile", ".csv");

        ReflectionCSVProcessor<Point> processor = new ReflectionCSVProcessor<>(';', Arrays.asList("x", "y", "z"), Point.class);

        // when
        boolean write = processor.write(file.toFile(), points);

        // then
        Assert.assertTrue(write);
        List<Point> read = processor.read(file.toFile());
        Assert.assertEquals(read.size(), 3);
        Assert.assertTrue(read.contains(new Point(0.5155, 0.88489, 0.568949)));
        Assert.assertTrue(read.contains(new Point(5.45646, 8.197997, 6.18916)));
        Assert.assertTrue(read.contains(new Point(2.9873, 1, 6464.234)));
    }

    @Test
    public void testRead2() throws IOException {
        // given
        ReflectionCSVProcessor<Sample> reader = new ReflectionCSVProcessor<>(';', Arrays.asList("a", "b", "c"), Sample.class);
        ClassPathResource resource = new ClassPathResource("sample.csv");
        File file = resource.getFile();

        // when
        List<Sample> read = reader.read(file, false);

        // then
        Assert.assertEquals(read.size(), 8);
        Assert.assertTrue(read.contains(new Sample("a", "b", "c")));
        Assert.assertTrue(read.contains(new Sample("a", "b", "")));
        Assert.assertTrue(read.contains(new Sample("a", "", "c")));
        Assert.assertTrue(read.contains(new Sample("", "b", "c")));
        Assert.assertTrue(read.contains(new Sample("a", "", "")));
        Assert.assertTrue(read.contains(new Sample("", "b", "")));
        Assert.assertTrue(read.contains(new Sample("", "", "c")));
        Assert.assertTrue(read.contains(new Sample("", "", "")));
    }

    @Test
    public void testRead3() throws IOException {
        // given
        ReflectionCSVProcessor<Something> reader = new ReflectionCSVProcessor<>(',', Arrays.asList("x", "y", "z"), Something.class);
        ClassPathResource resource = new ClassPathResource("test.csv");
        File file = resource.getFile();

        // when
        List<Something> read = reader.read(file, false);

        // then
        Assert.assertEquals(read.size(), 3);

        Assert.assertTrue(read.contains(new Something(45, "abc", 1)));
        Assert.assertTrue(read.contains(new Something(73, "def", 5)));
        Assert.assertTrue(read.contains(new Something(99, "ghi", -1)));
    }

    @Test
    public void testAddIgnoredField() throws IOException {
        // given
        ReflectionCSVProcessor<Something> reader = new ReflectionCSVProcessor<>(',', Arrays.asList("x", "y", "z"), Something.class);
        ClassPathResource resource = new ClassPathResource("test.csv");
        File file = resource.getFile();

        // when
        reader.addIgnoredField("y");
        reader.addIgnoredField("z");
        List<Something> read = reader.read(file, false);

        // then
        Assert.assertEquals(read.size(), 3);

        Assert.assertTrue(read.contains(new Something(45, null, 0)));
        Assert.assertTrue(read.contains(new Something(73, null, 0)));
        Assert.assertTrue(read.contains(new Something(99, null, 0)));
    }

    @Test
    public void testAddIgnoredField2() throws IOException {
        // given
        List<Point> points = Arrays.asList(new Point(0.5155, 0.88489, 0.568949),
                new Point(5.45646, 8.197997, 6.18916),
                new Point(2.9873, 1, 6464.234));

        Path file = Files.createTempFile("someTestFile", ".csv");

        ReflectionCSVProcessor<Point> processor = new ReflectionCSVProcessor<>(';', Arrays.asList("x", "y", "z"), Point.class);

        // when
        processor.addIgnoredField("z");
        boolean write = processor.write(file.toFile(), points);

        // then
        Assert.assertTrue(write);
        List<Point> read = processor.read(file.toFile());
        Assert.assertEquals(read.size(), 3);
        Assert.assertTrue(read.contains(new Point(0.5155, 0.88489, 0)));
        Assert.assertTrue(read.contains(new Point(5.45646, 8.197997, 0)));
        Assert.assertTrue(read.contains(new Point(2.9873, 1, 0)));
    }

    @Test
    public void testAddFieldConverter() throws IOException {
        // given
        ReflectionCSVProcessor<Something2> reader = new ReflectionCSVProcessor<>(',', Arrays.asList("x", "y", "z"), Something2.class);
        ClassPathResource resource = new ClassPathResource("test.csv");
        File file = resource.getFile();

        // when
        reader.addFieldConverter(SomeEnum.class, SomeEnum::valueOf);
        List<Something2> read = reader.read(file, false);

        // then
        Assert.assertEquals(read.size(), 3);

        Assert.assertTrue(read.contains(new Something2(45, SomeEnum.abc, 1)));
        Assert.assertTrue(read.contains(new Something2(73, SomeEnum.def, 5)));
        Assert.assertTrue(read.contains(new Something2(99, SomeEnum.ghi, -1)));
    }


    private enum SomeEnum {
        abc, def, ghi
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class Something {
        long x;
        String y;
        int z;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class Something2 {
        long x;
        SomeEnum y;
        int z;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class Sample {
        String a;
        String b;
        String c;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class Point {
        double x;
        double y;
        double z;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return MathUtils.equals(point.x, x, 0.00000001) &&
                    MathUtils.equals(point.y, y, 0.00000001) &&
                    MathUtils.equals(point.z, z, 0.00000001);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }
}