import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * This is the network.
 */
public class Network  extends JFrame {

    int width;
    int height;

    public List<Router> routers;


    public Network(List<Router> theRouters, String name){
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        routers = theRouters;
        width = 30;
        height = 30;
    }


    public void paint(Graphics g) { // draw the routers and connections
        super.paintComponents(g);
        FontMetrics f = g.getFontMetrics();
        int RoutersHeight = Math.max(height, f.getHeight());
        g.clearRect(30, 30, width, height);
        g.setColor(Color.black);
        for (Router r : routers) {
            for (Connection c : r.myConnections)
            g.drawLine(c.start.x, c.start.y,
                    c.end.x, c.end.y);
        }

        for (Router n : routers) {
            int RoutersWidth = Math.max(width, f.stringWidth(n.myRouterID)+width/2);
            if(n.myIpAddresses.size()>0){
                RoutersWidth = Math.max(width, 120);
            }
            int hh = 1;
            if (n.myIpAddresses.size()>0){
                hh = n.myIpAddresses.size() + 1;
            }
            g.setColor(Color.white);
            g.fillOval(n.x-RoutersWidth/2, n.y-((RoutersHeight*hh)/2),
                    RoutersWidth, (RoutersHeight*hh));
            g.setColor(Color.black);
            g.drawOval(n.x-RoutersWidth/2, n.y-(RoutersHeight*hh)/2,
                    RoutersWidth, (RoutersHeight*hh));

            g.drawString(n.myRouterID, n.x-f.stringWidth(n.myRouterID)/2,
                    n.y+f.getHeight()/2);
            int i = 0;
            for(Map.Entry<String, Integer> entry: n.myIpAddresses.entrySet()) {
                g.drawString(entry.getKey(), n.x - f.stringWidth(entry.getKey()) / 2,
                        n.y + (f.getHeight() / 2)+(15*(i+1)));
                i++;
            }
        }
    }


}
