package com.source;

import java.util.Arrays;

//custom stack implemented for the binary search tree iterators.

 class ObjectStack<typeName>
{
    private int size = 0;
    private Object[] st;

     ObjectStack() {
        st = new Object[10];
    }

     void push(typeName elem) {
        if (size == st.length) {
            resize();
        }
        st[size] = elem;
        size++;
    }

     typeName pop() {
        typeName top = (typeName) st[size-1];
        size--;
        st[size] = null;
        return top;
    }
     typeName peek() {
        typeName top = (typeName) st[size-1];
        return top;
    }

     boolean isEmpty() {
        return size==0;
    }

    private void resize() {
        int new_size = st.length * 2;
        st = Arrays.copyOf(st, new_size);
    }

}