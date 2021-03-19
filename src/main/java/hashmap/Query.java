package hashmap;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Query {
    private static final int ADD = 1;
    private static final int DELETE = 2;
    private static final int SUM = 3;

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> ledger = new HashMap<>();
        List<Integer> output = new ArrayList<>();
        Map<Integer, Integer> countLedger = new HashMap<>();

        for (List<Integer> query : queries) {
            int operation = query.get(0);
            Integer data = query.get(1);
            if (ADD == operation) {
                Integer count = ledger.compute(data, increaseStoredValue());

                countLedger.compute(count, increaseStoredValue());
                if (count > 1) {
                    countLedger.compute(count - 1, decreaseStoredValue());
                }
            } else if (DELETE == operation) {
                if (ledger.containsKey(data)) {
                    Integer count = ledger.compute(data, decreaseStoredValue());

                    if (count != null) {
                        countLedger.compute(count, increaseStoredValue());
                        countLedger.compute(count + 1, decreaseStoredValue());
                    } else {
                        countLedger.compute(1, decreaseStoredValue());
                    }
                }
            } else if (SUM == operation) {
                output.add(isFrequencyPresents(countLedger, data) ? 1 : 0);
            }
        }

        return output;
    }

    private static BiFunction<Integer, Integer, Integer> decreaseStoredValue() {
        return (key, oldValue) -> oldValue == 1 ? null : --oldValue;
    }

    private static BiFunction<Integer, Integer, Integer> increaseStoredValue() {
        return (key, oldValue) -> oldValue == null ? 1 : ++oldValue;
    }

    private static boolean isFrequencyPresents(Map<Integer, Integer> ledger, Integer data) {
        return ledger.get(data) != null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
