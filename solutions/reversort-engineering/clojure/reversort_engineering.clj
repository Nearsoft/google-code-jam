(ns clojure.examples.hello
	(:gen-class))
; This is require for get the input and give them a format    
(require '[clojure.string :as str] '[clojure.edn :as edn])

;This loop is for get all the values from the input 
;(loop [x N]
;    (when (> x 0)
;        (let [input-line (read-line)
;              num-strs   (str/split input-line #"\s+")
;              nums       (mapv edn/read-string num-strs)]
;            (def V (conj V nums)))
;        (recur (- x 1))))
        
;This function is for get the j values
;;TODO: Remove the part of get a random number
(defn cost [x n]
    (def C x)
    (def C1 (+ 2 (- C n)))
    (loop [i 0]
        (when (< i (- n 1))
            (if (> C1 0) 
                (def Ci (+ 1 (rand-int C1)))
                (def Ci 0))
            (println Ci)
            ;j no pude ser mayor que n
            (if (> (+ Ci i) n) (def Ci (- n i)))
            (println "Ci es:" Ci)
            (def C1 (- C1 Ci))
            (def j (+ i Ci))
            (def js (conj js j))
            (println j (+ i 1) Ci C1)
            (recur (+ i 1))
            )))

;This function is for check that (C < N - 1) or 
;(C > ((N * (N+1) / 2) -1))
(defn Nmax [n]
    (-(/ (* n (+ n 1))2)1))
(defn solve [N C] 
    (if(or (< C (- N 1)) (> C (Nmax N)))
        (println "case x: IMPOSSIBLE")))

; This function is for get a vector with N values from 1 to N   
(defn newL [N]
    (for [x (range 1 (+ N 1))] (int x)))
    
;Create a new vector [1 2 3 4 5]
(def l (into [] (newL 5)))

;This function is for swap the values in index j and i in a vector l
(defn operate [l i j]
    (println i j l)
    (def b1 (get l j))
    (def b2 (get l i))
    (swap! l (assoc l i b1))
    (println l)
    (swap! l (assoc l j b2))
    (println l))
    
;(def V '([0 2] [1 3]))    
;(println (for [[x y] V]
;    (def l (operate l x y))))
;(for [x '(1 2 3 4 5)]
;    (println x))
;(println (for [x [1 2 3]] (+ x 5)))
    
;(println l)
