/**

 *

 * 

 * @author Martijn van der Bruggen (Martijn.vanderBruggen@han.nl)

 * @version alpha - logica ontbreekt 

 * 

 * (c) 2006-2015 Hogeschool van Arnhem en Nijmegen

 *

 * Tic Tac Toe 4 BioInformatics - Afvinkopdracht 4 Creation date: 9 December

 * 2006 MvdB: 4 December 2007 - added usage of arrays and iteration MvdB: 20

 * November 2008 - added search for image path - rewrite of Message method MvdB:

 * 2 December 2009 - added additional header info panel MvdB: 24 November 2010 -

 * added additional comments MvdB: 7 december 2012 - correction image reference

 * MvdB: 19 september 2013 - renamed course reference and minor updates MvdB: 29

 * september 2015 - resized frame and some minor updates

 *

 * Onvolledige versie, implementatie van if-then-else en loops is vereist Neem

 * de bijbehorende images op in dezelfde directory als de gecompileerde classes,

 * dan zal het programma ze zelf vinden.

 *

 */

package afvinkopdracht.pkg4;



import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.*;



public class Afvinkopdracht4 extends JFrame implements ActionListener {



    /**

     * declaratie van globale variabelen en button array Merk op: access

     * modifiers staan op private. Final variabelen in hoofdletters

     *

     */

    private static JButton[][] button;  //declaratie van de array in array (geneste list in Python)

    private static final int XAS = 3, YAS = 3;  // Grootte van de array

    private JPanel panel, panelInfo, panelHeader; // Panels

    private static final String TITEL = "Tic Tac Toe 4 BioInformatics";  //Titel

    private static String imagePad, seperator = "/"; // Seperator forward slash zowel voor Unix als Windows geschikt

    private static ImageIcon leeg, kruisje, rondje, vraagteken;  //images

    private static JLabel messageLabel, headerLabel;  //labels met informatieve teksten
    
    private int count = 0;



    /**

     * Applicatie: main method, instantiering van frame (deze class) en

     * initialisering van GUI

     */

