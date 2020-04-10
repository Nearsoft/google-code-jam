
; All commented code was used to make the status more visible.


(def TestCases (read-string(read-line)))
;(println TestCases" Test cases")
(dotimes [i TestCases]

    (def spacey1x1 0)
    (def spacey1x2 0)
    (def spacey1x3 0)
    (def spacey2x1 0)
    (def spacey2x2 0)
    (def spacey2x3 0)
    (def spacey3x1 0)
    (def spacey3x2 0)
    (def spacey3x3 0)

    (def row 2)

    (def desiredArea (read-string(read-line)))
;    (println " Area deseada:"desiredArea)
    (def calculatedClmns (/ (+ desiredArea 2) 3))
    (def columns (max 3 calculatedClmns))
    ;(println "calculated columns: "calculatedClmns)
;    (println "MAX columns: "columns)
    (def pointerUnfinishedClmn 1)
    (def endApp 0)    

    (while (== endApp 0) 

       (def nextColumn (+ pointerUnfinishedClmn 1))
       (def nextColumn (min nextColumn (- columns 1)))
        
    ;   (println "next column "nextColumn)

;       (println "Coordenadas enviadas:")
       (println row" "nextColumn)
    ;   (flush)
        (def coordX (read-string(read-line))) ;Pointer value of X
        (def coordY (read-string(read-line))) ;POinter value of Y

;        (println "")
;       (println "Coordenadas recibidas:")
       (println coordX)

        (if (== coordX 0) 
            (if (== coordY 0) 
                (def endApp 1) nil 
            ) nil 
        )

;                (println "Aun no termina")

        (if (== coordY pointerUnfinishedClmn)
            (do 
                (if (== coordX (- row 1)) 
                    (def spacey1x1 1) nil 
                ) 1
                (if (== coordX row) 
                    (def spacey1x2 1) nil 
                ) 
                (if (== coordX (+ row 1)) 
                    (def spacey1x3 1) nil 
                )

 ;               (println "revision columna izq")
            )nil
             
        )

        (if (== coordY (+ pointerUnfinishedClmn 1))
            (do
                (if (== coordX (- row 1)) 
                    (def spacey2x1 1) nil 
                ) 
                (if (== coordX row) 
                    (def spacey2x2 1) nil 
                ) 
                (if (== coordX (+ row 1)) 
                    (def spacey2x3 1) nil 
                ) 

  ;              (println "revision columna central")
            )nil
        )

        (if (== coordY (+ pointerUnfinishedClmn 2))
            (do    
                (if (== coordX (- row 1)) 
                    (def spacey3x1 1) nil 
                ) 
                (if (== coordX row) 
                    (def spacey3x2 1) nil 
                ) 
                (if (== coordX (+ row 1)) 
                    (def spacey3x3 1) nil 
                )

  ;              (println "revision columna derecha")
            ) nil
        )


        (if (== spacey1x1 1)
            (if (== spacey1x2 1)
                (if (== spacey1x3 1)
                    (do 

                    (def pointerUnfinishedClmn (+ pointerUnfinishedClmn 1))
                    
                    (def spacey1x1 spacey2x1)
                    (def spacey1x2 spacey2x2)
                    (def spacey1x3 spacey2x3)

                    (def spacey2x1 spacey3x1)
                    (def spacey2x2 spacey3x2)
                    (def spacey2x3 spacey3x3)

                    (def spacey3x1 0)
                    (def spacey3x2 0)
                    (def spacey3x3 0)
                    
                    ) nil
                    
                )nil
            )nil
        )


 ;       (println "Matriz de seleccion:")
        
 ;       (println "Y:"pointerUnfinishedClmn""(+ pointerUnfinishedClmn 1)""(+ pointerUnfinishedClmn 2))
 ;       (println "  "spacey1x1""spacey2x1""spacey3x1)
 ;       (println "  "spacey1x2""spacey2x2""spacey3x2)
 ;       (println "  "spacey1x3""spacey2x3""spacey3x3)
        

     )
    (println "FIN DE RELLENO")
)


