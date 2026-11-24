package lab5.task1;

import java.util.Comparator;

public class FigureComparator implements Comparator<Figure> {
    @Override
    public int compare(Figure f1, Figure f2) {
        int areaCmp = Double.compare(f1.getArea(), f2.getArea());
        if (areaCmp != 0) {
            return areaCmp;
        }
        int perimCmp = Double.compare(f1.getPerimeter(), f2.getPerimeter());
        if (perimCmp != 0) {
            return perimCmp;
        }
        return f1.getName().compareTo(f2.getName());
    }
}
