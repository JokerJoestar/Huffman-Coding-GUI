package sample;

public class Node {
    Node leftChild = null;
    Node rightChild = null;
    double interval;
    String chars;


    public Node(double interval, String chars) {
        this.interval = interval;
        this.chars = chars;
    }

    public Node(Node leftChild, Node rightChild) {
        interval = leftChild.interval + rightChild.interval;
        chars = leftChild.chars + rightChild.chars;

        if(leftChild.interval < rightChild.interval) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
        else {
            this.leftChild = rightChild;
            this.rightChild = leftChild;
        }
    }
}
