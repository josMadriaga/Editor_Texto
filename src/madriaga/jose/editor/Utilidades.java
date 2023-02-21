package madriaga.jose.editor;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Utilidades {
    //-------agrega texto al final-------------------------
    public static void append(String linea, JTextPane areaTexto){
        try{
            Document doc=areaTexto.getDocument();
            doc.insertString(doc.getLength(), linea, null);
       }
       catch(BadLocationException exc){
            exc.printStackTrace();
       }
    }
    //--------------------------------------------------------
    //-----------para mostrar numeracion de renglones en paginas-------------
    public static void viewNumeracionInicio(boolean numeracion,JTextPane textArea, JScrollPane scroll){//numeracion: indica si esta activa o no la numeracion;JTextPane: area del texto;JScrollPane: le paso el scroll 
        if(numeracion){
            scroll.setRowHeaderView(new TextLineNumber(textArea));
        }else{
            scroll.setRowHeaderView(null);
        }
        //setRowHeaderView= agrega una columna al margen izq en el que se agrega la numeracion
    }
    public static void viewNumeracion(boolean numeracion,ArrayList<JTextPane> textArea, ArrayList<JScrollPane> scroll){
        if(numeracion){
            for(JScrollPane sc: scroll){
                sc.setRowHeaderView(new TextLineNumber(textArea.get(scroll.indexOf(sc))));//agrego numeracion a todas las areas de texto
            }
        }else{
            for(JScrollPane sc: scroll){
                sc.setRowHeaderView(null);//quito numeracion a todas las areas de texto
            }
        }
    }
    //-----------------------------------------------------------------------

}
