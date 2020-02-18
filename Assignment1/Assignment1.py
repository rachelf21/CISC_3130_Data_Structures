# Data Structures 3130 | Assignment 1 | Feb 2, 2020

from beautifultable import BeautifulTable
import pandas as pd

def dollar(x):
    #or, can use lambda by appending this: .apply(lambda x: "${:.2f}".format((x)))
    return "${:.2f}".format(x)

def single(x):
    #or, can use lambda by appending this: .apply(lambda x: "${:.2f}".format((x)))
    return "{:.0f}".format(x)  

master = pd.read_excel("assignment1.xlsx","master")
trans = pd.read_excel("assignment1.xlsx","transactions")
trans["Total"] = trans["Price"]*trans["Quantity"]
trans.fillna(0, inplace=True) #replace missing values
total_order_amount = pd.DataFrame(trans[trans['Type']=='O'].groupby(["Cust_Number"])["Total"].sum()) #creates table of total order amt per customer
total_payment_amount = pd.DataFrame(trans[trans['Type']=='P'].groupby("Cust_Number")["Payment"].sum()) #creates table of total payment amt per customer

#balances = total_order_amount.join(total_payment_amount)
#balances.reset_index(inplace=True)
#balances.rename(columns={"Price":"Total Order Amt"},inplace=True)
#balances.insert(1,"Prev Balance", master["Balance"])
#balances["Balance"]=master["Balance"]+balances["Total Order Amt"] - balances["Payment"]

for i in range(len(master['Cust_Number'])):  #cycles through list of customers
    x = master['Cust_Number'].iloc[i]  #sets current customer number
    cust_name = master[master['Cust_Number']== x]['Name'].tolist()[0]
    cust_trans = trans[(trans['Cust_Number']== x)][["Trans_Number","Item","Price","Quantity"]] #generates all transactions for current customer
    cust_trans['Price'] = cust_trans['Price'].apply(dollar) #format as currency
    cust_trans['Quantity'] = cust_trans['Quantity'].apply(single) #format with no decimals
    prev_bal =  master[master['Cust_Number']==x]['Balance'].tolist()[0]
    total_order = total_order_amount.loc[x].tolist()[0]
    total_payment = total_payment_amount.loc[x].tolist()[0]
    balance = prev_bal + total_order - total_payment

### PRINT INVOICE
    print("\n\n--------------------------------------------------------")
    print("  ***** CUSTOMER INVOICE ***** CUSTOMER INVOICE ***** " )
    print("--------------------------------------------------------")
    print("\nCUSTOMER NAME:", cust_name, "\t CUSTOMER NUMBER:", x)
    print("PREVIOUS BALANCE:", "${:,.2f}".format(prev_bal),"\n")
    table=BeautifulTable()
    table.column_headers=["Transaction#","Item Ordered/\nPayment","Order Amount/\nPayment Amount","Quantity"]
    for i in range(len(cust_trans)):
        table.append_row(cust_trans.iloc[i])
    table.set_style(BeautifulTable.STYLE_BOX_ROUNDED)#  STYLE_BOX_ROUNDED  STYLE_SEPARATED
    print(table)    
    print("\n\t\t\t\tBALANCE DUE: ${:,.2f}".format(balance))
    print("\nPlease remit payment at your earliest convenience. \nThank you.")
                    
