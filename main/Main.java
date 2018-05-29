package main;


import java.io.IOException;
import javax.swing.JFrame;
import org.jdom.JDOMException;


public class Main {
    
    //Jose Ortega Brenes
    //Josue Arguedas Duarte

    public static void main(String[] args) throws IOException, JDOMException {
        Interface familyT = new Interface();
        familyT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        familyT.pack();
        familyT.setVisible(true);
    }

}
