/**
 * connection
 */
public class Connection {
    public Router start;
    public Router end;
    public int hop;

    public Connection(Router theStart, Router theEnd) {
        start = theStart;
        end = theEnd;
        hop = 1;
    }

}