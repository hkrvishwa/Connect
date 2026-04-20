package com.example.calculator.sorting;

public abstract class Sort {

    int[] input = new int[]{2,5,6,3,9,7,6,1};
    public abstract void sort();
    public void displayBefore(int[] arr){
        display(arr,"Before");
    }

    public void displayAfter(int[] arr){
        display(arr,"After");
    }

    public void display(int[] arr,String msg){
        System.out.print(msg+" : ");
        for(int item : arr){
            System.out.print(arr+"  ");
        }
    }
}
