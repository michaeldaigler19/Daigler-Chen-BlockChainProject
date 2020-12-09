package src.GUIComponents;

import src.Artefact;
import src.TestData;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.text.SimpleDateFormat;

/* ComboBoxDemo2.java requires no other files. */
public class DropDownButton extends JPanel
        implements ActionListener {

    static JFrame frame;
    JLabel result;
    String currentPattern;

    public DropDownButton() {
        TestData data1 = new TestData();
        ArrayList<Artefact> artefactChoices = new ArrayList<>();
        artefactChoices.add(data1.transaction3.getArtefact());
        artefactChoices.add(data1.transactsion2.getArtefact());
        artefactChoices.add(data1.transaction1.getArtefact());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        String[] patternExamples = {
            artefactChoices.get(0).toString(),
                artefactChoices.get(1).toString(),
                artefactChoices.get(2).toString(),

        };

        currentPattern = patternExamples[0];

        //Set up the UI for selecting a pattern.
        JLabel patternLabel1 = new JLabel("Enter the pattern string or");
        JLabel patternLabel2 = new JLabel("select one from the list:");

        JComboBox patternList = new JComboBox(patternExamples);
        patternList.setEditable(true);
        patternList.addActionListener(this);

        //Create the UI for displaying result.
        JLabel resultLabel = new JLabel("Artefact",
                JLabel.LEADING); //== LEFT
        result = new JLabel(" ");
        result.setForeground(Color.black);
        result.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black),
                BorderFactory.createEmptyBorder(5,5,5,5)
        ));

        //Lay out everything.
        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel,
                BoxLayout.PAGE_AXIS));
        patternPanel.add(patternLabel1);
        patternPanel.add(patternLabel2);
        patternList.setAlignmentX(Component.LEFT_ALIGNMENT);
        patternPanel.add(patternList);

        JPanel resultPanel = new JPanel(new GridLayout(0, 1));
        resultPanel.add(resultLabel);
        resultPanel.add(result);

        patternPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(patternPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(resultPanel);

        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

//        reformat();
    } //constructor

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String newSelection = (String)cb.getSelectedItem();
        currentPattern = newSelection;
//        reformat();
    }

    /** Formats and displays today's date. */
//    public void reformat() {
//        Date today = new Date();
//        SimpleDateFormat formatter =
//                new SimpleDateFormat(currentPattern);
//        try {
//            String dateString = formatter.format(today);
//            result.setForeground(Color.black);
//            result.setText(dateString);
//        } catch (IllegalArgumentException iae) {
//            result.setForeground(Color.red);
//            result.setText("Error: " + iae.getMessage());
//        }
//    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    static void createAndShowGUI() {
        //Create and set up the window.


        //Create and set up the content pane.
        JComponent newContentPane = new DropDownButton();
        newContentPane.setOpaque(true); //content panes must be opaque
        newContentPane.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

    }
}
