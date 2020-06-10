;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;removing-negatives function: takes a list of non-nested integers and returns a list with all negative values removed
;;parameters:
;;    1st - a list of non-nested integers
;;assumptions:
;;    1. the integer list is not nested
;;    2. the integer list is not empty  
(defun removing-negatives (collected)
  (cond ((atom collected) '())
        ((null collected) '())
        ((> 0 (car collected)) (removing-negatives (cdr collected)))
        ((<= 0 (car collected)) (cons (car collected) (removing-negatives (cdr collected))))
        (t '())
             
  )
)

;;test plan for removing-negatives:
;;category/description                             data                 expected result
;;=====================================================================================
;;list with no negatives                           '(3 4 5 6 3 2 0 2)     (3 4 5 6 3 2 0 2)
;;list with all negatives                          '(-2 -4 -5 -3 -5)      NIL
;;list with some negatives                         '(3 -2 4 2 -6)         (3 4 2)
;;empty list                                       '()                    NIL
;;list with one number thats negative              '(-1)                  NIL
;;list with one number thats not negative          '(1)                   (1)