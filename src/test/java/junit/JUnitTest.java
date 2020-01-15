package junit;

import org.junit.Test;

public class JUnitTest {

    @Test
    public void codeChallenges() {

//        printMessage();
//        printMessage("New message");
//        swapNumbers();
//        System.out.println(divisible(8));
//        div2and5(40);
//
//        printNumbers(5);
//        printNumbers(-5);
//        System.out.println(Arrays.toString(returnNumbers(5)));
//        System.out.println(isPrime(11));
        fizzBuzz(20);

    }

    private void printMessage() {

        System.out.println("My JUnit test!");
    }

    private void printMessage(String message) {

        System.out.println(message);
    }

    private void swapNumbers() {

        int a = 5;
        int b = 9;

        int temp = a;
        a = b;
        b = temp;

        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }

    //    if by 3 - "div by 3"
//    if by 4 - "div by 4"
//    if by 3 and 4 - "div by 3 and 4"
    private String divisible(int num) {
        if (num % 3 == 0 && num % 4 == 0) {
            return "div by 3 and 4";
        }
        if (num % 3 == 0) {
            return "div by 3";
        }
        if (num % 4 == 0) {
            return "div by 4";
        }
        return ("not div by 3 or 4");
    }

    private void div2and5(int num) {
        if (num % 2 == 0 && num % 5 == 0) {
            System.out.println("div by 2 and 5");
        } else if (num % 2 == 0) {
            System.out.println("div by 2");
        } else if (num % 5 == 0) {
            System.out.println("div by 5");
        } else {
            System.out.println("not div by 2 or 5");
        }
    }

    private void printNumbers(int num) {
        if (num > 0) {
            for (int i = 0; i <= num; i++) {
                System.out.println(i);
            }
        } else {
            for (int i = 0; i >= num; i--) {
                System.out.println(i);
            }
        }
    }

    private int[] returnNumbers(int num) {
        int[] arr = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private boolean isPrime(int num) {

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


//function, accepts integer argument
//it should print all the numbers up to the argument
//BUT: if number is multiple of 3, it should print Fizz instead of number
//if number is multiple of 5, it should print Buzz instead of number
//If it is multiple of both 3 and 5, it should print FizzBuzz instead of number

//Result:
//1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16 17 Fizz 19 FizzBuzz

    public void fizzBuzz(int number) {
        for (int i = 1; i <= number; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}