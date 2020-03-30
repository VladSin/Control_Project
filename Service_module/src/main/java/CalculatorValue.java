public class CalculatorValue {
    public Salary calculateValue(String param){
        Double value = Double.valueOf(param);
        String value1 = String.valueOf(value * 0.6 / 100);
        String value2 = String.valueOf(value * 13 / 100);
        String value3 = String.valueOf(value * 1 / 100);
        String result = String.valueOf(value - value * 14.6 / 100);
        Salary salary = new Salary(param, value1, value2, value3, result);
        return salary;
    }
}
