public class LinkedEventList implements FutureEventList {
    private Node head;
    private int size = 0;
    private int simulationTime = 0;


    // Removes the first event of the LinkedEventList
    @Override
    public Event removeFirst(){
        Node first = this.head;

        // Case for when there is a single element in the LinkedEventList
        if(size == 1){
            simulationTime = first.getEvent().getArrivalTime();
            this.head = null;
            this.size--;
            return first.getEvent();
        }

        // Case for when there are more elements in the LinkedEvenetList
        // shift the head position to the next event after the first event gets removed
        else if(size > 1){
            simulationTime = first.getEvent().getArrivalTime();
            this.head = first.next;
            this.size--;
            return first.getEvent();
        }


        return null;
    }

    // Not needed
    @Override
    public boolean remove(Event e){
        return false;
    }

    // Inserts events in the list
    @Override
    public void insert(Event e){
        Node newNode = new Node(e);
        e.setInsertionTime(simulationTime);

        // If the list is empty, the inserted event will be the first one
        if(size == 0){
            this.head = newNode;
        }

        // insert an event at the beginning of the list if it has an arrival time thats less than the event that thats already in the list
        else if(e.getArrivalTime() < head.getEvent().getArrivalTime()){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        // find a suitable position to insert an event based on its arrival time
        else{
            Node currNode = this.head;
            while (currNode.next != null && (currNode.next.getEvent().getArrivalTime() <= e.getArrivalTime())){
                currNode = currNode.next;
            }

            newNode.next = currNode.next;
            if(currNode.next != null){
                currNode.next.prev = newNode;
            }
            currNode.next = newNode;
            newNode.prev = currNode;
        }

        this.size++;
    }

    // Returns the size of the LinkedEventList
    @Override
    public int size(){
        return this.size;
    }

    // Not needed
    @Override
    public int capacity(){
        return 0;
    }

    // Returns the simulation time 
    @Override
    public int getSimulationTime(){
       return simulationTime;
    }
}