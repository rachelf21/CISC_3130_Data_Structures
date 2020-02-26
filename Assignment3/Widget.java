import java.text.NumberFormat;
public class Widget {
    public int quantity;
    public Widget next;
    public static int auto_id=0;
    public int widget_id;
    public double price;

    public Widget(int quantity, double price) {
        auto_id++;
        this.widget_id = auto_id;
        this.quantity = quantity;
        this.price = price;
    }

    public void display() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.println("Shipment " + widget_id +" Received " + quantity + " widgets at " + nf.format(price) + " per widget.");
    }

    public int get_quantity(){
        return quantity;
    }

    public void update_quantity(int new_quantity){
        this.quantity = new_quantity;
    }

    public double get_price(){
        return price;
    }
    public static void main(String[] args) {
        LinkList shipment = new LinkList();
        shipment.receive_widgets(150,1);
       // shipment.firstLink.display();
        shipment.receive_widgets(130,2);
        shipment.receive_widgets(75,4);
        shipment.display_all_shipments();
        shipment.sell_widgets(100);

    }

}//Widget class