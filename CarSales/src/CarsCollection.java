// Copyright@20SW116

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class CarsCollection
{
	
	public static final int NO_ERROR = 0;
	
	public static final int CARS_MAXIMUM_REACHED = 1;
	
	public static final int MANUFACTURERS_MAXIMUM_REACHED = 2;

	private final int maxManufacturers = 20;
	private final int maxCars = 20;

	private Manufacturer[] manufacturer = new Manufacturer[0];

	public CarsCollection(){}

	public CarsCollection(Manufacturer man)
	{
		addManufacturer(man);
	}

	
	public int addCar(Car c)
	{
		Manufacturer man;
		String name = c.getManufacturer();
		int index = -1;
		int result = NO_ERROR;

		for (int i = 0; i < manufacturer.length; i++)
		{
			if (manufacturer[i].getManufacturerName().equalsIgnoreCase(name))
				index = i;
		}

		if (index == -1)
		{
			if (manufacturer.length < maxManufacturers)
			{
				man = new Manufacturer(name, c);
				addManufacturer(man);
			}
			else
				result = MANUFACTURERS_MAXIMUM_REACHED;
		}
		else
		{
			if (manufacturer[index].carCount() < maxCars)
				manufacturer[index].addCar(c);
			else
				result = CARS_MAXIMUM_REACHED;
		}

		return result;
	}

	
	private void addManufacturer(Manufacturer man)
	{
		manufacturer = resizeArray(manufacturer, 1);
		manufacturer[manufacturer.length - 1] = man;
	}

	
	public int carsCount()
	{
		int count = 0;

		for (int i = 0; i < manufacturer.length; i++)
			count += manufacturer[i].carCount();

		return count;
	}

	
	public int manufacturerCount()
	{
		return manufacturer.length;
	}

	
	public Car[] getAllCars()
	{
		Vector result = new Vector();
		Car[] car;
		for (int i = 0; i < manufacturer.length; i++)
		{
			car = manufacturer[i].getAllCars();
			for (int j = 0; j < car.length; j++)
			{
				result.addElement(car[j]);
			}
		}

		return CarSalesSystem.vectorToCar(result);
	}

	public Manufacturer[] getAllManufacturers()
	{
		return manufacturer;
	}

	
	public double getAverageAge()
	{
		Car[] car;
		double result = 0;
		int count = 0;

		for (int i = 0; i < manufacturer.length; i++)
		{
			car = manufacturer[i].getAllCars();
			for (int j = 0; j < car.length; j++)
			{
				result += car[j].getAge();
				count++;
			}
		}
		if (count == 0)
			return 0;
		else
			return (result / count);
	}

	public double getAverageDistance()
	{
		Car[] car;
		double result = 0;
		int count = 0;

		for (int i = 0; i < manufacturer.length; i++)
		{
			car = manufacturer[i].getAllCars();
			for (int j = 0; j < car.length; j++)
			{
				result += car[j].getKilometers();
				count++;
			}
		}
		if (count == 0)
			return 0;
		else
			return (result / count);
	}

	
	public double getAveragePrice()
	{
		Car[] car;
		double result = 0;
		int count = 0;

		for (int i = 0; i < manufacturer.length; i++)
		{
			car = manufacturer[i].getAllCars();
			for (int j = 0; j < car.length; j++)
			{
				result += car[j].getPrice();
				count++;
			}
		}
		if (count == 0)
			return 0;
		else
			return (result / count);
	}
	
	public void loadCars(String file) throws IOException, ClassNotFoundException
	{

		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(file));
		manufacturer = (Manufacturer[])inp.readObject();
		inp.close();
	}

	
	private Manufacturer[] resizeArray(Manufacturer[] inArray, int extendBy)
	{
		Manufacturer[] result = new Manufacturer[inArray.length + extendBy];

		for (int i = 0; i < inArray.length; i++)
		{
			result[i] = inArray[i];
		}

		return result;
	}

	
	public void saveCars(String file) throws IOException
	{
		int flag = 0;
		int items = manufacturer.length;
		Manufacturer temp;

		if (manufacturer.length > 0)
		{
			do
			{
				flag = 0;
				for (int i = 0; i < items; i++)
				{
					if (i + 1 < items)
					{
						if (manufacturer[i].getManufacturerName().compareTo(manufacturer[i + 1].getManufacturerName()) > 0)
						{
							temp = manufacturer[i];
							manufacturer[i] = manufacturer[i + 1];
							manufacturer[i + 1] = temp;
							flag++;
						}
					}
				}
			}
			while (flag > 0);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

			out.writeObject(manufacturer);
			out.close();
		}
	}

	
	public Car[] search(int minPrice, int maxPrice, double minDistance, double maxDistance)
	{
		Vector result = new Vector();
		int price;
		double distance;
		Car[] car;
		car = getAllCars();

		for (int i = 0; i < car.length; i++)
		{
			price = car[i].getPrice();
			distance = car[i].getKilometers();

			if (price >= minPrice && price <= maxPrice && distance >= minDistance && distance <= maxDistance)
 			{
					result.addElement(car[i]);
			}
		}

		return CarSalesSystem.vectorToCar(result);
	}

	
	public Car[] search(int minAge, int maxAge)
	{
		Car[] car;
		car = getAllCars();
		Vector result = new Vector();
		if (maxAge == -1)
		{
			for (int i = 0; i < car.length; i++)
			{
				if (car[i].getAge() >= minAge)
				{
					result.addElement(car[i]);
				}
			}
		}
		else
		{
			for (int i = 0; i < car.length; i++)
			{
				if (car[i].getAge() >= minAge && car[i].getAge() <= maxAge)
				{
					result.addElement(car[i]);
				}
			}
		}

		return CarSalesSystem.vectorToCar(result);
	}
}


//Copyright@20SW116