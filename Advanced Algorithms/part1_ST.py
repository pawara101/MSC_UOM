import importlib
from datetime import datetime
import datetime
import statistics

# import BinarySearchTree from ST.py file
st = importlib.import_module("ST").SplayTree()


def st_insert():

    times =[]
    for a in range(1,3):
        for b in range(1,4):
            file_path = f"insert_set{a}_data_{b}.txt"
            with open(file_path, "r") as file:
                # Read the contents of the file
                file_contents = file.read().splitlines()

            ## File content in list form.
            test_list = list(map(int, file_contents))

        start = datetime.datetime.now()
        ## Insert to tree.
        for item in test_list:
            st.insert(item)

        end = datetime.datetime.now()
        duration = end - start
        times.append(duration.microseconds)
    
    return times



def st_search():
    # We must insert data before doing any operation
    st_insert() # Please ignore the time measurement of this data insertion
    
    # TODO: Read all "Search data files" separately in sequential order
    # TODO: Measure time is taken for each data file
    # TODO: Return a list according to the requested format
    times =[]
    for a in range(1,3):
        for b in range(1,4):
            file_path = f"search_set{a}_data_{b}.txt"
            with open(file_path, "r") as file:
                # Read the contents of the file
                file_contents = file.read().splitlines()
            ## File content in list form.
            test_list = list(map(int, file_contents))

        start = datetime.datetime.now()
        ## search in tree.
        for item in test_list:
            st.search(item)

        end = datetime.datetime.now()
        duration = end - start
        times.append(duration.microseconds)

    
    return times

def st_delete():
    # We must insert data before doing any operation
    st_insert() # Please ignore the time measurement of this data insertion
    
    # TODO: Read all "Delete data files" separately in sequential order
    # TODO: Measure time is taken for each data file
    # TODO: Return a list according to the requested format

    times =[]
    for a in range(1,3):
        for b in range(1,4):
            file_path = f"delete_set{a}_data_{b}.txt"
            with open(file_path, "r") as file:
                # Read the contents of the file
                file_contents = file.read().splitlines()
            ## File content in list form.
            test_list = list(map(int, file_contents))

        start = datetime.datetime.now()
        ## search in tree.
        for item in test_list:
            st.delete(item)

        end = datetime.datetime.now()
        duration = end - start
        times.append(duration.microseconds)

    
    return times