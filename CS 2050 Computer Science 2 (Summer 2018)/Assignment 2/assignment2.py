import unittest

'''
Description: Dictionary that uses chaining
Author: Adam Schaible
Version: 1.0
Help provided to:
Help received from: Steven Beaty, Peter Nielson
'''

'''
    Implement a dictionary using chaining.
    You may assume every key has a hash() method, e.g.:
    >>> hash(1)
    1
    >>> hash('hello world')
    -2324238377118044897
'''


class dictionary:
    def __init__(self, init=None, limit=10):
        self.__limit = limit
        self.__items = [[] for _ in range(self.__limit)]
        self.__count = 0

        if init:
            for i in init:
                self.__setitem__(i[0], i[1])

    def __len__(self):
        x = self.__iter__()
        for value in x:
            self.__count = self.__count + 1
        return self.__count

    def flattened(self):
        return [item for inner in self.__items for item in inner]

    def __iter__(self): 
        return iter(self.flattened())
    
    def __str__(self): 
        return str(self.flattened())

    def __getoutercell__(self, key):
        return hash(key) % self.__limit

    def __setitem__(self, key, value):
        ''' Add to the dictionary. '''
        outercell = self.__getoutercell__(key)
        appenditemtofilledlist = True

        '''If outercell is empty then append key and value to the end'''
        if self.__items[outercell] == []:
                self.__items[outercell].append([key, value])
                appenditemtofilledlist = False

        else:
            for spot in self.__items[outercell]:
                if spot[0] == key:
                    spot[1] = value
                    appenditemtofilledlist = False

        if appenditemtofilledlist:
            self.__items[outercell].append([key, value])

    def __getitem__(self, key):
        outercell = self.__getoutercell__(key)
        noreturnvalue = True

        for spot in self.__items[outercell]:
            if spot[0] == key:
                noreturnvalue = False
                return spot[1]

        if noreturnvalue:
            return None

    def __contains__(self, key):
        flatarray = self.flattened()
        matchingkey = False

        for i in flatarray:
            if i[0] == key:
                matchingkey = True
                return True

        if not matchingkey:
            return False

    def __tasksdonetoresizethedictionary__(self):
        datacopy = self.__iter__()
        '''creating a larger blank dictionary using self.__init__()'''
        self.__init__(limit=self.__limit)

        for keyandvalueset in datacopy:
            copiedkey = keyandvalueset[0]
            copiedvalue = keyandvalueset[1]
            '''placing old dictionary into new dictionary using self.__setitem__(copiedkey, copiedvalue)'''
            self.__setitem__(copiedkey, copiedvalue)

    def __loadfactor__(self):
        return (self.__len__()/self.__limit)*100

    def __doubledictionarysize__(self):
        if self.__loadfactor__() > 75:
            self.__limit = self.__limit * 2
            self.__tasksdonetoresizethedictionary__()

    def __halfdictionarysize__(self):
        if self.__loadfactor__() < 25:
            self.__limit = self.__limit // 2
            self.__tasksdonetoresizethedictionary__()

    def __delitem__(self, key):
        outercell = self.__getoutercell__(key)
        itemdeleted = False
        index = 0

        if self.__items[outercell] == []:
            raise RuntimeError("Nothing to delete")

        for spot in self.__items[outercell]:
            if spot[0] == key:
                itemdeleted = True
                self.__items[outercell].pop(index)
            index = index + 1

        if not itemdeleted:
            raise RuntimeError("Nothing to delete")

    def __tasksdonetogetkeysandvalues(self, slot):
        iteratablelist = self.__iter__()
        newlist = []
        for value in iteratablelist:
            newlist.append(value[slot])
        return newlist

    def keys(self):
        return self.__tasksdonetogetkeysandvalues(0)
        ''' Implements the 'in' operator. '''

    def values(self):
        return self.__tasksdonetogetkeysandvalues(1)
        ''' Implements the 'in' operator. '''

    def __eq__(self, key, value):
        outercell = self.__getoutercell__(key)

        for spot in self.__items[outercell]:
            if spot[0] == key and spot[1] == value:
                return True
            else:
                return False


''' C-level work '''


class test_double_dictionary_size(unittest.TestCase):
    def test(self):
        s = dictionary()
        for x in range(8):
            s[x] = str(x)

        oldloadfactor = s.__loadfactor__()
        s.__doubledictionarysize__()
        newloadfactor = s.__loadfactor__()

        self.assertGreater(oldloadfactor, 75)
        self.assertEqual(newloadfactor, 40)


class test_half_dictionary_size(unittest.TestCase):
    def test(self):
        s = dictionary()
        for x in range(2):
            s[x] = str(x)

        oldloadfactor = s.__loadfactor__()
        s.__halfdictionarysize__()
        newloadfactor = s.__loadfactor__()

        self.assertLess(oldloadfactor, 25)
        self.assertEqual(newloadfactor, 40)


class test_delete_item(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[1] = "one"
        s[2] = "two"
        s[3] = "three"
        self.assertEqual(len(s), 3)
        s.__delitem__(1)
        self.assertEqual(len(s), 2)

class test_getting_keylist(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[1] = "one"
        s[2] = "two"
        s[3] = "three"
        keylist = s.keys()
        self.assertEqual(keylist, [1, 2, 3])


class test_getting_valuelist(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[1] = "five"
        s[2] = "eight"
        s[3] = "three"
        s[4] = "nine"
        valuelist = s.values()
        self.assertEqual(valuelist, ['five', 'eight', 'three', 'nine'])


class test_equal_key_and_value(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[1] = "thirty"
        s[2] = "two"
        s[3] = "six"
        self.assertTrue(s.__eq__(1, "thirty"))
        self.assertTrue(s.__eq__(2, "two"))
        self.assertTrue(s.__eq__(3, "six"))
        self.assertFalse(s.__eq__(3, "sixty"))
        self.assertFalse(s.__eq__(4, "nine"))


class test_add_two(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[1] = "one"
        s[2] = "two"
        self.assertEqual(len(s), 2)
        self.assertEqual(s[1], "one")
        self.assertEqual(s[2], "two")


class test_add_twice(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[1] = "one"
        s[1] = "one"
        self.assertEqual(len(s), 1)
        self.assertEqual(s[1], "one")


class test_store_false(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[1] = False
        self.assertTrue(1 in s)
        self.assertFalse(s[1])


class test_store_none(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[1] = None
        self.assertTrue(1 in s)
        self.assertEqual(s[1], None)


class test_none_key(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[None] = 1
        self.assertTrue(None in s)
        self.assertEqual(s[None], 1)


class test_False_key(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[False] = 1
        self.assertTrue(False in s)
        self.assertEqual(s[False], 1)


class test_collide(unittest.TestCase):
    def test(self):
        s = dictionary()
        s[0] = "zero"
        s[10] = "ten"
        self.assertEqual(len(s), 2)
        self.assertTrue(0 in s)
        self.assertTrue(10 in s)


''' B-level work
    Add doubling and rehashing when load goes over 75%
    
    
    
    
    Add __delitem__(self, key)
'''

''' A-level work
    Add halving and rehashing when load goes below 25%
    Add keys()
    Add values()
'''

''' Extra credit
    Add __eq__()
    Add items(), "a list of D's (key, value) pairs, as 2-tuples"
'''

if __name__ == '__main__':
    unittest.main()
