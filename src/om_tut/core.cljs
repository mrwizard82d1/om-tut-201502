(ns ^:figwheel-always om-tut.core
    (:require[om.core :as om :include-macros true]
              [om.dom :as dom :include-macros true]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:list ["Lion" "Zebra" "Buffalo" "Antelope"]}))

;; I believe you must define the function `stripe` before it is used
;; by `om/root`. (But I'm uncertain. Something is ticking the back of
;; my brain.)

(defn stripe [text background-color]
  (let [st #js {:backgroundColor background-color}]
    (dom/li #js {:style st} text)))

(om/root
  (fn [data owner]
    (om/component (apply dom/ul #js {:className "animals"}
                         (map stripe (:list data)
                              (cycle ["#ff0" "#fff"])))))
  app-state
  {:target (. js/document (getElementById "app0"))})


