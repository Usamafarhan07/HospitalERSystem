/**
 * Demo.java walks through every required operation for every data structure,
 * in one run, with clear printed section headers.
 * Point your screen recording at this class running for the demonstration video.
 */
public class Demo {

    public static void run() {
        printHeader("1. PATIENT RECORDS - BINARY SEARCH TREE (BST)");
        PatientBST patientBST = new PatientBST();

        Patient p1 = new Patient(105, "Nimal Perera", 34, "0771234567", "Fracture - Left Arm");
        Patient p2 = new Patient(102, "Sanduni Silva", 28, "0719876543", "High Fever");
        Patient p3 = new Patient(110, "Kamal Fernando", 45, "0765554321", "Chest Pain");
        Patient p4 = new Patient(101, "Ishara Gunawardena", 19, "0701112233", "Sprained Ankle");
        Patient p5 = new Patient(108, "Ruwan Jayasuriya", 60, "0752223344", "Breathing Difficulty");

        System.out.println("Inserting patients into the BST...");
        patientBST.insert(p1);
        patientBST.insert(p2);
        patientBST.insert(p3);
        patientBST.insert(p4);
        patientBST.insert(p5);

        System.out.println("\nIn-order traversal (ascending Patient ID):");
        patientBST.inorderTraversal();

        System.out.println("\nSearching for Patient ID 110:");
        Patient found = patientBST.search(110);
        System.out.println(found != null ? found : "Not found.");

        System.out.println("\nSearching for a non-existent Patient ID 999:");
        Patient notFound = patientBST.search(999);
        System.out.println(notFound != null ? notFound : "Not found.");

        System.out.println("\nDeleting Patient ID 102 (Sanduni Silva)...");
        patientBST.delete(102);

        System.out.println("\nIn-order traversal after deletion:");
        patientBST.inorderTraversal();

        printHeader("2. EMERGENCY PATIENT QUEUE - QUEUE (FIFO)");
        EmergencyQueue emergencyQueue = new EmergencyQueue();

        System.out.println("Testing dequeue on an empty queue:");
        emergencyQueue.dequeue();

        System.out.println("\nEnqueueing arriving patients...");
        emergencyQueue.enqueue(p4); // Ishara
        emergencyQueue.enqueue(p1); // Nimal
        emergencyQueue.enqueue(p3); // Kamal

        System.out.println("\nDisplaying the waiting queue:");
        emergencyQueue.displayQueue();

        System.out.println("\nDequeueing the next patient for treatment:");
        Patient nextPatient = emergencyQueue.dequeue();

        System.out.println("\nDisplaying the waiting queue after dequeue:");
        emergencyQueue.displayQueue();

        printHeader("3. TREATMENT HISTORY - STACK (LIFO)");
        TreatmentStack treatmentStack = new TreatmentStack();

        System.out.println("Testing pop on an empty stack:");
        treatmentStack.pop();

        System.out.println("\nPushing completed treatment records...");
        treatmentStack.push(new TreatmentRecord(1, nextPatient.getPatientId(), nextPatient.getName(),
                "Ankle bandaged and pain relief given", "2026-09-01"));
        treatmentStack.push(new TreatmentRecord(2, p1.getPatientId(), p1.getName(),
                "Arm splinted, sent for X-ray", "2026-09-02"));
        treatmentStack.push(new TreatmentRecord(3, p3.getPatientId(), p3.getName(),
                "ECG done, admitted for observation", "2026-09-03"));

        System.out.println("\nDisplaying treatment records (most recent first):");
        treatmentStack.displayStack();

        System.out.println("\nPopping the most recent treatment record:");
        treatmentStack.pop();

        System.out.println("\nDisplaying treatment records after pop:");
        treatmentStack.displayStack();

        printHeader("4. PATIENT VISIT HISTORY - SINGLY LINKED LIST");
        System.out.println("Adding visit history for Patient " + p1.getPatientId() + " (" + p1.getName() + ")...");
        p1.getVisitHistory().addVisit(new Visit(1, "2025-01-10", "Dr. Perera", "Common Cold", "Rest and medication"));
        p1.getVisitHistory().addVisit(new Visit(2, "2025-06-22", "Dr. Silva", "Minor Cut", "Wound cleaned and dressed"));
        p1.getVisitHistory().addVisit(new Visit(3, "2026-03-15", "Dr. Bandara", "Migraine", "Painkillers prescribed"));

        System.out.println("\nDisplaying full visit history:");
        p1.getVisitHistory().displayVisits();

        System.out.println("\nSearching for Visit ID 2:");
        Visit foundVisit = p1.getVisitHistory().searchVisit(2);
        System.out.println(foundVisit != null ? foundVisit : "Visit not found.");

        System.out.println("\nSearching for a non-existent Visit ID 99:");
        Visit missingVisit = p1.getVisitHistory().searchVisit(99);
        System.out.println(missingVisit != null ? missingVisit : "Visit not found.");

        System.out.println("\nRemoving Visit ID 1...");
        p1.getVisitHistory().removeVisit(1);

        System.out.println("\nDisplaying visit history after removal:");
        p1.getVisitHistory().displayVisits();

        printHeader("DEMO COMPLETE");
    }

    private static void printHeader(String title) {
        System.out.println("\n==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }
}
