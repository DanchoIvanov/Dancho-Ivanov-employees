package exceptions.models;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Project {
    private int id;
    private TreeSet<Employee> employees;

    public int getId() {
        return id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Project(int id) {
        this.id = id;
        this.employees = new TreeSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
