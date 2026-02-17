package hw;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private Node root; //pointer to the root
    private int size; //hold num of spells in the tree
    private String category; //the category that the tree represent
	
	// private Node class for the AVL Tree nodes
    private class Node { 
        private Spell spell; //value
        private Node left;
        private Node right;
        private int height;

        private Node(Spell spell) {
            this.spell=spell;
            this.left=null;
            this.right=null;
            this.height=1;
        }
    }

    // Constructor
    public AVLTree(Spell spell) {
        this.root= new Node(spell);
        this.size=1;
        this.category=spell.getCategory();
    }
    //getter fot the tree height-according to the root height
    public int getTreeHeight(){
        return rec_helper_tree_height(root);
    }
    //helper counting tree height
    private int rec_helper_tree_height(Node node){
        if (node==null) return -1;
        return 1+Math.max(rec_helper_tree_height(node.left),rec_helper_tree_height(node.right));
    }
    //getter for the tree size
    public int getSize(){
        return size;
    }
    //getter for the tree category
    public String getCategory() {
        return category;
    }

    //search in the tree by power level and spell name
    public Spell search(String spellName, int powerLevel) {
        return search_rec(root, spellName, powerLevel);
    }
    //recursive helper for search, AVL searching.
    private Spell search_rec(Node node,String spellName, int powerLevel){
         if (node==null){
            return null;
        }
         int curr_level= node.spell.getPowerLevel();
         if (powerLevel==curr_level && spellName.equals(node.spell.getName())){
             return node.spell;
         }
         if (powerLevel<curr_level){
             return search_rec(node.left,spellName,powerLevel);
         }
         if (powerLevel>curr_level) {
             return search_rec(node.right,spellName,powerLevel);
         }
         else{
             return null; //the power is the same but the name is different
         }
    }


    public void insert(Spell spell) {
        root= insert_rec(root,spell);
        size++;
    }

    //recursive for insert: add to the correct position and balance the tree.
    private Node insert_rec(Node node, Spell spell){
        //if the tree is empty
        if(node==null){
            return new Node(spell);
        }
        int new_power= spell.getPowerLevel();
        int curr_power= node.spell.getPowerLevel();

        if (new_power<curr_power){
            node.left=insert_rec(node.left,spell);
        }
        if (new_power>curr_power){
            node.right=insert_rec(node.right,spell);
        }
        node.height= 1+ Math.max(height(node.right),height(node.left));
        int balance= getBalance(node);

        if (balance>1 && new_power<node.left.spell.getPowerLevel()){
            return rotateRight(node);
        }
        if (balance<-1 && new_power>node.right.spell.getPowerLevel()){
            return rotateLeft(node);
        }
        if (balance>1 && new_power>node.left.spell.getPowerLevel()){
            node.left= rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance<-1 && new_power<node.right.spell.getPowerLevel()){
            node.right= rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;

    }
    //helper to avoid null- height
    private int height(Node node){
        if (node==null){
            return 0;
        }
        return node.height;
    }
    //helper-calculate the balance
    private int getBalance(Node node){
        if (node==null){
            return 0;
        }
        return height(node.left)-height(node.right);
    }
    //helper rotate the node to the right
    private Node rotateRight(Node k2){
        Node k1= k2.left;
        Node B = k1.right;
        k1.right=k2;
        k2.left=B;
        k2.height= 1 + Math.max(height(k2.left), height(k2.right));
        k1.height= 1 + Math.max(height(k1.left), height(k1.right));
        return k1;
    }
    //helper rotate the node to the left
    private Node rotateLeft(Node k2){
        Node k1= k2.right;
        Node B = k1.left;
        k1.left=k2;
        k2.right=B;
        k2.height= 1 + Math.max(height(k2.left), height(k2.right));
        k1.height= 1 + Math.max(height(k1.left), height(k1.right));
        return k1;
    }

    //get the top k-th spell (largest power level)
    public List<Spell> getTopK(int k) {
        List<Spell> list= new ArrayList<>(k);
        reverseInOrder(root,list,k);
        return list;
    }
    //helper recursive reverse in order that adding spells to the list
    private void reverseInOrder(Node node, List<Spell> list, int k){
        if (node==null || list.size()>=k){
            return;
        }
        reverseInOrder(node.right,list,k);
        if (list.size()<k){
            list.add(node.spell);

        }
        reverseInOrder(node.left,list,k);
    }
}


