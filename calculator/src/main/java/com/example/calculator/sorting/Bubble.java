package com.example.calculator.sorting;

public class Bubble extends Sort{

    @Override
    public  void sort(){

        boolean isSwap = false;
        displayBefore(input);
        for(int i=0;i<input.length;i++) {

            for (int j = 1; j < input.length - i; j++) {
                if (input[j]  < input[j - 1]) {
                    isSwap = true;
                    int temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }

            if(!isSwap){
                break;
            }
        }
        displayAfter(input);

    }
}
