^{:nextjournal.clerk/visibility {:code :hide}}
(ns reality.introduction
  "The first essay in the Road to Reality series."
  {:nextjournal.clerk/toc true
   :nextjournal.clerk/visibility {:code :show :result :hide}}

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

;; # Pruebas Emmy

;; ## Cambio de Bases

^{::clerk/visibility {:result :hide}}

(defn prim [mat]
  (clerk/tex (->TeX mat)))

^{::clerk/visibility {:result :hide}}
(def B' (matrix-by-rows
          '(  0   1  0  1)
          '(0.5 0.5  1  0)
          '(  1   0  1  0)
          '(  0   1  1  1)))
(def A' (matrix-by-rows
         '(2 0 0 0)
         '(0 2 0 0)
         '(0 0 4 1)
         '(0 0 0 4)))

(def A (matrix-by-rows
        '(2 -4 2 2)
        '(-2 0 1 3)
        '(-2 -2 3 3)
        '(-2 -6 3 7)))

(def e1 (matrix-by-cols
         '(1 0 0 0)))
(def e2 (matrix-by-cols
         '(0 1 0 0)))
(def e3 (matrix-by-cols
         '(0 0 1 0)))
(def e4 (matrix-by-cols
         '(0 0 0 1)))
(prim B')
(def e1_B' (/ e1 B'))
(def e2_B' (/ e2 B'))
(def e3_B' (/ e3 B'))
(def e4_B' (/ e4 B'))

;; (clerk/tex (->TeX (* Q TB (invert Q))))

;; (clerk/tex (->TeX (* A e1)))

;; (gauss-jordan-solve-linear-system Q (vector 1 0 0 0 0) )

;; e_1 en la base B

;; (pr (* TB e1_B))

;; ## Resolver ecuaciones

(def MAT1 (matrix-by-rows
           '(1 0 0 -3 0 0 -1 0 0 )
           '(0 1 0 0 -3 0  0 0 0 )
           '(0 0 1 0  0 -3 4 0 0 )
           '(2 0 0 1  0  0 0 -10 0)
           '(0 2 0 0  1  0 0  7  0)
           '(0 0 2 0  0  1 0 -7  0)
           '(1 0 0 -2 0  0 0  0  0)
           '(0 1 0  0 -2 0 0  0  -1)
           '(0 0 1  0 0 -2 0  0  6)))

(def MAT1b (matrix-by-cols
            '(0 0 -2 0 0 5 0 0 0)))

(prim (/ MAT1b MAT1))
