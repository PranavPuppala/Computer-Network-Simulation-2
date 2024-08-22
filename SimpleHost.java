public class SimpleHost extends Host{
    private int destAddress;
    private int interval;
    private int duration;
    private int durationTimer;

    // sendPings creates two timers
    public void sendPings(int destAddr, int inter, int dur){
        this.destAddress = destAddr;
        this.interval = inter;
        this.duration = dur;
        durationTimer = newTimer(dur);
        newTimer(interval);
    }

    @Override
    protected void receive(Message msg){

        // Executes the following lines if the message is a ping request
        if(msg.getMessage().equals("request")){
            System.out.println("[" + msg.getArrivalTime() + "ts] Host " + msg.getDestAddress() + ": Ping request from host " + msg.getSrcAddress());
            Message response = new Message("response", msg.getDestAddress(), msg.getSrcAddress());
            this.sendToNeighbor(response);
        }

        // Executes the following lines if the message is a ping response
        else if(msg.getMessage().equals("response")){
            int rtt = (msg.getArrivalTime() - msg.getInsertionTime()) * 2; // Time taken for one round trip
            System.out.println("[" + msg.getArrivalTime() + "ts] Host " + msg.getDestAddress() + ": Ping response from host " + msg.getSrcAddress() + " (RTT = " + rtt + "ts)");
        }
    }

    @Override
    protected void timerExpired(int eventId){
        if(eventId == durationTimer){
            System.out.println("[" + this.getCurrentTime() + "ts] Host " + this.getHostAddress() + ": Stopped sending pings");
        }
        else{
            Message message = new Message("request", this.getHostAddress(), this.destAddress);
            sendToNeighbor(message);
            System.out.println("[" + this.getCurrentTime() + "ts] Host " + this.getHostAddress() + ": Sent ping to host " + this.destAddress);

            if(this.getCurrentTime() + interval < duration){
                newTimer(interval);
            }
        }

    }

    // Might not be required.
    @Override
    protected void timerCancelled(int eventId){
    }  
}