package string;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SherlockValid {

    private static final String NO = "NO";
    private static final String YES = "YES";
    private static final int ZERO = 0;

    // Complete the isValid function below.
    static String isValid(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (Character c : s.toCharArray()) {
            counter.compute(c, (key, oldCont) -> oldCont == null ? 1 : oldCont + 1);
        }
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer count : counter.values()) {
            result.compute(count, (key, oldCont) -> oldCont == null ? 1 : oldCont + 1);
        }
        if (result.size() > 2) {
            return NO;
        } else if (result.size() == 2) {
            ArrayList<Map.Entry<Integer, Integer>> entries = new ArrayList<>(result.entrySet());
            Map.Entry<Integer, Integer> entry1 = entries.get(0);
            Map.Entry<Integer, Integer> entry2 = entries.get(1);
            if (entry1.getValue().equals(1)) {
                return evaluate(entry1, entry2);
            } else if (entry2.getValue().equals(1)) {
                return evaluate(entry2, entry1);
            }
            return NO;
        }
        return YES;
    }

    private static String evaluate(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
        Integer occurrence = entry1.getKey();
        Integer afterRemove = occurrence - 1;
        return afterRemove.equals(ZERO) || afterRemove.equals(entry2.getKey()) ? YES : NO;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
