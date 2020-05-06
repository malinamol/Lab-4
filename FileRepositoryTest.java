package test;

import api.domain.Employee;
import api.repository.FileRepository;
import api.repository.MemoryRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileRepositoryTest {

    String file_name;
    MemoryRepository mr;
    FileRepository fr;


    @Before
    public void setUp() {
        mr = new MemoryRepository();
        fr = new FileRepository(mr);

        Employee person_1 = new Employee("bob", "alex", "casa",
                Employee.Position.hr, 3, 4, 5);
        Employee person_2 = new Employee("ana", "bora", "test",
                Employee.Position.pr, 5, 5, 5);

        mr.people.add(person_1);
        mr.people.add(person_2);
    }

    /**
     * TESTARE INCREMENTALA top down- integram modulele individuale se combina si se testeaza ca si grup
     * Integram in functia de testare a FileRepository si modulul de Memory Repository (care a fost testat anterior)
     */
    @Test
    public void Test() {

        fr.memoryToFile();
        fr.fileToMemory();

        System.out.println(fr.findAll());
        Employee person = new Employee("test", "test", "test",
                Employee.Position.backend, 0, 0, 0);

        fr.save(person);
        System.out.println(fr.findAll());
        assertEquals(person, fr.findOne(person.getId()));

        fr.memoryToFile();
        fr.delete(person.getId());
        fr.fileToMemory();
        System.out.println(fr.findAll());
    }
}