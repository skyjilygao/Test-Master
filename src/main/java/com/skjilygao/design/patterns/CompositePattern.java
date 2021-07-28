package com.skjilygao.design.patterns;

import java.util.*;

/**
 * 组合模式
 * @author skyjilygao
 */
public class CompositePattern {

public static void main(String[] args) {
    CompositePattern pattern = new CompositePattern();
    pattern.composite();
}

private void composite() {
    Employee ceo = new Employee("gao", "ceo", 2000);
    Employee cfo = new Employee("sky1", "cfo", 2000);
    Employee cto = new Employee("sky", "cto", 2000);
    ceo.add(cfo).add(cto);

    Employee cfo_e1 = new Employee("cf_e1", "cfo", 10);
    Employee cfo_e2 = new Employee("cf_e2", "cfo", 10);
    cfo.add(cfo_e1).add(cfo_e2);

    Employee cto_e1 = new Employee("ct_e1", "cto", 9);
    Employee cto_e2 = new Employee("ct_e2", "cto", 9);
    cto.add(cto_e1).add(cto_e2);

    Stack<Employee> stack = new Stack<>();
    stack.add(ceo);
    while (stack != null && stack.size() > 0){
        Employee e = stack.pop();
        System.out.println(e);
        List<Employee> es;
        if((es = e.getSubordinates()) != null && es.size() > 0){
            stack.addAll(es);
        }
    }
}

/**
 * 定义员工
 */
public class Employee{
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    public Employee(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.subordinates = new ArrayList<>();
    }

    public Employee add(Employee e){
        subordinates.add(e);
        return this;
    }

    public Employee remove(Employee e){
        subordinates.remove(e);
        return this;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\'' + ", dept='" + dept + '\'' + ", salary=" + salary + '}';
    }
}
}
