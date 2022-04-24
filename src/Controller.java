import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Controller {
    public static List<String> read() throws IOException{
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "txt Files", "txt");
        fileChooser.setFileFilter(filter);

        int response = fileChooser.showOpenDialog(null);
        if(response == JFileChooser.CANCEL_OPTION) System.exit(0);
        if(response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(Pattern.compile(",*\\p{javaWhitespace}+"));
            return scanner.tokens().toList();
        }
        return new ArrayList<>();
    }
    public static String all_values(List<String> file_tokens, AFD afd){
        return file_tokens
                .stream()
                .map(x-> x +": "+ afd.process(x))
                .reduce("", (x, y) -> x + "\n" + y);
    }
    public static String  true_values(List<String> file_tokens, AFD afd){
        return file_tokens
                .stream()
                .filter(afd::process)
                .map(x-> x +": true")
                .reduce("", (x, y) -> x + "\n" + y);

    }
}
