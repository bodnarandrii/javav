package lab5.task1;

import java.util.Locale;
import java.util.Objects;

public class Circle extends Figure implements Comparable<Circle> {
    private double r;

    public Circle() {
    }

    public Circle(double r) {
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public double getArea() {
        return Math.PI * r * r;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public String getName() {
        return "Коло";
    }

    @Override
    public int compareTo(Circle other) {
        return Double.compare(this.r, other.r);
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "Коло [r=%.2f, площа=%.2f, периметр=%.2f]",
                r, getArea(), getPerimeter());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Circle circle = (Circle) obj;
        return Double.compare(circle.r, r) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r);
    }
}
