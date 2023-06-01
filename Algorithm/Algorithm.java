import java.util.Arrays;
import java.util.Scanner;

public class Algorithm {
    static void miniMaxSum(int[] arr) {
        Arrays.sort(arr);
        long minSum = 0;
        long maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1) minSum += arr[i];

            if (i > 0) maxSum += arr[i];
        }
        System.out.println(minSum + " " + maxSum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            if (sc.hasNextInt()) {
                arr[i] = sc.nextInt();
            }
        }
        miniMaxSum(arr);
    }
}