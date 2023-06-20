import datetime
import statistics

from RBT import *
a = 2
b = 3
file_path = f"C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/insert_set{a}_data_{b}.txt"


# Open the file in read mode
with open(file_path, "r") as file:
    # Read the contents of the file
    file_contents = file.read().splitlines()

test_list = list(map(int, file_contents))

# Create an instance of the RedBlackTree class
rb_tree = RedBlackTree()
time_list = []

for t in range(0,3):
    start = datetime.datetime.now()
    # Insert the values into the Red-Black Tree
    for i in test_list:
        rb_tree.insert(i)

    end = datetime.datetime.now()

    dur = end - start
    time_list.append(dur.microseconds)


print(time_list)
print("Average Execution Time:", statistics.mean(time_list))

print('------------------------------------------------------')
height = rb_tree.tree_height()
print(height)

# Search for a value in the tree

time_list = []
file_path = f"C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/delete_set{a}_data_{b}.txt"

# Open the file in read mode
with open(file_path, "r") as file:
    # Read the contents of the file
    file_contents = file.read().splitlines()

test_list = list(map(int, file_contents))

for t in range(0,3):
    start = datetime.datetime.now()
    # Insert the values into the Red-Black Tree
    for i in test_list:
        rb_tree.insert(i)

    end = datetime.datetime.now()

    dur = end - start
    time_list.append(dur.microseconds)


print("Average Delete Time:", statistics.mean(time_list))


print('------------------------------------------------------')
height = rb_tree.tree_height()
print(height)