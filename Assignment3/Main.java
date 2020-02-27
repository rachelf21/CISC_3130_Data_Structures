import java.text.NumberFormat;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    int qty = 0;
    double markup = .3;
    double cost = 0;
    int sell=0;
    double discount = .25;
    static boolean promotion = false;
    static int count = 0;
    LinkedList<Widget> ll = new LinkedList<Widget>();

    public static void main(String[] args) {
        Main widgets = new Main();
        widgets.readFile("data.csv");
        // widgets.ll.add(new Widget(10, 1));
        // widgets.ll.add(new Widget(15, 2));
        // widgets.ll.add(new Widget(40, 1));
        // System.out.println("Size now: " + widgets.ll.size());
        // set_discount();
        // widgets.sell_widgets(5, promotion);
        // System.out.println("\nNEW ORDER");
        // widgets.sell_widgets(10, promotion);
        // widgets.sell_widgets(5, promotion);
        // set_discount();
        // widgets.sell_widgets(2, promotion);
        // widgets.sell_widgets(5, promotion);
        // widgets.sell_widgets(1, promotion);

        System.out.println("new size of list is " + widgets.ll.size());     
    }

///**** READFILE *///////////////////////////////////////////////////////////
    public void readFile(String filename){
        try{
            Scanner data = new Scanner(new File(filename));
            while(data.hasNext()){
                String[] line = data.nextLine().split(",");
                if (line[0].equals("S")){
                    Widget w = new Widget(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                    ll.add(w);
                    System.out.println();
                    w.display();
                }
                else if (line[0].equals("R")){
                    sell++;
                    System.out.println("\nSales No: " + sell);
                    sell_widgets(ll,Integer.parseInt(line[1]),promotion);
                }
                else if(line[0].equals("D")){
                    set_discount();
                }
                else
                    System.out.println("Improper card number");      
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Missing file " + e.getMessage());
        }
    }
 ///**** SET DISCOUNT *///////////////////////////////////////////////////////////
    public static void set_discount() {
        promotion = true;
        count = 0;
        System.out.println("\n**Promotion has been activated for next two orders.**\n");
    }
 ///****SELL WIDGETS *///////////////////////////////////////////////////////////
    public void sell_widgets(LinkedList<Widget> ll, int amount, boolean promotion) {
        int originalAmount = amount;
        double widget_markup = 0;
        double total = 0;
        
        Iterator<Widget> itr = ll.listIterator();
        while (itr.hasNext()) {
            Widget w = itr.next();
            qty = w.get_quantity();
            if (qty >= amount) {
                w.update_quantity(qty - amount);
                widget_markup = markup * (w.get_price() * amount);
                cost = w.get_price() * amount + widget_markup;
                total = total + cost;
                System.out.println(amount +" items for total of: " + nf.format(cost));
                amount = amount - qty;
                if(amount==0){
                    itr.remove();
                }
                break;
            } else { // if there isn't enought, remove whatever we have'
                amount = amount - qty; //amount = 15-10 = 5
                widget_markup = markup * (w.get_price() * qty);
                cost = w.get_price() * qty + widget_markup;
                total = total + cost;
                System.out.println(amount +" itemss for total of: " + nf.format(cost));
                itr.remove();
            }
        }
        if (amount == originalAmount) {
            System.out.println("None of your order was completed. So sorry :( ");
        }
        else {
            if (promotion && count < 2) {
                System.out.println("Promotional discount of 25% has been applied.");
                count++;
                total = total - (discount * total);
            }
            if (amount > 0){
                System.out.println("Sorry, we were short of " + amount + " widgets");
            }
        }
        System.out.println("TOTAL BILL: " + nf.format(total));
    }
}//Main