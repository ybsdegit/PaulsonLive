import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName: FinalOperation
 * @Author Paulson
 * @Date 2020/7/17
 * @Description: 最终操作
 */
public class FinalOperation {
    public static void main(String[] args) {
        // colloctUsage();
        // reduceUsage();
        // countUsage();
        // forEachUsage();
        // MaxAndMinUsage();
        // matchUsage();
        // findUsage();
        IntStreamUsage();
    }

    /**
     * IntStream
     */
    public static void IntStreamUsage() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        IntStream stream = Arrays.stream(array);
        // OptionalInt result = stream.max();
        // OptionalInt result = stream.min();
        // System.out.println(result.orElse(-1));

        // int result = stream.sum();
        // long result = stream.count();
        // double result = stream.average().getAsDouble();
        // System.out.println(result);

        // 对流中的数据进行分析
        IntSummaryStatistics intSummaryStatistics = stream.summaryStatistics();
        System.out.println("max = " + intSummaryStatistics.getMax());
        System.out.println("min = " + intSummaryStatistics.getMin());
        System.out.println("sum = " + intSummaryStatistics.getSum());
        System.out.println("average = " + intSummaryStatistics.getAverage());
        System.out.println("count = " + intSummaryStatistics.getCount());
    }


    /**
     * findFirst 从流中获取开头元素
     * findAny 从流中获取元素（一般是开头元素） 在多线程的环境下，可能不是开头元素
     */
    public static void findUsage() {
        // Stream<Integer> dataSource = getDataSource();
        // Optional<Integer> result = dataSource.findFirst();
        // Optional<Integer> result = dataSource.findAny();


        List<Integer> dataSourse = new ArrayList<>();
        Collections.addAll(dataSourse, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional<Integer> result = dataSourse.parallelStream().findAny();

        System.out.println(result.orElse(null));
    }

    /**
     * allMatch anyMatch noneMatch
     */
    public static void matchUsage() {
        Stream<Integer> dataSource = getDataSource();
        // boolean result = dataSource.allMatch(e -> e >= 0);
        // boolean result = dataSource.anyMatch(e -> e >= 9);
        boolean result = dataSource.noneMatch(e -> e > 9);
        System.out.println(result);
    }

    /**
     * max and min
     * 按照指定的对象比较的规则，进行大小比较，得出流中最大或最小的元素
     */
    public static void MaxAndMinUsage() {
        Stream<Integer> dataSource = getDataSource();
        // Optional<Integer> result = dataSource.max(Integer::compareTo);
        Optional<Integer> result = dataSource.min(Integer::compareTo);
        System.out.println(result.orElse(0));

    }

    /**
     * forEach
     * 遍历流中数据
     */
    public static void forEachUsage() {
        Stream<Integer> dataSource = getDataSource();
        dataSource.forEach(System.out::println);
    }

    /**
     * 统计流中数据的数量
     */
    public static void countUsage() {
        Stream<Integer> dataSource = getDataSource();
        long count = dataSource.count();
        System.out.println(count);
    }

    /**
     * reduce
     * 将流中的数据按照一定的规则聚合起来
     */
    public static void reduceUsage() {
        Stream<Integer> dataSource = getDataSource();
        Optional<Integer> reduce = dataSource.reduce(Integer::sum);
        System.out.println(reduce.orElse(0));
    }

    /**
     * 最终操作
     * collect 将流中的数据整起来，最常见的处理是：读取流中的数据，整个到一个容器中，得到一个集合
     */
    public static void colloctUsage() {

        Stream<Integer> dataSource = getDataSource();
        // List<Integer> collect = dataSource.collect(Collectors.toList());
        // Set<Integer> collect = dataSource.collect(Collectors.toSet());
        Map<String, Integer> collect = dataSource.collect(Collectors.toMap(Object::toString, i -> i));

        System.out.println(collect);
    }

    /**
     * 数据源的获取，从一个容器中获取数据源中的数据
     *
     * @return 读取数据源中的数据，得到的一个 Stream 对象
     */
    public static Stream<Integer> getDataSource() {
        List<Integer> dataSourse = new ArrayList<>();
        Collections.addAll(dataSourse, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        return dataSourse.stream();
    }


}
