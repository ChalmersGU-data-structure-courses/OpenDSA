interface BTNode<K, V> { // Binary tree node ADT
    public K key();
    public V value();
    public BTNode<K, V> left();
    public BTNode<K, V> right();

    // return TRUE if a leaf node, FALSE otherwise
    public boolean isLeaf();
}
