import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args){
        File file = new File("C:\\Users\\Vicho\\IdeaProjects\\AFD\\src\\ruts.txt");
        try(Scanner scanner = new Scanner(file)){
            scanner.useDelimiter(Pattern.compile(",*\\p{javaWhitespace}+"));
            AFD rut_parser = AFD.rut_test();
            List<String> file_tokens = scanner.tokens().toList();

            Test.true_values(file_tokens, rut_parser);
            Test.all_values(file_tokens, rut_parser);

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void all_values(List<String> file_tokens, AFD afd){
        file_tokens
                .stream()
                .map(x-> x +": "+ afd.process(x))
                .forEach(System.out::println);
        System.out.println();
    }
    public static void true_values(List<String> file_tokens, AFD afd){
        file_tokens
                .stream()
                .filter(afd::process)
                .map(x-> x +": true")
                .forEach(System.out::println);
        System.out.println();
    }
}
