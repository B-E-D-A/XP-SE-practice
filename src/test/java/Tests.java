import org.junit.jupiter.api.Test;

public class Tests {

//    @Test
//    void createDeadline(){
//        Deadline deadline = new Deadline("Тестовый дедлайн", LocalDateTime.now().plusMinutes(5));
//        assertFalse(deadline.isApproaching(10)); // Не должен приближаться через 10 минут
//
//        deadline = new Deadline("Тест", LocalDateTime.now().plusMinutes(5));
//        assertTrue(deadline.isApproaching(6));
//    }
//
//    private TaskManager taskManager;
//
//    @BeforeEach
//    void setUp() {
//        taskManager = new TaskManager();
//    }
//
//    @Test
//    void testAddDeadline() {
//        Deadline deadline = new Deadline("Тест", LocalDateTime.now().plusDays(1));
//        taskManager.addDeadline(deadline);
//        assertEquals(1, taskManager.getDeadlines().size());
//        assertEquals("Тест", taskManager.getDeadlines().get(0).getTitle());
//    }
//
//    @Test
//    void testNotifyUser() {
//        NotificationService notificationService = new NotificationService();
//        Deadline deadline = mock(Deadline.class);
//        when(deadline.getTitle()).thenReturn("Тест");
//
//        // Печать уведомления
//        notificationService.notifyUser(deadline);
//    }

}
