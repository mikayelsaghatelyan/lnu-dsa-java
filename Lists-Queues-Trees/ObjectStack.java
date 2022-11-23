package com.source;

import java.util.Arrays;

class ObjectStack<typeName> {
    int size = 0;
    Object[] stack;

    ObjectStack() {
        stack = new Object[10];
    }

    void push(typeName elem) {
        if (size == stack.length)
            resize();
        stack[size] = elem;
        size++;
    }

    typeName pop() {
        typeName top = (typeName) stack[size - 1];
        size--;
        stack[size] = null;
        return top;
    }

    typeName peek() {
        typeName stackTop = (typeName) stack[size - 1];
        return stackTop;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void resize() {
        int new_length = stack.length * 2;
        stack = Arrays.copyOf(stack, new_length);
    }
}