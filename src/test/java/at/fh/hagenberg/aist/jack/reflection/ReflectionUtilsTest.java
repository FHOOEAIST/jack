package at.fh.hagenberg.aist.jack.reflection;

import at.fh.hagenberg.aist.jack.reflection.domain.A;
import at.fh.hagenberg.aist.jack.reflection.domain.B;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>Created by Christoph Praschl on 02/10/2019</p>
 * <p>Test class for {@link ReflectionUtils}</p>
 *
 * @author Christoph Praschl
 */
public class ReflectionUtilsTest {

    @Test
    public void testGetAllFieldsOfClass() {
        // given

        // when
        List<Field> allFieldsOfClass = ReflectionUtils.getAllFieldsOfClass(B.class)
                // remove synthetic fields because execution otherwise fails on CI
                .stream().filter(f -> !f.isSynthetic()).collect(Collectors.toList());

        // then
        Assert.assertEquals(allFieldsOfClass.size(), 6);
    }

    @Test
    public void testGetAllFieldsOfClass2() {
        // given

        // when
        List<Field> allFieldsOfClass = ReflectionUtils.getAllFieldsOfClass(A.class)
                // remove synthetic fields because execution otherwise fails on CI
                .stream().filter(f -> !f.isSynthetic()).collect(Collectors.toList());

        // then
        Assert.assertEquals(allFieldsOfClass.size(), 3);

    }
}