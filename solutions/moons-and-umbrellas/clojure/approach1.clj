;; the 'main' function
(defn cj-eval [s tot prev x y]
  ;; params: input string, total, previously evaluated char, x, y
  ;; first, check for an empty string
  (if (not= s "")
    ;; check if the current char isn't a '?'
    (if (not= (subs s 0 1) "?")
      ;; the "CJ" case, recurse with tot+x
      (if (= (str prev (subs s 0 1)) "CJ")
        (cj-eval (subs s 1) (+ tot x) (subs s 0 1) x y)
        ;; the "JC" case, recurse with tot+y
        (if (= (str prev (subs s 0 1)) "JC")
          (cj-eval (subs s 1) (+ tot y) (subs s 0 1) x y)
          ;; the "JJ" or "CC" case. recurse without adding
          (cj-eval (subs s 1) tot (subs s 0 1) x y)))
      ;; the '?' case. get the min value of the 'C' and 'J' path.
      ;; '?' can be viewed as a decision. we just split the road.
      (min
       (cj-eval (str "C" (subs s 1)) tot prev x y)
       (cj-eval (str "J" (subs s 1)) tot prev x y)))
    tot)) 

;; an example: (cj-eval "CJCCC" 0 "X" 2 3)

(defn -main[]
  (let [testnum (Integer/parseInt (read-line))]
    (loop [cnum 1]
      (when (not (> cnum testnum))
        (let [iv (clojure.string/split (read-line) #" ")]
          (println
           (str "Case #" cnum ": "
            (cj-eval
             (clojure.string/replace (nth iv 2) pat "?") 0 "X"
             (Integer/parseInt (nth iv 0))
             (Integer/parseInt (nth iv 1)))))
          (recur (+ cnum 1)))))))

(-main)
