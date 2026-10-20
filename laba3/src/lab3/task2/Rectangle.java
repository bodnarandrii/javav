package lab3.task2;

import java.util.Objects;

public class Rectangle extends Figure {
    private double a;
    private double b;

    public Rectangle() {
    }

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double getArea() {
        return a * b;
    }

    @Override
    public double getPerimeter() {
        return 2 * (a + b);
    }

    @Override
    public String getName() {
        return "Прямокутник";
    }

    @Override
    public String toString() {
        return "Прямокутник [a=" + a + ", b=" + b + ", площа=" + getArea() + ", периметр=" + getPerimeter() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rectangle that = (Rectangle) obj;
        return (Double.compare(that.a, a) == 0 && Double.compare(that.b, b) == 0) ||
               (Double.compare(that.a, b) == 0 && Double.compare(that.b, a) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(a, b), Math.max(a, b));
    }
}
