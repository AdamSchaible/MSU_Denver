;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;count-groups function: Takes in a non-nested list that can consist of either words or numbers and returns a count of the number of groups(made up of two or more adjacent items)
;;parameters:
;;    1st - a non-nested list that made up of either words or numbers
;;assumptions:
;;    1. the list is not nested
;;    2. the list is made up of words or numbers 
(defun count-groups (list)
  (cond ((null list) 0)
        (
        
  )
)

;;test plan for remove-negatives:
;;category/description                              data                        expected result
;;=====================================================================================
;;list with a group and words only                  '(fish can swim swim)       1
;;list without a group and words only               '(fish can swim)            0
;;list with a group and numbers only                '(4 4 5 6 6)                2
;;list without a group and numbers only             '(4 5 6 7)                  0
;;list with a group with both numbers and words     '(fish fish 4 5 5)          2
;;list without a group with both numbers and words  '(can 4 now 4 5)            0
;;list with a single word                           '(fish)                     0
;;list with a single number                         '(1)                        0