import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorValueTest {

    @Test
    public void calculateValue() {
        Double value = Double.valueOf("1000");
        String value1 = String.valueOf(value * 0.6 / 100);
        String value2 = String.valueOf(value * 13 / 100);
        String value3 = String.valueOf(value * 1 / 100);
        String result = String.valueOf(value - value * 14.6 / 100);
        Salary salary = new Salary("1000", value1, value2, value3, result);

        assertEquals(salary.getValue1(), "6.0");
        assertEquals(salary.getValue2(), "130.0");
        assertEquals(salary.getValue3(), "10.0");
        assertEquals(salary.getResult(), "854.0");
    }
}
