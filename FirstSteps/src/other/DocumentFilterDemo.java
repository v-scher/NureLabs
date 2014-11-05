package other;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
public class DocumentFilterDemo extends JPanel implements ActionListener 
{

    private int hours;
    private JLabel hoursLabel;
    private JLabel minsLabel;
    private static String hoursString = " hours: ";
    private static String minsString = " minutes: ";
    private JComboBox timeList;
    private JTextField hoursField;

    public DocumentFilterDemo() 
    {
        super(new BorderLayout());

        hoursLabel = new JLabel(hoursString);
        minsLabel = new JLabel(minsString);
        hoursField = new JTextField();
        //hoursField.setValue(new Integer(hours));
        hoursField.setColumns(10);
        hoursLabel.setLabelFor(hoursField);
        minsLabel.setLabelFor(minsLabel);
        Document doc = hoursField.getDocument();
        if (doc instanceof AbstractDocument)
        {
            AbstractDocument abDoc  = (AbstractDocument) doc;
            abDoc.setDocumentFilter(new DocumentInputFilter());
        }

        JPanel fieldPane = new JPanel(new GridLayout(0, 2));

        JButton cntButton = new JButton("Continue");
        cntButton.setActionCommand("cnt");
        cntButton.addActionListener(this);
        JButton prevButton = new JButton("Back");

        String[] quarters = { "15", "30", "45" };

        /*
         * Declared timeList as an Instance Variable, so that 
         * it can be accessed inside the actionPerformed(...)
         * method.
         */
        timeList = new JComboBox(quarters);
        timeList.setSelectedIndex(2);
        timeList.addActionListener(this);

        fieldPane.add(hoursField);
        fieldPane.add(hoursLabel);
        fieldPane.add(timeList);
        fieldPane.add(minsLabel);
        fieldPane.add(prevButton);
        fieldPane.add(cntButton);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(fieldPane, BorderLayout.CENTER);
    }

    private static void createAndShowGUI() 
    {    
        JFrame frame = new JFrame("FormattedTextFieldDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DocumentFilterDemo());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {

        String time = "";
        if (e.getActionCommand().equalsIgnoreCase("cnt")) 
        {
            hours = Integer.parseInt(hoursField.getText());
            time = hours + " : " + ( (String) timeList.getSelectedItem());
            System.out.println(time);
        }
    }

    /*
     * This class will check for any invalid input and present 
     * a Dialog Message to user, for entering appropriate input.
     * you can let it make sound when user tries to enter the
     * invalid input. Do see the beep() part for that inside 
     * the class's body.
     */
    class DocumentInputFilter extends DocumentFilter
    {
        public void insertString(FilterBypass fb
                    , int offset, String text, AttributeSet as) throws BadLocationException
        {
            int len = text.length();
            if (len > 0)
            {
                /* Here you can place your other checks
                 * that you need to perform and do add
                 * the same checks for replace method
                 * as well.
                 */
                if (Character.isDigit(text.charAt(len - 1)))
                    super.insertString(fb, offset, text, as);
                else 
                {
                    JOptionPane.showMessageDialog(null, "Please Enter a valid Integer Value."
                                                            , "Invalid Input : ", JOptionPane.ERROR_MESSAGE);
                    Toolkit.getDefaultToolkit().beep();
                }   
            }                                               
        }

        public void replace(FilterBypass fb, int offset
                            , int length, String text, AttributeSet as) throws BadLocationException
        {
            int len = text.length();
            if (len > 0)
            {
                if (Character.isDigit(text.charAt(len - 1)))
                    super.replace(fb, offset, length, text, as);
                else 
                {
                    JOptionPane.showMessageDialog(null, "Please Enter a valid Integer Value."
                                                            , "Invalid Input : ", JOptionPane.ERROR_MESSAGE);
                    Toolkit.getDefaultToolkit().beep();
                }
            }                                               
        }
    }

} // end class