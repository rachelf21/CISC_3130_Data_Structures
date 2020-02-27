import java.text.NumberFormat;
import java.util.*;

public class Main {
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    int qty = 0;
    double profit = 0;
    double cost = 0;
    double discount = .25;
    static boolean promotion = false;
    static int count = 0;
    LinkedList<Widget> ll = new LinkedList<Widget>();

    public static void main(String[] args) {
        Main widgets = new Main();
        widgets.ll.add(new Widget(10, 1));
        widgets.ll.add(new Widget(5, 2));
        widgets.ll.add(new Widget(10, 4));
        System.out.println("Size now: " + widgets.ll.size());
        // set_discount();
        widgets.sell_widgets(38, promotion);
        System.out.println("NEW ORDER");
        widgets.sell_widgets(10, promotion);
        System.out.println("new size of list is " + widgets.ll.size());

    }

    public static void set_discount() {
        promotion = true;
        count = 0;
    }

    public void sell_widgets(int amount, boolean promotion) {
        int originalAmount = amount;
        Iterator<Widget> itr = ll.listIterator();
        while (itr.hasNext()) {
            Widget w = itr.next();
            qty = w.get_quantity();
            if (qty >= amount) {
                System.out.println(
                        "\nYou asked for " + amount + ". We currently have enough of this in stock. We have " + qty);
                w.update_quantity(qty - amount);
                cost = cost + w.get_price() * amount;
                System.out.println(nf.format(cost));
                System.out.println("Taken from this widget" + w);
                amount = amount - qty;
                break;
            } else {
                System.out.println("\nYou asked for " + amount
                        + ". We currently do NOT have enough of this in stock. We have " + qty);

                amount = amount - qty;

                System.out.println(
                        "But we will give you " + qty + " and get the remaining " + amount + " from the next widget.");
                cost = cost + w.get_price() * qty;
                System.out.println(nf.format(cost));
                System.out.println("Taken from this widget:" + w);
                itr.remove();

            }

        }
        profit = .3 * cost;
        cost = profit + cost;
        // TODO: Cost should not print if order was not filled.
        // only print for that amount.
        if (amount == originalAmount) {
            System.out.println("None of your order was completed. So sorry :( ");
        }

        else {
            System.out.println("final cost, including markup is: " + nf.format(cost));
            if (promotion && count < 2) {
                System.out.println("Promotional discount of 25% has been applied.");
                count++;
                cost = cost - (discount * cost);
            }

            if (amount > 0)

            {
                System.out.println("Sorry, we were short of " + amount + " widgets");
            }
        }
    }
}