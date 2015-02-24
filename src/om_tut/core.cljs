(ns ^:figwheel-always om-tut.core
    (:require[om.core :as om :include-macros true]
              [om.dom :as dom :include-macros true]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state
  (atom
   {:contacts
    [{:first "Ben" :last "Bitdiddle" :email "benb@mit.edu"}
     {:first "Alyssa" :middle-initial "P" :last "Hacker"
      :email "aphacker@mit.edu"}
     {:first "Eva" :middle "Lu" :last "Ator" :email "eval@mit.edu"}
     {:first "Louis" :last "Reasoner" :email "prolog@mit.edu"}
     {:first "Cy" :middle-inital "D" :last "Effect"
      :email "bugs@mit.edu"}
     {:first "Lem" :middle-initial "E" :last "Tweakit"
      :email "morebugs@mit.edu"}]}))

(defn contacts-view [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/div nil
               (dom/h2 nil "Contact list")
               (apply dom/ul nil
                      (om/build-all contact-view
                                    (:contacts data)))))))

;; So much for my earlier ticklishness. I **must** define functions
;; before I use them. :)
;; When I save the file, figwheel compiles the code, but when it
;; encounters a warning, it refuses to load the code and so I see
;; errors. 
(defn contact-view [contact owner]
  (reify
    om/IRender
    (render [this]
      (dom/li nil (display-name contact)))))

(om/root
  contact-view
  app-state
  {:target (. js/document (getElementById "app0"))})


