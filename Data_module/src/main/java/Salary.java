public class Salary {
    private String salary;

    private String value1;
    private String value2;
    private String value3;
    private String result;

    public Salary(){}

    public Salary(String salary) {
        this.salary = salary;
    }

    public Salary(String salary, String value1, String value2, String value3, String result) {
        this.salary = salary;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.result = result;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getValue1() {
        return value1;
    }
    public String getValue2() {
        return value2;
    }
    public String getValue3() {
        return value3;
    }
    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salary='" + salary + '\'' +
                ", value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", value3='" + value3 + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