    public static void main(String[] args) {

        Afvinkopdracht4 frame = new Afvinkopdracht4();

        setImages();

        frame.setTitle(TITEL);

        frame.setSize(250, 280);

        frame.createGUI();

        frame.setVisible(true);

        // Zet icon voor programma

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(imagePad + "icon.jpg"));



    }



    /**

     * Zoeken en laden van images Pas hier je pad aan naar de images als de

     * images niet worden gevonden of als je andere images wilt gebruiken.

     */

    private static void setImages() {

        imagePad = System.getProperty("java.class.path") + seperator + "assets" + seperator;

        imagePad = imagePad.substring(imagePad.indexOf(':') + 1);

        System.out.println("Image pad: " + imagePad);

        leeg = new ImageIcon(imagePad + "leeg.jpg");

        kruisje = new ImageIcon(imagePad + "kruisje.jpg");

        rondje = new ImageIcon(imagePad + "rondje.jpg");

        vraagteken = new ImageIcon(imagePad + "question.jpg");

    }



    /**

     * Geef een bericht over de status van het spel Merk op dat deze methode

     * overloaded is

     *

     * @param message String die een bericht weergeeft in de status console

     *

     * @param tooltip Mouse over bericht

     *

     */

    private static void message(String message, String tooltip) {

        messageLabel.setText(message);

        messageLabel.setToolTipText(tooltip);

    }



    private static void message(String message) {

        message(message, message);

    }



    /**

     * Creatie van de Graphical User Interface

     */

    private void createGUI() {
        
        button = new JButton[XAS][YAS]; // Declareer de button array

        setDefaultCloseOperation(EXIT_ON_CLOSE); // Zorg dat het window gesloten wordt

        Container window = getContentPane(); // Creatie van een Window waarin twee JPanels worden geplaatst

        window.setLayout(new FlowLayout()); // Zet flowLayout in het Window



        panel = new JPanel();

        panel.setLayout(new GridLayout(XAS, YAS, 5, 5));// GridLayout om in een matrix van 3 bij 3 de velden te plaatsen

        panel.setPreferredSize(new Dimension(160, 160));

        panel.setBackground(Color.BLACK);

        panelInfo = new JPanel();

        panelInfo.setMaximumSize(new Dimension(200, 100));

        panelInfo.setLayout(new FlowLayout());

        panelInfo.setBackground(Color.GRAY);

        panelHeader = new JPanel();

        panelHeader.setMaximumSize(new Dimension(250, 20));

        panelHeader.setPreferredSize(new Dimension(250, 20));

        panelHeader.setLayout(new FlowLayout());

        panelHeader.setBackground(Color.lightGray);

        messageLabel = new JLabel("");

        headerLabel = new JLabel("Bioinformatica Tic Tac Toe");

        panelInfo.add(messageLabel);

        panelHeader.add(headerLabel);



        // Voeg aan het window de twee panels toe

        window.add(panelHeader);

        window.add(panel);

        window.add(panelInfo);



        //Geneste for-loop om de buttons te vullen en te tekenen

        for (int x = 0; x < XAS; x++) {

            for (int y = 0; y < YAS; y++) {

                button[x][y] = new JButton();

                button[x][y].addActionListener(this);

                button[x][y].setIcon(leeg);

                button[x][y].setRolloverIcon(vraagteken);

                panel.add(button[x][y]);

            }

        }

        message("Rondje mag beginnen");

    }



    /*

     * Actie indien de button geklikt is Hier vindt de afhandeling van het

     * klikken van een button plaats. Er zijn 9 buttons die op basis van de

     * source onderscheiden kunnen worden. Zorg dat indien een button wordt

     * geklikt deze een rondje of kruisje krijgt afhankelijk van wie er aan zet

     * is. Controleer na het plaatsten van het rondje of kruisje of iemand 3 op

     * een rij heeft of wanneer het laatste vakje is ingevuld of er iemand

     * gewonnen heeft.

     */

    public void actionPerformed(ActionEvent event) {

        /**

         * Code om events af te vangen en te reageren Tip werk met aanroep van

         * methodes en neem niet alle code in deze methode op

         */

        Object buttonPressed = event.getSource();
        
        count = ++count;
        
        if ((count%2) ==0){        
        message("rondje is aan de beurt");
        ((JButton)buttonPressed).setIcon(kruisje);
        ((JButton)buttonPressed).setEnabled(false);}
        if ((count%2)==1){
        ((JButton)buttonPressed).setIcon(rondje);
        message("kruisje is aan de beurt");
        ((JButton)buttonPressed).setEnabled(false);}
        
       
            

        /*

         * Als voorbeeld is hier opgenomen de boolean expressie

         * om te achterhalen of het vakje linksboven is aangeklikt

         * Bouw hieromheen een loop (of geneste loop?) met daarin

         * een if structuur om een kruisje danwel een rondje op de

         * button te plaatsen

         */         
        
        if (button[0][0].getIcon()==rondje && button[1][0].getIcon()==rondje && button[2][0].getIcon()==rondje){
           System.out.println("rondje wint!"); 
           JOptionPane.showMessageDialog(null, "rondje wint!");
       }

       if (button[0][1].getIcon()==rondje && button[1][1].getIcon()==rondje && button[2][1].getIcon()==rondje){
           System.out.println("rondje wint!"); 
           JOptionPane.showMessageDialog(null, "rondje wint!");
       }

       if (button[0][2].getIcon()==rondje && button[1][2].getIcon()==rondje && button[2][2].getIcon()==rondje){
           System.out.println("rondje wint!"); 
           JOptionPane.showMessageDialog(null, "rondje wint!");
       }
       if (button[0][0].getIcon()==rondje && button[0][1].getIcon()==rondje && button[0][2].getIcon()==rondje){
           System.out.println("rondje wint!"); 
           JOptionPane.showMessageDialog(null, "rondje wint!");
       }

       if (button[1][0].getIcon()==rondje && button[1][1].getIcon()==rondje && button[1][2].getIcon()==rondje){
           System.out.println("rondje wint!"); 
           JOptionPane.showMessageDialog(null, "rondje wint!");
       }

       if (button[2][0].getIcon()==rondje && button[2][1].getIcon()==rondje && button[2][2].getIcon()==rondje){
           System.out.println("rondje wint!"); 
           JOptionPane.showMessageDialog(null, "rondje wint!");
       }

       if (button[0][0].getIcon()==kruisje && button[0][1].getIcon()==kruisje && button[0][2].getIcon()==kruisje){
           System.out.println("kruisje wint!"); 
           JOptionPane.showMessageDialog(null, "kruisje wint!");
       }

       if (button[0][1].getIcon()==kruisje && button[1][1].getIcon()==kruisje && button[2][1].getIcon()==kruisje){
           System.out.println("kruisje wint!"); 
           JOptionPane.showMessageDialog(null, "kruisje wint!");
       }

       if (button[0][2].getIcon()==kruisje && button[1][2].getIcon()==kruisje && button[2][2].getIcon()==kruisje){
           System.out.println("kruisje wint!"); 
           JOptionPane.showMessageDialog(null, "kruisje wint!");
       }

       if (button[0][0].getIcon()==kruisje && button[1][0].getIcon()==kruisje && button[2][0].getIcon()==kruisje){
           System.out.println("kruisje wint!"); 
           JOptionPane.showMessageDialog(null, "kruisje wint!");
       }

       if (button[1][0].getIcon()==kruisje && button[1][1].getIcon()==kruisje && button[1][2].getIcon()==kruisje){
           System.out.println("kruisje wint!"); 
           JOptionPane.showMessageDialog(null, "kruisje wint!");
       }

       if (button[2][0].getIcon()==kruisje && button[2][1].getIcon()==kruisje && button[2][2].getIcon()==kruisje){
           System.out.println("kruisje wint!"); 
           JOptionPane.showMessageDialog(null, "kruisje wint!");
       }

       if (button[0][0].getIcon()==kruisje && button[1][1].getIcon()==kruisje && button[2][2].getIcon()==kruisje){
           System.out.println("kruisje wint!"); 
           JOptionPane.showMessageDialog(null, "kruisje wint!");
       }

       if (button[0][0].getIcon()==rondje && button[1][1].getIcon()==rondje && button[2][2].getIcon()==rondje){
           System.out.println("rondje wint!"); 
           JOptionPane.showMessageDialog(null, "rondje wint!");
       }

       if (button[0][2].getIcon()==kruisje && button[1][1].getIcon()==kruisje && button[2][0].getIcon()==kruisje){
           System.out.println("kruisje wint!"); 
           JOptionPane.showMessageDialog(null, "kruisje wint!");
       }

       if (button[0][2].getIcon()==rondje && button[1][1].getIcon()==rondje && button[2][0].getIcon()==rondje){
           System.out.println("rondje wint!"); 
           JOptionPane.showMessageDialog(null, "rondje wint!");
       }
       }
      
    }
    


    

