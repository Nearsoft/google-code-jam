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
            (empty? xs) "OK."
            (> x (first xs)) i
            :else (recur (inc i) xs))))
  )
  
  (def lines (line-seq (java.io.BufferedReader. *in*)))

(let [[T & cases] lines] ; First line of input contains T, the amount of cases.
  (loop [[N-str V-str & ls] cases
        N (read-string N-str)
        V (map read-string (str/split V-str #" "))
        i 1]
          (println (str "Case #" i ": " (troubleSort V)))
          (recur N-str V-str ls (inc i))
          ))