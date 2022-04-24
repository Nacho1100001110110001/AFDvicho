import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;


public class Test {
    public static void main(String[] args){
        try {
            List<String> values = Controller.read();

            AFD rut_parser = AFD.rut_test();

            String test1 = Controller.true_values(values, rut_parser);
            String test2 = Controller.all_values(values, rut_parser);

            print_values(test1, "Ruts encontrados");
            print_values(test2, "Todos los valores");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void print_values(String value, String description){
        JTextArea textArea = new JTextArea(value);
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JOptionPane.showConfirmDialog(null,new JScrollPane(textArea), description, JOptionPane.OK_CANCEL_OPTION);
    }


}
