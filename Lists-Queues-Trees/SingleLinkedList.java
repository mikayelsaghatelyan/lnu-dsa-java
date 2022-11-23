package com.source;

 class SingleLinkedList {
    static class SLNode {
        int val;
        SLNode next;

        SLNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    SLNode head = null;
    SLNode tail = null;

    //adding new element to list
     void add(int val) {
        SLNode elem = new SLNode(val);
        if (head == null) {
            head = elem;
        } else {
            tail.next = elem;
        }
        tail = elem;
    }

    //checking if the list is empty
     boolean empty() {
        return head == null;
    }

    //printing the list with the help of traversal
     void print() {
        SLNode curr = head;
        while (curr != null) {
            System.out.print("["+curr.val + "] ");
            curr = curr.next;
        }
        System.out.print("\n");
    }

    //reversing list through a pointer manipulation
     void reverse() {
        SLNode curr = head;
        tail = head;
        SLNode nextInList = null;
        SLNode prevInList = null;
        while (curr!=null) {
            nextInList = curr.next;
            curr.next = prevInList;
            prevInList=curr;
            curr = nextInList;
        }
        head = prevInList;
        assert tail != null;
    }
}
