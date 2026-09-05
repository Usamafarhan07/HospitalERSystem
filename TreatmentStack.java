/**
 * A custom LIFO stack that stores completed treatment records.
 * Implemented using linked nodes (no built-in java.util.Stack).
 */
public class TreatmentStack {

    // A single node in the stack, holding one completed treatment record.
    private static class StackNode {
        TreatmentRecord record; // the completed treatment record
        StackNode next;         // link to the record pushed just before this one

        StackNode(TreatmentRecord record) {
            this.record = record;
        }
    }

    private StackNode top; // the most recently pushed record
    private int size;      // how many records are currently on the stack

    /**
     * Pushes a newly completed treatment record onto the stack.
     * @param record the completed treatment record to add
     */
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Pushed treatment record: " + record);
    }

    /**
     * Removes and returns the most recently completed treatment record.
     * @return the popped record, or null if the stack was empty
     */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment stack is empty. No record to pop.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        System.out.println("Popped treatment record: " + record);
        return record;
    }

    /**
     * @return true if there are no treatment records on the stack
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * @return number of treatment records currently on the stack
     */
    public int size() {
        return size;
    }

    /**
     * Prints all treatment records, most recent first (LIFO order).
     */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No treatment records available.");
            return;
        }
        System.out.println("Treatment records (most recent first):");
        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.record);
            current = current.next;
            position++;
        }
    }
}
