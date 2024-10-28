import org.app.utils.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Tests {

    @Test
    void createDeadline(){
        Deadline deadline = new Deadline("Задача 1", "2023-10-28", "14:00", "Нет");

        assertNotNull(deadline.getId());
        assertEquals("Задача 1", deadline.getName());
        assertEquals("2023-10-01", deadline.getDate());
        assertEquals("12:00", deadline.getTime());
        assertEquals("Нет", deadline.getIsFinished());
    }

    @Test
    void addDeadline(){

    }

}
