import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventModal extends JDialog {
    private EventListPanel eventListPanel;

    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event");
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5,2,5,5));

        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField(20);
        JTextField dateField = new JTextField(20);
        JTextField endDateField = new JTextField(20);
        JTextField locationField = new JTextField(20);
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Deadline", "Meeting"});

        addFormRow(formPanel, "Name: ", nameField);
        addFormRow(formPanel, "Start Date (yyyy-MM-dd HH:mm): ", dateField);
        addFormRow(formPanel, "End Date (yyyy-MM-dd HH:mm): ", endDateField);
        addFormRow(formPanel, "Location: ", locationField);
        addFormRow(formPanel, "Types", typeComboBox);

        add(formPanel, BorderLayout.CENTER);

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addFormRow(JPanel panel, String labelText, JComponent field){
        panel.add(new JLabel(labelText));
        panel.add(field);
    }
}
