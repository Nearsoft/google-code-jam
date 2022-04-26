import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());
        for(int c = 1;c<=cases;c++){
            int nodes = Integer.parseInt(sc.nextLine());
            Tree tree = new Tree(nodes);
            String[] funs = sc.nextLine().split(" ");
            String[] parents = sc.nextLine().split(" ");
            for(int i = 0; i < nodes; i++)
                tree.addNode(i,Long.parseLong(funs[i]),Integer.parseInt(parents[i])-1);
            System.out.println("Case #"+c+": "+tree.calculate());
        }
        sc.close();
    }
}
class Tree{
    private List<Node> roots;
    private Node[] nodes;
    public BigInteger total;
    public Tree(int nodes){
        this.roots = new ArrayList<Node>();
        this.nodes = new Node[nodes];
        this.total = BigInteger.ZERO;
    }
    public void addNode(int id, long fun, int parentId){
        this.nodes[id] = new Node(fun);
        if(parentId >= 0)
            this.nodes[parentId].children.add(this.nodes[id]);
        else
            roots.add(this.nodes[id]);
    }
    public BigInteger calculate(){
        for (Node root : roots){
            BigInteger sub = BigInteger.valueOf(getMinOfMax(root));
            this.total = this.total.add(sub);
        }
        return this.total;
    }
    private long getMinOfMax(Node node){
        if(node.children.isEmpty())
            return node.fun;
        long[] results = new long[node.children.size()];
        long min = (long) 1000000001;
        for (int i = 0; i < node.children.size(); i++) {
            results[i] = getMinOfMax(node.children.get(i));
            this.total = this.total.add(BigInteger.valueOf(results[i]));
            min = (results[i]<min?results[i]:min);
        }
        this.total = this.total.subtract(BigInteger.valueOf(min));
        return (node.fun>min?node.fun:min);
    }
}
class Node{
    public long fun;
    public List<Node> children;
    Node(long fun){
        this.fun = fun;
        this.children = new ArrayList<Node>();
    }
}
