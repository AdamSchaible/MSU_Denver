from __future__ import print_function
import unittest

''' when run with "-m unittest", the following produces:
    FAILED (failures=9, errors=2)
    your task is to fix the failing tests by implementing the necessary
    methods. '''


class LinkedList(object):
    class Node(object):
        # pylint: disable=too-few-public-methods
        ''' no need for get or set, we only access the values inside the
            LinkedList class. and really: never have setters. '''

        def __init__(self, value, next_node):
            self.value = value
            self.next_node = next_node

    def __init__(self, initial=None):
        self.front = self.back = self.current = None
        if initial is not None:
            for i in initial:
                self.push_front(i)

    def empty(self):
        return self.front == self.back == None

    def __iter__(self):
        self.current = self.front
        return self

    def __next__(self):
        if self.current:
            tmp = self.current.value
            self.current = self.current.next_node
            return tmp
        else:
            raise StopIteration()

    def __str__(self):
        mylist = self.front
        s = ''
        if self.empty():
            return s

        continueloop = True
        while continueloop:

            comma = ''
            if mylist.next_node is not None:
                comma = ', '
            s = s + str(mylist.value) + comma
            continueloop = False

            if mylist.next_node is not None:
                mylist = mylist.next_node
                continueloop = True
        return s

    def push_front(self, value):
        new = self.Node(value, self.front)

        if self.empty():
            self.front = self.back = new
        else:
            self.front = new

    ''' you need to(at least) implement the following three methods'''

    def pop_front(self):
        currentnode = self.front

        if not self.empty():
            firstnodevalue = currentnode.value

        if self.empty():
            raise RuntimeError('No nodes to pop!')

        elif currentnode.next_node is None:
            self.front = self.back = None
            return firstnodevalue
        else:
            self.front = currentnode.next_node
            return firstnodevalue

    def push_back(self, value):
        newnode = self.Node(value, None)
        if self.empty():
            self.front = self.back = newnode
        else:
            lastnode = self.front
            while lastnode.next_node is not None:
                lastnode = lastnode.next_node
            lastnode.next = newnode
            self.back = newnode

    def pop_back(self):
        lastnode = self.front
        previousnode = None

        if self.empty():
            raise RuntimeError('No nodes to pop!')

        elif (self.front == self.back) and (self.back is not None):
            self.back = self.front = None
            return lastnode.value

        else:
            while lastnode.next_node is not None:
                previousnode = lastnode
                lastnode = lastnode.next_node

            self.back = previousnode
            previousnode.next = None
            return lastnode.value

    def find_middle_node(self):
        currentnode = self.front
        nodecounter = 1

        if self.empty():
            return 'No nodes, so no middle value'

        while currentnode.next_node is not None:
            currentnode = currentnode.next_node
            nodecounter = nodecounter + 1

        if nodecounter % 2 == 0:
            return 'The # of nodes are even & positive..so no middle node exists'
        else:
            middlenode = (nodecounter//2) + 1
            return middlenode

    def delete_value_from_node(self, nodenumber):
        if self.empty():
            poppednodevalue = 'No node values to delete!'
            return poppednodevalue

        if (type(nodenumber) == int) and (nodenumber >= 1):
            currentnode = self.front
            nodecounter = 1
            while currentnode.next_node is not None:
                currentnode = currentnode.next_node
                nodecounter = nodecounter + 1

            lastnode = self.front
            if nodenumber <= nodecounter:
                loop = 1
                while loop < nodenumber:
                    lastnode = lastnode.next_node
                    loop = loop + 1
                valueremoved = lastnode.value
                lastnode.value = ''
                return valueremoved
            else:
                poppednodevalue = 'node number entered is out of range'
                return poppednodevalue
        else:
            poppednodevalue = 'node number entered is not a positive integer'
            return poppednodevalue

''' C-level work '''


class TestFindMiddleNodeWhenNodesAreOdd(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        linked_list.push_front(1)
        linked_list.push_front(2)
        linked_list.push_front(3)
        self.assertEqual(linked_list.find_middle_node(), 2)


class TestFindMiddleNodeWhenNodesAreEvenAndPositive(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        linked_list.push_front(1)
        linked_list.push_front(2)
        linked_list.push_front(3)
        linked_list.push_front(4)
        self.assertEqual(linked_list.find_middle_node(), 'The # of nodes are even & positive..so no middle node exists')


class TestFindMiddleNodeWhenThereAreNoNodes(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        self.assertEqual(linked_list.find_middle_node(), 'No nodes, so no middle value')


class TestDeleteValueFromNodeWhenNoNodesPresent(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()

        self.assertEqual(linked_list.delete_value_from_node(5), 'No node values to delete!')


class TestDeleteValueFromNodeWhenNodeNumberEnteredIsNotAPositiveInteger(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        linked_list.push_front(1)
        linked_list.value = 12
        linked_list.push_front(2)
        linked_list.value = 15
        linked_list.push_front(3)
        linked_list.value = 9
        self.assertEqual(linked_list.delete_value_from_node('bwz'), 'node number entered is not a positive integer')
        self.assertEqual(linked_list.delete_value_from_node(-91), 'node number entered is not a positive integer')


class TestDeleteValueFromPositiveNodeNumberNotInTheList(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        linked_list.push_front(1)
        linked_list.value = 12
        linked_list.push_front(2)
        linked_list.value = 15
        linked_list.push_front(3)
        linked_list.value = 9
        self.assertEqual(linked_list.delete_value_from_node(4), 'node number entered is out of range')


class TestDeleteValueFromANodeThatExists(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        linked_list.push_front(10)
        linked_list.push_front(22)
        linked_list.push_front(31)
        linked_list.value = 9
        self.assertEqual(linked_list.delete_value_from_node(2), 22)


class TestEmpty(unittest.TestCase):
    def test(self):
        self.assertTrue(LinkedList().empty())


class TestPushFrontPopBack(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        linked_list.push_front(1)
        linked_list.push_front(2)
        linked_list.push_front(3)
        self.assertFalse(linked_list.empty())
        self.assertEqual(linked_list.pop_back(), 1)
        self.assertEqual(linked_list.pop_back(), 2)
        self.assertEqual(linked_list.pop_back(), 3)
        self.assertTrue(linked_list.empty())


class TestPushFrontPopFront(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        linked_list.push_front(1)
        linked_list.push_front(2)
        linked_list.push_front(3)
        self.assertEqual(linked_list.pop_front(), 3)
        self.assertEqual(linked_list.pop_front(), 2)
        self.assertEqual(linked_list.pop_front(), 1)
        self.assertTrue(linked_list.empty())


class TestPushBackPopFront(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        linked_list.push_back(1)
        linked_list.push_back(2)
        linked_list.push_back(3)
        self.assertFalse(linked_list.empty())
        self.assertEqual(linked_list.pop_front(), 1)
        self.assertEqual(linked_list.pop_front(), 2)
        self.assertEqual(linked_list.pop_front(), 3)
        self.assertTrue(linked_list.empty())


class TestPushBackPopBack(unittest.TestCase):
    def test(self):
        linked_list = LinkedList()
        linked_list.push_back(1)
        linked_list.push_back("foo")
        linked_list.push_back([3, 2, 1])
        self.assertFalse(linked_list.empty())
        self.assertEqual(linked_list.pop_back(), [3, 2, 1])
        self.assertEqual(linked_list.pop_back(), "foo")
        self.assertEqual(linked_list.pop_back(), 1)
        self.assertTrue(linked_list.empty())


''' B-level work '''


class TestInitialization(unittest.TestCase):
    def test(self):
        linked_list = LinkedList(("one", 2, 3.141592))
        self.assertEqual(linked_list.pop_back(), "one")
        self.assertEqual(linked_list.pop_back(), 2)
        self.assertEqual(linked_list.pop_back(), 3.141592)


class TestStr(unittest.TestCase):
    def test(self):
        linked_list = LinkedList((1, 2, 3))
        self.assertEqual(linked_list.__str__(), '1, 2, 3')


''' A-level work '''


class TestRepr(unittest.TestCase):
    def test(self):
        linked_list = LinkedList((1, 2, 3))
        self.assertEqual(linked_list.__repr__(), 'LinkedList((1, 2, 3))')


class TestErrors(unittest.TestCase):
    def test_pop_front_empty(self):
        self.assertRaises(RuntimeError, lambda: LinkedList().pop_front())

    def test_pop_back_empty(self):
        self.assertRaises(RuntimeError, lambda: LinkedList().pop_back())


''' write some more test cases. '''

''' extra credit.
    - write test cases for and implement a delete(value) method.
    - write test cases for and implement a method that finds the middle
      element with only a single traversal.
'''

''' the following is a demonstration that uses our data structure as a
    stack'''


def fact(number):
    '''"Pretend" to do recursion via a stack and iteration'''

    if number < 0:
        raise ValueError("Less than zero")
    if number == 0 or number == 1:
        return 1

    stack = LinkedList()
    while number > 1:
        stack.push_front(number)
        number -= 1

    result = 1
    while not stack.empty():
        result *= stack.pop_front()

    return result


class TestFactorial(unittest.TestCase):
    def test_less_than_zero(self):
        self.assertRaises(ValueError, lambda: fact(-1))

    def test_zero(self):
        self.assertEqual(fact(0), 1)

    def test_one(self):
        self.assertEqual(fact(1), 1)

    def test_two(self):
        self.assertEqual(fact(2), 2)

    def test_10(self):
        self.assertEqual(fact(10), 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1)


if '__main__' == __name__:
    unittest.main()
