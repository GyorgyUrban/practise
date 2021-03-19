package hashmap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Anagrams {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        Map<String, Integer> anagrams = new HashMap<>();
        int length = s.length();
        for (int subLength = 1; subLength < length; subLength++) {
            for (int startingPosition = 0; startingPosition <= length - subLength; startingPosition++) {
                String sub = s.substring(startingPosition, startingPosition + subLength);
                String sorted = sort(sub);
                anagrams.compute(sorted, (key, oldValue) -> oldValue == null ? 1 : ++oldValue);
            }
        }
        Integer sum = sum(anagrams);
        return sum;
    }

    private static Integer sum(Map<String, Integer> anagrams) {
        return anagrams.values().stream()
                .map(numberOfMatched -> numberOfMatched == 1 ? 0 : (numberOfMatched * (numberOfMatched - 1)) / 2)
                .reduce(0, Integer::sum);
    }

    private static String sort(String sub) {
        return Stream.of(sub.split(""))
                .sorted()
                .collect(Collectors.joining());
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
