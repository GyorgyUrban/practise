package comparator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.LinkedBlockingQueue;

public class Fraudulent {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int count = 0;
        Calculator calculator = new Calculator(d);
        for (int spending : expenditure) {
            if (calculator.isNotify(spending)) {
                count++;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    private static class Calculator {
        private TreeSet<Day> sortedDays;
        private Day middle;
        private LinkedBlockingQueue<Day> daysInOrder;
        private long dayCount;
        private boolean even;

        public Calculator(int d) {
            this.daysInOrder = new LinkedBlockingQueue<>(d);
            this.sortedDays = new TreeSet<>();
            this.dayCount = 0;
            this.even = (d / 2) * 2 == d;
        }

        public boolean isNotify(int spending) {
            Day actual = new Day(spending, dayCount);
            boolean notify = false;

            if (isStartupPeriodOver()) {
                calculateMiddleIfNeeded();

                notify = isNotify(actual);

                Day latestDay = removeLatestDay();
                trackActualDay(actual);

                recalculateMiddle(latestDay, actual);
            } else {
                trackActualDay(actual);
            }

            return notify;
        }

        private boolean isStartupPeriodOver() {
            return daysInOrder.remainingCapacity() == 0;
        }

        private void calculateMiddleIfNeeded() {
            if (middle == null) {
                int halfSize = sortedDays.size() / 2;
                Day[] array = this.sortedDays.stream().toArray(Day[]::new);
                middle = array[even ? halfSize - 1 : halfSize];
            }
        }

        private boolean isNotify(Day actual) {
            return even ? isNotifyEven(actual) : isNotifyOdd(actual);
        }

        private void recalculateMiddle(Day latestDay, Day actualDay) {
            if (latestDay.compareTo(middle) == 0) {
                if (actualDay.compareTo(middle) < 0) {
                    middle = sortedDays.lower(middle);
                } else {
                    middle = sortedDays.higher(middle);
                }
            } else if (latestDay.compareTo(middle) < 0) {
                if (actualDay.compareTo(middle) > 0) {
                    middle = sortedDays.higher(middle);
                }
            } else if (actualDay.compareTo(middle) < 0) {
                middle = sortedDays.lower(middle);
            }
        }

        private void trackActualDay(Day actual) {
            daysInOrder.offer(actual);
            sortedDays.add(actual);
            dayCount++;
        }

        private Day removeLatestDay() {
            Day latestDay = daysInOrder.poll();
            sortedDays.remove(latestDay);
            return latestDay;
        }

        private boolean isNotifyEven(Day actual) {
            return middle.getSpending() + sortedDays.higher(middle).getSpending() <= actual.getSpending();
        }

        private boolean isNotifyOdd(Day actual) {
            return middle.getSpending() * 2 <= actual.getSpending();
        }

        private class Day implements Comparable<Day> {
            private int spending;
            private long dayCounter;

            public Day(int spending, long dayCounter) {
                this.spending = spending;
                this.dayCounter = dayCounter;
            }

            public int getSpending() {
                return spending;
            }

            @Override
            public int compareTo(Day o) {
                if (this.spending < o.spending) {
                    return -1;
                } else if (this.spending > o.spending) {
                    return 1;
                } else {
                    if (this.dayCounter < o.dayCounter) {
                        return -1;
                    } else if (this.dayCounter > o.dayCounter) {
                        return 1;
                    }
                }
                return 0;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Day day = (Day) o;
                return spending == day.spending &&
                        dayCounter == day.dayCounter;
            }

            @Override
            public int hashCode() {
                return Objects.hash(spending, dayCounter);
            }

            @Override
            public String toString() {
                return "Day{" +
                        "spending=" + spending +
                        ", dayCounter=" + dayCounter +
                        '}';
            }
        }
    }
}
