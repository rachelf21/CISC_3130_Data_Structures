# Data Structures 3130 | Assignment 1 | Feb 2, 2020

from beautifultable import BeautifulTable
import pandas as pd

def dollar(x):
    #or, can use lambda by appending this: .apply(lambda x: "${:.2f}".format((x)))
    return "${:.2f}".format(x)
  
master = pd.read_excel("assignment1.xlsx","master")
trans = pd.read_excel("assignment1.xlsx","transactions")
trans["Total"] = trans["Price"]*trans["Quantity"]
trans.fillna(0, inplace=True) #replace missing values


for i in range(len(trans)):
    print(trans.loc[i]["Cust_Number"])