package com.example.calculator.questions;

public class TwoPointers {

    public static void main(String[] args){
        //reverse();
        //twoSum();
        //palindrome();
        //mostWater();
        validMountain();
    }

    public static void reverse(){
        int[] arr = new int[]{1,2,3,4,5,6};
        int start = 0;
        int end = arr.length-1;
        while (start<end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        for(int item : arr){
            System.out.print(item+" ");
        }
    }


    public static void twoSum(){
        int[] arr = new int[]{1,2,3,4,5,6};
        int target = 5;
        int start = 0;
        int end = arr.length-1;
        int sum = 0;
        while(start<end){
            sum = arr[start]+arr[end];
            if(target == sum){
                System.out.println(arr[start]+" "+arr[end]);
                return;
            }else if(target<sum){
                end--;
            }else{
                start++;
            }
        }
        System.out.println("not found");
    }

    public static void palindrome(){
        String input = "abccba";
        int start = 0;
        int end = input.length()-1;
        while(start<end){
            if(input.charAt(start)!=input.charAt(end)){
                System.out.println("not palindrome");
                return;
            }
            start++;
            end--;
        }
        System.out.println(" palindrome");
    }

    /**
     * Container With Most Water: Given  non-negative integers representing heights of lines,
     * find two lines which, together with the x-axis,
     * forms a container that holds the most water.
     * Area = Width(x-axis) × Height(y-axis , take min bcz)
     * height = water will spill over if it goes above min
     */
    public static void mostWater(){
        int[] arr = new int[]{1, 8, 6, 2, 5};
        int start = 0;
        int end = arr.length-1;
        int maxArea = 0;
        while(start<end){
            //area = width * height
            int area = (end-start) * Math.min(arr[start],arr[end]);
            maxArea = Math.max(maxArea,area);
            if(arr[start]<arr[end]){
                start++;
            }else{
                end--;
            }
        }
        System.out.print("max area is "+maxArea);
    }

    public static void validMountain(){
        int[] input = new int[]{0, 3, 5, 2, 1};
        int start = 0;
        int end = input.length-1;
        while(input[start]<input[start+1]){
            start++;
        }
        while(input[end]<input[end-1]){
            end--;
        }
        if(start>0 && end<input.length-1 && start==end){
            System.out.println("valid");
        }else{
            System.out.println("invalid");
        }
    }
}
