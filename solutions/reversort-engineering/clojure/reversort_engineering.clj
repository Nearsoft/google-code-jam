(ns clojure.examples.hello
	(:gen-class))
; This is require for get the input and give them a format    
(require '[clojure.string :as str] '[clojure.edn :as edn])

;This function is for check that (C < N - 1) or 
;(C > ((N * (N+1) / 2) -1))
(defn Nmax [n]
    (-(/ (* n (+ n 1))2)1))
(defn solve [N C] 
    (if(or (< C (- N 1)) (> C (Nmax N)))
        true false))

; This function is for get a vector with N values from 1 to N   
(defn newL [N]
    (for [x (range 1 (+ N 1))] (int x)))

;This fuction is for print all the items from a vector    
(defn printv [items]
        (println (clojure.string/join " " items)))

;This function is for reverse the values in a vector l as long as index i 
;is greater than index j 
(defn rev[l i j]
    (def x i)
    (def y j)
    (def lista l)
    (while (< x y)
        (def buf1 (get lista x))
        (def buf2 (get lista y))
        (def lista (assoc lista x buf2))
        (def lista (assoc lista y buf1))
        (def x (+ x 1))
        (def y (- y 1))))

;function that evaluate the values C and N and get a result
(defn result [C n]
    (def L (into [] (newL n)))
    (def costo C)
    (loop [i (- n 2)]
        (when (>= i 0)
            (def Ci (min (- costo i) (- n i)))
            (def costo (- costo Ci))
            (rev L i (- (+ Ci i) 1))
            (recur (- i 1))))
    (printv lista))

;First part of the program, get the number of test cases
(def T (Integer/parseInt (read-line)))
;Define a vector for the test cases values
(def V [])

;This loop is for get all the values from the input 
(loop [x T]
    (when (> x 0)
        (let [input-line (read-line)
              num-strs   (str/split input-line #"\s+")
              nums       (mapv edn/read-string num-strs)]
            (def V (conj V nums)))
        (recur (- x 1))))

;This loop is for evaluate each case from vector V
(loop [s 0]
    (when (< s T)
        (def v (get V s))
        (def C (get v 1))
        (def n (get v 0))
        (print (format "case #%d: " (+ s 1)))
        (if (solve n C) 
            (println "IMPOSSIBLE")
            (result C n))
        (recur (+ s 1))))