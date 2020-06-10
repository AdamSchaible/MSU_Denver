;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;expression-syntax-checker function: The expression-syntax-checker checks the syntax of a possibly nested list of expressions with numeric operands and binary infix operators composed of words (plus, minus, times, divideby). A nil is returned if there are not three tokens to each expression, operands are not numeric, invalid operator and if these conditions are not found T for true is returned. This function will be assisted with three helper functions.
;;parameters:
;;    1st - A list that might be nested
;;assumptions:
;;    1. the incoming list maybe nested
(defun expression-syntax-checker (search list)
  (cond (() do something)) 
  ) 
)

;;test plan for enforce-upper-limit:
;;category/description                                                 data                expected result
;;==============================================================================================================
;; non-nested list has more than 3 operands           			           '(7 plus 8 9)               nil
;; non-nested list has less than 3 operands                       		   '(7)                        nil
;; non-nested list has 3 operands, operands numeric                                '(7 minus 9)                T
;; non-nested list right operand is not numeric                                    '(3 plus six)               nil
;; non-nested list left operand is not numeric                                     '(nin plus 4)               nil
;; non-nested list both operands are not numeric                                   '(ti plus ri)               nil
;; non-nested list invalid opcode                                                  '(7 ye 10)                  nil
;; non-nested list invalid opcode, invalid left operand                            '(ti en 10)                 nil
;; non-nested list invalid opcode, invalid right operand			   '(4 ne yellow)              nil
;; non-nested list invalid opcode, invalid left and right operand                  '(de re oi)                 nil
;; non-nested list right operand is not numeric, too many tokens                   '(3 3 plus six)             nil
;; non-nested list left operand is not numeric, too many tokens                    '(nin plus 4 5)             nil
;; non-nested list invalid opcode, too many operands                               '(7 ye 10 9)                nil
;; non-nested list invalid opcode, invalid left operand, too many tokens           '(ti en 10 7)               nil
;; non-nested list invalid opcode, invalid right operand, too many tokens	   '(3 4 ne yellow)            nil
;; non-nested list invalid opcode, invalid left and right operand, too many tokens '(de en re oi)              nil 
;; non-nested list right operand is not numeric, too few operands                  '(plus six)                 nil
;; non-nested list left operand is not numeric, too few operands                   '(nin plus)                 nil
;; non-nested list invalid opcode, too few operands                                '(7 ye)                     nil
;; non-nested list invalid opcode, invalid left operand, too few operands          '(ti en)                    nil
;; non-nested list invalid opcode, invalid right operand, too few operands	   '(ne yellow)                nil
;; non-nested list too few operands                                                '(oi)                       nil
;; nested list has more than 3 operands           			           '(7 plus 8 9 (4))           nil
;; nested list has less than 3 operands                       		           '(7 (9))                    nil
;; nested list has 3 operands, operands numeric                                    '(7 minus (5))              T
;; nested list right operand is not numeric                                        '(3 plus (in2six))          nil
;; nested list left operand is not numeric                                         '(nin (plus) 4)             nil
;; nested list both operands are not numeric                                       '((ti) plus ri)             nil
;; nested list invalid opcode                                                      '(7 (ye) 10)                nil
;; nested list invalid opcode, invalid left operand                                '((ti) en 10)               nil
;; nested list invalid opcode, invalid right operand			           '(4 ne (yellow))            nil
;; nested list invalid opcode, invalid left and right operand                      '(de (re) oi)               nil
;; nested list right operand is not numeric, too many tokens                       '(3 3 (plus) six)           nil
;; nested list left operand is not numeric, too many tokens                        '(nin (plus) 4 5)           nil
;; nested list invalid opcode, too many operands                                   '(7 ye (10) 9)              nil
;; nested list invalid opcode, invalid left operand, too many tokens               '(ti (en) 10 7)             nil
;; nested list invalid opcode, invalid right operand, too many tokens	           '(3 (4) ne yellow)          nil
;; nested list invalid opcode, invalid left and right operand, too many tokens     '((de) en re oi)            nil 
;; nested list right operand is not numeric, too few operands                      '(plus (six))               nil
;; nested list left operand is not numeric, too few operands                       '((nin) plus)               nil
;; nested list invalid opcode, too few operands                                    '(7 (ye))                   nil
;; nested list invalid opcode, invalid left operand, too few operands              '((ti) en)                  nil
;; nested list invalid opcode, invalid right operand, too few operands	           '(ne (yellow))              nil
;; nested list too few operands                                                    '((oi))                     nil










                                         