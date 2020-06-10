;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;searching function: finds just how many times a integer is present in a list
;;parameters:
;;    1st - a integer value
;;    2nd - a list
;;assumptions:
;;    1. 1st parameter is a integer
;;    2. 2nd parameter is a list that contains both numbers and words
(defun searching (number list)
  (cond ((null list) 0)
        ((listp list) (+ (searching number (car list)) (searching number (cdr list))) )
        ((equal list number) 1)
        (t 0)
  )
)

;;Category/description                                                       data               expected result
;;============================================================================================================
;;non-nested list that contains the integer of interest                       5 '(6 5 blue)            1      
;;nested list that contains the integer of interest                           4 '(4 (4 green))         2
;;non-nested list that does not contain the integer of interest               5 '(6 1 blue)            0      
;;nested list that does not contain the integer of interest                   4 '(6 (84 green))        0
;;list contains words, integers, empty list and not the integer of interest   3 '(3 (green 4 () 3))    2    
;;list contains words, integers, empty list and not the integer of interest   3 '(6 (green 9 () 9))    0    
;;non-nested list that contains only words                                    3 '(yellow green)        0
;;non-nested list that contains only numbers that do not match                4 '(3 5 6 7 8)           0
;;non-nested list that contains only numbers and has a match                  2 '(3 2 4 5 1)           1
;;nested list that contains only words                                        5 '(red (blue new))      0
;;match on first item in list, non-nested                                     4 '(4 small 3)           1
;;match on second item in list, non-nested                                    4 '(small 4 3)           1
;;match on first item in list, nested                                       4 '(4 (small) 3)           1
;;match on second item in list, nested                                      4 '(small 4 (3))           1
;;nested list that contains only words                                    3 '(yellow (green) green)    0
;;nested list that contains only numbers that do not match                    1 '(2 3 5 (9) 3)         0
;;nested list that contains only numbers and has a match                      2 '(3 (2) 4 5 1)         1
;;match on a list composed of a single element list                           3 '(3)                   1
;;no match on a single element list                                           4 '(3)                   0
;;no match on a empty list                                                    4 '()                    0                                                                 