package be.pxl.ja.demo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //lambda
        new Thread(()-> System.out.println("printing from the runnable")).start();


        new Thread(()-> {
            System.out.println("printing from the runnable");
            System.out.println("line 2");
            System.out.format("This is line %d\n",3);
        }).start();

        //anonymous class
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("printing from the Runnable");
            }
        }).start();


        Employee john = new Employee("John doe", 30);
        Employee tim = new Employee("Tim", 21);
        Employee jack = new Employee("Jack hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(jack);
        employees.add(tim);
        employees.add(snow);

        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });

        //lambda
        Collections.sort(employees, (employee1, employee2) -> employee1.getName().compareTo(employee2.getName()));


        //method reference
        Collections.sort(employees, Comparator.comparing(Employee::getName));



        for (Employee employee : employees){
            System.out.println(employee.getName());
        }
    }
}




class Employee{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
