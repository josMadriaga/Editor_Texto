package madriaga.jose.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
        listFile=new ArrayList<File>();
        listAreaDeTexto=new ArrayList<JTextPane>();
        listScrool=new ArrayList<JScrollPane>();//scroll es la barra de desplazamineto
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
                        JFileChooser selectorArchivos=new JFileChooser();
                        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//visualizar directorios y archivos
                        int resultado=selectorArchivos.showOpenDialog(listAreaDeTexto.get(tPane.getSelectedIndex()));
                        
                        if(resultado==JFileChooser.APPROVE_OPTION){
                            try {
                                boolean existePath=false;
                                for(int i=0;i<tPane.getTabRunCount();i++){
                                    File f=selectorArchivos.getSelectedFile();
                                    if(listFile.get(i).getPath().equals(f.getPath()))//si en las direcciones ya abiertas existe la nueva 
                                    existePath=true;

                                }
                                if(!existePath){
                                    File archivo=selectorArchivos.getSelectedFile();
                                    listFile.set(tPane.getSelectedIndex(), archivo);
                                    FileReader entrada=new FileReader(listFile.get(tPane.getSelectedIndex()).getPath());
                                    BufferedReader miBuffer=new BufferedReader(entrada);
                                    String linea="";
                                    String titulo=listFile.get(tPane.getSelectedIndex()).getName();//el nombre del archivo
                                    tPane.setTitleAt(tPane.getSelectedIndex(), titulo);//el titulo se le agrega a la pestaña del panel nuevo que se crea, donde se encuentra el area de texto, donde ira el texto del archivo que el usuario ha seleccionado.
                                    while(linea!=null){
                                        linea=miBuffer.readLine();//lee linea a linea del archivo y lo almacena en el string
                                        if(linea!=null)Utilidades.append(linea+"\n", listAreaDeTexto.get(tPane.getSelectedIndex()));
                                    }
                                }else{
                                    for(int i =0;i<tPane.getTabCount();i++){
                                        File f=selectorArchivos.getSelectedFile();
                                        if(listFile.get(i).getPath().equals(f.getPath())){
                                            tPane.setSelectedIndex(i);//seleccionamos el panel que ya tiene el aarchivo abierto, pasandole por parametro la posicion en que tiene el path 
                                            eliminarUltimoPanel();
                                            break;//esto no siga ejecutanto al encontrar uno igual, para el ciclo for.
                                        }
                                    }
                                }

                            } catch (IOException e1) {
                                // TODO: handle exception
                            }
                        }else{
                            //Si se oprime el boton cancelar en la ventana de abrir archivo elimino el panel del area de texto que se crea por defecto
                            int seleccion=tPane.getSelectedIndex();
                            if(seleccion!=-1){
                                eliminarUltimoPanel();
                            }
                    }
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
       // areaTexto=new JTextPane();
       
        listFile.add(new File(""));
        listAreaDeTexto.add(new JTextPane());
        listScrool.add(new JScrollPane(listAreaDeTexto.get(contadorPanel)));

        ventana.add(listScrool.get(contadorPanel));

        tPane.addTab("TITLE",ventana);//agrego la nueva ventana
        tPane.setSelectedIndex(contadorPanel);
        contadorPanel++;
        existePanel=true;
    }
    public void eliminarUltimoPanel(){
        listAreaDeTexto.remove(tPane.getTabCount() - 1);
        listScrool.remove(tPane.getTabCount() - 1);
        listFile.remove(tPane.getTabCount() - 1);
        tPane.remove(tPane.getTabCount() - 1);// eliminamos el panel creado por ultima vez porque ya existe el panel y
                                              // no lo necesitamos
        contadorPanel--;
    }

    private int contadorPanel=0;//cuanta cuantos paneles se han creado
    private boolean existePanel=false;//nos dice si inicialmente existe un panel creado

    private JTabbedPane tPane;
    private JPanel ventana;
   // private JTextPane areaTexto;
    private ArrayList<File> listFile;
    private ArrayList<JScrollPane>listScrool;
    private ArrayList<JTextPane> listAreaDeTexto;

    private JMenuBar menu;
    private JMenu archivo,editar,seleccion,ver,apariencia;
    private JMenuItem elementoItem;

}