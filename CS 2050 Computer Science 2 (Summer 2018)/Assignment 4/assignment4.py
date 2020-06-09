'''
Description: A program that creates and navigates a binary tree
Author: Adam Schaible
Version: 1.0
Help received from: Steven Beaty
Help provided to: Raghav Singh
'''

import unittest


class binary_search_tree:
    def __init__(self, init=None):
        self.__value = self.__left = self.__right = None

        if init:
            for i in init:
                self.add(i)

    def __iter__(self):
        if self.__left:
            for node in self.__left:
                yield(node)

        yield(self.__value)

        if self.__right:
            for node in self.__right:
                yield(node)

    def __str__(self): 
        return','.join(str(node) for node in self)

    def add(self, value):
        if self.__value is None:
            self.__value = value
        if self.__value > value:
            if self.__left is None:
                self.__left = binary_search_tree([value])
            else:
                binary_search_tree.add(self.__left, value)

        if self.__value < value:
            if self.__right is None:
                self.__right = binary_search_tree([value])
            else:
                binary_search_tree.add(self.__right, value)

    def preorder(self):
        treelist = []
        treelist += [self.__value]
        if self.__left:
            treelist += self.__left.preorder()
        if self.__right:
            treelist += self.__right.preorder()
        return treelist

    def inorder(self):
        treelist = []
        if self.__left:
            treelist += self.__left.inorder()
        treelist += [self.__value]
        if self.__right:
            treelist += self.__right.inorder()
        return treelist

    def postorder(self):
        treelist = []
        if self.__left:
            treelist += self.__left.postorder()
        if self.__right:
            treelist += self.__right.postorder()
        treelist += [self.__value]
        return treelist

    def BFS(self):
        if self.__value is None:
            bfsnone = [None]
            return bfsnone
        else:
            resultlist = []
            queuelist = [self]
            while queuelist:
                resultlist += [queuelist[0].__value]
                if queuelist[0].__left is not None:
                    queuelist += [queuelist[0].__left]
                if queuelist[0].__right is not None:
                    queuelist += [queuelist[0].__right]
                queuelist.pop(0)
            return resultlist

    '''  
        # create a queue with the root element, and an empty list
        # while there are nodes in the queue
        # grab the first one and add it to the result list
        # if there is a node to the left, add that to the queue
        # if there is a node to the right, add that to the queue
    '''

class test_binary_search_tree (unittest.TestCase):
    '''
           20
          /  \
        10   30
            /  \
           25  35
    '''

    # C level
    def test_empty(self):
        self.assertEqual(str(binary_search_tree()), 'None')

    def test_one(self):
        self.assertEqual(str(binary_search_tree([1])), '1')

    def test_add(self):
        bt = binary_search_tree()
        bt.add(20)
        bt.add(10)
        bt.add(30)
        bt.add(25)
        bt.add(35)
        self.assertEqual(str(bt), '10,20,25,30,35')

    def test_init(self):
        bt = binary_search_tree([20, 10, 30, 25, 35])
        self.assertEqual(str(bt), '10,20,25,30,35')

    # B level

    def test_empty_inorder(self):
        self.assertEqual(binary_search_tree().inorder(), [None])

    def test_inorder(self):
        bt = binary_search_tree([20, 10, 30, 25, 35])
        self.assertEqual(bt.inorder(), [10, 20, 25, 30, 35])
    
    def test_preorder(self):
        bt = binary_search_tree([20, 10, 30, 25, 35])
        self.assertEqual(list(bt.preorder()), [20, 10, 30, 25, 35])
    
    def test_postorder(self):
        bt = binary_search_tree([20, 10, 30, 25, 35])
        self.assertEqual(bt.postorder(), [10, 25, 35, 30, 20])

    # A level

    def test_empty_BFS(self):
        self.assertEqual(binary_search_tree().BFS(), [None])
    
    def test_BFS(self):
        bt = binary_search_tree([20, 10, 30, 25, 35])
        self.assertEqual(bt.BFS(), [20, 10, 30, 25, 35])

if '__main__' == __name__:
    unittest.main()
