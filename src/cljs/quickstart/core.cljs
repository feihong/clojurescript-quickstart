(ns quickstart.core)

(enable-console-print!)

(defn random-hanzi []
  (let [start 0x4e00
        end 0x9fff
        ordinal (-> (- end start)
                    (inc)
                    (rand-int)
                    (+ start))]
    (str (char ordinal))))

(let [mesg (random-hanzi)
      el (js/document.querySelector "#message")]
  (println "Random hanzi" mesg)
  (aset el "textContent" mesg))
