package models;

import utils.MyDateUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
    POJO class used by the application to store information about employee id,
    start and end date for a project. Employee has a unique ID and is compared by it.
 */

public class Employee implements Comparable<Employee>{
    private int id;
    private Set<Long> workDays;

    public int getId() {
        return id;
    }

    public Set<Long> getWorkDays() {
        return Collections.unmodifiableSet(workDays);
    }

    public void addWorkDays (LocalDate startDate, LocalDate endDate) {
        Set<Long> daysToAdd = MyDateUtil.getSetOfEpochDays(startDate, endDate);
        this.workDays.addAll(daysToAdd);
    }

    public Employee(int id) {
        this.id = id;
        this.workDays = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Employee o) {
        return Integer.compare(this.getId(), o.getId());
    }
}
