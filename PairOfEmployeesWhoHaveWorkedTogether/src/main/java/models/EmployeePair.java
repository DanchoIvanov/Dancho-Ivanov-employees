package models;

import java.util.Objects;

public class EmployeePair implements Comparable<EmployeePair> {
    int id1;
    int id2;
    long days;

    public EmployeePair(int id1, int id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
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
        return Long.compare(this.getDays(), o.getDays());
    }

    @Override
    public String toString() {
        return id1 + ", " + id2 + ", " + days;
    }
}
