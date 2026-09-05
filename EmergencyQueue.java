/**
 * A custom FIFO queue used to manage patients waiting in the emergency unit.
 * Implemented using linked nodes (no built-in java.util.Queue).
 */
public class EmergencyQueue {

    // A single node in the queue, holding one waiting patient.
    private static class QueueNode {
        Patient patient;   // the waiting patient
        QueueNode next;    // link to the next patient in line

        QueueNode(Patient patient) {
            this.patient = patient;
        }
    }

    private QueueNode front; // the patient at the front (next to be treated)
    private QueueNode rear;  // the most recently added patient
    private int size;        // how many patients are currently waiting

    /**
     * Adds a patient to the back of the waiting queue.
     * @param patient the patient arriving at the emergency unit
     */
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            // queue was empty
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Enqueued: " + patient.getName() + " (ID: " + patient.getPatientId() + ")");
    }

    /**
     * Removes and returns the patient at the front of the queue (next for treatment).
     * @return the patient who was dequeued, or null if the queue was empty
     */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to dequeue.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null; // queue is now empty
        }
        size--;
        System.out.println("Dequeued for treatment: " + patient.getName() + " (ID: " + patient.getPatientId() + ")");
        return patient;
    }

    /**
     * @return true if there are no patients waiting
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * @return number of patients currently waiting
     */
    public int size() {
        return size;
    }

    /**
     * Prints all patients currently waiting, in FIFO order.
     */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting in the emergency queue.");
            return;
        }
        System.out.println("Patients currently waiting (front -> rear):");
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }
}
