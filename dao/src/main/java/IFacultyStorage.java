import it_academy.control_project.data.university.Faculty;

import java.util.List;

public interface IFacultyStorage {
    List<Faculty> getFaculty();
    Long save(Faculty faculty);
}
