import javax.swing.*;
import java.awt.*;
public class App extends JFrame{
    private static JLabel directionsLabel=new JLabel("Enter you name in the box");
    private static JLabel outputLabel=new JLabel();
    private static JTextField nameBox=new JTextField(25);
    private static JButton nameButton=new JButton("Click me");
    public static void main(String[] args) throws Exception {
        App window =new App();
        window.setSize(500,500);
        window.setVisible(true);
        window.setTitle("Editor");

        window.setLayout(new FlowLayout());
        window.getContentPane().add(directionsLabel);
        window.getContentPane().add(nameBox);
        window.getContentPane().add(nameButton);
        window.getContentPane().add(outputLabel);

        //add Swing objects here
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
