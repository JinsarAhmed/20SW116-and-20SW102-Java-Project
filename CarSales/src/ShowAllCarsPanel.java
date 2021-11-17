// Copyright@20SW116

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ShowAllCarsPanel extends JPanel implements ActionListener, ChangeListener
{
	private CarSalesSystem carSystem;
	private Car[] carList;
	private int currentIndex = 0;
	private JLabel headingLabel = new JLabel("Availaible Vehicles");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JPanel buttonPanel = new JPanel();
	private CarDetailsComponents carComponents = new CarDetailsComponents();
	private boolean carsUpdated = false;

	public ShowAllCarsPanel(CarSalesSystem carSys)
	{
		carSystem = carSys;
		carList = carSystem.getAllCars();

		if (carList.length > 0)
			carComponents.displayDetails(carList[0]);

		carSys.addCarUpdateListener(this);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		headingLabel.setAlignmentX(0.5f);

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);

		add(Box.createVerticalStrut(10));
		add(headingLabel);
		add(Box.createVerticalStrut(15));
		carComponents.add(buttonPanel, "Center");
		add(carComponents);

		carList = carSystem.getAllCars();
	}

	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == previousButton)
			previousButtonClicked();
		else if (ev.getSource() == nextButton)
			nextButtonClicked();
	}

	
	public void carsUpdated(CarUpdateEvent ev)
	{
		if (ev.getSource() == carSystem)
		{
			
			carsUpdated = true;
		}
	}

	
	public void stateChanged(ChangeEvent ev)
	{
		
		if (ev.getSource() instanceof JTabbedPane)
		{
			JTabbedPane tab = (JTabbedPane)ev.getSource();
			
			if (tab.getSelectedIndex() == 2)
			{
				
				if (carsUpdated)
				{
					carList = carSystem.getAllCars();
					if (!(carList == null))
						carComponents.displayDetails(carList[currentIndex]);
					
					carsUpdated = false;
				}
			}
		}
	}

	
	private void nextButtonClicked()
	{
		if (currentIndex < carList.length - 1)
		{
			currentIndex++;
			carComponents.displayDetails(carList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(carSystem, "You can't navigate any further", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	
	private void previousButtonClicked()
	{
		if (currentIndex > 0)
		{
			currentIndex--;
			carComponents.displayDetails(carList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(carSystem, "You can't navigate any further", "Alert", JOptionPane.ERROR_MESSAGE);
	}
}


//Copyright@20SW116