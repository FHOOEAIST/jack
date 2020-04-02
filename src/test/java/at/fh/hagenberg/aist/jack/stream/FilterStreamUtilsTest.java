package at.fh.hagenberg.aist.jack.stream;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Created by Andreas Pointner on 02/09/2019</p>
 * <p>Test class for {@link FilterStreamUtils}</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
public class FilterStreamUtilsTest {

    @Test
    public void testDistinctByKeys() {
        // given
        Person person = new Person("Andreas", "Pointner", "12345");
        Person person1 = new Person("Christoph", "Praschl", "456789");
        Person person2 = new Person("Andreas", "Pointner", "6789");
        Stream<Person> persons = Stream.of(person, person1, person2);

        // when
        Stream<Person> personDistinctFirstLastName = persons.filter(FilterStreamUtils.distinctByKeys(Person::getFirstName, Person::getLastName));

        // then
        List<Person> collect = personDistinctFirstLastName.collect(Collectors.toList());
        Assert.assertEquals(collect.size(), 2);
        Assert.assertTrue(collect.contains(person));
        Assert.assertTrue(collect.contains(person1));
        Assert.assertFalse(collect.contains(person2));
    }
}

class Person {
    private String firstName;
    private String lastName;
    private String id;

    Person(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    String getId() {
        return id;
    }
}