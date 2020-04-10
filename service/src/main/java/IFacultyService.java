import it_academy.control_project.data.university.Faculty;

import java.util.List;

public interface IFacultyService {

    List<Faculty> getFaculty();
    Long saveFaculty(Faculty faculty);
}
