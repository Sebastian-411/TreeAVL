package model;


import java.util.LinkedList;
import java.util.Queue;

public class TreeAVL<K extends Comparable<K>,T> {
    private NodeAVL<K, T> root;

    public void insert(K key, T value) {
        NodeAVL<K, T> x = new NodeAVL<>(value, key);
        if ( root == null ){
            root = x;
        } else {
            insert(root, x);
        }

    }

    private void insert(NodeAVL<K, T> current, NodeAVL<K, T> node) {
        if(node.getKey().compareTo(current.getKey()) < 0){
            if(current.getLeft() == null){
                node.setFather(current);
                current.setLeft(node);
            }else{
                insert(current.getLeft(), node);
            }
        }else if(node.getKey().compareTo(current.getKey()) > 0){
            if(current.getRight() == null){
                node.setFather(current);
                current.setRight(node);
            }else{
                insert(current.getRight(), node);
            }
        }
        rebalanceAVL(node, node);
    }

    public void delete(K key) {
        if ( root == null ){
            return;
        }

        NodeAVL<K, T> toDelete = search(key);
        if ( toDelete == null ){
            return;
        }
        NodeAVL<K, T> temporalToDelete;
        NodeAVL<K, T> connection;

        if ( toDelete.getRight() == null || toDelete.getLeft() == null ) temporalToDelete = toDelete;
        else temporalToDelete = succesor(toDelete);

        if ( temporalToDelete.getLeft() != null ) connection = temporalToDelete.getLeft();
        else connection = temporalToDelete.getRight();

        if ( connection != null ) connection.setFather(temporalToDelete.getFather());

        if ( temporalToDelete.getFather() == null ){
            root.setKey(connection.getKey());
            root.setValue(connection.getValue());
        } else {
            if ( temporalToDelete == temporalToDelete.getFather().getLeft() )
                temporalToDelete.getFather().setLeft(connection);
            else temporalToDelete.getFather().setRight(connection);
        }

        if ( temporalToDelete != toDelete ){
            toDelete.setKey(temporalToDelete.getKey());
            toDelete.setValue(temporalToDelete.getValue());
        }
        rebalanceAVL(toDelete, toDelete);
    }


    private void leftRotate(NodeAVL<K, T> current) {
        NodeAVL<K, T> tem = current.getRight().getLeft();
        NodeAVL<K, T> father = current.getFather();

        if (father == null) {
            root = current.getRight();
        } else if (father.getRight() == current) {
            father.setRight(current.getRight());
        } else {
            father.setLeft(current.getRight());
        }

        current.getRight().setFather(father);
        current.setFather(current.getRight());
        current.getFather().setLeft(current);
        current.setRight(tem);

        if (tem != null) {
            tem.setFather(current);
        }
    }

    private void rightRotate(NodeAVL<K, T> current) {
        NodeAVL<K, T> tem = current.getLeft().getRight();
        NodeAVL<K, T> father = current.getFather();

        if (father == null) {
            root = current.getLeft();
        } else if (father.getRight() == current) {
            father.setRight(current.getLeft());
        } else {
            father.setLeft(current.getLeft());
        }

        current.getLeft().setFather(father);
        current.setFather(current.getLeft());
        current.getFather().setRight(current);
        current.setLeft(tem);

        if (tem != null) {
            tem.setFather(current);
        }
    }

    private void rebalanceAVL(NodeAVL<K, T> x, NodeAVL<K, T> current) {
        if(Math.abs((current.balanced())) > 1){
            if ((current.balanced()) > 0){

                if (current.getRight().balanced() == 1){
                    leftRotate(current);
                } else if (current.getRight().balanced() == -1) {
                    rightRotate(current.getRight());
                    leftRotate(current);
                } else {
                    leftRotate(current);
                }

            } else {
                if ((current.getLeft()).balanced() == 1){
                    leftRotate(current.getLeft());
                    rightRotate(current);
                } else if ((current.getLeft()).balanced() == -1) {
                    rightRotate(current);
                } else {
                    rightRotate(current);
                }
            }
        }
        if(current.getFather() != null){
            rebalanceAVL(x, current.getFather());
        }
    }

    public String inOrden() {
        if ( root == null ){
            return null;
        } else {
            return inOrden(root);
        }
    }

    private String inOrden(NodeAVL<K, T> x) {
        if ( x != null ){
            return inOrden(x.getLeft()) + x.getKey() + inOrden(x.getRight());
        }
        return "";
    }

    public NodeAVL<K, T> search(K key) {
        if ( root == null ){
            return null;
        } else {
            return search(root, key);
        }
    }

    private NodeAVL<K, T> search(NodeAVL<K, T> x, K keyS) {
        if ( x == null ){
            return null;
        } else if ( x.getKey().equals(keyS) ){
            return x;
        } else {
            if ( keyS.compareTo(x.getKey()) < 0 ){
                return search(x.getLeft(), keyS);
            } else {
                return search(x.getRight(), keyS);
            }
        }
    }

    public NodeAVL<K, T> minimun(NodeAVL<K, T> x) {
        while (x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
    }

    public NodeAVL<K, T> maximum(NodeAVL<K, T> x) {
        while (x.getRight() != null) {
            x = x.getRight();
        }
        return x;
    }

    public NodeAVL<K, T> succesor(NodeAVL<K, T> x) {
        if ( x.getRight() != null ){
            return minimun(x.getRight());
        } else {
            return minimun(x.getLeft());
        }
    }

    public String levels() {
        if(root == null) return "Arbol vacio";
        String txt = "";
        Queue<NodeAVL<K,T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            NodeAVL<K,T> node = queue.poll();
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            txt += node.getValue() + " ";

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return txt;
    }


    public String preOrden() {
        if ( root == null ){
            return null;
        } else {
            return preOrden(root);
        }
    }

    private String preOrden(NodeAVL<K, T> x) {
        if ( x != null ){
            return x.getKey() + "(BF=" + x.balanced()*-1 + ") " + preOrden(x.getLeft()) + preOrden(x.getRight());
        }
        return "";
    }

}
