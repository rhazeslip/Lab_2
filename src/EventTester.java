import java.time.LocalDateTime;

public class EventTester {
    public static void main(String[] args) {
        testDeadline();
        testMeeting();
    }

    public static void testDeadline() {
        Deadline deadline = new Deadline("Submit Report", LocalDateTime.of(2023, 10, 15, 23, 59));
        System.out.println(deadline);
        deadline.complete();
        System.out.println(deadline);
    }

    public static void testMeeting() {
        Meeting meeting = new Meeting("Team Sync", LocalDateTime.of(2023, 10, 10, 10, 0), LocalDateTime.of(2023, 10, 10, 11, 0), "Conference Room");
        System.out.println(meeting);
        meeting.complete();
        System.out.println(meeting);
    }
}
