;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;divide function: Takes a list and returns just how many of them are divisible by 3
;;parameters:
;;    1st - a list of numbers
;;assumptions:
;;    1. the inputted list may or may not be nested
;;    2. the divide function will use a helper function to tell it whether a number in the list is divisible by 3  
(defun divide (list)
  (cond ((null list) 0)
        ((listp list) (+ (divide (car list)) (divide (cdr list))) )
        ((divisible-by-3 list) 1)
        (t 0)
  )
)

;;test plan for factorial:
;;category/description                                                      data         expected result
;;==================================================================================================
;;non-nested list with number divisible by 3                                '(3 4 6)           2
;;nested list with number divisible by 3                                    '(3 (3 1))         2
;;non-nested list with numbers not divisible by 3                           '(2 1 8)           0
;;nested list with numbers not divisible by 3                               '(2 (1 7))         0
;;nested list with numbers, empty list and numbers divisible by 3           '(3 6 ())          2
;;nested list with numbers, empty list and numbers not divisible by 3       '(4 4 ())          0
;;nested list with negative number divisible by 3                           '(3 -6 (3))        3
;;nested list with negative number not divisible by 3                       '(-2 -5 (-10))     0 
;;nested list with negative number divisible by 3 and a empty nest          '(-3 -6 (-9 ()))   3    
;;nested list with negative number not divisible by 3 and a empty nest      '(-2 -5 (-8 ()))   0
;;nested list(single number) with negative number not divisible by 3        '((-1))            0
;;nested list(single number) with negative number divisible by 3            '((-3))            1
;;nested list(single number) with positive number not divisible by 3        '((1))             0
;;nested list(single number) with positive number divisible by 3            '((3))             1
;;non-nested list(single number) with negative number not divisible by 3    '(-1)              0
;;non-nested list(single number) with negative number divisible by 3        '(-3)              1
;;non-nested list(single number) with positive number not divisible by 3    '(1)               0
;;not-nested list(single number) with positive number divisible by 3        '(3)               1            ;;empty list not divisible by 3                                             '()                0 

;;============================================================================
;;============================================================================
;;divisible-by-3 function: Takes a number and returns whether it is divisible by 3
;;parameters:
;;    1st - a number
;;assumptions:
;;    1. the function will evaluate as to whether a number is divisible by 3
;;    2. if the number is divisible by 3 the function will return true(t) and if not true it will return false(nill)  
(defun divisible-by-3 (number)
  ;;(format t "Currently on integer: ~D~%" number)
  (cond ((equal number 0) t)
        ((> 0 number) (cond ((>= 0 (+ number 3)) (divisible-by-3 (+ number 3)))))
        ((< 0 number) (cond ((<= 0 (- number 3)) (divisible-by-3 (- number 3)))))
  )
)
;;test plan for divisible-by-3:
;;category/description                                             data         expected result
;;==============================================================================================
;;number tested for divisibility is positive & divisible by 3       6            T
;;number tested for divisibility is negative & divisible by 3      -9            T
;;number tested for divisibility is zero(base case)                 0            0
;;number tested for divisibility is positive & not divisible by 3   2            NIL
;;number tested for divisibility is negative & not divisible by 3   -4           NIL


