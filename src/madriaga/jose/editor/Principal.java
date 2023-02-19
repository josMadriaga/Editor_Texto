package madriaga.jose.editor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class Principal {
    public static void main(String[] args) {
        Marco marco=new Marco();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//necesita el nombre de la clase y el nombre del metodo oconstante que necesitamos
        marco.setVisible(true);//siempre al final para que cargue todo antes de visualizar
    }
}
class Marco extends JFrame{
    public Marco(){
        setBounds(300, 300, 300, 300);
        setTitle("josEditor");
       
        add(new Panel());
    }
}
class Panel extends JPanel{
    public Panel(){

        //------------------Area de texto-----
        tPane=new JTabbedPane();

        //------------------------------------

        add(tPane);
        
    }

    public void creaPanel(){
        ventana=new JPanel();
        areaTexto=new JTextPane();

        ventana.add(areaTexto);//a la ventana le agrego el area de texto
        tPane.addTab("TITLE",ventana);//agrego la nueva ventana
    }

    private JTabbedPane tPane;
    private JPanel ventana;
    private JTextPane areaTexto;

}