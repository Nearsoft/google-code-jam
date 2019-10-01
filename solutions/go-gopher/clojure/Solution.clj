(use '[clojure.string :only (split triml)])

(defn parseInt [s]
  (Integer. (re-find #"[0-9]*" s)))
(defn abs [n] (max n (- n)))


(defn updateTargets [targets [tx ty]]
    ;(compare-and-set! targets @targets (update @targets target - 1))
    (loop [[[x y :as key] & ks] (keys @targets)]
        (do 
            (if (and (<= (- x 1) tx (+ x 1)) (<= (- y 1) ty (+ y 1)))
                (swap! targets assoc key (dec (get @targets key))))
            (if (>= (count ks) 1) (recur ks))
        )
))

(defn getBestTarget [targets ]
    
   (key (apply max-key val @targets)) 
)

(defn contains [v o]

    (not(= -1 (.indexOf v o))

))


(defn startShooting [targets]
    (def x -1)
    (def y -1)
    (def alreadyPrepared (atom []))
    
  (while (and 
              (not (= x 0))
              (not (= y 0))
          ) (do
                  (let [[x y] (getBestTarget targets)]
                    (println x y)
                  )
                  
                  (def input (split (read-line) #"\s"))
                  (def x (parseInt (nth input 0 )))
                  (def y (parseInt (nth input 1 )))
                  (if (not(contains @alreadyPrepared [x y] ))
                      (do
                          (swap! alreadyPrepared conj [x y])
                          (updateTargets targets [x y])
                      )
                  )
              )
          )    
)


(defn setTargets [n m originaSize]
  (def targets (atom {}))
  (loop [i 2]
      (when (< i n)
      (do
        (loop [j 2]
          (when (< j m)
          (do
            (swap! targets assoc [i j] 9)
          )
          (recur (inc j))
          )
        )
      )
      (recur (inc i))))
  (startShooting targets)
)

(defn findSquareDimentions [a originalSize]
(def smallestDifference a)

   (dotimes [i a]
    (def iplus (+ i 1))
     (if
        (and 
             (= (mod a iplus) 0)
             (< (abs (- iplus (/ a iplus))) smallestDifference)
        )
        (do
            (def smallestDifference (abs (- iplus (/ a iplus))))
            (def n iplus)
            (def m (/ a iplus))
            
        )
    )
    )
 
  (if (or (< n 3) (< m 3)) (recur (+ a 1) originalSize)  (setTargets n m originalSize))
)

(defn main []
    
    (def t (parseInt(read-line)))
    (dotimes [times t]
             (def a (parseInt(read-line)))
             (def squareSize a)
             (findSquareDimentions  a a)
    )
)

(main)