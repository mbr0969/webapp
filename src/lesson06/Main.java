package lesson06;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int i = 10;
        System.out.println(Integer.valueOf(10) == Integer.valueOf(10));
        System.out.println(Integer.valueOf(1000).equals( Integer.valueOf(1000)));

        print(Collections.emptyList());
    }

    public static <T> void print(List<T> list){
        System.out.println(list.toString());
    }
}
