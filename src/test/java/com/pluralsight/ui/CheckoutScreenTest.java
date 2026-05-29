package com.pluralsight.ui;

import com.pluralsight.models.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CheckoutScreenTest
{
    @Test
    void testStartCheckoutWithEmptyOrder()
    {
        CheckoutScreen screen = new CheckoutScreen();
        Order order = new Order();

        boolean result = screen.startCheckout(order);

        assertFalse(result);
    }

    @Test
    void testCheckoutScreenInitialization()
    {
        CheckoutScreen screen = new CheckoutScreen();
        assertNotNull(screen);
    }
}