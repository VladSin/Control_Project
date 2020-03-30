import java.util.List;

public interface ISalaryService {
    List<Salary> getSalary();
    String saveSalary(Salary salary);
}
