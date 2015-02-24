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

;; I must define `middle-name` before its use by `display-name`.
;; This function returns `nil` if its argument has neither a `:middle`
;; nor a `:middle-initial` key. 
(defn middle-name [{:keys [middle middle-initial]}]
  (cond
    middle (str " " middle)
    middle-initial (str " " middle-initial ".")))

;; I must define `display-name` before its use by `contact-view`.
(defn display-name [{:keys [first last] :as contact}]
  ;; Remember that `(str nil)` returns an empty string.
  (str last ", " first (middle-name contact)))

;; I must define `contact-view` before its use by `contacts-view`.
(defn contact-view [contact owner]
  (reify
    om/IRender
    (render [this]
      (dom/li nil (display-name contact)))))

(defn contacts-view [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/div nil
               (dom/h2 nil "Contact list")
               (apply dom/ul nil
                      (om/build-all contact-view
                                    (:contacts data)))))))

(om/root
  contacts-view
  app-state
  {:target (. js/document (getElementById "contacts"))})


