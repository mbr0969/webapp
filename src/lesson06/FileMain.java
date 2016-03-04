package lesson06;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class FileMain {
    public static void main(String[] args) throws IOException {

        Stream<String> lines = Files.lines(Paths.get("D:\\SVN\\Java1.txt"), StandardCharsets.UTF_8);
        Iterator<String> it = lines.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();

        Files.lines(Paths.get("D:\\SVN\\Java1.txt"), StandardCharsets.UTF_8).forEach(System.out::println);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("d:\\SVN\\Java1.txt"), StandardCharsets.UTF_8));

        System.out.println();
        String line;
        while((line=reader.readLine()) != null)
            System.out.println(line);
    }
}
