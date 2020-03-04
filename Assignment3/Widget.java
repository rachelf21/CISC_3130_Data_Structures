//Rachel Friedman | Data Structures 3130 | Assignment 3 | February 27, 2020

import java.text.NumberFormat;

public class Widget {
    public static int auto_id = 0;
    public int quantity;
    // public Widget next;
    public int widgetID;
    public double price;

    public Widget(int quantity, double price) {
        auto_id++;
        this.widgetID = auto_id;
        this.quantity = quantity;
        this.price = price;
    }

    public void display() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.println("Shipment " + widgetID + "| " + quantity + " widgets at " + nf.format(price) + " each");
    }

    public int getQuantity() {
        return quantity;
    }

    public void updateQuantity(int new_quantity) {
        this.quantity = new_quantity;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return "Shipment " + widgetID + " | Quantity " + quantity + " | Price " + nf.format(price);
    }
    // public static void main(String[] args) {
    // LinkList shipment = new LinkList();
    // shipment.receive_widgets(150,1);
    // // shipment.firstLink.display();
    // shipment.receive_widgets(130,2);
    // shipment.receive_widgets(75,4);
    // shipment.display_all_shipments();
    // shipment.sell_widgets(100);

    // }

}// Widget class