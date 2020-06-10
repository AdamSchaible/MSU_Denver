;;Adam Schaible - CS3210 - Spring 2019
;;====================================
;;Convert function: Takes Farenheit and converts to Celcius and vice versa
;;parameters:
;;    1st - a integer
;;    2nd - a letter C or F to represent Celcius or Farenheit
;;assumptions:
;;    1. inputs a integer and C or F, C represents Celcius and F represents Farenheit
;;    2. The output will be the temperature and whether the temp is Celcius of Farenheit.

(defun convert (temp type)
  (cond ((equal "F" type) (F-to-C temp)) 
        ((equal "C" type) (C-to-F temp))        
  )
)

;;test plan for convert:
;;category/description                           data               expected result
;;===============================================================================
;;Celcius to Farenheit no remainder              0 "C"                  32
;;Celcius to Farenheit with remainder            73 "C"                 163
;;Farenheit to Celcius no remainder              32 "F"                 0
;;Farenheit to Celcius remainder                 42 "F"                 5 
;;Celcius to Farenheit no remainder              -40 "C"                -40
;;Farenheit to Celcius no remainder              -40 "F"                -40
;;Celcius to Farenheit no remainder              100 "C"                212
;;Farenheit to Celcius no remainder              212 "F"                100



;;**************************************************************************************
;;F-to-C function: Takes Farenheit and converts to Celcius
;;parameters:
;;    1st - a integer (the Farenheit value)
;;assumptions:
;;    1. The single input is Farenheit as a integer.
(defun F-to-C (temp)
 (cond (t (* (/ 5 9) (- (- temp 32) (mod (- temp 32) 9)))))
)
;;test plan for F-to-C:
;;category/description                           data               expected result
;;===============================================================================
;;Farenheit to Celcius no remainder              32                     0
;;Farenheit to Celcius remainder                 42                     5 
;;Farenheit to Celcius no remainder              -40                    -40
;;Farenheit to Celcius no remainder              212                    100
;;**************************************************************************************


;;**************************************************************************************
;;C-to-F function: Takes Celcius and converts to Farenheit
;;parameters:
;;    1st - a integer (the Celcius value)
;;assumptions:
;;    1. The single input is Celcius as a integer.
(defun C-to-F (temp)
 (cond (t (+ (* (/ 9 5) temp) (- 32 (/ (mod (* 9 temp) 5) 5) ))))
)
;;test plan for C-to-F:
;;category/description                           data               expected result
;;===============================================================================
;;Celcius to Farenheit no remainder              0                      32
;;Celcius to Farenheit with remainder            73                     163
;;Celcius to Farenheit no remainder              -40 "C"                -40
;;Celcius to Farenheit no remainder              100 "C"                212
;;**************************************************************************************
