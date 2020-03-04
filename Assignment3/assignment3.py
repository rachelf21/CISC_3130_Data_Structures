# -*- coding: utf-8 -*-
"""
Created on Fri Feb 28 09:12:43 2020

@author: Rachel
"""

import pandas as pd

class Node:
    def __init__(self,node_id, qty,price):
        self.node_id = node_id 
        self.qty=int(qty)
        self.price=price
        self.next = None
        self.prev = None
    def __str__(self):
        return str(self.node_id) + "| " + str(self.qty) + " | $" + "{:.2f}".format(self.price)
        
        
class LinkedList:
    def __init__(self):
        self.head=None
    def printList(self):
        temp=self.head
        print("{0:>2}{1:>9}{2:>12}".format("\nID","Qty","Price"))
        print("-"*23)
        while(temp):
            line ="{0:>2}| {1:>7}{2:>6}{3:>6.2f}".format(temp.node_id, temp.qty, "$", temp.price)
            print(line)
            print("-"*23)
            temp = temp.next
            
def sell_widget(llist, widget, amt):
    original_amount = amt
    customer_price=0
    qty=0
    markup=.3
    cost = 0
    total = 0
    
    while(widget):
        print("next widget in list" , widget.node_id)
        if(widget.qty>=amt):
            print("you asked for ", str(amt), "we have ", str(widget.qty))
            widget.qty = widget.qty-amt
            amt = amt - widget.qty
            #retreive previous widget and assign next value of previous widget to next value, if widget.qty=0
            print("remaining in this widget", widget.qty)
            if(widget.qty<=0):
                if widget.prev==None:
                    llist.head=widget.next
                    if(widget.next!=None):
                        widget.next.prev=None
                else:
                    widget.prev.next = widget.next
                    widget.next.prev = widget.prev
                    widget=widget.next
                widget=widget.next
                break
        else:
            print("you are getting a partial amount of ", str(widget.qty), "from this widget ", widget.node_id)
            amt = amt - widget.qty
            print("amt is now", amt)
        
            if(amt > 0 and widget.next == None):
                print("Sorry ", amt , "widgets not available at this time.")
            else:
                if widget.prev==None:
                    print("widget.prev= None")
                    llist.head = widget.next
                    #widget.next.prev=None
                    print("Head of list is now", widget.next)
                    widget.next.prev = None
                else:
                    print("Prevous widget", widget.prev)
                    widget.prev.next= widget.next
                    widget.next.prev = widget.prev

        widget = widget.next
            
                
def process_data(llist, data):
    sales_order_number=0    
    widget = None
    llist.head=widget
    count=0
    for index, row in data.iterrows():
        if(data[0][index])=="R":
            count+=1
            qty = data[1][index]
            price = data[2][index]
            new_widget=Node(count,qty,price)
            if(widget==None):
                new_widget.prev=None
                llist.head=new_widget
            else:
                new_widget.prev = widget
                widget.next = new_widget
            widget=new_widget
            print(widget)
        elif(data[0][index]=="S"):
            sales_order_number+=1
            amount = int(data[1][index])
            print("\nSales Order ", sales_order_number, "for amount" , str(amount))
            sell_widget(llist, llist.head,amount)
    return llist
     
data = pd.read_csv("data.csv",header=None) 
widget_list=LinkedList() 
records=process_data(widget_list, data)
widget_list.printList()
#widget=widget_list.head


    
    
#def main():
#if __name__ == "__main__":
#    main()
       