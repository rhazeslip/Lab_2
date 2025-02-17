import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    private boolean complete;

    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
        this.complete = false;
    }

    @Override
    public void complete()
    {
        this.complete = true;
    }

    public boolean isComplete()
    {
        return complete;
    }

    public String toString()
    {
        return "Deadline: " + getName() + " at " + getDateTime() + " - " + (isComplete() ? "Complete" : "Incomplete");
    }
}
