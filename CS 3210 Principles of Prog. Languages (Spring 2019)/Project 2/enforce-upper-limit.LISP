;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;enforce-upper-limit function: compares a number with any numbers in a list that could contain words, numbers or both as well as might be nested and if the number in the list is greater than the number provided the list number shall be replaced with that number. 
;;parameters:
;;    1st - number
;;    2nd - list made of words, numbers or both and could be nested
;;assumptions:
;;    1. the incoming list has no duplicates
(defun enforce-upper-limit (findingatom lst)
  (cond ((null lst) nil) 
        ;;((listp lst) (+ (divide (car lst)) (divide (cdr lst))) )
        ((numberp (car lst)) ((< findingatom (car 1st)) (print "1")) (t (print "21")))
        ;;((numberp (car lst)) ((< findingatom (car lst)))) (cons findingatom (enforce-upper-limit findingatom (cdr lst))))

        (t (cons (car lst) (enforce-upper-limit findingatom (cdr lst))))
  )       
)

;;test plan for enforce-upper-limit:
;;category/description                                                 data                        expected result
;;====================================================================================================================
;;list is not nested and contains only words                           9 '(cat dog tree)           (cat dog tree)
;;list is nested and contains only words                               9 '(cat dog (star))         (cat dog (star))
;;list contains only words and has a empty nest                        9 '(cat dog ())             (cat dog ())
;;list contains only numbers, not nested, no numbers above limit       9 '(4 5 2 1)                (4 5 2 1)
;;list contains only numbers, not nested, some numbers above limit     3 '(4 5 2 1)                (3 3 2 1)
;;list contains only numbers, nesting, no numbers above limit          8 '(4 6 2 (7))              (4 6 2 (7))
;;list contains only numbers, empty nest, not numbers above limit      4 '(1 2 3 ())               (1 2 3 ())
;;list contains only numbers, nesting, some numbers above limit        3 '(1 3 5 (4))              (1 3 3 (3))
;;list contains only numbers, empty nest, some numbers above limit     4 '(1 5 6 ())               (1 4 4 ())
;;list contains numbers & words, not nested, no numbers above limit    9 '(1 cat 2 dog)            (1 cat 2 dog)
;;list contains numbers & words, not nested, some numbers above limit  3 '(4 dog 3 cat)            (3 dog 3 cat)
;;list contains numbers & words, nested, not numbers above limit       6 '(4 star 5 (cat 6))       (4 star 5 (cat 6))
;;list contains numbers & words, nested, some numbers above limit      4 '(5 cat 4 (star 9))       (4 cat 4 (star 4))
;;list contains numbers & words, empty nest, no numbers above limit    7 '(5 dog 3 ())             (5 dog 3 ())
;;list contains numbers & words, empty nest, some numbers above limit  2 '(5 dog 6 ())             (2 dog 2 ())
;;list contains only one number, number above limit                    2 '(4)                      (2)
;;list contains only one word                                          3 '(red)                    (red)
;;list contains only one number, number not above limit                2 '(1)                      (1)
;;list contains only one number, empty list, number above limit        2 '(3 ())                   (2 ())
;;list contains only one word, with empty list                         2 '(red ())                 (red ())
