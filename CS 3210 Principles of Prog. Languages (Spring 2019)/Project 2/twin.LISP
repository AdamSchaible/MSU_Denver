;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;twin function: takes a list that is not nested and doubles all elements 
;;parameters:
;;    1st - a list thats not nested
;;assumptions:
;;    1. the incoming list is not nested
(defun twin (collected)
  (cond ((atom collected) '())
        ((null collected) '())

        (t (cons (car collected) (cons (car collected) (twin (cdr collected)))))                
  )
)

;;test plan for enforce-upper-limit:
;;category/description                                       data                expected result
;;=============================================================================================================
;;a incoming list that contains words                        '(two next)           (TWO TWO NEXT NEXT)
;;a incoming list that is empty                               '()                   NIL
;;a incoming list that contains numbers                       '(3 4 5)              (3 3 4 4 5 5)
;;a incoming list contains words and numbers                  '(3 mark 5 now)       (3 3 MARK MARK 5 5 NOW NOW)
;;a incoming list contains just one word                      '(mark)               (MARK MARK)
;;a incoming list contains just one number                    '(1)                  (1 1)
  
