;;; Function that swaps all S's with E's and vice versa
(defun invert-path(path)
    (let ((new-path nil)) ; Create a local variable for new list
    (loop for c across path do ; Loop across Lydia's path
        (if (string= c "S") ; If the char is an "S"
            (setf new-path (cons 'E new-path)) ; Add an E to the list
            (setf new-path (cons 'S new-path)) ; If not, add an S to the list
        )
    )
    (return-from invert-path (reverse new-path)) ; Return new list
))

;;; Function that solves a test case
(defun solve(casenum)
    (let ((maze-size (read))) ; Read the maze size
    (let ((path (read-line))) ; Read Lydia's path
    (format t "Case #~d: ~{~d~}~%" casenum (invert-path path))
)))

;;; Read the amount of test cases
(defvar *cases* (read))

;;; Run the solve function for each test case
(loop for x from 1 to *cases* do
    (solve x)
)