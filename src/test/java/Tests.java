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
    public void testGetDeadlinesFromDB() {
        // Настраиваем моки для возвращаемых списков
        when(appSystem.getFinishedDeadlines()).thenReturn(List.of(new Deadline(UUID.randomUUID(), "Finished Task", "2023-10-01", "10:00", "True")));
        when(appSystem.getUnfinishedDeadlines()).thenReturn(List.of(new Deadline(UUID.randomUUID(), "Unfinished Task", "2023-10-02", "12:00", "False")));

        List<Deadline> deadlinesFromDB = appSystem.getDeadlinesFromDB();
        assertEquals(2, deadlinesFromDB.size());
        assertEquals("Finished Task", deadlinesFromDB.get(0).getName());
        assertEquals("Unfinished Task", deadlinesFromDB.get(1).getName());
    }
    @Test
    public void testOrganizeDeadlinesByDate() {
        Deadline deadline1 = new Deadline(UUID.randomUUID(), "Task 1", "2023-10-01", "10:00", "False");
        Deadline deadline2 = new Deadline(UUID.randomUUID(), "Task 2", "2023-10-01", "12:00", "False");
        Deadline deadline3 = new Deadline(UUID.randomUUID(), "Task 3", "2023-10-02", "15:00", "False");

        List<Deadline> deadlines = List.of(deadline1, deadline2, deadline3);

        appSystem.organizeDeadlinesByDate(deadlines);

        assertEquals(2, appSystem.deadlinesByDate.size());
        assertTrue(appSystem.deadlinesByDate.containsKey(LocalDate.parse("2023-10-01")));
        assertTrue(appSystem.deadlinesByDate.containsKey(LocalDate.parse("2023-10-02")));
        assertEquals(2, appSystem.deadlinesByDate.get(LocalDate.parse("2023-10-01")).size());
        assertEquals(1, appSystem.deadlinesByDate.get(LocalDate.parse("2023-10-02")).size());
    }

}
