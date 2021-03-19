package string;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Special {

    // Complete the substrCount function below.
    static long substrCount(int length, String s) {
        List<Block> blocks = createBlocks(length, s);
        long counter = blocks.stream().map(Block::getSum).reduce(0l, Long::sum);
        for (int i = 0; i < blocks.size() - 2; i++) {
            Block first = blocks.get(i);
            Block middle = blocks.get(i + 1);
            Block third = blocks.get(i + 2);
            if (middle.getCount() == 1 && first.getCharacter().equals(third.getCharacter())) {
                counter += Math.min(first.getCount(), third.getCount());
            }
        }
        return counter;
    }

    private static List<Block> createBlocks(int length, String s) {
        char[] chars = s.toCharArray();
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Character current = chars[i];
            if (blocks.isEmpty()) {
                blocks.add(new Block(1, current));
            } else {
                Block lastBlock = blocks.get(blocks.size() - 1);
                if (lastBlock.getCharacter().equals(current)) {
                    lastBlock.increaseCount();
                } else {
                    blocks.add(new Block(1, current));
                }
            }
        }

        return blocks;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    private static class Block {
        private long count;
        private Character character;

        public Block(long count, Character character) {
            this.count = count;
            this.character = character;
        }

        public long getSum() {
            long halfCount = count / 2;
            boolean even = halfCount * 2 == count;
            if (even) {
                return halfCount * (count + 1);
            } else {
                return (halfCount * (count + 1)) + halfCount + 1;
            }
        }

        public long getCount() {
            return count;
        }

        public void increaseCount() {
            count++;
        }

        public Character getCharacter() {
            return character;
        }

        @Override
        public String toString() {
            return "Block{" +
                    "count=" + count +
                    ", character=" + character +
                    '}';
        }
    }
}
