;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;duplicates function: takes a list of non-nested integers and returns T(true) if it found duplicate numbers in the list, else returns nil(false)
;;parameters:
;;    1st - a list of non-nested integers
;;assumptions:
;;    1. the inputted list is composed of integers
;;    2. the inputted list is not nested  
(defun duplicates (list)
  (cond ((null list) nil)
        ((listp list) (or (duplicate-checker (car list) (cdr list)) (duplicates (cdr list))))
  )  
)

;;test plan for duplicates:
;;category/description                                                                data         expected result
;;==========================================================================================================
;;non-nested list with multiple duplicates                                           '(2 3 4 2 3)        T
;;non-nested list with single duplicate                                              '(1 2 3 2)          T
;;non-nested list with one number                                                    '(1)                NIL
;;non-nested list with two non-matching numbers                                      '(1 2)              NIL
;;non-nested list with multiple non-matching numbers                                 '(1 2 3 4 5)        NIL
;;empty list with no matches                                                         '()                 NIL
;;non-nested list with multiple duplicates (negative integers)                       '(-2 -3 -4 -2 -3)   T
;;non-nested list with single duplicate (negative integers)                          '(-1 -2 -3 -2)      T
;;non-nested list with one number (negative integers)                                '(-1)               NIL
;;non-nested list with two non-matching numbers (negative integers)                  '(-1 -2)            NIL
;;non-nested list with multiple non-matching numbers (negative integers)             '(-1 -2 -3 -4 -5)   NIL
;;non-nested list with multiple duplicates (negative & positive integers)            '(2 -3 4 -2 3)      T
;;non-nested list with single duplicate (negative & positive integers)               '(1 -2 3 -2)        T
;;non-nested list with one number (positive integers)                                '(1)               NIL
;;non-nested list with two non-matching numbers (negative & positive integers)       '(1 -2)            NIL
;;non-nested list with multiple non-matching numbers (negative & positive integers)  '(-1 2 -3 -4 -5)   NIL

;;=======================================================================================================================================================
;;duplicate-checker function: sees if a integer is present in a list and if it is true(T) is returned and if it is not in the list false(NIL) is returned
;;parameters:
;;    1st - a integer value
;;    2nd - a non-nested integer list
;;assumptions:
;;    1. the list is a non-nested list of integers

(defun duplicate-checker (number list)
  (cond ((null list) nil)
        ((null number) nil)
        ((listp list) (or (duplicate-checker number (car list)) (duplicate-checker number (cdr list))) )
        ((equal list number) t)
        
  )
) 
;;test plan for duplicate-checker
;;category/description                                                                    data               expected result
;;==========================================================================================================================
;;non-nested list that contains the integer of interest                                   3 '(3 8)                 T      
;;non-nested list that does not contain the integer of interest                           2 '(0 1)                 NIL      
;;non-nested list that contains negative integers of interest                             -1 '(-1 0)               T
;;non-nested list that does not contain a negative integer of interest                    -2 '(1 2 0 3)            NIL
;;non-nested list that contains a single number and matching the integer of interest      -1 '(-1)                 T
;;non-nested list that contains a single number and not matching the integer of interest  2 '(4)                   NIL           
;;number is entered in along with a empty list                                            3 '()                    NIL
;;empty list is entered in for both the number and the list                               '() '()                  NIL
;;empty list is entered in for the number along with a list of numbers                    '() '(3 4 2)             NIL