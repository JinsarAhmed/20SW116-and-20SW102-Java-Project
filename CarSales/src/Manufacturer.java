// Copyright@20SW116

import java.util.*;

public class Manufacturer implements java.io.Serializable
{
	private String manufacturer;		
	private Car[] car = new Car[0];		
	
	public Manufacturer(String nam, Car c)
	{
		manufacturer = nam.toUpperCase();
		addCar(c);
	}

	
	public void addCar(Car c)
	{
		car = resizeArray(car, 1);
		car[car.length - 1] = c;
	}

	
	public int carCount()
	{
		return car.length;
	}

	
	public Car[] getAllCars()
	{
		return car;
	}



	public String getManufacturerName()
	{
		return manufacturer;
	}

	
	private Car[] resizeArray(Car[] c, int extendBy)
	{
		Car[] result = new Car[c.length + extendBy];

		for (int i = 0; i < c.length; i++)
		{
			result[i] = c[i];
		}

		return result;
	}

	
	public void setManufacturersName(String nam)
	{
		manufacturer = nam.toUpperCase();
	}
}


//Copyright@20SW116