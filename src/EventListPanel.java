import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JCheckBox hideComplete;
    private JCheckBox hideMeetings;
    private JCheckBox hideDeadlines;

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

        String[] sortOptions = {"Sort by Name", "Sort by Reverse Name", "Sort by Earliest Date", "Sort by Latest Date"};
        JComboBox<String> sortDropDownBox = new JComboBox<>(sortOptions);
        sortDropDownBox.addActionListener(e -> {
            String selectedOption = (String) sortDropDownBox.getSelectedItem();
            sortEvents(selectedOption);
            refreshDisplay();
        });
        controlPanel.add(new JLabel("Sort by:"));
        controlPanel.add(sortDropDownBox);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filter Events"));

        //Adds all the checkboxes for filters
        hideComplete = new JCheckBox("Hide Completed Tasks");
        hideMeetings = new JCheckBox("Hide Meetings");
        hideDeadlines = new JCheckBox("Hide Deadlines");

        //Adds action listeners for checkboxes
        hideComplete.addActionListener(e -> refreshDisplay());
        hideMeetings.addActionListener(e -> refreshDisplay());
        hideDeadlines.addActionListener(e -> refreshDisplay());

        //Adds checkboxes to filter panel
        filterPanel.add(hideComplete);
        filterPanel.add(hideMeetings);
        filterPanel.add(hideDeadlines);

        //Adds filter panel to layout
        add(filterPanel, BorderLayout.SOUTH);
    }

    private void sortEvents(String sortOption){
        switch (sortOption) {
            case "Sort by Name":
                Collections.sort(events, Comparator.comparing(Event::getName));
                break;
            case "Sort by Reverse Name":
                Collections.sort(events, Comparator.comparing(Event::getName).reversed());
                break;
            case "Sort by Earliest Date":
                Collections.sort(events, Comparator.comparing(Event::getDateTime));
                break;
            case "Sort by Latest Date":
                Collections.sort(events, Comparator.comparing(Event::getDateTime).reversed());
                break;
        }

    }

    private void refreshDisplay(){
        displayPanel.removeAll();
        for (Event event : events){
            if (shouldDisplayEvent(event)) {
                displayPanel.add(new EventPanel(event));
            }
        }
        revalidate();
        repaint();
    }

    public void addEvent(Event event) {
        events.add(event);
        displayPanel.add(new EventPanel(event));
        refreshDisplay();
    }

    private boolean shouldDisplayEvent(Event event) {
        //Checks for complete tasks and hides them.
        if (hideComplete.isSelected() && event instanceof Completable && ((Completable)event).isComplete()){
            return false;
        }

        //Checks for meetings and hides them.
        if (hideMeetings.isSelected() && event instanceof Meeting){
            return false;
        }

        //Checks for deadlines and hides them.
        if (hideDeadlines.isSelected() && event instanceof Deadline){
            return false;
        }

        return true;
    }
}
