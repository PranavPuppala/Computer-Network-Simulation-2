public class Message extends Event{
    private String message;
    private int SrcAddress;
    private int DestAddress;
    private int distance;
    private Host host;

    // Message constructor
    public Message(String Message, int srcadd, int destadd){
        this.message = Message;
        this.SrcAddress = srcadd;
        this.DestAddress = destadd;
    }

    // Returns the message string
    public String getMessage(){
        return this.message;
    }

    // Returns the senders address
    public int getSrcAddress(){
        return this.SrcAddress;
    }

    // Returns the destination address
    public int getDestAddress(){
        return this.DestAddress;
    }

    // Essentially a setter method
    public void setNextHop(Host neighbor, int seperation){
        this.distance = seperation;
        this.host = neighbor;
    }

    // sets the insertion time of the message instance and also its arrival time
    @Override
    public void setInsertionTime(int currentTime){
        this.insertionTime = currentTime;
        this.arrivalTime = insertionTime + distance;
    }

    // Not needed
    @Override
    public void cancel(){
        
    }

    @Override
    public void handle(){
        this.host.receive(this);
    }
}