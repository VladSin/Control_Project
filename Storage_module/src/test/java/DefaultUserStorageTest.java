import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultUserStorageTest {


//Абсолютно бесполезный тест, знаю, но пусть будет)))

    @Test
    public void save() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            User user = new User("name", "surname", "phone", "email");
            users.add(user);
        }
        assertAll("All users",
                ()->assertEquals(users.get(0).getName(),"name"),
                ()->assertEquals(users.get(1).getSurname(),"surname"),
                ()->assertEquals(users.get(2).getPhone(),"phone"),
                ()->assertEquals(users.get(3).getEmail(),"email"));
    }
}
