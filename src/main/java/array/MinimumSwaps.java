package array;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MinimumSwaps {
    static int minimumSwaps(int[] q) {
        int sum = 0;
        int alreadySorted = 1;
        boolean isSwapped = true;
        while (isSwapped) {
            UntilSorted untilSorted = swap(q, alreadySorted);
            isSwapped = untilSorted.isSwapped;
            if (isSwapped) {
                sum++;
            }
            alreadySorted = untilSorted.alreadySorted;
        }
        return sum;
    }

    private static UntilSorted swap(int[] q, int alreadySorted) {
        for (int actualPosition = alreadySorted; actualPosition <= q.length; actualPosition++) {
            int originalPosition = q[actualPosition - 1];

            if (isNotInPlace(actualPosition, originalPosition)) {
                int replaceTo = findReplaceTo(actualPosition, q);
                q[actualPosition - 1] = actualPosition;
                q[replaceTo - 1] = originalPosition;
                return new UntilSorted(actualPosition + 1, true);
            }
        }
        return new UntilSorted(0, false);
    }

    private static class UntilSorted {
        int alreadySorted;
        boolean isSwapped;

        public UntilSorted(int alreadySorted, boolean isSwapped) {
            this.alreadySorted = alreadySorted;
            this.isSwapped = isSwapped;
        }

        public int getAlreadySorted() {
            return alreadySorted;
        }

        public boolean isSwapped() {
            return isSwapped;
        }
    }

    private static int findReplaceTo(int originalPosition, int[] q) {
        for (int actualPosition = originalPosition + 1; actualPosition <= q.length; actualPosition++) {
            if (q[actualPosition - 1] == originalPosition) {
                return actualPosition;
            }
        }
        throw new IllegalStateException("Should not happen");
    }

    private static boolean isNotInPlace(int actualPosition, int originalPosition) {
        return actualPosition != originalPosition;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
