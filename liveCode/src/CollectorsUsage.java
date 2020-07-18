import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: CollectorsUsage
 * @Author Paulson
 * @Date 2020/7/18
 * @Description:
 */
public class CollectorsUsage {

    public static void main(String[] args) {

        String[] array = {"hello", "world", "bigDate", "salary", "goodProgrammer"};
        Stream<String> stream = Arrays.stream(array);

        // joining方法，只适用于Stream<String>
        // String result = stream.collect(Collectors.joining());
        // String result = stream.collect(Collectors.joining(", ", "[", "]"));

        // int result = stream.mapToInt(String::length).sum();
        // IntSummaryStatistics result = stream.collect(Collectors.summarizingInt(String::length));
        List<String> result = stream.collect(Collectors.toList());
        System.out.println(result);
    }
}
