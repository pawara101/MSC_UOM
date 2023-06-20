import importlib
from datetime import datetime

# import BinarySearchTree from BST.py file
bst = importlib.import_module("BST").BinarySearchTree()


def bst_insert():
    # TODO: Read all "Insert data files" separately in sequential order
    
    # TODO: Measure time is taken for each data file
    # TODO: Return a list according to the requested format


def bst_search():
    # We must insert data before doing any operation
    bst_insert() # Please ignore the time measurement of this data insertion
    
    # TODO: Read all "Search data files" separately in sequential order
    # TODO: Measure time is taken for each data file
    # TODO: Return a list according to the requested format


def bst_delete():
    # We must insert data before doing any operation
    bst_insert() # Please ignore the time measurement of this data insertion
    
    # TODO: Read all "Delete data files" separately in sequential order
    # TODO: Measure time is taken for each data file
    # TODO: Return a list according to the requested format