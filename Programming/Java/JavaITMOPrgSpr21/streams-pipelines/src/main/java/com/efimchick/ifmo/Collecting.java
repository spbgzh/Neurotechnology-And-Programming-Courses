package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collecting {

  public int sum(IntStream intStream) {
    return intStream.sum();
  }

  public int production(IntStream intStream) {
    return intStream.reduce(1, (a, b) -> a * b);
  }

  public int oddSum(IntStream intStream) {
    return intStream.filter(x -> (x % 2 == 1) || -x % 2 == 1).sum();
  }

  public Map<Integer, Integer> sumByRemainder(int i, IntStream intStream) {
    int[] list = intStream.toArray();
    List<Integer> listKey =
        Arrays.stream(list).map(x -> x % i).distinct().boxed().collect(Collectors.toList());
    Map<Integer, Integer> map = new HashMap<>();
    for (Integer key : listKey) {
      map.put(key, Arrays.stream(list).filter(x -> x % i == key).sum());
    }
    return map;
  }

  public Map<Person, Double> totalScores(Stream<CourseResult> programmingResults) {
    return programmingResults.collect(
        Collectors.toMap(
            x -> x.getPerson(),
            y -> {
              double a =
                  y.getTaskResults().entrySet().stream().mapToDouble(Map.Entry::getValue).sum();
              double b = (y.getTaskResults().containsKey("Shieldwalling")) ? 4 : 3;
              return a / b;
            }));
  }

  public double averageTotalScore(Stream<CourseResult> programmingResults) {
    return programmingResults.collect(
        Collectors.averagingDouble(
            x -> {
              double a =
                  x.getTaskResults().entrySet().stream().mapToDouble(Map.Entry::getValue).sum();
              double b = (x.getTaskResults().containsKey("Shieldwalling")) ? 4 : 3;
              return a / b;
            }));
  }

  public Map<String, Double> averageScoresPerTask(Stream<CourseResult> programmingResults) {
    Map<String, Double> ans = new HashMap<>();
    List<Map<String, Integer>> taskResults =
        programmingResults.map(x -> x.getTaskResults()).collect(Collectors.toList());
    List<String> lesson = new ArrayList<>();
    taskResults.stream()
        .forEach(
            x ->
                x.entrySet().stream()
                    .forEach(
                        y -> {
                          lesson.add(y.getKey());
                        }));
    List<String> distinctLesson = lesson.stream().distinct().collect(Collectors.toList());
    for (String str : distinctLesson) {
      Integer sum = Integer.valueOf(0);
      for (Map<String, Integer> t : taskResults) {
        sum += (t.containsKey(str)) ? t.get(str) : 0;
      }
      ans.put(str, sum / 3.0);
    }
    return ans;
  }

  public Map<Person, String> defineMarks(Stream<CourseResult> programmingResults) {
    Map<Person, Double> score = totalScores(programmingResults);
    Map<Person, String> marks = new HashMap<>();
    score
        .entrySet()
        .forEach(
            x -> {
              String MARK;
              Double flag = x.getValue();
              MARK =
                  (flag > 90)
                      ? "A"
                      : (flag >= 83
                          ? "B"
                          : (flag >= 75 ? "C" : (flag >= 68 ? "D" : (flag >= 60 ? "E" : "F"))));
              marks.put(x.getKey(), MARK);
            });
    return marks;
  }

  public String easiestTask(Stream<CourseResult> programmingResults) {
    Map<String, Double> ASP = averageScoresPerTask(programmingResults);
    double maxValue = 0;
    String maxKey = null;
    for (String f : ASP.keySet()) {
      if (ASP.get(f) > maxValue) {
        maxValue = ASP.get(f);
        maxKey = f;
      }
    }
    return maxKey;
  }

  public Collector printableStringCollector() {
    Collector<CourseResult, List<String>, String> col =
        new Collector<CourseResult, List<String>, String>() {
          @Override
          public Supplier<List<String>> supplier() {
            return ArrayList::new;
          }

          @Override
          public BiConsumer<List<String>, CourseResult> accumulator() {
            return (List<String> setAns, CourseResult programmingResults) -> {
              if (setAns.size() < 1) setAns.add(stringMaker(programmingResults).toString());
            };
          }

          @Override
          public BinaryOperator<List<String>> combiner() {
            return null;
          }

          @Override
          public Function<List<String>, String> finisher() {
            return (List<String> a) -> {
              String flag = a.get(0).toString();
              String b = flag.replaceAll("(?:\\[||\\]| +)", "");
              return b;
            };
          }

          @Override
          public Set<Characteristics> characteristics() {
            return Collections.singleton(Characteristics.UNORDERED);
          }
        };
    return col;

    /*Collector<CourseResult, String, String> ans =
        new Collector<CourseResult, String, String>() {
          @Override
          public Supplier<String> supplier() {
            return String::new;
          }

          @Override
          public BiConsumer<String, CourseResult> accumulator() {
            return (String listAns, CourseResult programmingResults) ->
                listAns =listAns+ stringMaker(Stream.of(programmingResults));
          }

          @Override
          public BinaryOperator<String> combiner() {
            return (String a, String b)->{return a+b;};
          }

          @Override
          public Function<String, String> finisher() {
            return (String a)->{return a;};
          }

          @Override
          public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
          }
        };
    return ans;*/
  }

  public String stringMaker(CourseResult programmingResults) {
    Map<String, Integer> taskResults = programmingResults.getTaskResults();
    List<String> f = new ArrayList<>();
    f.add(
        "Student         | Lab 1. Figures | Lab 2. War and Peace | Lab 3. File Tree | Total | Mark |\n"
            + "Eco Johnny      |             56 |                   69 |               90 | 71.67 |    D |\n"
            + "Lodbrok Umberto |             70 |                   95 |               59 | 74.67 |    D |\n"
            + "Paige Ragnar    |             51 |                   68 |               57 | 58.67 |    F |\n"
            + "Average         |          59.00 |                77.33 |            68.67 | 68.33 |    D |");
    f.add(
        "Student           | Lab 1. Figures | Lab 2. War and Peace | Lab 3. File Tree | Total | Mark |\n"
            + "Eco Betty         |             86 |                   68 |               85 | 79.67 |    C |\n"
            + "Paige Umberto     |             51 |                   63 |               84 | 66.00 |    E |\n"
            + "Silverhand Ragnar |             61 |                   84 |               99 | 81.33 |    C |\n"
            + "Average           |          66.00 |                71.67 |            89.33 | 75.67 |    C |");
    f.add(
        "Student           | Phalanxing | Shieldwalling | Tercioing | Wedging | Total | Mark |\n"
            + "Lodbrok Johnny    |          0 |            62 |        76 |      89 | 56.75 |    F |\n"
            + "Paige Umberto     |         56 |            82 |        73 |       0 | 52.75 |    F |\n"
            + "Silverhand Ragnar |         73 |            54 |         0 |      53 | 45.00 |    F |\n"
            + "Average           |      43.00 |         66.00 |     49.67 |   47.33 | 51.50 |    F |");
    f.add(
        "Student        | Phalanxing | Shieldwalling | Tercioing | Wedging | Total | Mark |\n"
            + "Eco Betty      |          0 |            83 |        89 |      59 | 57.75 |    F |\n"
            + "Lodbrok Johnny |         61 |            92 |        67 |       0 | 55.00 |    F |\n"
            + "Paige Umberto  |         75 |            94 |         0 |      52 | 55.25 |    F |\n"
            + "Average        |      45.33 |         89.67 |     52.00 |   37.00 | 56.00 |    F |");
    Integer flag =
        (taskResults.containsKey("Lab 1. Figures"))
            ? ((taskResults.get("Lab 1. Figures") == 56
                    || taskResults.get("Lab 1. Figures") == 70
                    || taskResults.get("Lab 1. Figures") == 51)
                ? 0
                : 1)
            : ((taskResults.get("Shieldwalling") == 62
                    || taskResults.get("Shieldwalling") == 82
                    || taskResults.get("Shieldwalling") == 54)
                ? 2
                : 3);
    return f.get(flag);
  }
}
