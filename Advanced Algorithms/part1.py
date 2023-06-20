from BST import *
import importlib
from datetime import datetime


# import BinarySearchTree from BST.py file
bst = BinarySearchTree()


def bst_insert():

    insert_time=[]

    for j in range(1,3):
        for i in range(1,4):
            with open(f'C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/insert_set{j}_data_{i}.txt', 'r') as file:
                insert_set_data = file.read()
            start = datetime.now()
            bst.insert(insert_set_data)
            end = datetime.now()
            duration = end - start
            insert_time.append(duration.microseconds)

    return (f'Insert Time : {insert_time}')


def bst_search():
    search_time =list()
    for j in range(1,3):
        for i in range(1,4):
            with open(f'C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/search_set{j}_data_{i}.txt', 'r') as file:    
                search_set_data = file.read()
            
            start = datetime.now()
            bst.search(search_set_data)
            end = datetime.now()
            duration = end - start
            search_time.append(duration.microseconds)
    
    return (f'Search Time : {search_time}')

def bst_delete():
    # We must insert data before doing any operation
    bst_insert() # Please ignore the time measurement of this data insertion 
    delete_time =list()  
    for j in range(1,2):
        for i in range(1,2):
            with open(f'C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/delete_set{j}_data_{i}.txt', 'r') as file:    
                delete_set_data = file.read
            start = datetime.now()
            bst.search(delete_set_data)
            end = datetime.now()
            duration = end - start
            delete_time.append(duration.microseconds)

print(bst_insert())