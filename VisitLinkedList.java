/**
 * A singly linked list holding one patient's past visit history.
 * Supports adding, removing, searching and displaying visits.
 */
public class VisitLinkedList {

    // A single node in the linked list, holding one visit.
    private static class VisitNode {
        Visit visit;      // the visit data stored at this node
        VisitNode next;   // link to the next visit in the history

        VisitNode(Visit visit) {
            this.visit = visit;
        }
    }

    private VisitNode head; // first visit in the history

    /**
     * Adds a new visit to the end of the patient's visit history.
     * @param visit the visit to add
     */
    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
            return;
        }
        VisitNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    /**
     * Removes a visit from the history by its Visit ID.
     * @param visitId the ID of the visit to remove
     * @return true if a visit was removed, false if not found
     */
    public boolean removeVisit(int visitId) {
        // visitId : the ID of the visit we want to remove
        if (head == null) {
            return false;
        }
        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            return true;
        }
        VisitNode current = head;
        while (current.next != null && current.next.visit.getVisitId() != visitId) {
            current = current.next;
        }
        if (current.next == null) {
            return false; // reached the end without finding the visit
        }
        current.next = current.next.next; // skip over the removed node
        return true;
    }

    /**
     * Searches the history for a visit by its Visit ID.
     * @param visitId the ID of the visit to find
     * @return the matching Visit, or null if not found
     */
    public Visit searchVisit(int visitId) {
        // visitId : the ID of the visit to search for
        VisitNode current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Prints every visit in this patient's history, in chronological (added) order.
     */
    public void displayVisits() {
        if (head == null) {
            System.out.println("No visit history recorded for this patient.");
            return;
        }
        VisitNode current = head;
        while (current != null) {
            System.out.println(current.visit);
            current = current.next;
        }
    }
}
