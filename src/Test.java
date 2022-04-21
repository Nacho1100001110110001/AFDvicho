import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args){
        File file = new File("C:\\Users\\Vicho\\Desktop\\ruts.txt");
        try(Scanner scanner = new Scanner(file)){
            //System.out.println(scanner.delimiter().pattern());
            scanner.useDelimiter(Pattern.compile(",*\\p{javaWhitespace}+"));
            AFD rut_parser = AFD.rut_test();
            Stream<String> file_tokens = scanner.tokens();

            Test.true_values(file_tokens, rut_parser);
            Test.all_values(file_tokens, rut_parser);

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    static void all_values(Stream<String> file_tokens, AFD afd){
        file_tokens
                .map(x-> x +": "+ afd.process(x))
                .forEach(System.out::println);
    }
    static void true_values(Stream<String> file_tokens, AFD afd){
        file_tokens
                .filter(afd::process)
                .map(x-> x +": true")
                .forEach(System.out::println);
    }
}
