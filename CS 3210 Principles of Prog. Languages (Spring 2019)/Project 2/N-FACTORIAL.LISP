;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;factorial function: Calculates the factorial of a positive integer and returns it
;;parameters:
;;    1st - a integer
;;assumptions:
;;    1. 1st parameter input is a positive number
(defun factorial (a)
  (cond ((= a 1) 1) ;;IF a=1 return 1, base case
        ((> a 1) (* a (factorial(1- a)))) ;;recursion of factorial where input is a - 1
  )
)

;;test plan for factorial:
;;category/description             data               expected result
;;===================================================================
;;positive number                   4                      24
;;base case                         1                      1