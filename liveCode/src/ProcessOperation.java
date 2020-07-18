import java.util.*;
import java.util.stream.Stream;

/**
 * @ClassName: ProcessOperation
 * @Author Paulson
 * @Date 2020/7/18
 * @Description: 流式编程的中间操作
 */
public class ProcessOperation {

    /**
     * 学生类
     * 存储于集合中数据类型
     */
    private static class Student implements Comparable<Student> {
        private String name;
        private Integer age;
        private Integer score;

        public Student(String name, Integer age, Integer score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
            return Objects.equals(name, student.name) &&
                    Objects.equals(age, student.age) &&
                    Objects.equals(score, student.score);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, score);
        }

        @Override
        public int compareTo(Student o) {
            return score - o.score;
        }


        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", score=" + score +
                    '}';
        }
    }

    public static void main(String[] args) {
        // filterUsage();
        // distinctUsage();
        // sortedUsage();
        // limitAndSkipUsage();
        // mapUsage();
        flatmapUsage();
    }

    /**
     * 中间操作：flatmap
     * 扁平化映射：一般用在map映射完成后，流中的数据是一个容器，而我们需要对容器中的数据进行处理
     * 此时使用扁平化映射
     */
    public static void flatmapUsage() {
        String[] array = {"hello", "world"};
        Stream<String> stream = Arrays.stream(array);
        // stream.map(String::toCharArray).forEach(e -> System.out.println(Arrays.toString(e)));
        stream.map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .forEach(System.out::println);
    }

    /**
     * 读取数据源
     *
     * @return 从数据源中读取到的数据
     */
    public static Stream<Student> getDataSource() {
        List<Student> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, new Student("小明", 18, 90),
                new Student("xiaohong", 19, 90),
                new Student("xiaohong", 20, 60),
                new Student("xiaohong", 18, 98),
                new Student("xiaohong", 17, 56),
                new Student("xiaohong", 21, 56),
                new Student("xiaohong", 20, 34),
                new Student("xiaohong", 22, 75),
                new Student("xiaohong", 21, 98),
                new Student("xiaohong", 28, 100),
                new Student("xiaobao", 28, 100),
                new Student("xiaobao", 28, 100),
                new Student("xiaohong", 15, 23)
        );
        return arrayList.stream();

    }

    /**
     * 中间操作：map
     * 元素映射：提供一个映射规则，将流中的每一个元素替换成指定的元素
     */
    public static void mapUsage() {
        Stream<Student> dataSource = getDataSource();
        // 获取所有学生的名字
        // dataSource.map(Student::getName).forEach(System.out::println);

        // 获取所有的成绩
        dataSource.map(Student::getScore).forEach(System.out::println);
    }

    /**
     * 中间操作：sorted
     * 排序
     * sorted() 将流中的数据按照其对应的类实现的Comparable接口提供的比较奥尼规则进行排序
     * sorted(Comparator<T> comparator) 将流中的数据按照昂参数接口提供的比较规则进行排序
     */
    public static void sortedUsage() {
        Stream<Student> dataSource = getDataSource();
        // dataSource.sorted().forEach(System.out::println);
        dataSource.sorted(Comparator.comparingInt(Student::getAge)).forEach(System.out::println);
    }

    /**
     * 中间操作：limit skip
     * limit：限制，表示截取流中的指定数量的数据
     * skip：跳过指定数量的数据，截取剩余部分
     */
    public static void limitAndSkipUsage() {
        Stream<Student> dataSource = getDataSource();
        // 获取成绩前3-5名
        dataSource.sorted((s1, s2) -> s2.getScore() - s1.getScore())
                .distinct()
                .skip(2)
                .limit(3)
                .forEach(System.out::println);
    }

    /**
     * 中间操作：distinct
     * 去重：无参，去重规则与hashSet相同
     * 1. hashcode
     * 2. equals
     */
    public static void distinctUsage() {
        Stream<Student> dataSource = getDataSource();
        dataSource.distinct().forEach(System.out::println);
    }

    /**
     * 中间操作：filter
     * 条件过滤： 将流中满足指定条件的数据保留，删除不满足指定条件的数据
     */
    public static void filterUsage() {
        Stream<Student> dataSource = getDataSource();
        // 过滤掉不及格的信息
        dataSource.filter(s -> s.getScore() >= 60).forEach(System.out::println);

    }

}
