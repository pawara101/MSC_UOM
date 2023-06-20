import datetime
import statistics
from BST import *
from RBT import *

bst = BinarySearchTree()

bst.insert(23)
bst.insert(4)
bst.insert(12)
bst.insert(89)
bst.insert(34)

print("Sorted BST -->",bst.traverse_in_order([]))
print("Insert order -->",bst.traverse_pre_order([]))

result = bst.find_node_and_parent(24)
print(result)

print(bst.traverse_post_order([]))

height = bst.find_height()
print(height)