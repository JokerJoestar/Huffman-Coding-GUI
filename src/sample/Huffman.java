package sample;
import java.util.*;

public class Huffman {
    private String encodedText = null;
    private String decodedText = null;
    private String inputText;
    private Comparator<Node> comparator = new QueueComparator();
    private PriorityQueue<Node> nodes = new PriorityQueue<>(10, comparator);
    private TreeMap<Character, String> codesMap = new TreeMap<>();
    private Map<Character, Integer> charMap = new HashMap<>();

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getEncodedText() {
        return encodedText;
    }

    public String getDecodedText() {
        return decodedText;
    }

    public TreeMap<Character, String> getCodesMap() {
        return codesMap;
    }

    public Map<Character, Integer> getCharsMap() {
        return charMap;
    }

    private void setEncodedText() {
        for (char ch : inputText.toCharArray())
            encodedText += codesMap.get(ch);
    }

    private void setDecodedText() {
        Node node = nodes.peek();

        for (int i = 0; i < encodedText.length();) {
            Node tempNode = node;

            while (tempNode.rightChild != null && tempNode.leftChild != null && i < encodedText.length()) {
                if (encodedText.charAt(i) == '0')
                    tempNode = tempNode.leftChild;
                else
                    tempNode = tempNode.rightChild;

                i++;
            }

            if (tempNode != null)
                if (tempNode.chars.length() == 1)
                    decodedText += tempNode.chars;
        }
    }

    private void calcCharIntervals(PriorityQueue<Node> tree) {
        // Put every count of characters on charMap
        for (char ch : inputText.toCharArray()) {
            if (charMap.containsKey(ch))
                charMap.put(ch, charMap.get(ch) + 1);
            else
                charMap.put(ch, 1);
        }

        // Find interval for all characters from charMap to create the tree
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            if (entry.getValue() > 0) {
                double interval = (double) entry.getValue() / inputText.length();

                tree.add(new Node(interval, entry.getKey().toString()));
            }
        }
    }

    private void createTree(PriorityQueue<Node> tree) {
        while (tree.size() >= 2)
            tree.add(new Node(tree.poll(), tree.poll()));
    }

    private void generateCodes(Node node, String code) {
        if (node != null) {
            if (node.leftChild != null)
                generateCodes(node.leftChild, code + "0");

            if (node.rightChild != null)
                generateCodes(node.rightChild, code + "1");

            // Put the codes you find for every character on codesMap
            if (node.leftChild == null && node.rightChild == null)
                codesMap.put(node.chars.charAt(0), code);
        }
    }

    private void newText() {
        charMap.clear();
        nodes.clear();
        codesMap.clear();
        encodedText = "";
        decodedText = "";
    }

    public void findText() {
        // Clear everything for the new input string
        newText();
        calcCharIntervals(nodes);
        createTree(nodes);
        generateCodes(nodes.peek(), "");
        setEncodedText();
        setDecodedText();
    }
}
