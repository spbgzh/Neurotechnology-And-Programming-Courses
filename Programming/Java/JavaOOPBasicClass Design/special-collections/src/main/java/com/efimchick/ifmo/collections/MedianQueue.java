package com.efimchick.ifmo.collections;

import java.util.PriorityQueue;

public class MedianQueue extends PriorityQueue<Integer> {

    public MedianQueue() {
        super((o1, o2) -> Integer.compare(o1, o2));
    }

    @Override
    public Integer peek() {
        int size = size();
        int index;
        if (size % 2 == 1){
            index = size / 2;
        } else{
            index = size / 2 - 1;
        }

        PriorityQueue<Integer> subQueue = new PriorityQueue<>();
        for (int i=0; i < index; i++){
            subQueue.add(super.poll());
        }
        int ret = super.peek();
        this.addAll(subQueue);
        return ret;
    }

    @Override
    public Integer poll() {
        int size = size();
        int index;
        if (size % 2 == 1){
            index = size / 2;
        } else{
            index = size / 2 -1;
        }

        PriorityQueue<Integer> subQueue = new PriorityQueue<>();
        for (int i=0; i < index; i++){
            subQueue.add(super.poll());
        }
        int ret = super.poll();
        this.addAll(subQueue);
        return ret;
    }
}
