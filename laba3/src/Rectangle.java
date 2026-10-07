public class Rectangle extends Figure {
    private double a, b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getArea() { return a * b; }

    @Override
    public double getPerimeter() { return 2 * (a + b); }

    @Override
    public String toString() { return "Прямокутник (a=" + a + ", b=" + b + ")"; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rectangle rectangle = (Rectangle) obj;
        return Double.compare(rectangle.a, a) == 0 && Double.compare(rectangle.b, b) == 0;
    }
}