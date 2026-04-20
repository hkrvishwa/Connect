package com.example.calculator.searching;

public abstract  class BinearSearch {

    int[] input = new int[]{2,5,6,7,8,9,10,15};

    int target = 7;

    public void search(){

        int start = 0;
        int end = input.length-1;
        while(start<end){
           int  mid = (start+end)/2;
           if(input[mid] == target){
               display("Found at index "+mid);
               return;
           }else if(target<input[mid]){
               end = mid-1;
           }else{
               start = mid+1;
           }
        }
        display(target+" not Found");
    }
    public abstract void display(String msg);
}
