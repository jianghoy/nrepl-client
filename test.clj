(require '[nrepl.core :as nrepl])
;;; Try to jack in using nrepl/nrepl-client to bb/nrepl-server

; this is the default nrepl code from doc:
; https://nrepl.org/nrepl/usage/clients.html
; meaning that simple stuff is supported

; on a second look, nrepl/connect must be 
(with-open [conn (nrepl/connect :port 1667)]
  (-> (nrepl/client conn 1000)    ; message receive timeout required
      (nrepl/message {:op "eval" :code "(+ 3 4)"})
      nrepl/response-values))


(def conn (nrepl/connect :port 1667))
(def client (nrepl/client conn 1000))
(def session (nrepl/new-session client))
(nrepl/message client {:op "eval" :code "(def session-specific \"session-specific\")" :session "c8187c2e-8178-42aa-af34-f49e42b0aeb9"})
(nrepl/message client {:op "eval" :code "(prn session-specific)"})


;;; Below is the same code but jack in onto nrepl/nrepl-server
(def nrepl-conn (nrepl/connect :port 64490))
(def nrepl-client (nrepl/client nrepl-conn 1000))
(def nrepl-persistent-session-defn (nrepl/new-session nrepl-client :clone))
(def nrepl-persistent-session-eval (nrepl/new-session nrepl-client :clone))
(nrepl/message nrepl-client {:op "eval" :code "(def session-specific \"session-specific\")" :session nrepl-persistent-session-defn})
(nrepl/message nrepl-client {:op "eval" :code "session-specific" :session nrepl-persistent-session-eval})