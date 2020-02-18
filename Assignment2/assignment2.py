import pandas as pd

price = [2,7,8.5]
cost = [0,0,0]
fulfilled = True

data = pd.read_csv("asst2_data.csv", header=0)
warehouses = {"New York": [0,0,0],"Miami": [0,0,0],"Los Angeles":[0,0,0],"Houston":[0,0,0],"Chicago":[0,0,0]}
 
for i in range (0, len(data)):
    city = data["City"][i]
    #############PROCESS SHIPMENT
    if(data["Type"][i]=="s"):
        warehouses[city][0]+=data.iloc[i,2]
        warehouses[city][1]+=data.iloc[i,3]
        warehouses[city][2]+=data.iloc[i,4]
        print("\n" , i+1 ,"| SHIPMENT ENTRY:", data.iloc[i,1], data.iloc[i,2], data.iloc[i,3], data.iloc[i,4])
        print("UPDATED:", city, warehouses[city])
    
    #############PROCESS ORDER   
    if(data["Type"][i]=="o"): 
        total=0
        print("\n" , i+1 ,"| ORDER ENTRY:", data.iloc[i,1], data.iloc[i,2], data.iloc[i,3], data.iloc[i,4])
        for j in range(0,3): #iterate through each item on this row
            surcharge = 0
            max_amt = warehouses[city][j] #need at least this amount
            items_short = warehouses[city][j]-data.iloc[i,j+2]  

            if items_short>=0:
                warehouses[city][j]-=data.iloc[i,j+2]  #if have enough, simply deduct that amount
            else: #if don't have enough
                for key in warehouses: #iterate through dict warehouse and search that item
                    amount = warehouses[key][j] # amount found in other warehouse
                    max_amt = max(amount,max_amt) #store largest value
                    if(max_amt == warehouses[key][j]):
                        pos = key               
                if(max_amt >= abs(items_short)): #if can find enough
                    warehouses[pos][j]-=abs(items_short) # remove from second warehouse
                    #print("remaining: " , warehouses[pos][j], "items in", pos)
                    warehouses[city][j]=0 #remove from requesting warehouse
                    surcharge = .1*abs(items_short)*price[j]
                    print("* Shipped ", abs(items_short), " of Item",(j+1)," from ", pos, "to", city)
                    print("  An additonal ten percent has been charged for this item.")
                    fulfilled = True
                else: #if none of the other warehouses have enough
                    print("  Insufficient Items. Order not fulfilled.")
                    fulfilled = False
                    item_num = j  #keep track of which item to set cost to 0
                    
            ##### CALCULATE COST
            cost[j] = data.iloc[i,j+2] * price[j] + surcharge #calculates cost per item, qty*price
            if not fulfilled:
                cost[item_num]=0  #set unfulfilled item price to 0  
            total += cost[j] #calculates total
            
        print("UPDATED:", city, warehouses[city])
        print("\n*** CUSTOMER INVOICE ****")
        print("{:12} {:10} {:10} {:10}".format("City", "Amount1", "Amount2", "Amount3"))
        print("{:12}".format(city), "{:7.2f}".format(cost[0]),"{: 10,.2f}".format(cost[1]),"{: 10,.2f}".format(cost[2]))             
        print("PRICE OF ORDER: ${:,.2f}".format(total))    
