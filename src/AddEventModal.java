import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventModal extends JDialog {
    private EventListPanel eventListPanel;

    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField endDateField = new JTextField();
        JTextField locationField = new JTextField();
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Deadline", "Meeting"});

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Date (yyyy-MM-dd HH:mm):"));
        add(dateField);
        add(new JLabel("End Date (yyyy-MM-dd HH:mm):"));
        add(endDateField);
        add(new JLabel("Location:"));
        add(locationField);
        add(new JLabel("Type:"));
        add(typeComboBox);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            LocalDateTime date = LocalDateTime.parse(dateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            if (typeComboBox.getSelectedItem().equals("Deadline")) {
                eventListPanel.addEvent(new Deadline(name, date));
            } else {
                LocalDateTime endDate = LocalDateTime.parse(endDateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                String location = locationField.getText();
                eventListPanel.addEvent(new Meeting(name, date, endDate, location));
            }
            dispose();
        });
        add(addButton);

        setVisible(true);
    }
}
