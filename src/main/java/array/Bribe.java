package array;

import java.util.Scanner;
import java.util.TreeSet;

public class Bribe {

    // Complete the minimumBribes function below.
    public static void minimumBribes(int[] q) {
        int sum = 0;
        TreeSet<Integer> positions = new TreeSet<>();
        for (int actualPosition = 1; actualPosition <= q.length; actualPosition++) {
            int originalPosition = q[actualPosition - 1];

            if (isChaotic(actualPosition, originalPosition)) {
                System.out.println("Too chaotic");
                return;
            }

            int numberOfBribed = getBribeNumber(positions, originalPosition);

            positions.add(originalPosition);

            sum += numberOfBribed;
        }


        System.out.println(sum);
    }

    private static int getBribeNumber(TreeSet<Integer> positions, int originalPosition) {
        return positions.tailSet(originalPosition).size();
    }

    private static boolean isChaotic(int actualPosition, int originalPosition) {
        return originalPosition > actualPosition + 2;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
