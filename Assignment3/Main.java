import java.text.NumberFormat;
import java.util.*;
import java.io.*;


public class Main {
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    int qty = 0;
    double profit = 0;
    double markup = .3;
    double cost = 0;
    double discount = .25;
    static boolean promotion = false;
    static int count = 0;
    LinkedList<Widget> ll = new LinkedList<Widget>();

    public static void main(String[] args) {
        Main widgets = new Main();
        widgets.ll.add(new Widget(10, 1));
        widgets.ll.add(new Widget(15, 2));
        widgets.ll.add(new Widget(40, 1));
        System.out.println("Size now: " + widgets.ll.size());
        set_discount();
        widgets.sell_widgets(5, promotion);
        System.out.println("\nNEW ORDER");
        widgets.sell_widgets(10, promotion);
        widgets.sell_widgets(5, promotion);
        set_discount();
        widgets.sell_widgets(2, promotion);
        widgets.sell_widgets(5, promotion);
        widgets.sell_widgets(1, promotion);

        System.out.println("new size of list is " + widgets.ll.size());

    }

    public static void set_discount() {
        promotion = true;
        count = 0;
        System.out.println("\n**Promotion has been activated for next two orders.**\n");
    }

    public void sell_widgets(int amount, boolean promotion) {
        int originalAmount = amount;
        double widget_markup = 0;
        double total = 0;
        Iterator<Widget> itr = ll.listIterator();
        while (itr.hasNext()) {
            Widget w = itr.next();
            qty = w.get_quantity();
            if (qty >= amount) {
                System.out.println(
                        "\nYou asked for " + amount + ". We currently have enough of this in stock. We have " + qty);
                w.update_quantity(qty - amount);
                widget_markup = markup * (w.get_price() * amount);
                cost = w.get_price() * amount + widget_markup;
                total = total + cost;
                System.out.println("Cost for item: " + nf.format(cost));
                System.out.println("Taken from this widget: " + w);
                amount = amount - qty;
                if(amount==0){
                    itr.remove();
                }
                break;
            } else {
                System.out.println("\nYou asked for " + amount
                        + ". We currently do NOT have enough of this in stock. We have " + qty);

                amount = amount - qty; //amount = 15-10 = 5

                System.out.println(
                        "But we will give you " + qty + " and get the remaining " + amount + " from the next widget.");
                widget_markup = markup * (w.get_price() * qty);
                cost = w.get_price() * qty + widget_markup;
                total = total + cost;
                System.out.println("Final cost for item: " + nf.format(cost));
                System.out.println("Taken from this widget: " + w);
                itr.remove();

            }

        }
        // TODO: Cost should not print if order was not filled.
        // only print for that amount.
        if (amount == originalAmount) {
            System.out.println("None of your order was completed. So sorry :( ");
        }

        else {
            System.out.println("Final cost for item: " + nf.format(cost));
            if (promotion && count < 2) {
                System.out.println("Promotional discount of 25% has been applied.");
                count++;
                total = total - (discount * total);
            }

            if (amount > 0)

            {
                System.out.println("Sorry, we were short of " + amount + " widgets");
            }
        }
        System.out.println("TOTAL BILL: " + nf.format(total));
    }
}