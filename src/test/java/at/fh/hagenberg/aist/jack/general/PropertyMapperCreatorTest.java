package at.fh.hagenberg.aist.jack.general;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>Created by Andreas Pointner on 03.12.2018</p>
 *
 * @author Andreas Pointner
 */
public class PropertyMapperCreatorTest {
    @Test
    public void testCreatePropertyMapper() {
        // given
        // when
        Function<Person, PersonDTO> propertyMapper =
                new PropertyMapperCreator<Person, PersonDTO>()
                        .from(Person::getFirstname).to(PersonDTO::setFirstname)
                        .from(Person::getLastname).to(PersonDTO::setLastname)
                        .from(Person::getDateOfBirth).toWith(PersonDTO::setDateOfBirth).with(ld -> ld.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        .create(PersonDTO::new);

        // then
        Assert.assertNotNull(propertyMapper);
    }

    @Test
    public void testCreatePropertyMapperApply() {
        // given
        Person p = new Person("Andreas", "Pointner", LocalDate.now());
        Function<Person, PersonDTO> propertyMapper =
                new PropertyMapperCreator<Person, PersonDTO>()
                        .from(Person::getFirstname).to(PersonDTO::setFirstname)
                        .from(Person::getLastname).to(PersonDTO::setLastname)
                        .from(Person::getDateOfBirth).toWith(PersonDTO::setDateOfBirth).with(ld -> ld.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        .create(PersonDTO::new);

        // when
        PersonDTO dto = propertyMapper.apply(p);

        // then
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getFirstname(), p.getFirstname());
        Assert.assertEquals(dto.getLastname(), p.getLastname());
        Assert.assertEquals(dto.getDateOfBirth(), p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    @Test
    public void testCreatePropertyMapperApply2() {
        // given
        Person p = new Person("Andreas", "Pointner", LocalDate.now());
        BiFunction<Person, PersonDTO, PersonDTO> propertyMapper =
                new PropertyMapperCreator<Person, PersonDTO>()
                        .from(Person::getFirstname).to(PersonDTO::setFirstname)
                        .from(Person::getLastname).to(PersonDTO::setLastname)
                        .from(Person::getDateOfBirth).toWith(PersonDTO::setDateOfBirth).with(ld -> ld.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        .create();


        // when
        PersonDTO dto = new PersonDTO();
        dto = propertyMapper.apply(p, dto);

        // then
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getFirstname(), p.getFirstname());
        Assert.assertEquals(dto.getLastname(), p.getLastname());
        Assert.assertEquals(dto.getDateOfBirth(), p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    @Test
    public void testCreatePropertyMapperIff() {
        // given
        Person p = new Person("Andreas", "Pointner", null);
        Function<Person, PersonDTO> propertyMapper =
                new PropertyMapperCreator<Person, PersonDTO>()
                        .from(Person::getFirstname).to(PersonDTO::setFirstname)
                        .from(Person::getLastname).to(PersonDTO::setLastname)
                        .fromIf(Person::getDateOfBirth).iff(Objects::nonNull).toWith(PersonDTO::setDateOfBirth).with(ld -> ld.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        .create(PersonDTO::new);

        // when
        PersonDTO dto = propertyMapper.apply(p);

        // then
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getFirstname(), p.getFirstname());
        Assert.assertEquals(dto.getLastname(), p.getLastname());
    }

    @Test
    public void testCreateWithTwoElement() {
        // given
        Person p = new Person("Andreas", "Pointner", null);
        Function<Person, Person2DTO> propertyMapper = new PropertyMapperCreator<Person, Person2DTO>()
                .from(Person::getFirstname, Person::getLastname)
                .toWith(Person2DTO::setName)
                .with((f, l) -> f + " " + l)
                .fromIf(Person::getDateOfBirth)
                .iff(Objects::nonNull)
                .toWith(Person2DTO::setDateOfBirth)
                .with(ld -> ld.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .create(Person2DTO::new);

        // when
        Person2DTO person2DTO = propertyMapper.apply(p);

        // then
        Assert.assertNotNull(person2DTO);
        Assert.assertEquals(person2DTO.getName(), "Andreas Pointner");
        Assert.assertNull(person2DTO.getDateOfBirth());
    }

    @Test
    public void testCreateWithNElement() {
        // given
        Person p = new Person("Andreas", "Pointner", LocalDate.of(1993, 11, 1));
        Function<Person, PersonNDTO> propertyMapper = new PropertyMapperCreator<Person, PersonNDTO>()
                .fromIf(Person::getFirstname, Person::getLastname, Person::getDateOfBirth)
                .iff(Objects::nonNull)
                .toWith(PersonNDTO::setData)
                .with(l -> l.stream().map(Object::toString).collect(Collectors.joining(" ")))
                .create(PersonNDTO::new);

        // when
        PersonNDTO apply = propertyMapper.apply(p);

        // then
        Assert.assertNotNull(apply);
        Assert.assertEquals(apply.getData(), "Andreas Pointner 1993-11-01");
    }

    @Test
    public void testCreateWithNElement2() {
        // given
        Person p = new Person("Andreas", "Pointner", LocalDate.of(1993, 11, 1));
        Function<Person, PersonNDTO> propertyMapper = new PropertyMapperCreator<Person, PersonNDTO>()
                .from(Person::getFirstname, Person::getLastname, Person::getDateOfBirth)
                .to((pDTO, l) -> pDTO.setData(l.stream().map(Object::toString).collect(Collectors.joining(" "))))
                .create(PersonNDTO::new);

        // when
        PersonNDTO apply = propertyMapper.apply(p);

        // then
        Assert.assertNotNull(apply);
        Assert.assertEquals(apply.getData(), "Andreas Pointner 1993-11-01");
    }

    //<editor-fold desc="Helper Classes for testing">
    @AllArgsConstructor
    @Getter
    @Setter
    private static class Person {
        private String firstname;
        private String lastname;
        private LocalDate dateOfBirth;
    }

    @Getter
    @Setter
    private static class PersonDTO {
        private String firstname;
        private String lastname;
        private String dateOfBirth;
    }

    @Getter
    @Setter
    private static class Person2DTO {
        private String name;
        private String dateOfBirth;
    }

    @Getter
    @Setter
    private static class PersonNDTO {
        private String data;
    }
    //</editor-fold>
}
