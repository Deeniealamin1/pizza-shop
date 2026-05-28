package com.pluralsight.models;

public class SignaturePizza extends Pizza
{
    public SignaturePizza(String name, String size, String crustType)
    {
        super(name, size, crustType);
    }

    public static SignaturePizza margherita()
    {
        SignaturePizza pizza = new SignaturePizza("Signature Margherita", "12", "regular");
        pizza.addCheese("Mozzarella");
        pizza.addRegularTopping("Tomatoes");
        pizza.addRegularTopping("Basil");
        pizza.setSpecialOption("toasted");
        return pizza;
    }

    public static SignaturePizza veggie()
    {
        SignaturePizza pizza = new SignaturePizza("Signature Veggie", "8", "regular");
        pizza.addRegularTopping("Bell Peppers");
        pizza.addRegularTopping("Spinach");
        pizza.addRegularTopping("Olives");
        pizza.addCheese("Mozzarella");
        return pizza;
    }
}