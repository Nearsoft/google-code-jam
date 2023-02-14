;; Function that gets a bit from the judge
(defun get-bit (idx)
  (if (not idx) 
      (progn (format t "~d~%" 1) ;; In case idx is nil
             (finish-output)
             (parse-integer (read-line))
             nil)
      (progn (format t "~d~%" (1+ idx)) ;; idx is a valid number
             (finish-output)
             (parse-integer (read-line)))))

;; Function to query a pair of bits
(defun get-pair (left-idx)
  (values (get-bit left-idx) (get-bit (- +number-length+ left-idx 1)))) ;; Return the left bit an its counterpart

; Function that returns true if left and right are symmetric (equal)
(defun symmetricp (left right)
  (= left right))

;; Function that checks if the digit has changed after fluctuation
(defun digit-changed (old-value new-value)
  (/= old-value new-value))

;; Function that returns the type of fluctuation when we have both pairs
(defun get-fluctuation-sym-asym (old-sym-value old-asym-value new-sym-value new-asym-value) 
    (cond 
        ((and (/= old-sym-value new-sym-value) 
              (/= old-asym-value new-asym-value))
            ; Symmetrical AND asymmetrical changed
            "C")
        ((/= old-sym-value new-sym-value) ; Only symmetrical changed
            "CR")
        ((/= old-asym-value new-asym-value) ; Only asymmetrical changed
              "R")
        (t "N")) ; Neither changed
  )

;; Function that returns the fluctuation type when we have only a symmetrical pair
(defun get-fluctuation-sym (old-sym-value new-sym-value)   
  (if (/= old-sym-value new-sym-value)
      "C"
      "N"))

;; Function that returns the fluctuation type when we have only an asymmetrical pair
(defun get-fluctuation-asym (old-asym-value new-asym-value)
  (if (/= old-asym-value new-asym-value)
      "C"
      "N"))

;; Function that gets the fluctuation type, calls helper functions
(defun get-fluctuation-type (old-sym-value old-asym-value new-sym-value new-asym-value ) 
  (cond ( (and old-sym-value old-asym-value) ; We have both pairs stored
          (get-fluctuation-sym-asym old-sym-value old-asym-value new-sym-value new-asym-value)) ; Call helper function
          ; We only have one pair
          ( old-sym-value ; We only have sym
            (get-fluctuation-sym old-sym-value new-sym-value) ; Call helper function
         )
         ; We only have asym
          (t (get-fluctuation-asym old-asym-value new-asym-value)) ; Call helper function 
  )
)

;; Function that changes 0s to 1s and viceversa   .VERIFICAR CUANDO ELEMENTO DE ARR ES NIL
(defun complement-array (arr) ; bit index)
  (loop for i from 0 to (- +number-length+ 1) do ; Loop through the array
        (if (eql (aref arr i) 0) ; If the current bit is a 0
            (setf (elt arr i) 1) ; Set the bit to a 1
            (setf (elt arr i) 0) ; Set the bit to a 0
)))

;; Function that updates our stored array
(defun update-array (arr bit-index fluct-type)
  (cond ( (equal fluct-type "C") ; If the fluctuation type is a C
          (complement-array arr ) ; We complement the array
        )
        ( (equal fluct-type "R") ; If the fluctuation type is an R
          (setf arr (nreverse arr)) ; We reverse the array
        )
        ( (equal fluct-type "CR") ; If the fluctuation type is a CR
          (complement-array arr) ; We complement the array
          (setf arr (nreverse arr)) ; We also reverse the array
        )
        (t nil) ; We leave our array the same
  )  
)

;; Helper function to convert an array to a list
(defun array-to-list (array)
  (map 'list #'identity array))

;; Helper function that splits the input into ints
(defun split-space (s)
  (let ( (space-idx (position #\Space s)))
        ;Return inputs as ints
       (values (parse-integer (subseq s 0 space-idx)) (parse-integer (subseq s (1+ space-idx))) ))) 

;; Function that solves a test case
(defun solve-test-case (length)
      ;; Solve cases
      (defconstant +number-length+ length) ; Define constant length of arrays
      (defparameter arr (make-array +number-length+ :initial-element nil)) ; Create empty array of the length inputed
      ; Declare symmetrical and asymmetrical indexes, number of queries and current index
      (let ((sym-index nil) 
            (asym-index nil)
            (queries 0)
            (bit-index 0)) 
          (loop
              (when (= bit-index (floor (/ +number-length+ 2))) (return)) ; Loop through the half of the array
              (multiple-value-bind (left right) (get-pair bit-index) ; Declare left and right values
                  (setf queries (+ queries 2)) ; Add 2 to the queries
                  (if (or (not sym-index) (not asym-index)) ; If we dont have a sym or asym pair
                      (if (symmetricp left right) ; Check if its symmetrical
                          (if (not sym-index) (setf sym-index bit-index)) ; Set sym index, if we dont have one
                          (if (not asym-index) (setf asym-index bit-index)))) ; Set asym index, if we dont have one

                  (if (and (= (mod queries 10) 2) (/= bit-index 0)) ; Check if a fluctuation happened
                      (progn 
                        (let ((new-sym-value (get-bit sym-index)) ; Get fluctuation and update accordingly
                            (new-asym-value (get-bit asym-index))
                            (old-sym-value (if (not sym-index) nil (elt arr sym-index)))
                            (old-asym-value (if (not asym-index) nil (elt arr asym-index))))
                           (update-array arr
                                         bit-index
                                         (get-fluctuation-type old-sym-value 
                                                               old-asym-value 
                                                               new-sym-value 
                                                               new-asym-value)))
                        (setf queries (+ queries 2))) ; Add 2 to the queries
                  )
                  (setf (elt arr bit-index) left) ; Set new left value
                  (setf (elt arr (- +number-length+ bit-index 1)) right) ; Set new right value
                  (setf bit-index (1+ bit-index)) ; Add one to the current index
                  ))
          (format t "~{~d~}~%" (array-to-list arr)))) ; Print array
           
(defun main ()          
  (setf in (read-line)) ; Read input
  (multiple-value-bind (test-cases length) (split-space in) ; Format input into test-cases and length
    (loop for test from 0 to (- test-cases 1) do ; Loop test-cases times
      (solve-test-case length) 
      (setf result (read-line))
      (if (equal result "N") (return))) ; If we got a wrong answer, stop the program
    ))

(main)