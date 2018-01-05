(ns quickstart.core)

(enable-console-print!)

(defn random-hanzi []
  (let [start 0x4e00
        end 0x9fff
        ordinal (-> (- end start)
                    (inc)
                    (rand-int)
                    (+ start))
        hanzi (str (char ordinal))]
    (println "Random hanzi" hanzi)
    hanzi))

(defn set-hanzi []
  (let [hanzi (random-hanzi)
        el (js/document.getElementById "hanzi")]
    (set! (.-textContent el) hanzi)))

(let [btn (js/document.getElementById "generate-btn")]
  (set! (.-onclick btn) set-hanzi))

(set-hanzi)
