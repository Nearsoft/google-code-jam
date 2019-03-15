(defn Sort [list2] 
    (def numList list2)
    (def done false)
    (while (not done) 
        (def done true)
        (def i 0)
        (dotimes[n (- (count numList) 2)]
            (if (> (nth numList i) (nth numList (+ i 2)))
                (do 
                    (def done false)
                    (def aux (nth numList i))
                    (def numList (assoc numList i (nth numList (+ i 2))))
                    (def numList (assoc numList (+ i 2) aux))
                )
            )   
            (def i (+ i 1))
        )
    )
    numList
)
(def checkCount 1)

(defn Check [list2]
    (def error false)
    (def i 0)
    (while (< i (- (count list2) 1))
        (if (> (nth list2 i) (nth list2 (+ i 1)))
            (do
                (def error true)
                (println "Case #" checkCount ":" i)
                (def i 9999999999999)
            )
        )
        (def i (+ i 1))
    )
    (if (not error)
        (println "Case #" checkCount ": OK")
    )
    (def checkCount (+ checkCount 1))
)

(defn things []
    (println "how long the array? ")
    (def arrayLen (read-string (read-line)))
    (def tarArray [])
    (dotimes [n arrayLen] 
        (println "value: ")
        (def tarArray (conj tarArray (read-string (read-line))))  
    )
)
(defn UserMain []
    (println "how many cases you want? ")
    (def T (read-string (read-line)))
    (dotimes [n T]
        (things)
        (Check (Sort tarArray))
    )
)


(def x [8 9 7]) ;my first numList :)'
(def x2 [5 6 8 4 3])

(UserMain)