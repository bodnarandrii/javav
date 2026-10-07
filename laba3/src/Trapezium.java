public class Trapezium extends Figure {
    private double a, b, c, d, h;

    public Trapezium(double a, double b, double c, double d, double h) {
        this.a = a; this.b = b; this.c = c; this.d = d; this.h = h;
    }

    @Override
    public double getArea() { return ((a + b) / 2) * h; }

    @Override
    public double getPerimeter() { return a + b + c + d; }

    @Override
    public String toString() { return "Трапеція (основи=" + a + "," + b + ", висота=" + h + ")"; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Trapezium that = (Trapezium) obj;
        return Double.compare(that.a, a) == 0 && Double.compare(that.b, b) == 0 &&
               Double.compare(that.c, c) == 0 && Double.compare(that.d, d) == 0 &&
               Double.compare(that.h, h) == 0;
    }
}