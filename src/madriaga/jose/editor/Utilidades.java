package madriaga.jose.editor;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class Utilidades {
    // -------agrega texto al final-------------------------
    public static void append(String linea, JTextPane areaTexto) {
        try {
            Document doc = areaTexto.getDocument();
            doc.insertString(doc.getLength(), linea, null);
        } catch (BadLocationException exc) {
            exc.printStackTrace();
        }
    }

    // --------------------------------------------------------
    // -----------para mostrar numeracion de renglones en paginas-------------
    public static void viewNumeracionInicio(boolean numeracion, JTextPane textArea, JScrollPane scroll) {// numeracion:
                                                                                                         // indica si
                                                                                                         // esta activa
                                                                                                         // o no la
                                                                                                         // numeracion;JTextPane:
                                                                                                         // area del
                                                                                                         // texto;JScrollPane:
                                                                                                         // le paso el
                                                                                                         // scroll
        if (numeracion) {
            scroll.setRowHeaderView(new TextLineNumber(textArea));
        } else {
            scroll.setRowHeaderView(null);
        }
        // setRowHeaderView= agrega una columna al margen izq en el que se agrega la
        // numeracion
    }

    public static void viewNumeracion(boolean numeracion, ArrayList<JTextPane> textArea,
            ArrayList<JScrollPane> scroll) {
        if (numeracion) {
            for (JScrollPane sc : scroll) {
                sc.setRowHeaderView(new TextLineNumber(textArea.get(scroll.indexOf(sc))));// agrego numeracion a todas
                                                                                          // las areas de texto
            }
        } else {
            for (JScrollPane sc : scroll) {
                sc.setRowHeaderView(null);// quito numeracion a todas las areas de texto
            }
        }
    }
    // -----------------------------------------------------------------------
    //-----------------Seccion de apariencia----------------------------------
    public static void aFondo(String tipo, ArrayList<JTextPane> textArea){
        if(tipo.equals("White")){
            for(JTextPane ta:textArea){
                ta.selectAll();
                StyleContext sc=StyleContext.getDefaultStyleContext();
                //PARA EL COLOR DEL TEXTO
                AttributeSet aset=sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,Color.BLACK);//SimpleAttributeSet.EMPTY=es un objeto vacio,porque apenas se ha creado. Las letras color negro por el fondo blanco. 
                //PARA LA FUENTE DE TEXTO
                aset=sc.addAttribute(aset,StyleConstants.FontFamily,"Arial");//fontFamily es para notar que queremos cambiar las fuentes.

                ta.setCharacterAttributes(aset, false);
                ta.setBackground(Color.WHITE);
            }
        }else  if(tipo.equals("Dark")){
            for(JTextPane ta:textArea){
                ta.selectAll(); 
                StyleContext sc=StyleContext.getDefaultStyleContext();
                //PARA EL COLOR DEL TEXTO
                AttributeSet aset=sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,Color.WHITE);//SimpleAttributeSet.EMPTY=es un objeto vacio,porque apenas se ha creado. Las letras color negro por el fondo blanco. 
                //PARA LA FUENTE DE TEXTO
                aset=sc.addAttribute(aset,StyleConstants.FontFamily,"Arial");//fontFamily es para notar que queremos cambiar las fuentes.

                ta.setCharacterAttributes(aset, false);
                ta.setBackground(Color.DARK_GRAY);
            }
        }
    }
    //------------------------------------------------------------------------
    //-----------------------------Button-------------------------------------
    public static JButton addButton(URL url, Object objContenedor, String rotulo){
            JButton button=new JButton(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            button.setToolTipText(rotulo);
            ( (Container) objContenedor).add(button);
            return button;
    }
    //------------------------------------------------------------------------
}
