public class RectangleI implements IFigure {
    private double a, b;
    public RectangleI(double a, double b) { this.a = a; this.b = b; }
    @Override public double getArea() { return a * b; }
    @Override public double getPerimeter() { return 2 * (a + b); }
    @Override public String toString() { return "Прямокутник [Інтерфейс] (a=" + a + ", b=" + b + ")"; }
}