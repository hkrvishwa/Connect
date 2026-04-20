package com.example.calculator.sorting;

public class Selection extends Sort{
    @Override
    public void sort() {
        displayBefore(input);
        for(int i=0;i< input.length;i++){
            int minIndex = i;
            for(int j=i+1;j<input.length;j++){
                if(input[j]<input[minIndex]){
                    minIndex = j;
                }
            }
            int temp = input[minIndex];
            input[minIndex] = input[i];
            input[i] = temp;
        }
        displayAfter(input);
    }
}

/**
 * SELECTION SORT ANALYSIS:
 * 1. Time Complexity: Best O(n²), Worst O(n²)
 * 2. Space Complexity: O(1)
 * 3. Stable: No
 * 4. Data: Poor for both small and large data compared to others.
 * 5. Overall: Best when memory writes (swaps) are expensive.
 */
