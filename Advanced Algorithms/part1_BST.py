import importlib
from datetime import datetime
import datetime
import statistics

# import BinarySearchTree from BST.py file
bst = importlib.import_module("BST").BinarySearchTree()


def bst_insert():

    times =[]
    for a in range(1,3):
        for b in range(1,4):
            file_path = f"C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/insert_set{a}_data_{b}.txt"
            with open(file_path, "r") as file:
                # Read the contents of the file
                file_contents = file.read().splitlines()

            ## File content in list form.
            test_list = list(map(int, file_contents))

        start = datetime.datetime.now()
        ## Insert to tree.
        for item in test_list:
            bst.insert(item)

        end = datetime.datetime.now()
        duration = end - start
        times.append(duration.microseconds)
    
    return times



def bst_search():
    # We must insert data before doing any operation
    bst_insert() # Please ignore the time measurement of this data insertion
    
    # TODO: Read all "Search data files" separately in sequential order
    # TODO: Measure time is taken for each data file
    # TODO: Return a list according to the requested format
    times =[]
    for a in range(1,3):
        for b in range(1,4):
            file_path = f"C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/search_set{a}_data_{b}.txt"
            with open(file_path, "r") as file:
                # Read the contents of the file
                file_contents = file.read().splitlines()
            ## File content in list form.
            test_list = list(map(int, file_contents))

        start = datetime.datetime.now()
        ## search in tree.
        for item in test_list:
            bst.search(item)

        end = datetime.datetime.now()
        duration = end - start
        times.append(duration.microseconds)

    
    return times

def bst_delete():
    # We must insert data before doing any operation
    bst_insert() # Please ignore the time measurement of this data insertion
    
    # TODO: Read all "Delete data files" separately in sequential order
    # TODO: Measure time is taken for each data file
    # TODO: Return a list according to the requested format

    times =[]
    for a in range(1,3):
        for b in range(1,4):
            file_path = f"C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/delete_set{a}_data_{b}.txt"
            with open(file_path, "r") as file:
                # Read the contents of the file
                file_contents = file.read().splitlines()
            ## File content in list form.
            test_list = list(map(int, file_contents))

        start = datetime.datetime.now()
        ## search in tree.
        for item in test_list:
            bst.delete(item)

        end = datetime.datetime.now()
        duration = end - start
        times.append(duration.microseconds)

    
    return times