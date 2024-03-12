^{:nextjournal.clerk/visibility {:code :hide}}
(ns reality.introduction
  "The first essay in the Road to Reality series."
  {:nextjournal.clerk/toc true
   :nextjournal.clerk/visibility {:code :show :result :show}}

  (:refer-clojure
   :exclude [+ - * / zero? compare divide numerator denominator
             infinite? abs ref partial =])
  (:require [emmy.env :refer :all]
            [emmy.mafs :as plot]
            [emmy.viewer :as ev :refer [with-let]]
            [mentat.clerk-utils.viewers :refer [q]]
            [nextjournal.clerk :as clerk]
            [reality.toroid :as toroid]
            [reality.viewer :as rv]))

^{::clerk/visibility {:code :hide :result :hide}}
(rv/install!)

;; # Ejercicios Forma Canónica de Jordan

;; ## Eje 1

{:nextjournal.clerk/visibility {:code :show :result :hide}}
(defn print-mat [mat]
  (clerk/tex (->TeX mat)))

; El operador original, la matriz A
(def A (matrix-by-rows
        '(2 -4 2 2)
        '(-2 0 1 3)
        '(-2 -2 3 3)
        '(-2 -6 3 7)))

; La forma de Jordan de A
(def A' (matrix-by-rows
         '(2 0 0 0)
         '(0 2 0 0)
         '(0 0 4 1)
         '(0 0 0 4)))

; La base de jordan para A'
(def B' (matrix-by-rows
         '(  0   1  0  1)
         '(0.5 0.5  1  0)
         '(  1   0  1  0)
         '(  0   1  1  1)))


(def e1 (matrix-by-cols
         '(1 0 0 0)))
(def e2 (matrix-by-cols
         '(0 1 0 0)))
(def e3 (matrix-by-cols
         '(0 0 1 0)))
(def e4 (matrix-by-cols
         '(0 0 0 1)))

{:nextjournal.clerk/visibility {:code :show :result :show}}

;;Los vectores elementales en la base B' son

(def e1_B' (/ e1 B'))
(def e2_B' (/ e2 B'))
(def e3_B' (/ e3 B'))
(def e4_B' (/ e4 B'))

;; Por lo tanto la matriz de cambio de base de B a B' es:

^{::clerk/visibility {:code :hide :result :hide}}
(def QBB' (matrix-by-cols
           e1_B'
           e2_B'
           e3_B'
           e4_B'))

(print-mat QBB')

;; de hecho esta matriz es la inversa de B'

(print-mat (invert B'))

;; ### Ahora verifiquemos que A' genere los mismos vectores al transformar los vectores elementales.

(def Ae1 (* A e1))

;; Pasemos (2,-2,-2,-2) a la base B' usando la matriz de cambio de base QBB'

(print-mat (* QBB' Ae1))

;; Ahora transformemos e1_B' |==> A'e1_B'

(print-mat (* A' e1_B'))

;; ### Ahora con e2

(def Ae2 (* A e2))

;; Pasemos este resulado a la base B' usando la matriz de cambio de base QBB'

(print-mat (* QBB' Ae2))

;; Ahora transformemos e2_B' |==> A'e2_B'

(print-mat (* A' e2_B'))

;; ### Ahora con e3

(def Ae3 (* A e3))

;; Pasemos este resulado a la base B' usando la matriz de cambio de base QBB'

(print-mat (* QBB' Ae3))

;; Ahora transformemos e3_B' |==> A'e3_B'

(print-mat (* A' e3_B'))

;; ### Ahora con e4

(def Ae4 (* A e4))

;; Pasemos este resulado a la base B' usando la matriz de cambio de base QBB'

(print-mat (* QBB' Ae4))

;; Ahora transformemos e4_B' |==> A'e4_B'

(print-mat (* A' e4_B'))

;; ### Verificando que A y A' son similares

(print-mat (* (invert QBB') A' QBB'))

;; Ahora con la fórmula A' = B'^-1 * A * B'

(print-mat (* (invert B') A B'))
