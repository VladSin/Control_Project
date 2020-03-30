import java.util.List;

public interface ISalaryStorage {
    List<Salary> getSalaries();
    String save(Salary salary);
}
