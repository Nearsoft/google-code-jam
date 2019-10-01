(require '[clojure.string :as str])

(defn troubleSort [unSortedArray]
  (let [even (sort (take-nth 2 unSortedArray))
        odd (sort (take-nth 2 (rest unSortedArray)))
        interleaved (if (not= (count even) (count odd)) 
          (cons (first even) (interleave odd (rest even)))
          (interleave even odd))
        ]
        (loop [i 0 [x & xs] interleaved]
          (cond
            (empty? xs) "OK"
            (> x (first xs)) i
            :else (recur (inc i) xs)))))

(def lines (line-seq (java.io.BufferedReader. *in*)))

(let [[T & cases] lines
      T (read-string T)] ; First line of input contains T, the amount of cases.
  (loop [[N-str V-str & ls] cases
          i 1]
    (let [N (read-string N-str)
          V (map read-string (str/split V-str #" "))]
      (println (str "Case #" i ": " (troubleSort V)))
      (if (< i T) (recur ls (inc i))) 
      )))