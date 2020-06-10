;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;sort-by-recency function: Takes a word and a list of non-nested words and moves the list to the front of the list if it is in the list and if it is not in the list it appends that word to the front of the list.
;;parameters:
;;    1st - a word 
;;    2nd - a list of words
;;assumptions:
;;    1. The incoming list will have no duplicates.
;;    2. The words will be in lower case.




(defun sort-by-recency (test)
  (cond ((atom test) '())
        ((null test) '())
        (t (cons (car test) (sort-by-helper (car test) (cdr test))))
             
  )
)
;;test plan for sort-by-recency(first nested list is search word) (second nested list is search list):
;;category/description                 data                         expected result
;;===================================================================================
;;word is in 2nd spot in the list      '((dog) (cat dog rabbit))        (cat dog rabbit)
;;word is in 1st spot of the list      '(cat) '(cat dog rabbit)        (cat dog rabbit)
;;word is not in the list              '(dog) '(cat mouse rabbit)      (dog cat mouse rabbit) 
;;word in list with single word        '(red) '(red)                   (red)
;;word not in list with single word    '(blue) '(red)                  (blue red)

