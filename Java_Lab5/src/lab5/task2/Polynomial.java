package lab5.task2;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Polynomial {
    // Ключ — степінь змінної x, значення — коефіцієнт при цьому степені
    private final HashMap<Integer, Double> coefficients;

    public Polynomial() {
        this.coefficients = new HashMap<>();
    }

    public Polynomial(HashMap<Integer, Double> coefficients) {
        this.coefficients = new HashMap<>(coefficients);
    }

    public void setCoefficient(int power, double coeff) {
        if (power < 0) {
            throw new IllegalArgumentException("Степінь многочлена не може бути від'ємним.");
        }
        coefficients.put(power, coeff);
    }

    public double getCoefficient(int power) {
        return coefficients.getOrDefault(power, 0.0);
    }

    public HashMap<Integer, Double> getCoefficients() {
        return new HashMap<>(coefficients);
    }

    public int getDegree() {
        int maxPower = 0;
        for (Map.Entry<Integer, Double> entry : coefficients.entrySet()) {
            if (Math.abs(entry.getValue()) > 1e-12 && entry.getKey() > maxPower) {
                maxPower = entry.getKey();
            }
        }
        return maxPower;
    }

    public Polynomial add(Polynomial other) {
        HashMap<Integer, Double> resultMap = new HashMap<>(this.coefficients);
        for (Map.Entry<Integer, Double> entry : other.coefficients.entrySet()) {
            int power = entry.getKey();
            double sum = resultMap.getOrDefault(power, 0.0) + entry.getValue();
            resultMap.put(power, sum);
        }
        return new Polynomial(resultMap);
    }

    @Override
    public String toString() {
        int maxPower = -1;
        for (Integer p : coefficients.keySet()) {
            if (p > maxPower) {
                maxPower = p;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p = maxPower; p >= 0; p--) {
            double c = coefficients.getOrDefault(p, 0.0);
            if (Math.abs(c) < 1e-12) {
                continue;
            }

            if (sb.length() > 0) {
                sb.append(c > 0 ? " + " : " - ");
            } else if (c < 0) {
                sb.append("-");
            }

            double absC = Math.abs(c);
            String numStr = formatNumber(absC);

            if (p == 0) {
                sb.append(numStr);
            } else if (p == 1) {
                if (Math.abs(absC - 1.0) > 1e-12) {
                    sb.append(numStr);
                }
                sb.append("x");
            } else {
                if (Math.abs(absC - 1.0) > 1e-12) {
                    sb.append(numStr);
                }
                sb.append("x^").append(p);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    private static String formatNumber(double value) {
        if (Math.abs(value - Math.rint(value)) < 1e-12) {
            return String.valueOf((long) Math.rint(value));
        }
        return String.format(Locale.US, "%.2f", value);
    }
}
