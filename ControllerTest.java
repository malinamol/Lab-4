package test;

import api.controller.Controller;
import api.domain.Employee;
import api.repository.FileRepository;
import api.repository.MemoryRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;

import static org.junit.Assert.*;

public class ControllerTest {

    private Controller controller;
    Employee person;

    @Before
    public void setUp() {
        controller = new Controller(true);

        person = new Employee("test", "test", "test",
                Employee.Position.backend, 5, 5, 5);

    }

    /**
     * testare unitara
     */
    @Test
    public void calculateSalary() {
        int nrOfDays = 5;
        //assertEquals(controller.calculateSalary(person, nrOfDays), 125.0, 0.1);
        assertEquals(controller.calculateSalary(person, nrOfDays), 130.0, 0.1);
    }
}