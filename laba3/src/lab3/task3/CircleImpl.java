package lab3.task3;

import java.util.Objects;

public class CircleImpl implements FigureInterface {
    private double r;

    public CircleImpl() {
    }

    public CircleImpl(double r) {
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
    public String toString() {
        return "CircleImpl [r=" + r + ", площа=" + getArea() + ", периметр=" + getPerimeter() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CircleImpl that = (CircleImpl) obj;
        return Double.compare(that.r, r) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r);
    }
}
