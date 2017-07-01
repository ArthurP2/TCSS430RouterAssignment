import java.util.*;

/**
 * This is like a node
 */
public class Router {


    private Map<Router, Boolean> vis = new HashMap<Router, Boolean>();

    private Map<Router, Router> prev = new HashMap<Router, Router>();

    public String myIPAddress;
    public List<Connection> myConnections;
    public List<Router> myDestinations;


    public Router(String theIPAddress){
        myIPAddress = theIPAddress;
        myConnections = new ArrayList<>();
        myDestinations = new ArrayList<>();
    }

    public void addConnection(Router theOther){
        Connection newConnection = new Connection(this, theOther);
        myConnections.add(newConnection);
        Connection newConnection2 = new Connection(theOther, this);
        theOther.myConnections.add(newConnection2);
    }

    public List<Router> getConnections(){
        List<Router> returner = new ArrayList<>();
        for(int i = 0; i < myConnections.size(); i++){
            returner.add(myConnections.get(i).end);
        }
        return returner;
    }

    public void getTable(){
        for(int i = 0; i < myDestinations.size(); i++){
            System.out.println("Router:" + myDestinations.get(i).myIPAddress + " Hops:" + getHops(this, myDestinations.get(i)) + " Port:");
        }
    }

    public void kill(){
        for (int i = 0; i < myConnections.size(); i++){
            for (int q = 0; q < myConnections.get(i).end.myConnections.size(); q++){
                if (myConnections.get(i).end.myConnections.get(q).end.equals(this)) {
                    myConnections.get(i).end.myConnections.remove(q);
                }
            }
        }
        myConnections.clear();
    }

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
