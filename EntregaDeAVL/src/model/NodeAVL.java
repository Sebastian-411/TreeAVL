package model;
public class NodeAVL<K extends Comparable<K>, T>{
    private NodeAVL<K, T> father;
    private T value;
    private K key;
    private NodeAVL<K, T> left;
    private NodeAVL<K, T> right;

    public NodeAVL(T value, K key) {
        this.value = value;
        this.key = key;

    }

    public int balanced(){
        return height(right, 1) - height(left,1);
    }

    public int height(NodeAVL<K, T> current, int counter) {
        if ( current == null){
            return 0;
        }

        if ( current.getLeft() == null && current.getRight() == null ){
            return counter;
        }
        if ( current.getRight() != null && current.getLeft() == null ){
            return height(current.getRight(), counter + 1);
        }

        if ( current.getRight() == null && current.getLeft() != null ){
            return height(current.getLeft(), counter + 1);
        }

        if ( current.getLeft() != null && current.getRight() != null ){
            if ( height(current.getRight(), counter + 1) > height(current.getLeft(), counter + 1) ){
                return height(current.getRight(), counter + 1);
            } else return height(current.getLeft(), counter + 1);

        } else return 0;
    }

    public NodeAVL<K, T> getFather() {
        return father;
    }

    public void setFather(NodeAVL<K, T> father) {
        this.father = father;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public NodeAVL<K, T> getLeft() {
        return left;
    }

    public void setLeft(NodeAVL<K, T> left) {
        this.left = left;
    }

    public NodeAVL<K, T> getRight() {
        return right;
    }

    public void setRight(NodeAVL<K, T> right) {
        this.right = right;
    }
}
