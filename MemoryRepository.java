package api.repository;

import api.domain.Employee;
import api.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepository implements Repository<Employee> {

    public List<Employee> people;

    public MemoryRepository() {
        people = new ArrayList<>();
    }


    @Override
    public Iterable<Employee> findAll() {
        return this.people;
    }

    public void setPeople(List<Employee> people) {
        this.people = people;
    }

    @Override
    public Employee findOne(long id) {
        for (Employee employee : this.people) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }


    @Override
    public Employee save(Employee employee) {
        Person found = this.findOne(employee.getId());
        if (found == null) { // not found
            people.add(employee);
            return null;
        }
        return employee;
    }

    @Override
    public Employee delete(long id) {
        Employee found = this.findOne(id);
        if (found != null) {
            people.remove(found);
        }
        return found;
    }


    @Override
    public Employee update(Employee employee, long id) {
        Employee found = this.findOne(id);
        if (found != null) {
            employee.setId(id);
            people.remove(found);
            people.add(employee);
        }
        return found;
    }

}
