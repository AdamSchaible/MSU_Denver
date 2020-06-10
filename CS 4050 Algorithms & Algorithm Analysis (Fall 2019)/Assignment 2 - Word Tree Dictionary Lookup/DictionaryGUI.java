//Name Adam Schaible
//CS4050
//Programming Assignment 2

//RUN THIS FILE TO PERFORM THE DICTIONARY SEARCH 

//modified from https://docs.oracle.com/javase/tutorial/uiswing/examples/events/KeyEventDemoProject/src/events/KeyEventDemo.java
//comments made by myself(Adam Schaible) have "-AS" at the end of the comment line

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
//removed import java.awt.Container; -AS
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.FileNotFoundException; //added line -AS
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

//renamed class from KeyEventDemo to DictionaryGUI, removed ActionListener -AS
public class DictionaryGUI extends JFrame implements KeyListener
{
    DictionaryTree temporaryTree = new DictionaryTree(); //added this line -AS
    Trie dictionaryTreeUsed = temporaryTree.getADictionaryTree(); //added this line -AS

    JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");

    public static void main(String[] args) {

        try {
            //REMOVED TWO LINES OF COMMENTED OUT CODELINE THAT WAS HERE -AS
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            //ADDED A TRY CATCH HERE -AS
            public void run() {
                try {
                    createAndShowGUI();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() throws FileNotFoundException {
        //Create and set up the window.
        DictionaryGUI frame = new DictionaryGUI("DictionaryTree search: Start typing a word to get hints for how to complete it - note only use backspace to undo what you typed"); //Added line of code and removed KeyEventDemo object initialization -AS
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        frame.addComponentsToPane();


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane() {

        //JButton button = new JButton("Clear"); //commented out, not used -AS
        //button.addActionListener(this); //commented out, not used -AS

        typingArea = new JTextField(50);
        typingArea.addKeyListener(this);

        //REMOVED A BLOCK OF COMMENTS -AS

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(800, 600)); //changed dimensions -AS

        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        //getContentPane().add(button, BorderLayout.PAGE_END); //Commented out -AS
    }

    //Renamed KeyEventDemo to DictionaryGUI and added a FileNotFoundException -AS
    public DictionaryGUI(String name) throws FileNotFoundException { 
        super(name);
    }


    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        displayInfo(e);
    }
    //DELETED keyPressed,keyReleased,actionPerformed methods -AS
    @Override
    public void keyPressed(KeyEvent e) {} //added line -AS
    @Override
    public void keyReleased(KeyEvent e) {} //added line -AS
    //removed comment block -AS

    private void displayInfo(KeyEvent e){ //removed parameter keyStatus -AS
        //You should only rely on the key char if the event
        //is a key typed event.
        //int id = e.getID(); //commented out, not needed -AS
        //removed if, else statement and modified code that was in if statement -AS

        //Start of more code that I wrote -AS

        String lastCharacter = "" + e.getKeyChar();
        lastCharacter = lastCharacter.toLowerCase();

        int spot = lastCharacter.charAt(0) - 'a'; //sidenote for spot --> backspace = -89 and delete = 30

        if((spot == -89) || (spot == 30)) //If backspace or delete is pushed
        {
            lastCharacter = "";
        }


        String user = typingArea.getText() + lastCharacter;
        user = user.toLowerCase();

        Object[] spotInDictionaryTree = dictionaryTreeUsed.searchForSpotInTree(user);
        Trie.TrieNode currentlyAtNode = (Trie.TrieNode)spotInDictionaryTree[1]; //casting object as TrieNode
        boolean stillInsideDictionary = (boolean)spotInDictionaryTree[0]; //casting object as integer

        PredictionList predictor = new PredictionList();
        List <String> emptyList = new ArrayList<String>();

        List <String> predictorMatches = predictor.getStringArray(currentlyAtNode,emptyList,stillInsideDictionary);
        if(!stillInsideDictionary)
        {
            predictorMatches.clear();
        }

        String ofTenMatches = "";
        for(int i = 0; (i< predictorMatches.size())&&(i<10); i++){
            ofTenMatches = ofTenMatches + predictorMatches.get(i) + "\n";
        }

        displayArea.setText("");
        if(!((spot == -89) && (typingArea.getText().length() == 0)))
        {
            displayArea.append("Are you typing?: \n\n" + ofTenMatches + newline); //deleted keyStatus, modString,ActionString, locationString -AS
        }

        if(((spot == -89) && (typingArea.getText().length() == 0)))
        {
            displayArea.append("Type a word:" + newline); //deleted keyStatus, modString,ActionString, locationString -AS
        }
        if(!stillInsideDictionary)
        {
            displayArea.append("What you typed does not match the spelling of any word in the dictionary. \n"  + newline);
            displayArea.append("Use the backspace to clear out what you typed. \n\n" + ofTenMatches + newline);
        }
        //End of more code that I added -AS
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }
}
