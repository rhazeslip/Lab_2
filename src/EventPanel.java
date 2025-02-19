import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;
    private JLabel eventDetails;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        // Create a label to display event details
        eventDetails = new JLabel(event.toString());
        add(eventDetails, BorderLayout.CENTER);

        // Add a "Complete" button for Completable events
        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete(); // Mark the event as complete
                updateDisplay(); // Refresh the display
            });
            add(completeButton, BorderLayout.EAST);
        }

        updateUrgency(); // Set the background color based on urgency
    }

    // Update the display to reflect the current state of the event
    private void updateDisplay() {
        eventDetails.setText(event.toString()); // Update the event details text

        //Changes the background color of button based on completion
        if (event instanceof Completable && ((Completable) event).isComplete()) {
            setBackground(Color.GREEN);
            if (completeButton != null) {
                completeButton.setEnabled(false);// Disable the button if the event is complete
            }
        }
        revalidate(); // Refresh the panel
        repaint();
    }

    // Set the background color based on the urgency of the event, Green does not mean complete
    private void updateUrgency() {
        LocalDateTime now = LocalDateTime.now();
        if (event.getDateTime().isBefore(now)) {
            setBackground(Color.RED); // Overdue
        } else if (event.getDateTime().isBefore(now.plusDays(1))) {
            setBackground(Color.MAGENTA); // Imminent
        } else {
            setBackground(Color.PINK); // Distant
        }
    }
}
