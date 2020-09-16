package science.aist.jack.persistence.filesystem.implementation;

import org.springframework.core.io.ClassPathResource;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.jack.general.function.TriConsumer;
import science.aist.jack.math.MathUtils;
import science.aist.jack.persistence.filesystem.CSVReader;
import science.aist.jack.persistence.filesystem.CSVWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * <p>Test class for {@link CSVProcessor}</p>
 *
 * @author Christoph Praschl
 * @since 1.0
 */
public class CSVProcessorTest {
    private final BiFunction<Point, List<String>, List<String>> elementToColumnFunc = (elem, columnDefinition) -> Arrays.asList(Double.toString(elem.x), Double.toString(elem.y), Double.toString(elem.z));
    private final BiFunction<List<String>, List<String>, Point> columnsToElementFunc = (splitLines, columnDefinition) -> {
        Point p = new Point();
        for (int i = 0; i < columnDefinition.size(); i++) {
            String s = columnDefinition.get(i);
            double v = Double.parseDouble(splitLines.get(i));
            if ("x".equals(s)) {
                p.x = v;
            } else if ("y".equals(s)) {
                p.y = v;
            } else if ("z".equals(s)) {
                p.z = v;
            }
        }
        return p;
    };

    @Test
    public void testRead() throws IOException {
        // given
        ClassPathResource resource = new ClassPathResource("points.csv");
        File file = resource.getFile();
        CSVReader<Point> reader = CSVProcessor.getReader(';', Arrays.asList("x", "y", "z"), columnsToElementFunc);

        // when
        List<Point> read = reader.read(file, true);

        // then
        Assert.assertEquals(read.size(), 3);

        Assert.assertTrue(read.contains(new Point(0.5155, 0.88489, 0.568949)));
        Assert.assertTrue(read.contains(new Point(5.45646, 8.197997, 6.18916)));
        Assert.assertTrue(read.contains(new Point(2.9873, 1, 6464.234)));
    }

    @Test
    public void testRead2() throws IOException {
        // given
        ClassPathResource resource = new ClassPathResource("points.csv");
        File file = resource.getFile();
        CSVReader<Point> reader = CSVProcessor.getReader(';', Arrays.asList("x", "y", "z"), columnsToElementFunc);

        // when
        List<Point> read = reader.read(file, true, true);

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
        CSVProcessor<Point> processor = CSVProcessor.getProcessor(';', Arrays.asList("x", "y", "z"), elementToColumnFunc, columnsToElementFunc);


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
    public void testGetReader() {
        // given

        // when
        CSVReader<Point> reader = CSVProcessor.getReader(';', null, columnsToElementFunc);

        // then
        Assert.assertNotNull(reader);
    }

    @Test
    public void testGetWriter() {
        // given

        // when
        CSVWriter<Point> writer = CSVProcessor.getWriter(';', null, elementToColumnFunc);

        // then
        Assert.assertNotNull(writer);
    }

    @Test
    public void testGetProcessor() {
        // given

        // when
        CSVProcessor<Point> processor = CSVProcessor.getProcessor(';', null, elementToColumnFunc, columnsToElementFunc);

        // then
        Assert.assertNotNull(processor);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testGetReader2() {
        // given

        // when
        CSVReader<Object> reader = CSVProcessor.getReader(';', null, null);

        // then - exception
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testGetWriter2() {
        // given

        // when
        CSVWriter<Object> writer = CSVProcessor.getWriter(';', null, null);

        // then
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetProcessor2() {
        // given

        // when
        CSVProcessor<Object> processor = CSVProcessor.getProcessor(';', null, null, null);

        // then
    }

    @Test
    public void testTestGetProcessor() throws IOException {
        // given
        ClassPathResource resource = new ClassPathResource("points.csv");
        File file = resource.getFile();
        TriConsumer<Point, String, String> triConsumer = (point, s, s2) -> {
            double v = Double.parseDouble(s);
            if ("x".equals(s2)) {
                point.x = v;
            } else if ("y".equals(s2)) {
                point.y = v;
            } else if ("z".equals(s2)) {
                point.z = v;
            }
        };

        CSVProcessor<Point> processor = CSVProcessor.getProcessor(';', Arrays.asList("x", "y", "z"), elementToColumnFunc, Point::new, triConsumer);

        // when
        List<Point> read = processor.read(file, true, true);


        // then
        Assert.assertEquals(read.size(), 3);

        Assert.assertTrue(read.contains(new Point(0.5155, 0.88489, 0.568949)));
        Assert.assertTrue(read.contains(new Point(5.45646, 8.197997, 6.18916)));
        Assert.assertTrue(read.contains(new Point(2.9873, 1, 6464.234)));
    }


    static class Point {
        double x;
        double y;
        double z;

        public Point() {
        }

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }

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