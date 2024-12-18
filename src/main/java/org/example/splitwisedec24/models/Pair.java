package org.example.splitwisedec24.models;

public class Pair<K,V> {
    K key;
    V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
