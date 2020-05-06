package test;

import api.domain.Employee;
import api.repository.MemoryRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * testare BIG BANG - testare unitara pentru fiecare modul (functie in cazul nostru)
 */
public class MemoryRepositoryTest {

    private MemoryRepository mr;
    Employee person_1;
    Employee person_2;
    Employee person_3;
    Employee person_4;


    @Before
    public void setUp() {
        mr = new MemoryRepository();

        person_1 = new Employee("bob", "alex", "casa",
                Employee.Position.hr, 3, 4, 5);
        person_2 = new Employee("ana", "bora", "test",
                Employee.Position.pr, 5, 5, 5);
        person_3 = new Employee("lola", "rus", "bloc",
                Employee.Position.frontend, 9, 2, 4);
        person_4 = new Employee("zeti", "popa", "kog",
                Employee.Position.backend, 1, 4, 5);

        mr.people.add(person_1);
        mr.people.add(person_2);
        mr.people.add(person_3);
        mr.people.add(person_4);
    }


    /**
     * testare unitara
     */
    @Test
    public void save() {
        Employee person = new Employee("test", "test", "test",
                Employee.Position.backend, 0, 0, 0);

        //Employee person2 = new Employee("t45fw", "test", "test", Employee.Position.backend, 0, 0, 0);

        long person_id = person.getId();
        assertNull(mr.save(person)); // first call -> added
        assertEquals(person, mr.save(person)); // second call -> already in list, not added
        assertEquals(mr.people.size(), 5);
        assertEquals(mr.people.size(), 4);
        assertEquals(person.getId(), person_id);
        assertEquals(person.getForename(), "test");
    }

    /**
     * testare unitara
     */
    @Test
    public void findOne() {
        Employee person = new Employee("test_find", "test", "test",
                Employee.Position.backend, 0, 0, 0);
        assertEquals(mr.findOne(person_1.getId()), person_1);
        assertEquals(mr.findOne(person_2.getId()), person_2);
        assertNull(mr.findOne(person.getId()));
    }

    /**
     * testare unitara
     */
    @Test
    public void delete() {
        assertEquals(mr.people.size(), 4);
        mr.delete(1);
        assertEquals(mr.people.size(), 3);
        assertNull(mr.findOne(1));
    }

    /**
     * testare unitara
     */
    @Test
    public void update() {
        Employee person = new Employee("test_update", "test", "test",
                Employee.Position.backend, 0, 0, 0);

        assertEquals(mr.people.size(), 4);
        mr.update(person, person_1.getId());
        Employee updated_person = mr.findOne(person_1.getId());
        assertEquals(person, updated_person);
        assertEquals(mr.people.size(), 4);
    }


}