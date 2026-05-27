package com.yearup.dealership;

public class Drink extends MenuItem
{
    private static final String SMALL  = "small";
    private static final String MEDIUM = "medium";
    private static final String LARGE  = "large";

    private String size;

    public Drink(String name, String size)
    {
        super(name);
        // Normalize and default to "large" only for recognized sizes
        String normalized = (size != null) ? size.trim().toLowerCase() : "";
        if (normalized.equals(SMALL) || normalized.equals(MEDIUM) || normalized.equals(LARGE))
        {
            this.size = normalized;
        }
        else
        {
            this.size = LARGE; // safe default; caller already validated
        }
    }

    @Override
    public double calculatePrice()
    {
        switch (size)
        {
            case SMALL:  return 2.00;
            case MEDIUM: return 2.50;
            default:     return 3.00; // large
        }
    }

    @Override
    public String toString()
    {
        return size + " " + getName() + " - $" + String.format("%.2f", calculatePrice());
    }
}