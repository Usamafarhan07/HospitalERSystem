/**
 * Binary Search Tree that stores Patient records, keyed by Patient ID.
 * Supports insert, search, delete and in-order traversal (ascending Patient ID).
 */
public class PatientBST {

    // A single node in the BST. Holds one Patient plus links to left/right subtrees.
    private static class Node {
        Patient patient;  // the patient data stored at this node
        Node left;        // left child (smaller patient IDs)
        Node right;       // right child (larger patient IDs)

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node root; // the top of the tree

    /**
     * Inserts a new patient into the BST, ordered by patientId.
     * @param patient the patient to insert
     */
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node current, Patient patient) {
        // current : the node we are currently checking (null means we found the empty spot)
        // patient : the patient we are trying to insert
        if (current == null) {
            return new Node(patient);
        }
        if (patient.getPatientId() < current.patient.getPatientId()) {
            current.left = insertRec(current.left, patient);
        } else if (patient.getPatientId() > current.patient.getPatientId()) {
            current.right = insertRec(current.right, patient);
        } else {
            System.out.println("Patient ID " + patient.getPatientId() + " already exists. Insert skipped.");
        }
        return current;
    }

    /**
     * Searches for a patient using their Patient ID.
     * @param patientId the ID to search for
     * @return the matching Patient, or null if not found
     */
    public Patient search(int patientId) {
        return searchRec(root, patientId);
    }

    private Node searchNode(int patientId) {
        return searchNodeRec(root, patientId);
    }

    private Node searchNodeRec(Node current, int patientId) {
        // current   : node currently being examined
        // patientId : the ID we are looking for
        if (current == null || current.patient.getPatientId() == patientId) {
            return current;
        }
        if (patientId < current.patient.getPatientId()) {
            return searchNodeRec(current.left, patientId);
        }
        return searchNodeRec(current.right, patientId);
    }

    private Patient searchRec(Node current, int patientId) {
        Node found = searchNodeRec(current, patientId);
        return (found == null) ? null : found.patient;
    }

    /**
     * Deletes the patient with the given Patient ID from the BST, if present.
     * @param patientId the ID of the patient to delete
     */
    public void delete(int patientId) {
        root = deleteRec(root, patientId);
    }

    // Helper used by delete(): finds the node with the smallest patientId in a subtree.
    // Needed to find the in-order successor when deleting a node with two children.
    private Node findMin(Node node) {
        // node : root of the subtree to search for the minimum in
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node deleteRec(Node current, int patientId) {
        // current   : node currently being examined
        // patientId : the ID of the patient to remove
        if (current == null) {
            System.out.println("Patient ID " + patientId + " not found. Nothing deleted.");
            return null;
        }

        if (patientId < current.patient.getPatientId()) {
            current.left = deleteRec(current.left, patientId);
        } else if (patientId > current.patient.getPatientId()) {
            current.right = deleteRec(current.right, patientId);
        } else {
            // Found the node to delete.
            if (current.left == null && current.right == null) {
                return null; // no children
            }
            if (current.left == null) {
                return current.right; // only right child
            }
            if (current.right == null) {
                return current.left; // only left child
            }
            // Two children: replace with the smallest value in the right subtree,
            // then delete that smallest value from the right subtree.
            Node successor = findMin(current.right);
            current.patient = successor.patient;
            current.right = deleteRec(current.right, successor.patient.getPatientId());
        }
        return current;
    }

    /**
     * Prints all patients in ascending order of Patient ID.
     */
    public void inorderTraversal() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        inorderRec(root);
    }

    private void inorderRec(Node current) {
        // current : node currently being visited
        if (current == null) {
            return;
        }
        inorderRec(current.left);
        System.out.println(current.patient);
        inorderRec(current.right);
    }
}
