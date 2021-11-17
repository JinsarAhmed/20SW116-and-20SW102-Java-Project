// Copyright@20SW116

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class AboutDialog extends JDialog implements ActionListener
{
	private JButton okButton = new JButton("OK");
	private JPanel buttonPanel = new JPanel();
	private WindowCloser closer = new WindowCloser();

	public AboutDialog(JFrame parent, String title, boolean modal)
	{
		super(parent, title, modal);
		Container c = getContentPane();

		setSize(480, 130);
		setLocationRelativeTo(parent);
		addWindowListener(closer);
		c.setLayout(new GridLayout(3, 1));
		setTitle(title);
		buttonPanel.add(okButton);
		c.add(new JLabel("Sheikh Motors- This Project is Developed by (20SW116) and (20SW102)", JLabel.CENTER));
		//c.add(new JLabel("Roll No: 20SW116 and 20SW102", JLabel.CENTER));
		c.add(new JLabel("Submitted To Mam Mariam and Sir Sajjad", JLabel.CENTER));
		c.add(buttonPanel);
		okButton.addActionListener(this);
	}


	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == okButton)
			closing();
	}

	public void closing()
	{
		setVisible(false);
	}

	public void showAbout()
	{
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	class WindowCloser extends WindowAdapter
	{
		public void windowClosing(WindowEvent ev)
		{
			closing();
		}
	}
}

//Copyright@20SW116