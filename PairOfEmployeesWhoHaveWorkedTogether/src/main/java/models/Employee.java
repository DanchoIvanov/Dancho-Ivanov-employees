package models;

import java.time.LocalDate;
import java.util.Objects;

/*
    POJO class used by the application to store information about employee id,
    start and end date for a project. Employee has a unique ID and is compared by it.
 */

public class Employee implements Comparable<Employee>{
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Employee(int id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
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
