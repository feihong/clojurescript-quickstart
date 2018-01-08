(ns quickstart.core
  (:require [quickstart.hanzi]
            [quickstart.emoji]))


;; Initialization
(enable-console-print!)

(quickstart.hanzi/render)
(quickstart.emoji/render)
