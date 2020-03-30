import java.util.ArrayList;
import java.util.List;

public class DefaultSalaryStorage implements ISalaryStorage {

    List<Salary> salaries;
    private static volatile ISalaryStorage instance;

    public DefaultSalaryStorage() {
        this.salaries = new ArrayList<>();
    }

    public static ISalaryStorage getInstance(){
        ISalaryStorage localInstance = instance;
        if(localInstance == null){
            synchronized (ISalaryStorage.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DefaultSalaryStorage();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<Salary> getSalaries() {
        return salaries;
    }

    @Override
    public String save(Salary salary) {
        salaries.add(salary);
        return salary.getSalary();
    }

    public void setSalaries(Salary salary) {
        this.salaries.add(salary);
    }
}
