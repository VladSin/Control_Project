import java.util.List;

public class DefaultSalaryService implements ISalaryService {

    private ISalaryStorage salaryStorage= DefaultSalaryStorage.getInstance();
    private static volatile ISalaryService instance;

    public static ISalaryService getInstance(){
        ISalaryService localInstance = instance;
        if (localInstance == null){
            localInstance = instance;
            if(localInstance == null){
                instance = localInstance = new DefaultSalaryService();
            }
        }
        return localInstance;
    }

    @Override
    public List<Salary> getSalary() {
        return salaryStorage.getSalaries();
    }

    @Override
    public String saveSalary(Salary salary) {
        return salaryStorage.save(salary);
    }
}
