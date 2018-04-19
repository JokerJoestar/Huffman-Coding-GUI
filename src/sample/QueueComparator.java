package sample;

import java.util.Comparator;

public class QueueComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        if (n1.interval < n2.interval)
            return -1;

        if (n1.interval > n2.interval)
            return 1;

        return 0;
    }
}
