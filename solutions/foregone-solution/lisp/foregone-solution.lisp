;;; Function that calculates the amount for each check
(defun get-check-amounts(num)
    (let ((a nil)) ; Create a local variable for the check a
    (let ((b nil)) ; Create a local variable for the check b
    
    (loop for c across num do ; Loop across the string
        (if (string= c "4") ; If we find a 4
            (progn
                (setf a (cons '2 a)) ; We add a 2 to list a
                (setf b (cons '2 b)) ; We add a 2 to list b
            )
            (progn ; If we dont find a 4
              (setf a (cons c a)) ; We add the number to list a
              (if b ; If b doesnt have a value
              ; We check this, so that there are no leading 0s
                (setf b (cons '0 b)) ; We add a 0 to list b
              )
            )  
        )
    )
    (values (reverse a) (reverse b)) ; We return both lists
    ))
)

;;; Function that solves a test case
(defun solve(casenum)
    (let ((num (read-line))) ; Read number
    (multiple-value-bind (a b) (get-check-amounts num) 
        (format t "Case #~d: ~{~d~} ~{~d~}~%" casenum a b))
))

;;; Read the amount of test cases
(defvar *cases* (read))

;;; Run the solve function for each test case
(loop for x from 1 to *cases* do
    (solve x)
)