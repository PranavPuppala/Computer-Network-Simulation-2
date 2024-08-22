public class Node {
    private Event e;
    public Node next;
    public Node prev;

    public Node(Event event){
        this.e = event;
        this.next = null;
        this.prev = null;
    }

    // Returns the event object stored in the node
    public Event getEvent(){
        return this.e;
    }
}
