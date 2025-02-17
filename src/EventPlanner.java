import javax.swing.*;
import java.time.LocalDateTime;

public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        EventListPanel eventListPanel = new EventListPanel();
        addDefaultEvents(eventListPanel);

        frame.add(eventListPanel);
        frame.setVisible(true);
    }

    public static void addDefaultEvents(EventListPanel eventListPanel) {
        eventListPanel.addEvent(new Deadline("Submit Report", LocalDateTime.of(2023, 10, 15, 23, 59)));
        eventListPanel.addEvent(new Meeting("Team Sync", LocalDateTime.of(2023, 10, 10, 10, 0), LocalDateTime.of(2023, 10, 10, 11, 0), "Conference Room"));
    }
}
