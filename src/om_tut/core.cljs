(ns ^:figwheel-always om-tut.core
    (:require[om.core :as om :include-macros true]
              [om.dom :as dom :include-macros true]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello, Om World!"}))

(om/root
  (fn [data owner]
    (reify om/IRender
      (render [_]
        (dom/p nil (:text data)))))
  app-state
  {:target (. js/document (getElementById "app"))})


