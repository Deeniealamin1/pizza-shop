package com.yearup.dealership;

public class Side extends MenuItem
{
    public Side(String name)
    {
        super(name);
    }

    @Override
    public double calculatePrice()
    {
        return 1.50;
    }

    @Override
    public String toString()
    {
        return getName() + " - $" + String.format("%.2f", calculatePrice());
    }
}