import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This ist he main class.
 *
 *
 */
public class main {

    public static void main(String[] args){
        //Create a list of routers.
        List<Router> lr = new ArrayList<>();
        Router r0 = new Router("1",100,75);
        Router r1 = new Router("2",225,75);
        Router r2 = new Router("3",350,75);
        Router r3 = new Router("4",500, 75);
        Router r4 = new Router("5", 100, 250);
        Router r5 = new Router("6", 350, 250);
        Router r6 = new Router("7",255, 375);

        //Connects the routers
        r0.addConnection(r1);
        r1.addConnection(r2);
        r2.addConnection(r3);
        r3.addConnection(r4);
        r4.addConnection(r5);
        r5.addConnection(r6);
        r0.addConnection(r4);

        //Adds routers to router list
        lr.add(r0);
        lr.add(r1);
        lr.add(r2);
        lr.add(r3);
        lr.add(r4);
        lr.add(r5);
        lr.add(r6);

        //Generates random Ip Address
        List<String> randomIps = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            String ii = randomIp();
            randomIps.add(ii);
        }


        //Puts IP Addresses to router ports
        r0.myIpAddresses.put(randomIps.get(0), 80);
        r6.myIpAddresses.put(randomIps.get(1), 40);
        r6.myIpAddresses.put(randomIps.get(2), 90);
        r6.myIpAddresses.put(randomIps.get(3), 90);
        r4.myIpAddresses.put(randomIps.get(4), 21);
        r4.myIpAddresses.put(randomIps.get(5), 180);
        r4.myIpAddresses.put(randomIps.get(6), 90);
        r3.myIpAddresses.put(randomIps.get(7), 512);
        r2.myIpAddresses.put(randomIps.get(8), 40);
        r1.myIpAddresses.put(randomIps.get(9), 50);


        //Creates a network graph from the routers.
        Network n0 = new Network(lr,"Network");
        n0.setSize(900,900);
        n0.setVisible(true);

        //Creates a table of destinations for router 1.
        //Router 1 will be the router with the visible table.
        r0.myDestinations.put(randomIps.get(0), r0);
        r0.myDestinations.put(randomIps.get(1), r6);
        r0.myDestinations.put(randomIps.get(2), r6);
        r0.myDestinations.put(randomIps.get(3), r6);
        r0.myDestinations.put(randomIps.get(4), r4);
        r0.myDestinations.put(randomIps.get(5), r4);
        r0.myDestinations.put(randomIps.get(6), r4);
        r0.myDestinations.put(randomIps.get(7), r3);
        r0.myDestinations.put(randomIps.get(8), r2);
        r0.myDestinations.put(randomIps.get(9), r1);
       // r0.myDestinations.add(r6);
        r0.getTable();

        //Gets user input.
        Scanner user_input = new Scanner(System.in);
        String input = "";
        while (!input.equals("end")){
            input = user_input.next();
            //Connects two routers. Ex. connect.1.2
            if (input.contains("connect")){
                int r = Integer.valueOf(input.split("\\.")[1]);
                int l = Integer.valueOf(input.split("\\.")[2]);
                r-=1;
                l-=1;
                n0.routers.get(r).addConnection(n0.routers.get(l));
                n0.repaint();
            }
            //Disconnects a router from other routers. Ex. kill.3
            if (input.contains("kill")){
                int r = Integer.valueOf(input.split("\\.")[1]);
                r-=1;
                lr.get(r).kill();
                n0.repaint();
            }
            r0.getTable();
        }


    }

    public static String randomIp(){
               Random r = new Random();
               return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
           }





}
