import datetime
import statistics
from BST import *
from RBT import *

sol = BinarySearchTree()
a =2
b =3
file_path = f"C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/insert_set{a}_data_{b}.txt"

# Open the file in read mode
with open(file_path, "r") as file:
    # Read the contents of the file
    file_contents = file.read().splitlines()

test_list = list(map(int, file_contents))

time_list = []

for i in range(3):
    start = datetime.datetime.now()
    print('Start Time:', start)
    # Insert each element from test_list individually into the tree
    for item in test_list:
        sol.insert(item)
    end = datetime.datetime.now()
    print('End Time:', end)
    duration = end - start
    time_list.append(duration.microseconds)

print(time_list)
print("Average Execution Time:", statistics.mean(time_list))


height = sol.find_height()
print(height)

print('----------------------------------------')

file_path = f"C:/Users/USER/OneDrive/Python3/Vs Code/Advanced Algorithms/Assignment-1 Q5 Resources (Provided Python Code and Datasets)-20230610/PythonCodeDataset/PythonCode&Dataset/data/delete_set{a}_data_{b}.txt"

# Open the file in read mode
with open(file_path, "r") as file:
    # Read the contents of the file
    file_contents = file.read().splitlines()

test_list = list(map(int, file_contents))

time_list = []

for i in range(3):
    start = datetime.datetime.now()
    print('Start Time:', start)
    # Insert each element from test_list individually into the tree
    for item in test_list:
        sol.insert(item)
    end = datetime.datetime.now()
    print('End Time:', end)
    duration = end - start
    time_list.append(duration.microseconds)

print(time_list)
print("Average delete Time:", statistics.mean(time_list))



print('------------------------------------------------------')
height = sol.find_height()
print(height)