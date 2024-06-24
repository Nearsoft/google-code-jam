;;;;; Clojure script for Saving the Universe in Clojure
;;; https://codingcompetitions.withgoogle.com/codejam/round/00000000000000cb/0000000000007966
(require '[clojure.string :as str])

(defn damage [p]
  (first
    (reduce 
      ; Goes through the input program and tracks both accumulated damage
      ; and current damage per shot
      (fn [[dmg charge] c]
          (if (= c \S)
           [(+ dmg charge) charge]
           [dmg (* charge 2)]))
      [0 1] p))) ; we reduce into a vector with two things, but we only want the first

;;; Makes a single "hack" by moving the first c it finds to the right.
(defn flow-left [cs]
  (loop [[l c & t] (reverse cs)
         res '()]
    (cond
      (and (= l \S) (= c \C)) 
        (concat (reverse t) (list \S \C) res)
      (nil? c) nil
      :else (recur (cons c t) (cons l res)))))

(defn save-the-universe [D P]
  (loop [p P 
         hacks 0]
    (if (<= (damage p) D)
      hacks
      (if-let [new-p (flow-left p)]
        (recur new-p (inc hacks))
        "IMPOSSIBLE"))))

;; Line seq turns our stdin into a sequence of each line that's read in.
(def lines (line-seq (java.io.BufferedReader. *in*)))

(let [[T & cases] lines] ; First line of input contains T, the amount of cases.
  (doseq [[idx c] (map-indexed vector cases)]
    (let [[d-str p] (str/split c #" ") ; Each case contains two parts separated by spaces.
          d (read-string d-str) ; Converts d (max damage) into a number
          result (save-the-universe d p)]
      (println (str "Case #" (+ 1 idx) ": " result)))))

