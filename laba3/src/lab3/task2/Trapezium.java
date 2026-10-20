package lab3.task2;

import java.util.Objects;

public class Trapezium extends Figure {
    private double a;
    private double b;
    private double c;
    private double d;

    public Trapezium() {
    }

    public Trapezium(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public static boolean isValid(double a, double b, double c, double d) {
        if (a <= 0 || b <= 0 || c <= 0 || d <= 0) {
            return false;
        }
        double diff = Math.abs(a - b);
        if (diff == 0) {
            return false; // основи не повинні бути рівними
        }
        return (diff + c > d) && (diff + d > c) && (c + d > diff);
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

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    @Override
    public double getArea() {
        double diff = Math.abs(a - b);
        double s = (diff + c + d) / 2.0;
        double triangleArea = Math.sqrt(s * (s - diff) * (s - c) * (s - d));
        double h = (2.0 * triangleArea) / diff;
        return ((a + b) / 2.0) * h;
    }

    @Override
    public double getPerimeter() {
        return a + b + c + d;
    }

    @Override
    public String getName() {
        return "Трапеція";
    }

    @Override
    public String toString() {
        return "Трапеція [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d +
                ", площа=" + getArea() + ", периметр=" + getPerimeter() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Trapezium that = (Trapezium) obj;
        boolean bases = (a == that.a && b == that.b) || (a == that.b && b == that.a);
        boolean legs = (c == that.c && d == that.d) || (c == that.d && d == that.c);
        return bases && legs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(a, b), Math.max(a, b), Math.min(c, d), Math.max(c, d));
    }
}
