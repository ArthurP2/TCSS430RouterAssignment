import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This is the router node
 */
public class Router {


    private Map<Router, Boolean> vis = new HashMap<Router, Boolean>();

    private Map<Router, Router> prev = new HashMap<Router, Router>();

    public String myRouterID;
    public List<Connection> myConnections;
    public Map<String, Router> myDestinations;
    public Map<String, Integer> myIpAddresses;
    public String timeStamp;
    int x, y;

    public Router(String theRouterID, int xx, int yy){
        myRouterID = theRouterID;
        myConnections = new ArrayList<>();
        myDestinations = new HashMap<>();
        myIpAddresses = new HashMap<>();
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        x = xx;
        y = yy;
    }

    /**
     * Adds a connection between routers.
     *
     * @param theOther is the other router.
     */
    public void addConnection(Router theOther){
        Connection newConnection = new Connection(this, theOther);
        myConnections.add(newConnection);
        Connection newConnection2 = new Connection(theOther, this);
        theOther.myConnections.add(newConnection2);
        timeUpdate();
    }

    /**
     * Gets connections.
     *
     * @return the connections
     */
    public List<Router> getConnections(){
        List<Router> returner = new ArrayList<>();
        for(int i = 0; i < myConnections.size(); i++){
            returner.add(myConnections.get(i).end);
        }
        return returner;
    }

    /**
     * Returns the routing table.
     */
    public void getTable(){
        System.out.print("Table for router: " + myRouterID +"\n");
        System.out.println("-----------------------------------------------------------------");
        System.out.format("%20s%10s%10s%25s\n", "Destination", "Hops","Port", "Time");
        System.out.print("-----------------------------------------------------------------");
        for(Map.Entry<String, Router> entry: myDestinations.entrySet()) {
            String ip = entry.getKey();
            int hops = getHops(this, entry.getValue());
            int port = entry.getValue().myIpAddresses.get(entry.getKey());
            System.out.println("");
            System.out.format("%20s%10s%10s%25s\n", ip, hops, port, entry.getValue().timeStamp);
        }
        System.out.print("-----------------------------------------------------------------");
        System.out.println();
    }

    /**
     * Disconnects the router from other routers.
     *
     */
    public void kill(){
        for (int i = 0; i < myConnections.size(); i++){
            for (int q = 0; q < myConnections.get(i).end.myConnections.size(); q++){
                if (myConnections.get(i).end.myConnections.get(q).end.equals(this)) {
                    myConnections.get(i).end.myConnections.remove(q);
                }
            }
        }
        timeUpdate();
        myConnections.clear();
    }

    /**
     * Updates the time stamp.
     */
    public void timeUpdate(){
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    /**
     * Finds shortest path.
     *
     * @param start the starting router.
     * @param finish the ending router.
     * @return
     */
    public int getHops(Router start, Router finish){
        List directions = new LinkedList();
        Queue<Router> q = new LinkedList();
        Router current = start;
        q.add(current);
        vis.put(current, true);
        while(!q.isEmpty()){

            current = q.remove();
            if (current.equals(finish)){
                break;
            }else{
                for(Router router : current.getConnections()){
                    if(!vis.containsKey(router)){
                        q.add(router);
                        vis.put(router, true);
                        prev.put(router, current);
                    }
                }
            }
        }
        for(Router router = finish; router != null; router = prev.get(router)) {
            directions.add(router);
        }
        vis.clear();
        prev.clear();
        int x = directions.size()-1;
        if (!current.equals(finish)){
            x = -1;
        }
        return x;
    }


}
