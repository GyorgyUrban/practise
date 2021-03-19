package hashmap;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Triplets {
    private static final long ZERO = 0l;

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        Map<Long, TripletsCounter> triplets = new HashMap<>();
        for (Long base : arr) {
            triplets.computeIfAbsent(base, unused -> new TripletsCounter(r)).calculateFirst();
            long secondDivided = divide(base, r);
            if (secondDivided != ZERO && triplets.containsKey(secondDivided)) {
                triplets.get(secondDivided).calculateSecond();
                long thirdDivided = divide(secondDivided, r);
                if (thirdDivided != ZERO && triplets.containsKey(thirdDivided)) {
                    triplets.get(thirdDivided).calculateThird();
                }
            }
        }

        return triplets.values().stream()
                .map(TripletsCounter::count)
                .reduce(0l, Long::sum);
    }

    private static Long divide(Long base, long r) {
        Long d = base / r;
        return base.equals(d * r) ? d : ZERO;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static class TripletsCounter {
        private final long ratio;
        private long first = 0l;
        private long second = 0l;
        private long third = 0l;

        public TripletsCounter(long r) {
            this.ratio = r;
        }

        public void calculateFirst() {
            first++;
        }

        public void calculateSecond() {
            second += first;
        }

        public void calculateThird() {
            third += second;
        }

        public long count() {
            if (ratio == 1l) {
                return first >= 3 ? (first * (first - 1) * (first - 2)) / 6l : 0;
            } else {
                return third;
            }
        }
    }
}
