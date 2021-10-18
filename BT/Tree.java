import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



class main{
    public static int num = 100000;
    
 
    

    static class Tree{
        Node root;
        
        Tree(){
            this.root = null;
        }
    }
    static class Node {
        int value;
        Node Parent;
        Node left;
        Node right;
    
        Node(int value) {
            this.value = value;
            Parent = null;
            right = null;
            left = null;
        }
    }

public static Stack<Node> stk= new Stack<>();  
public static void main(String[] args){    
    try {
    FileWriter Filei = new FileWriter("BinaryTreeInsert.txt");
    FileWriter Filed = new FileWriter("BinaryTreeDelete.txt");

    for(int j =1;j<num;j*=2){
        Integer[] Arr = CreateArr(j);
        for(int q=0;q<3;q++){
        Tree tree = new Tree();
        long start= System.nanoTime();
        build_tree(tree,j,Arr);
        long end = System.nanoTime();
        long tot = (end - start);
        Filei.write(String.valueOf(j)+" "+String.valueOf(tot) +" "+String.valueOf(Depth(tree.root))+"\n");

        start= System.nanoTime();
        destruct_tree(tree);
        end = System.nanoTime();
        tot = (end - start);
        Filed.write(String.valueOf(j)+" "+String.valueOf(tot)+"\n");
       
        }
    }
    Filei.close();
    Filed.close();
      } catch (IOException e) {
        e.printStackTrace();
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
        stk.push(temp);
        Tree_insert(T,temp);
    }
}

public static void destruct_tree(Tree T){
    while(stk.empty() == false){
        Tree_delete(T,stk.pop());
    }
}




public static int Depth(Node x){
    if(x == null){
        return -1 ;
    }
    int left = Depth(x.left);
    int right = Depth(x.right);

    if(left>right){
        return(left+1);
    }else{
        return(right+1);
    }

}

public static void PrintNode(Node n){
   System.out.println("Node");
   System.out.print(n.value);
   System.out.print(":");

   System.out.println("");
   System.out.println("Parent:");
   if(n.Parent == null){
    System.out.print("root");
   }else{
    System.out.print(n.Parent.value);
   }
   System.out.println("");
   System.out.println("Right Child:");

   if(n.right == null){
    System.out.print("null");
   }else{
    System.out.print(n.right.value);
   }
   System.out.println("");
   System.out.println("Left Child:");

   if(n.left == null){
    System.out.print("null");
   }else{
    System.out.print(n.left.value);
   }
   System.out.println("");
   
}




public static void Tree_insert(Tree tree, Node input){
Node y = null;
Node x = tree.root;
while(x != null){
    y = x;
    if(input.value < x.value){
        x = x.left;
    }else{
        x = x.right;
    }
}
    input.Parent = y;
    if(y == null){
        tree.root = input;
    }else if(input.value<y.value){
        y.left = input;
    }else{
        y.right = input;
    }
    
}

public static void Transplant(Tree tree, Node u , Node v){
    if(u.Parent == null){
        tree.root = v;
    }else if(u == u.Parent.left){
        u.Parent.left = v;
    }else{
        u.Parent.right=v;
    }
    if(v != null){
        v.Parent = u.Parent;
    }

}

public static Node TreeMinimum(Node x){
while(x.left!=null){
    x=x.left;
}
return x;
}

public static void Tree_delete(Tree tree,Node input){
if(input.left == null){
    Transplant(tree,input,input.right);
}else if(input.right == null){
    Transplant(tree,input,input.left);
}else{
    Node y = TreeMinimum(input.right);
    if(y.Parent != input){
        Transplant(tree,y,y.right);
        y.right = input.right;
        input.right.Parent = y;
    }
    Transplant(tree,input,y);
    y.left = input.left;
    y.left.Parent = y;
}
}

}