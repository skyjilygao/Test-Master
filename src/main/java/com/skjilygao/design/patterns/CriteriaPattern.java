package com.skjilygao.design.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 过滤器模式 或 标准模式
 * @author skyjilygao
 */
public class CriteriaPattern {

public static void main(String[] args) {
    CriteriaPattern pattern = new CriteriaPattern();
    pattern.criteria();
}

private void criteria() {
    List<Person> list = new ArrayList<>();
    list.add(new Person("s", "male", "single"));
    list.add(new Person("s", "female", "single"));
    list.add(new Person("ss", "female", "single"));
    list.add(new Person("ssf", "female", "single"));
    list.add(new Person("ssfs", "male", "single"));
    list.add(new Person("asdf", "male", "married"));

    Criteria female = new CriteriaFemale();
    List<Person> femalePerson = female.meetCriteria(list);
    System.out.println("female:");
    System.out.println(femalePerson);

    Criteria male  = new CriteriaMale();
    List<Person> malePeople = male.meetCriteria(list);
    System.out.println("\nmale:");
    System.out.println(malePeople);

    Criteria single = new CriteriaSingle();
    List<Person> singlePerson = single.meetCriteria(list);
    System.out.println("\nsingle:");
    System.out.println(singlePerson);

    Criteria or = new OrCriteria(female, single);
    List<Person> orPerson = or.meetCriteria(list);
    System.out.println("\nfemale or single:");
    System.out.println(orPerson);
}

public class Person {
    private String name;
    private String gender;
    private String maritalStatus;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", gender='" + gender + '\'' + ", maritalStatus='" + maritalStatus + '\'' + '}';
    }
}

/**
 * 过滤器接口
 */
public interface Criteria{
    List<Person> meetCriteria(List<Person> personList);
}

/**
 * 男性过滤器
 */
public class CriteriaMale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        return personList.stream().filter(p -> "male".equalsIgnoreCase(p.getGender())).collect(Collectors.toList());
    }
}

/**
 * 女性过滤器
 */
public class CriteriaFemale implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        return personList.stream().filter(p -> "female".equalsIgnoreCase(p.getGender())).collect(Collectors.toList());
    }
}

/**
 * 单身过滤器
 */
public class CriteriaSingle implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        return personList.stream().filter(p -> "single".equalsIgnoreCase(p.getMaritalStatus())).collect(Collectors.toList());
    }
}

/**
 * 已婚过滤器
 */
public class CriteriaMarried implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        return personList.stream().filter(p -> "married".equalsIgnoreCase(p.getMaritalStatus())).collect(Collectors.toList());
    }
}

/**
 * and: 并且逻辑
 */
public class AndCriteria implements Criteria{
    private Criteria criteria;
    private Criteria otherCriteria;

    private AndCriteria() {
    }

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        // 1. 筛选出部分person
        List<Person> criteriaPerson = criteria.meetCriteria(personList);
        // 2. 再从步骤1筛选的结果中再筛选，从而达到and效果
        return otherCriteria.meetCriteria(criteriaPerson);
    }
}

/**
 * or: 或逻辑
 */
public class OrCriteria implements Criteria{
    private Criteria criteria;
    private Criteria otherCriteria;

    private OrCriteria() {
    }

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        // 1. 筛选出各自的person
        List<Person> people = criteria.meetCriteria(personList);
        List<Person> people1 = otherCriteria.meetCriteria(personList);
        // 2. 此时，将2个结果合并返回即是or的结果。但是防止数据重复，则有个排重逻辑
        for (Person person : people1) {
            if(!people.contains(person)){
                people.add(person);
            }
        }
        return people;
    }
}

}

