import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName: DataSource
 * @Author Paulson
 * @Date 2020/7/17
 * @Description: 集合流式编程中数据源的获取
 */
public class DataSource {
    public static void main(String[] args) {
        collectionDataSource();
        arrayDataSource();
        arrayDataSource2();
    }


    /**
     * 将集合作为数据源，读取集合中的数据到一个流中
     */
    public static void collectionDataSource() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        // 同步流
        Stream<Integer> stream = list.stream();
        // 并发流 （效率更高）
        Stream<Integer> integerStream = list.parallelStream();
        System.out.println(stream);
        System.out.println(integerStream);
    }

    /**
     * 将数组作为数据源，读取集合中的数据到一个流中
     */
    public static void arrayDataSource() {
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Stream<Integer> stream = Arrays.stream(array);
        System.out.println(stream);
    }

    /**
     * 将数组作为数据源，读取集合中的数据到一个流中
     */
    public static void arrayDataSource2() {
        int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        IntStream stream = Arrays.stream(array);
        System.out.println(stream);
    }

}
