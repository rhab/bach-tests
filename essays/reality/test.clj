^{:nextjournal.clerk/visibility {:code :hide}}
(ns reality.introduction
  "The first essay in the Road to Reality series."
  {:nextjournal.clerk/toc true}
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

;; ## Álgebra básica
(simplify (+ 'x 'x))
;; => (* 2 x)

;; ## Cálculo
(defn f1 [x]
  (+ (* 1/120 (expt x 5))
     (* -1/6 (expt x 3))
     x))
;; => #'reality.introduction/f

(f1 'x)
;; => (+ (* 1/120 (expt x 5)) (* -1/6 (expt x 3)) x)

;; [^viewers]: Using code from the
;;     [emmy-viewers](https://github.com/mentat-collective/emmy-viewers) library.

(plot/of-x f1 {:color (:blue plot/Theme)})

(plot/mafs
 {:height 400}
 (plot/cartesian)
 (plot/of-x f1 {:color (:blue plot/Theme)})
 (plot/of-x (D f1) {:color (:green plot/Theme)})
 (plot/of-x ((square D) f1) {:color (:violet plot/Theme)}))

;; ## Pruebas sección "Our Notations"

;; con un solo parámetro, cube se autocompone
((cube sin) 2)
((compose cube sin) 2)

;; pero con mas de un parámetro, square no se autocompone
;; (square cube sin) esto da error
(def g
  (compose square cube sin))

(g 2)

;; Esta como sería en función ?
(* (cube 2) (sin 2))
;; => 7.274379414605454

;; aqui si se autocompone porque * espera dos parámetros
((* cube sin) 2)
;; => 7.274379414605454

(simplify ((+ (square sin) (square cos)) 'x))
;; => 1

(sqrt -2)
;; => #emmy/complex [0.0 1.4142135623730951]

(def r (literal-function 'r) )

(r 'y)
;; => (r y)

(def s (literal-function 's '(-> Real (X Real Real))))

(s 'x)
;; => (up (g↑0 x) (g↑1 x))

(def h (literal-function 'h '(-> (X Real Real Real) Real)))

(h 'x 'y 'z)
;; => (h x y)

;; ## Tuplas

;; Vector columna
(up 'x 'y 'z)
;; Vector fila 
(down 'x 'y 'z)
(def v1 (up 1 2 3))
(def v2 (down 4 5 6))
(* v1 v2)

;; ## Transformaciones lineales

(def pi2 (/ pi 2))
(def matriz
  (down (up (cos pi2) (sin pi2))
        (up (- (sin pi2)) (cos pi2) )))

(* matriz (up 1 1))

(* (down 1 1) (* matriz (up 1 1)))

;; ## Partial Derivaties


(defn x [r theta]
  (* r (cos theta)))

(defn y [r theta]
  (* r (sin theta)))


;; (def f_1 (compose h x y))

;; ((D f_1) 'x 'y 'a 'b)
;;
(def H (literal-function 'H '(-> (UP Real Real) Real)))

((D (compose H (up x y ))) 'a 'b)

;;En R^3 -> R. Superficie.
(let [f (literal-function 'f '(-> (UP Real Real Real) Real))
      x (literal-function 'x '(-> (X Real Real ) Real))
      y (literal-function 'y '(-> (X Real Real ) Real))
      z (literal-function 'z '(-> (X Real Real ) Real))
      ]
((D (compose f (up x y z))) 'u 'v ))

;; ## Cambio de Bases


(let [Q (matrix-by-rows
          '(0 1 0 -1)
          '(0.5 0.5 -1 1)
          '(1 0 -1 1)
          '(0 1 -1 0))
      TB (matrix-by-rows
          '(2 0 0 0)
          '(0 2 0 0)
          '(0 0 4 1)
          '(0 0 0 4))
      A (matrix-by-rows
         '(2 -4 2 2)
         '(-2 0 1 3)
         '(-2 -2 3 3)
         '(-2 -6 3 7))
      v1 (matrix-by-cols
          '(1 0 0 0))]
  (clerk/tex (->TeX Q))
  (clerk/html (* Q TB (invert Q)))
  (* A v1))

;; # Pruebas mafs
(def horas [62 141 234 392])
(clerk/plotly {:data [{:x ["Año 2020" "Año 2021" "Año 2022" "Año 2023"] :y horas
                       :type "bar"
                       :text horas
                       :marker {:color "rgb(158,202,225)"
                                :opacity 0.6
                                :line {:color "rgb(8,48,107)"
                                       :width 1.5 }}}]
               :layout {:title "Trabajo de Grado -- Horas/Hombre por Año (Total 829 h/h)"
                        :barmode "stack"}})
