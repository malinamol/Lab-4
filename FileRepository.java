package api.repository;

import api.domain.Employee;

import java.io.*;
import java.util.*;



public class FileRepository implements Repository<Employee> {

    MemoryRepository mr;
    String file;
    String split = ",";
    String fileName = "C:\\Users\\Malina\\IdeaProjects\\3lab\\src\\employee.txt";
    File myFile = new File(fileName);

    public FileRepository(MemoryRepository mr_value) {
        this.mr = mr_value;
    }


    //  memory -> file
    public void memoryToFile() {

        PrintWriter w;
        StringBuilder line;
        Iterable<Employee> people = mr.findAll();

        try {
            w = new PrintWriter(this.myFile);
            line = new StringBuilder();

            for (Employee employee : people) {
                line.append(employee.getId());
                line.append(split);

                line.append(employee.getForename());
                line.append(this.split);

                line.append(employee.getSurname());
                line.append(this.split);

                line.append(employee.getAdress());
                line.append(this.split);

                line.append(employee.getPosition());
                line.append(this.split);

                line.append(employee.getExperience());
                line.append(this.split);

                line.append(employee.getSalary());
                line.append(this.split);

                line.append(employee.getHoursPerDay());
                line.append('\n');

            }
            w.append(line.toString());
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //    file -> memory
    public void fileToMemory() {

        String line;
        List<Employee> people = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            while ((line = br.readLine()) != null) {
                //use comma separator
                String[] country = line.split(this.split);
                long id = Long.parseLong(country[0]);
                String forename = country[1];
                String surname = country[2];
                String adress = country[3];
                String position_str = country[4];
                Employee.Position position = Employee.Position.valueOf(position_str);
                int experience = Integer.parseInt(country[5]);
                double salary = Double.parseDouble(country[6]);
                int hoursPerDay = Integer.parseInt(country[7]);

                Employee employee = new Employee(forename, surname, adress,
                        position, experience, salary, hoursPerDay);
                employee.setId(id);
                people.add(employee);
            }
            mr.setPeople(people);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Employee findOne(long id) {
        return mr.findOne(id);
    }


    @Override
    public Iterable<Employee> findAll() {
        return mr.findAll();
    }


    @Override
    public Employee save(Employee employee) {

        Employee result = mr.save(employee);
        this.memoryToFile();
        return result;
    }


    @Override
    public Employee delete(long id) {

        Employee result = mr.delete(id);
        this.memoryToFile();
        return result;
    }


    @Override
    public Employee update(Employee employee, long id) {

        Employee result = mr.update(employee, id);
        this.memoryToFile();
        return result;
    }

}
