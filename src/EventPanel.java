import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        JLabel eventDetails = new JLabel(event.toString());
        add(eventDetails, BorderLayout.CENTER);

        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                updateUrgency();
                revalidate();
                repaint();
            });
            add(completeButton, BorderLayout.EAST);
        }

        updateUrgency();
    }

    private void updateUrgency() {
        LocalDateTime now = LocalDateTime.now();
        if (event.getDateTime().isBefore(now)) {
            setBackground(Color.RED);
        } else if (event.getDateTime().isBefore(now.plusDays(1))) {
            setBackground(Color.YELLOW);
        } else {
            setBackground(Color.GREEN);
        }
    }
}
