package models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
    POJO class used by the application to store information about employees pairs.
    It contains 2 ids, one for every employee and Set of numbers representing the epoch days they worked together.
    Pair has 2 employee ids which given in certain order
    make the object unique. Example: EmployeePair(id1, id2) != EmployeePair(id2, id1).
    Employee pair is a comparable object and is compared by the days.
 */

public class EmployeePair implements Comparable<EmployeePair> {
    int id1;
    int id2;
    Set<Long> daysTogether;

    public EmployeePair(int id1, int id2) {
        this.id1 = id1;
        this.id2 = id2;
        daysTogether = new HashSet<>();
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public int getDays() {
        return daysTogether.size();
    }

    public void addDays(Set<Long> daysToAdd) {
        this.daysTogether.addAll(daysToAdd);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair that = (EmployeePair) o;
        return id1 == that.id1 && id2 == that.id2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }

    @Override
    public int compareTo(EmployeePair o) {
        return Integer.compare(this.getDays(), o.getDays());
    }

    @Override
    public String toString() {
        return id1 + ", " + id2 + ", " + this.getDays();
    }
}
