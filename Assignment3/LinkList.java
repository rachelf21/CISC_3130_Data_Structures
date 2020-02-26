import java.text.NumberFormat;

public class LinkList {

    public Widget firstLink;

    public LinkList() {
        firstLink = null;
    }

    public boolean isEmpty() {
        return firstLink == null;
    }

    public void receive_widgets(int quantity, double price) {
        Widget widget = new Widget(quantity, price);
        widget.next = firstLink;
        firstLink = widget;
    }

    public void display_all_shipments() {
        Widget temp = firstLink;
        while (firstLink != null) {
            firstLink.display();
            firstLink = firstLink.next;
        }
        firstLink = temp;
    }

    public void sell_widgets(int quantity) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        double cost = 0;
        Widget temp = firstLink;
        int amt = 0;
        while (firstLink != null) {
            amt = firstLink.get_quantity();
            temp = firstLink;
            firstLink = firstLink.next;
        }
        if (amt >= quantity) {
            System.out.println("updated quantity");
            temp.update_quantity(amt - quantity);
            cost = .3 * quantity + quantity;
            System.out.println("Your price: " + nf.format(cost));
            temp.display();
        }
        else {

        }
    }

}//LinkList class