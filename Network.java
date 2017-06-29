import java.util.*;


public class Network{

    public List<Router> routers;

    private Map<Router, Boolean> vis = new HashMap<Router, Boolean>();

    private Map<Router, Router> prev = new HashMap<Router, Router>();

    public Network(List<Router> theRouters){
        routers = theRouters;
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
        if (!current.equals(finish)){
            System.out.println("can't reach destination");
        }
        for(Router router = finish; router != null; router = prev.get(router)) {
            directions.add(router);
        }
        return directions.size()-1;
    }


}
