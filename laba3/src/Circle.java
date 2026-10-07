public class Circle extends Figure {
    private double r;

    public Circle(double r) { this.r = r; }

    @Override
    public double getArea() { return Math.PI * r * r; }

    @Override
    public double getPerimeter() { return 2 * Math.PI * r; }

    @Override
    public String toString() { return "Коло (r=" + r + ")"; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Circle circle = (Circle) obj;
        return Double.compare(circle.r, r) == 0;
    }
}