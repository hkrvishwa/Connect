package com.example.calculator.searching;

public abstract class LinearSearch  {

    int[] input = new int[]{2,5,6,3,9,7,6,1};

    int target = 7; // index = 5

    public void search(){
        for(int i=0;i<input.length;i++){
            if(input[i] == target){
                display("Found at index "+i);
                return;
            }
        }
    }

    public abstract void display(String msg);
}
