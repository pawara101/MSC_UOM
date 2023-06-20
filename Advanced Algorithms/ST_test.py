import datetime
import statistics

from ST import *

a =2
b =2
file_path = f"C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/insert_set{a}_data_{b}.txt"


# Open the file in read mode
with open(file_path, "r") as file:
    # Read the contents of the file
    file_contents = file.read().splitlines()

test_list = list(map(int, file_contents))


# Create an instance of the RedBlackTree class
splayTree = SplayTree()
time_list = []


for t in range(0,3):
    start = datetime.datetime.now()
    # Insert the values into the Red-Black Tree
    for i in test_list:
        splayTree.insert(i)

    end = datetime.datetime.now()

    dur = end - start
    time_list.append(dur.microseconds)


print(time_list)
print("Average Execution Time:", statistics.mean(time_list))
# Search for a value in the tree

print('------------------------------------------------------')
height = splayTree.get_tree_height()
print(height)

file_path = f"C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/delete_set{a}_data_{b}.txt"

time_list = []
# Open the file in read mode
with open(file_path, "r") as file:
    # Read the contents of the file
    file_contents = file.read().splitlines()

test_list = list(map(int, file_contents))

for t in range(0,3):
    start = datetime.datetime.now()
    # Insert the values into the Red-Black Tree
    for i in test_list:
        splayTree.search(i)

    end = datetime.datetime.now()

    dur = end - start
    time_list.append(dur.microseconds)

print(time_list)
print("Average search Time:", statistics.mean(time_list))


print('------------------------------------------------------')
height = splayTree.get_tree_height()
print(height)