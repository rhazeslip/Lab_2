import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(displayPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> new AddEventModal(this));
        controlPanel.add(addEventButton);
        add(controlPanel, BorderLayout.NORTH);
    }

    public void addEvent(Event event) {
        events.add(event);
        displayPanel.add(new EventPanel(event));
        revalidate();
        repaint();
    }
}
