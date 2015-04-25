import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Atm extends JFrame
{
	private JPanel displayPanel, pinpadPanel; //DISPLAYS A PASSWORD FIELD, DISPLAYS THE PINPAD NUMBERS
	private JButton [] numberButtons = new JButton[10]; //FILLS PAD WITH 1-9
	private JButton enterButton, clearButton,zeroButton; //ADDS ENTER, CLEAR, 0 BUTTON
	private JPasswordField passwordField;	//THE PASSWORDFIELD
	private String input=""; //STRING FOR PASSWORD
	private int check =0; //COUNTER FOR ATTEMPTS
	
	public Atm()
	{
		passwordField = new JPasswordField(); //PASSWORD FIELD
		passwordField.setFont(new Font("MS San Serif",Font.BOLD,17)); //CHANGE FONT
		
		displayPanel = new JPanel(); //DISPLAY PANEL
		displayPanel.setBackground(Color.CYAN);//SET COLOR OF BACKGROUND
		displayPanel.setLayout(new BorderLayout());	//SIMPLE LAYOUT
		displayPanel.add(passwordField,BorderLayout.NORTH); //DISPLAY PANEL IS GIVEN A PASSWORD FIELD
		
		pinpadPanel = new JPanel(); //PINPAD PANEL
		pinpadPanel.setBackground(Color.GRAY); //SET COLOR
		pinpadPanel.setLayout(new GridLayout(4,3)); //GIVEN 12 SECTIONS		
		
		for(int i=1;i<=9;i++) //CREATES 1-9 BUTTONS
		{
			numberButtons[i] = new JButton(String.valueOf(i)); //MAKES BUTTONS 1-9
			numberButtons[i].addActionListener(new ActionListener() //ADDS ACTION
			{public void actionPerformed(ActionEvent e)	//ACTION PUT IN HERE
				{	input+=e.getActionCommand(); //THIS MAKES STRING ("") GET A NEW STRING WHICH IS THE INPUT OF THE BUTTON
					passwordField.setText(input); //PUTS YOUR INPUT IN THE PASSWORDFIELD
					System.out.println(input); //TRACING
				}});
			pinpadPanel.add(numberButtons[i]); //ADDS THE BUTTONS TO PINPAD PANEL
		}
		
		clearButton = new JButton("c"); //MAKES C BUTTON
		clearButton.addActionListener(new ClearListener()); //ADDS ACTION CALLED CLEARLISTENER, NOW YOU MAKE THE METHOD
		pinpadPanel.add(clearButton); //ADDS THE CLEAR BUTTON
		
		zeroButton = new JButton("0"); //MAKES 0 BUTTON BECAUSE 0 IS PLACED AFTER CLEAR BUTTON AND BETWEEN ENTER
		zeroButton.addActionListener(new ActionListener()//ADD ACTIONLISTENER
		{public void actionPerformed(ActionEvent e)	//PUT ACTION HERE
			{input+=e.getActionCommand(); //THIS MAKES STRING ("") GET A NEW STRING WHICH IS THE INPUT OF THE BUTTON
				passwordField.setText(input); //PUTS YOUR INPUT IN THE PASSWORDFIELD
				System.out.println(input); //TRACING
			}});
		pinpadPanel.add(zeroButton); //ADDS THE 0 BUTTON
		
		enterButton = new JButton("\u21b5"); //MAKES A ENTER BUTTON WITH ENTER SYMBOL
		enterButton.addActionListener(new EnterListener()); //NEW ACTION WHICH IS ENTER ACTION
		pinpadPanel.add(enterButton); //ADDS THE ENTER BUTTON
		
		
		
		
		displayPanel.add(pinpadPanel,BorderLayout.CENTER); //ADDS PINPAD IN CENTER OF DISPLAY WITH PASSWORD NORTH
		
		add(displayPanel); //ADDS THE DISPLAY TO THE FRAME
		setVisible(true); //SET VISIBILITY
		pack(); //WRAPS AROUND PIXEL PERFECT
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //CLOSE ON EXIT
		setLocationRelativeTo(null); //CENTER
	}	
	
	public static void main(String[] args)
	{	JOptionPane.showMessageDialog(null, "Please enter your 4-Digit PIN.");
		Atm atmTest = new Atm();//STARTS THE FRAME
	}	
	class ClearListener implements ActionListener //ACTIONLISTENER CLEAR
	{
		public void actionPerformed(ActionEvent e)	//PUT CLEAR ACTION IN HERE
		{
			passwordField.setText(""); //SETS PASSWORDFIELD AS ""
			input="";	//SETS INPUT AS ""
		}
	}
	class EnterListener implements ActionListener //ACTIONLISTENER ENTER
	{
		public void actionPerformed(ActionEvent e)	//PUT ENTER ACTION IN HERE
		{
			if (Authentication.authentificationPin(input)) //CHECKS AUTHENTICATION OF PASSWORD IN ANOTHER CLASS
			{
				JOptionPane.showMessageDialog(null, "Welcome!"); //IF CORRECT
				check=0;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Failed Authentication."); //IF WRONG
				check++;
				JOptionPane.showMessageDialog(null, 4-check+" attempts left.");//SHOW HOW MANY ATTEMPTS YOU HAVE UNTIL PROGRAM EXIT
				if(check==4) //IF TO CHECK HOW MANY ATTEMPTS
				{
					JOptionPane.showMessageDialog(null, "Contact an administrator to reset your PIN.");
					System.exit(0);//PROGRAM EXIT
				}
			}
		}
	}
}