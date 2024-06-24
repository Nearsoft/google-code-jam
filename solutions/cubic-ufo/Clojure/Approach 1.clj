(ns my.ns
  (:gen-class))
(require '[clojure.string :as str])
(defn Deg2Rad [Degs](* Degs (/ Math/PI 180))) ;; 
(defn printVector [x] 

    (loop [n x]
   (when (seq n)
   (print (str  (first n) ))
    (if (< 1 (count n) )
    (print " ")
    )
    

   (recur (rest n) )))
   
);;Convierte grados a radianes

(defn Rad2Deg [Rads](* Rads (/ 180 Math/PI))) ;; Radianes a grados
(defn Angle [Area];; La formula mamalona que calculamos
  (- (Math/asin (/ Area (* 2 (Math/sin (Deg2Rad 60)))))  (Math/asin (/ 1 (Math/sqrt 3))) ) ;; Por confirmar la precision de este angulo  
)

(defn TurnUFO [case maxcases] ;; La funcion que te da la posicion final de las caras en base a el Area
 (flush)
 (def areaStr (read-line))
 (def Area (bigdec areaStr))
 (def Theta (Angle Area))
    ;; FX
   (def FX [(* 0.5 (Math/sin (Deg2Rad 45)))
     (* (* 0.5 (Math/sin (Deg2Rad 45))) (Math/sin Theta))
     (* (* -0.5 (Math/sin (Deg2Rad 45))) (Math/cos Theta)) ]
    ) 

    ;; FY
    (def FY[ 0 
     (* 0.5 (Math/cos Theta)) 
     (* 0.5 (Math/sin Theta))]
    )

    ;; FZ
    (def FZ[(* 0.5 (Math/sin (Deg2Rad 45)))
     (* (* -0.5 (Math/sin (Deg2Rad 45))) (Math/sin Theta))
     (* (* 0.5 (Math/sin (Deg2Rad 45))) (Math/cos Theta))] 
    )
  
(println (str "\nCase #" case ":"  ))
(printVector FX)
(print "\n")
(printVector FY)
(print "\n")
(printVector FZ)
(print "\n")

(if (< case maxcases) (TurnUFO (inc case) maxcases))
  
)

(defn calcula []

  (def casesStr (read-line))
  (flush)
  (def numCases (Integer/parseInt casesStr))
  (if (> numCases 0) (TurnUFO 1 numCases))   
)
(calcula)

