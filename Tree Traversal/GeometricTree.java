package comp2402a4;
import java.util.*;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {

    public GeometricTree() {
        super (new GeometricTreeNode());
    }

    /**
     * Set the x and y-coordinates of each node such that it is between the
     * x-coordinate of its two children, no two nodes have the same
     * x-coordinate, and each level of the tree is drawn on separate y-coordinates.
     */
    public void inorderDraw() {
        assignLevels();
        // TODO: use your code here instead
        GeometricTreeNode a = firstNode();
        int i = 0;
        while(a != nil){
            a.position.x = i;
            i++;
            a = nextNode(a);
        }


    }

    /**
     * Set the x- and y-coordinates of each node such that each node
     * has an x-coordinate as small as possible without overlapping
     * any other node at the same level and each level of the tree is
     * drawn on separate y-coordinates
     */
    public void leftistDraw() {
        assignLevels();
        // TODO: use your code here instead
        Queue<GeometricTreeNode> q = new LinkedList<GeometricTreeNode>();
        q.add(r);
        r.position.x = 0;
        r.position.y = 0;
        GeometricTreeNode checking = r;
        while (!q.isEmpty()) {
            GeometricTreeNode u = q.remove();
            if(checking.position.y == u.position.y){
                u.position.x = checking.position.x;
                checking = u;
                checking.position.x++;
            }
            else{
                checking = u;
                checking.position.x =0;
            }

            if (u.left != nil) q.add(u.left);
            if (u.right != nil) q.add(u.right);
        }
        r.position.x = 0;
        r.position.y = 0;

    }

    /**
     * Set the x- and y-coordinate of each node such that the smaller
     * of a node's two subtrees is drawn directly below the node, and the
     * larger is drawn directly to the right, but far enough away that
     * it does not intersect with the smaller subtree.
     */
    HashMap<GeometricTreeNode, Integer> map = new HashMap<>();

    public void assignXY(GeometricTreeNode a, int x, int y){
        if(a == nil) return;
        else{
            a.position.x = x;
            a.position.y = y;

        }
    }

    int x = 0, y = 0;
    private void assignNode(GeometricTreeNode u) {
        assignXY(u,x,y);
        if (u == null) return;
        else if (u.left == nil && u.right != nil){
            x++;
            assignNode(u.right);
        }
        else if (u.right == nil && u.left != nil) {
            x++;
            assignNode(u.left);
        }
        else if(u.left != nil && u.right != nil){
            if(smaller(u) == u.left){
                y++;
                assignNode(u.left);
                y--;
                x++;
                assignNode(u.right);
            }
            else{
                y++;
                assignNode(u.right);
                y--;
                x++;
                assignNode(u.left);
            }
        }
    }

    public void remove_position(GeometricTreeNode u){
        if(u == nil) return;
        assignXY(u,0,0);
        remove_position(u.left);
        remove_position(u.right);

    }
    public void traversal(GeometricTreeNode u){
        GeometricTreeNode s = u;
        int x =0;
        int y = 0;
        while(true){
            while(s.left != nil || s.right != nil){
               if(s.right != nil && s.left != nil){
                   s = smaller(s);
                   y++;
               }
               else if (s.right == nil){
                    s= s.left;
                    x++;
               }
               else if (s.left == nil){
                   s = s.right;
                   x++;
               }
                assignXY(s,x,y);
            }
            while (s != nil && ((s.left == nil || s.right == nil) || larger(s).position.x > 0))
            {
                s = s.parent;
            }
            if (s == nil) {
                break;
            }
            y = s.position.y;
            s = larger(s);
            x++;
            assignXY(s,x,y);
        }

    }

    private GeometricTreeNode smaller(GeometricTreeNode s) {
        if(size(s.left) < size(s.right))
            return s.left;
        else
            return s.right;
    }

    private GeometricTreeNode larger(GeometricTreeNode s) {
        if(size(s.right) > size(s.left))
            return s.right;
        else
            return s.left;
    }

    private void assignTable(GeometricTreeNode u) {
        if (u == null) return;
        map.put(u,size(u));
        assignTable(u.left);
        assignTable(u.right);
    }

    public void balancedDraw() {
        // TODO: use your code here instead
        x =0;
        y =0;
        //r.position.x =0;
        //r.position.y = 0;
        //assignTable(r);
        //assignNode(r);
        remove_position(r);
        traversal(r);
    }

    /**This function randomly assign's x values to each node in the tree.
     It is for demonstration purposes only*/
    protected void randomX(GeometricTreeNode u, Random r) {
        if (u == null) return;
        u.position.x = r.nextInt(60);
        randomX(u.left, r);
        randomX(u.right, r);
    }


    /**This function sets the y values for all nodes in the tree according to their depth*/
    protected void assignLevels() {
        assignLevels(r, 0);
    }

    protected void assignLevels(GeometricTreeNode u, int i) {
        if (u == null) return;
        u.position.y = i;
        assignLevels(u.left, i+1);
        assignLevels(u.right, i+1);
    }

    public static void main(String[] args) {
        GeometricTree t = new GeometricTree();
        galtonWatsonTree(t, 100);
        System.out.println(t);
        t.inorderDraw();
        System.out.println(t);
    }

}
