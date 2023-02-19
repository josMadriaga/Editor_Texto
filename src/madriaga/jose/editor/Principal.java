package madriaga.jose.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JPopupMenu.Separator;

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
        //---------------menu---------------------
        JPanel panelMenu=new JPanel();
        menu =new JMenuBar();

        archivo=new JMenu("Archivo");
        editar=new JMenu("Editar");
        seleccion=new JMenu("Seleccción");
        ver=new JMenu("Ver");
        apariencia= new JMenu("Apariencia");

        menu.add(archivo);
        menu.add(editar);
        menu.add(seleccion);
        menu.add(ver);

       //------------Elementos del menu archivo----------------
        creaItem("Nuevo Archivo","archivo","nuevo");
        creaItem("Abrir Archivo","archivo","abrir");
        editar.addSeparator();
        creaItem("Guardar","archivo","guardar");
        creaItem("Guardar Como","archivo","guardarComo");
        //--------------------------------------------------------
        //-----------Elementos del manu editar------------------
        creaItem("Deshacer","editar","");
        creaItem("Rehacer","editar","");
        editar.addSeparator();
        creaItem("Cortar","editar","");
        creaItem("Copiar","editar","");
        creaItem("Pegar","editar","");
        //------------------------------------------------------
        //------------Elementos del menu seleccion---------------
        creaItem("Seleccionar","seleccionar","");
        //-------------------------------------------------------
        //------------Elementos del menu ver---------------------
        creaItem("Numeracion","ver","");
        editar.addSeparator();
        ver.add(apariencia);
        creaItem("Normal","apariencia","");
        creaItem("Dark","apariencia","");
        //-------------------------------------------------------
        panelMenu.add(menu);
        //--------------------------------------

        //------------------Area de texto-----
        tPane=new JTabbedPane();

        //------------------------------------

        add(panelMenu);
        add(tPane);
        
    }

    public void creaItem(String rotulo, String menu, String accion){
        elementoItem=new JMenuItem(rotulo);

        if(menu.equals("archivo")){
            archivo.add(elementoItem);
            if(accion.equals("nuevo")){
                elementoItem.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        creaPanel();
                    }
                });
            }
            else if(accion.equals("abrir")){
                elementoItem.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        creaPanel();
                    }
                });
            }
            else if(accion.equals("guardar")){
                elementoItem.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        creaPanel();
                    }
                });
            }
            else if(accion.equals("guardarComo")){
                elementoItem.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        creaPanel();
                    }
                });
            }
        }else if(menu.equals("editar")){
            editar.add(elementoItem);
        }else if(menu.equals("seleccion")){
            seleccion.add(elementoItem);
        }else if(menu.equals("ver")){
            ver.add(elementoItem);
        }
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
    private JMenuBar menu;
    private JMenu archivo,editar,seleccion,ver,apariencia;
    private JMenuItem elementoItem;

}