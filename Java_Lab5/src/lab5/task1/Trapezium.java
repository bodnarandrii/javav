package lab5.task1;

import java.util.Locale;
import java.util.Objects;

public class Trapezium extends Figure implements Comparable<Trapezium> {
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
    public int compareTo(Trapezium other) {
        int areaCmp = Double.compare(this.getArea(), other.getArea());
        if (areaCmp != 0) {
            return areaCmp;
        }
        return Double.compare(this.getPerimeter(), other.getPerimeter());
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "Трапеція [a=%.2f, b=%.2f, c=%.2f, d=%.2f, площа=%.2f, периметр=%.2f]",
                a, b, c, d, getArea(), getPerimeter());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Trapezium that = (Trapezium) obj;
        boolean bases = (Double.compare(a, that.a) == 0 && Double.compare(b, that.b) == 0) ||
                        (Double.compare(a, that.b) == 0 && Double.compare(b, that.a) == 0);
        boolean legs = (Double.compare(c, that.c) == 0 && Double.compare(d, that.d) == 0) ||
                       (Double.compare(c, that.d) == 0 && Double.compare(d, that.c) == 0);
        return bases && legs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(a, b), Math.max(a, b), Math.min(c, d), Math.max(c, d));
    }
}
