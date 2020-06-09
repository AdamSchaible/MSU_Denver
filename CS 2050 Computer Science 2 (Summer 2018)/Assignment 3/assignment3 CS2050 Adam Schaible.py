import unittest
  
'''
Write a recursive method that takes 1) a string to find, 2) a string to replace the found string with, and 3) an initial string. Return the initial string with all the found strings replaced with the replacement string. You may not use loops or the built-in string methods except comparison, length, and slicing. Here is an outline.
'''


'''
Description: A program that uses recursion to find character or string inside a string and replace it with something else
Author: Adam Schaible
Version: 1.0
Help received from: Steven Beaty, https://www.pythoncentral.io/how-to-slice-listsarrays-and-tuples-in-python/
Help provided to:
'''


def findandreplace(find, replace, string):
    if string is None:
        return None
    elif string is "":
        return ""
    elif find is None or replace is None:
        return string
    elif find is "" and string is not "":
        return string
    elif find == string[:len(find)]:
        newstring = replace + findandreplace(find, replace, string[len(find):])
        return newstring
    else:
        newstring = string[0] + findandreplace(find, replace, string[1:])
        return newstring


class TestFindAndReplace(unittest.TestCase):

    def test_all_none(self):
        self.assertEqual(findandreplace(None, None, None), None)

    def test_find_none(self):
        self.assertEqual(findandreplace(None, "a", "aabb"), "aabb")

    def test_find_empty(self):
        self.assertEqual(findandreplace("", "a", "aabb"), "aabb")

    def test_replace_none(self):
        self.assertEqual(findandreplace("a", None, "aabb"), "aabb")

    def test_string_none(self):
        self.assertEqual(findandreplace("a", "b", None), None)

    def test_simple(self):
        self.assertEqual(findandreplace("a", "b", "aabb"), "bbbb")

    def test_remove(self):
        self.assertEqual(findandreplace(" ", "", " a abb"), "aabb")

    def test_gettysburg(self):
        self.assertEqual(findandreplace("Four score", "Twenty", "Four score and seven years ago"), "Twenty and seven years ago")


if __name__ == '__main__':
    unittest.main()