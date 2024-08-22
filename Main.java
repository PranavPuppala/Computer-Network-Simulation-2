import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
public class Main{
	public static void main(String[] args) throws IOException{
        
        // Event list to store all message and timer objects
        LinkedEventList eventList = new LinkedEventList();

        // Arraylist to store central host and its neighbors
        ArrayList<SimpleHost> hosts = new ArrayList<SimpleHost>();

		FileInputStream fs  = new FileInputStream("simulation4.txt");
		Scanner scn = new Scanner(fs);

        // Instantiating the central host
        int hostAddress = scn.nextInt();
        SimpleHost centralHost = new SimpleHost();
        centralHost.setHostParameters(hostAddress, eventList);
        hosts.add(centralHost);

        int neighborAddress = scn.nextInt();

        // Instantiating the neighboring hosts
        while (neighborAddress != -1){
            int distance = scn.nextInt();
            SimpleHost neighborHost = new SimpleHost();
            neighborHost.setHostParameters(neighborAddress, eventList);
            centralHost.addNeighbor(neighborHost, distance);
            neighborHost.addNeighbor(centralHost, distance);
            hosts.add(neighborHost);
            neighborAddress = scn.nextInt();
        }

        // Running simulations
        while (scn.hasNextLine()){
            int senderAddress = scn.nextInt();
            int receiverAddress = scn.nextInt();
            int interval = scn.nextInt();
            int duration = scn.nextInt();

            for(SimpleHost simpleHost: hosts){
                if(simpleHost.getHostAddress() == senderAddress){
                    simpleHost.sendPings(receiverAddress, interval, duration);
                    break;
                }
            }
        }

        // Handling each event in the LinkedEventList
        while (eventList.size() > 0){
            Event event = eventList.removeFirst();
            event.handle();
        }

        scn.close();
    }
}
