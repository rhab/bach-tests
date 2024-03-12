^{:nextjournal.clerk/visibility
  {:code :hide}}
(ns reality.index
  {:nextjournal.clerk/toc true}
  (:require [reality.viewer :as rv]
            [nextjournal.clerk :as clerk]))

;; # Análisis de Brauer a "Canon Perpetuus super thema regium"

;; ## Palabras

(def w_1 '("d_0^8" "e_0^4" "sigma_-1f^4" "varphi_4e^8" "d^4" "sigma_-1varphi_4c^8" "b^4" "varphi_4a^8" "g^2" "a^2"))
(def  w_2 '("b^4" "sigma_-1c^4" "b^4" "a^4" "g^4" "e^4" "f^4" "sigma_-1g^4" "a^4" "sigma_-1g^4" "a^4" "b^4" "sigma_-1c^4" "a^4" "b^4" "sigma_-1c^4"))
(def  w_3 '("f^16" "d_0^8" "b^8" "e^8" "sigma_-1f^4" "g^4" "a^16"))
(def  w_4 '("a^8" "b^4" "c^4" "d^8" "sigma_1d^8" "varphi_8e^16" "f^4" "g^4"))
(def  w_5 '("a^32" "a^8" "g^8" "d_0^8" "d^8"))
(def  w_6 '("varphi_4d^8" "sigma_-1f^4" "varphi_4e^8" "d^4" "sigma_-1varphi_4c^8" "b^4" "varphi_4a^8" "g^2" "a^2"))
(def  w_7 '("b^4" "sigma_-1c^4" "b^4" "a^4" "g^4" "e^4" "f^4" "sigma_-1g^4" "a^4" "sigma_-1g^4" "a^4" "b^4" "sigma_-1c^4" "a^4" "b^4" "sigma_-1c^4"))
(def  w_8 '("f^16" "d_0^8" "b^8" "e^8" "sigma_-1f^4" "g^4" "a^16"))
(def  w_9 '("a^8" "b^4" "c^4" "d^8" "sigma_1d^8" "varphi_8e^16" "f^4" "g^4"))
(def  w_10 '("a^32" "a^8" "g^8" "d_0^8" "d^8"))
(def  w_11 '("varphi_4d^8" "sigma_-1f^4" "varphi_4e^8" "d^4" "sigma_-1c^32"))

(def w (vector w_1 w_2 w_3 w_4 w_5 w_6 w_7 w_8 w_9 w_10 w_11))
(count w)
(count (flatten w))

(def sec-suc (distinct (flatten w) ))
(count sec-suc)
(def w-freq (frequencies (flatten w)))

;; ## Valencias

(def valencias (map #(hash-map %1 (w-freq %1)) sec-suc))

(defn suc-nota [lista nota id-pal]
  (cond
    (empty? lista) lista
    (= (first lista) nota) (cons (str "w_" id-pal) (suc-nota (rest lista) nota id-pal))
    :else (suc-nota (rest lista) nota id-pal)
    ))

(defn recorrer-vectores [lista nota pos]
  (cond
    (empty? lista) lista
    :else (cons (suc-nota (first lista) nota pos) (recorrer-vectores (rest lista) nota (inc pos)))
    ))

(defn genfn-seq-suc [w]
  (fn [nota] {nota (flatten (recorrer-vectores w nota 1))}))


;; ## Secuencia de Sucesores
(def sucesores (map (genfn-seq-suc w) sec-suc))

