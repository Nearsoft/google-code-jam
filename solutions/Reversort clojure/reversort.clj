(ns clojure-sandbox.reversort)

;Splits the input by whitespace so it can be accepted
(defn split-by-whitespace [s]
  (clojure.string/split s #"\s+"))

;It recieves de outcome from console at parse it to int
(defn input []
  (let [userKeyBoardInput [(split-by-whitespace (read-line))]]
    (def x (into [] (map #(Integer/parseInt %) (into [] (flatten [userKeyBoardInput]))))) x))

;(2) the swap is made here, it receives the array, i and j
(defn rev [arr i j]
  (into [] (concat
            (if (> i 0) (subvec arr 0 i))
            (reverse (subvec arr i (+ j 1)))
            (if (< j (count arr)) (subvec arr (+ j 1))))))

;(1)reversorft function, it receives 3 values to be initialialized, the array of values
;the total & the iteration-i, both will be 0... it's a recursive function, it will
;call itself in every iteration. The 'letl statement allows us to create 'local' vars
;in this case we receive j which is the index from the smallest number in every iteration
;then it will call the function rev to make the change and then calculate the total amount
;of work and increase i for a new iteration
(defn reversort [arr tot i]
  (if (< i (- (count arr) 1))
    (let [j (.indexOf arr (apply min (subvec arr i)))]
      (reversort (rev arr i j) (+ tot j (- i) 1) (+ i 1))) tot))

;main metod, it loops the process according to how many arrays the user will give
(defn -main []
  (def T (Integer/parseInt (read-line)))
  (loop [a 0]
    (if (< a T)
      (do
        (def N (Integer/parseInt (read-line)))
        (def v (reversort (input) 0 0))
        (println "Case #" (+ a 1) ":" v)
        (recur (inc a))))))

(-main)