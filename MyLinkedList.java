public class MyLinkedList {

    private static class Node {
        Session data;
        Node next;

        Node(Session data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Purpose: insert a new session at the very front of the list.
    // Test: list = [20, 30], addFirst(session id 10) -> list = [10, 20, 30]
    public MyLinkedList addFirst(Session session) {
        Node newNode = new Node(session);
        newNode.next = head;
        head = newNode;
        size++;
        return this;
    }

    // Purpose: insert a new session at the end of the list.
    // Test: list = [10, 20], addLast(session id 30) -> list = [10, 20, 30]
    public MyLinkedList addLast(Session session) {
        Node newNode = new Node(session);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return this;
    }

    // Purpose: insert a new session in its correct sorted position.
    // Test: list = [10, 30], insertAfter(session id 20) -> list = [10, 20, 30]
    public MyLinkedList insertAfter(Session session) {
        if (head == null || session.getSessionID() < head.data.getSessionID()) {
            return addFirst(session);
        }
        Node current = head;
        while (current.next != null && current.next.data.getSessionID() < session.getSessionID()) {
            current = current.next;
        }
        Node newNode = new Node(session);
        newNode.next = current.next;
        current.next = newNode;
        size++;
        return this;
    }

    // Purpose: find a session by its unique sessionID.
    // Test: list contains id 20 -> returns its info; id 999 -> "Session not found"
    public String searchByID(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.getSessionID() == id) {
                return current.data.toString();
            }
            current = current.next;
        }
        return "Session not found";
    }

    // Purpose: find every session led by a given mentor.
    // Test: two sessions mentored by "Alex Chen" -> both returned
    public String searchByMentor(String mentor) {
        StringBuilder result = new StringBuilder();
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.data.getMentor().equalsIgnoreCase(mentor)) {
                result.append(current.data.toString()).append("\n");
                found = true;
            }
            current = current.next;
        }
        return found ? result.toString() : "No session found for mentor " + mentor;
    }

    // Purpose: remove a session (matched by sessionID) from the list.
    // Test: list = [10, 20, 30], remove(session id 20) -> list = [10, 30]
    public String remove(Session session) {
        if (head == null) {
            return "Session not found";
        }
        if (head.data.getSessionID() == session.getSessionID()) {
            head = head.next;
            size--;
            return "Session removed";
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getSessionID() == session.getSessionID()) {
                current.next = current.next.next;
                size--;
                return "Session removed";
            }
            current = current.next;
        }
        return "Session not found";
    }

    // Purpose: register one participant into a session if seats remain.
    // Test: session has 2/5 seats -> registerParticipant returns true, becomes 3/5
    public boolean registerParticipant(Session session) {
        if (session.getCurrentParticipants() < session.getMaxParticipants()) {
            session.setCurrentParticipants(session.getCurrentParticipants() + 1);
            return true;
        }
        return false;
    }

    // Purpose: print every session in the list to the console.
    public void display() {
        if (head == null) {
            System.out.println("No sessions available.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println(current.data.toString());
            current = current.next;
        }
    }

    // Helper methods (not required by the spec, added for our own use)
    public Session getSessionByID(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.getSessionID() == id) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public Session getFirst() {
        return head == null ? null : head.data;
    }

    public Session getLast() {
        if (head == null) {
            return null;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "No sessions available.\n";
        }
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data.toString()).append("\n");
            current = current.next;
        }
        return sb.toString();
    }
}