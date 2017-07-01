import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args){

        List<Router> lr = new ArrayList<>();
        Router r0 = new Router("1");
        Router r1 = new Router("2");
        Router r2 = new Router("3");
        Router r3 = new Router("4");
        Router r4 = new Router("5");
        Router r5 = new Router("6");
        Router r6 = new Router("7");
        r0.addConnection(r1);
        r1.addConnection(r2);
        r2.addConnection(r3);
        r3.addConnection(r4);
        r1.addConnection(r4);
        r4.addConnection(r5);
        r5.addConnection(r6);
        lr.add(r0);
        lr.add(r1);
        lr.add(r2);
        lr.add(r3);
        lr.add(r4);
        lr.add(r5);
        lr.add(r6);
        Network n0 = new Network(lr);
        for(int i = 0; i < n0.routers.size(); i++){
            System.out.println("Router " + n0.routers.get(i).myIPAddress + ":" );
            for (int q = 0; q < n0.routers.get(i).myConnections.size(); q++){
                System.out.print(n0.routers.get(i).myConnections.get(q).end.myIPAddress + " ");
            }
            System.out.println();

        }
        r0.myDestinations.add(r1);
        r0.myDestinations.add(r2);
        r0.myDestinations.add(r3);
        r0.myDestinations.add(r4);
        r0.myDestinations.add(r5);
       // r0.myDestinations.add(r6);
        r0.getTable();

        Scanner user_input = new Scanner(System.in);
        String input = "";
        while (!input.equals("end")){
            input = user_input.next();
            if (input.contains("add")){
                int r = Integer.valueOf(input.split("\\.")[1]);
                r-=1;
                if (!r0.myDestinations.contains(lr.get(r))) {
                    r0.myDestinations.add(lr.get(r));
                }
            }
            if (input.contains("disconnect")){
                int r = Integer.valueOf(input.split("\\.")[1]);
                r-=1;
                if (r0.myDestinations.contains(lr.get(r))) {
                    lr.get(r).kill();
                }
            }
            r0.getTable();
        }


    }



}
