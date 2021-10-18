import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class main{
    public static int num = 100000;
    static class Tree{
        Node root;
        Node nil;
        
        Tree(Node root , Node nil){
            this.root = root;
            this.nil = nil;
        }
    }
    static class Node {
        int value;
        String colour;
        Node parent;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            colour = "BLACK";
            parent = null;
            right = null;
            left = null;
        }
    
    }
    public static void main(String[] args){


        Node nil = new Node(-1);
        nil.colour = "NULL";


        try {
            FileWriter Filei = new FileWriter("RBTreeInsert.txt");
            
            for(int j =1;j<num;j*=2){
                Integer[] Arr = CreateArr(j);
                for(int q=0;q<3;q++){

                Node root = new Node((int)Math.floor(Math.random()*(100-0+1)+0));
                root.parent = nil;
                root.left = nil;
                root.right = nil;
                Tree tree = new Tree(root,nil);

                long start= System.nanoTime();
                build_tree(tree,j,Arr);
                long end = System.nanoTime();
                long tot = (end - start);
                Filei.write(String.valueOf(j)+" "+String.valueOf(tot) +" "+String.valueOf(Depth(tree.root)-1)+"\n");
                }
            }
            Filei.close();
              } catch (IOException e) {
                e.printStackTrace();
              }
        
    }
    public static int Depth(Node x){
        if(x == null){
            return -1 ;
        }
        int left = Depth(x.left);
        int right = Depth(x.right);
    
        if(left > right){
            return(left+1);
        }else{
            return(right+1);
        }
    
    }


    public static Integer[] CreateArr(int Num){
        Integer[] Arr = new Integer[Num];
        for(int k=0;k<Num;k++){
            Arr[k] = k+1;
        }
        List<Integer> ls = Arrays.asList(Arr);
        Collections.shuffle(ls);
        Arr = ls.toArray(Arr);
        return(Arr);
    }
    

    public static void build_tree(Tree T,int Number,Integer[] Arr){
        for(int k = 0 ;k<Number;k++){
            Node temp = new Node(Arr[k]);
            temp.left = T.nil;
            temp.right = T.nil;
            temp.parent = T.nil;
            RB_Insert(T,temp);
        }
    }


    



    public static void Left_Rotate(Tree T, Node x){
        Node y = x.right;
        x.right = y.left;
        if(y.left != T.nil){
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == T.nil){
            T.root = y;
        }else if(x == x.parent.left){
            x.parent.left=y;
        }else{
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public static void Right_Rotate(Tree T, Node x){
        Node y = x.left;
        x.left = y.right;
        if(y.right != T.nil){
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == T.nil){
            T.root = y;
        }else if(x == x.parent.right){
            x.parent.right=y;
        }else{
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public static void RB_Insert(Tree T,Node z){
        Node y = T.nil;
        Node x = T.root;


        
        while(x != T.nil){
            y = x;

            
            if (z.value < x.value){
                x=x.left;
            }else{
                x=x.right;
            }

        }
        z.parent = y;
        if(y == T.nil){
            T.root = z;
        }else if(z.value < y.value){
            y.left = z;
        }else{
            y.right = z;
        }
        z.left = T.nil;
        z.right = T.nil;
        z.colour = "RED";
        
        RB_Insert_Fixup(T,z);
    }

    public static void RB_Insert_Fixup(Tree T,Node z){
        Node y = T.nil;
        while(z.parent.colour == "RED"){
            if(z.parent == z.parent.parent.left){
                    y = z.parent.parent.right;
                    if(y.colour == "RED"){
                        z.parent.colour = "BLACK";
                        y.colour = "BLACK";
                        z.parent.parent.colour = "RED";
                        z = z.parent.parent;
                    }else{
                        if(z == z.parent.right){
                        z=z.parent;
                        Left_Rotate(T,z);
                    }
                    z.parent.colour = "BLACK";
                    z.parent.parent.colour = "RED";
                    Right_Rotate(T,z.parent.parent);
                }
            }else{
                y = z.parent.parent.right;
                if(y.colour == "RED"){
                    z.parent.colour = "BLACK";
                    y.colour = "BLACK";
                    z.parent.parent.colour = "RED";
                    z = z.parent.parent;
                }else{
                    if(z == z.parent.right){
                    z=z.parent;
                    Right_Rotate(T,z);
                }
                z.parent.colour = "BLACK";
                z.parent.parent.colour = "RED";
                Left_Rotate(T,z.parent.parent);
             }
            }
            T.root.colour = "BLACK";
        }
    
    
    }

    public static void PrintNode(Tree T , Node n){
        System.out.println("Node");
        System.out.print(n.value);
        System.out.print(":");
     
        System.out.println("");
        System.out.println("Parent:");
        if(n.parent == T.nil){
         System.out.print("root");
        }else{
         System.out.print(n.parent.value);
        }
        System.out.println("");
        System.out.println("Right Child:");
     
        if(n.right == T.nil){
         System.out.print("null");
        }else{
         System.out.print(n.right.value);
        }
        System.out.println("");
        System.out.println("Left Child:");
     
        if(n.left == T.nil){
         System.out.print("null");
        }else{
         System.out.print(n.left.value);
        }
        System.out.println("");
        System.out.println(n.colour);
        
     }
}