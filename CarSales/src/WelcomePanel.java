// Copyright@20SW116

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class WelcomePanel extends JPanel implements ChangeListener
{
	private CarSalesSystem carSystem;
	private JLabel headingLabel = new JLabel("Welcome to the Car Sales System", JLabel.CENTER);
	private JLabel carsLabel = new JLabel();
	private JLabel manufacturersLabel = new JLabel();
	private JLabel avgPriceLabel = new JLabel();
	private JLabel avgKmLabel = new JLabel();
	private JLabel avgAgeLabel = new JLabel();
	private JLabel versionLabel = new JLabel();
	private JLabel dataSizeLabel = new JLabel();
	private JPanel statsPanel = new JPanel();
	private JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
	private boolean carsUpdated = false;
	private String file;

	public WelcomePanel(CarSalesSystem carSys, String data)
	{
		carSystem = carSys;
		file = data;
		setLayout(new BorderLayout(0, 10));
		carSys.addCarUpdateListener(this);

		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		centerPanel.add(statsPanel);
		headingLabel.setBorder(new EmptyBorder(new Insets(10, 0, 0, 0)));

		updateStats();

		statsPanel.add(carsLabel);
		statsPanel.add(manufacturersLabel);
		statsPanel.add(avgPriceLabel);
		statsPanel.add(avgKmLabel);
		statsPanel.add(avgAgeLabel);
		statsPanel.add(Box.createVerticalStrut(20));
		statsPanel.add(dataSizeLabel);

		add(headingLabel, "North");
		add(centerPanel, "Center");
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
			
			if (tab.getSelectedIndex() == 0)
			{
				
				if (carsUpdated)
				{
					updateStats();
					carsUpdated = false;
				}
			}
		}
	}

	
	private void updateStats()
	{
		
		int cars = (int)carSystem.getStatistics(CarSalesSystem.CARS_COUNT);
		int manufacturers = (int)carSystem.getStatistics(CarSalesSystem.MANUFACTURERS_COUNT);
		double avgPrice = Math.floor(carSystem.getStatistics(CarSalesSystem.AVERAGE_PRICE) * 10 + 0.5) / 10;
		double avgKm = Math.floor(carSystem.getStatistics(CarSalesSystem.AVERAGE_DISTANCE) * 10 + 0.5) / 10;
		double avgAge = Math.floor(carSystem.getStatistics(CarSalesSystem.AVERAGE_AGE) * 10 + 0.5) / 10;
		java.io.File f = new java.io.File(file);
		long size = f.length(); 

		carsLabel.setText("Total number of Cars: " + String.valueOf(cars));
		manufacturersLabel.setText("Total Number of Manufacturers: " + String.valueOf(manufacturers));
		avgPriceLabel.setText("Average Car Price: " + String.valueOf(avgPrice));
		avgKmLabel.setText("Average Car kilometers: " + String.valueOf(avgKm));
		avgAgeLabel.setText("Average Car Age: " + String.valueOf(avgAge));
		dataSizeLabel.setText("Total Size of Data file: " + size + " Bytes");
	}
}

//Copyright@20SW116