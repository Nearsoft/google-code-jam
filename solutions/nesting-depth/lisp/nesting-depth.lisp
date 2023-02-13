(defun add_parenthesis (parenthesis s)
    "If parenthesis > 0 adds '(' parenthesis times. If < 0 adds ')' parenthesis times. 
     If parenthesis equals 0 return the unmodified string"
    (cond ((zerop parenthesis) s)
          ((plusp parenthesis) (add_parenthesis (1- parenthesis) (concatenate 'string s "(")))
          ((minusp parenthesis) (add_parenthesis (1+ parenthesis) (concatenate 'string s ")")))))

(defun nesting_depth (number_string final_string open_parenthesis)
    "Given a string of digits S, insert a minimum number of opening and closing parentheses
     into it such that the resulting string is balanced and each digit d is inside exactly d 
     pairs of matching parentheses."
    (if (equal number_string "")
        (add_parenthesis (* -1 open_parenthesis) final_string) ;; Close the last opened parenthesis
        (let* ((digit_number (digit-char-p (char number_string 0)))
               (digit_string (write-to-string digit_number)))
            (if (= digit_number open_parenthesis) ;; open parenthesis match the digit, hence concatenates the digit
                (nesting_depth  (subseq number_string 1)
                                (concatenate 'string final_string digit_string)
                                digit_number)
                (nesting_depth  (subseq number_string 1) ;; parnthesis needs to be open or close before concatenating the digit
                                (concatenate 'string (add_parenthesis (- digit_number open_parenthesis) 
                                                                      final_string)
                                                     digit_string)
                                digit_number)))))

(defun main ()
    (loop for test from 1 to (parse-integer (read-line)) do
        (setf result (nesting_depth (read-line) "" 0))
        (format t "Case #~d: ~d~%" test result)))
(main)