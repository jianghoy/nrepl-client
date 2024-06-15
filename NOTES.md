1. Based on [this](https://nrepl.org/nrepl/usage/tls.html) it seems TLS is just for cross-host jack-in.
2. It seems session doesn't retain split all remote states, meaning that: 
    1. it's a middleware of nREPL; so if you `(def foo "bar")` in one session, 
    another session still has it, which makes sense otherwise all simple eval 
    without session will never work; 
    2. it's mainly used for: retaining a set of [dynamic vars](https://nrepl.org/nrepl/design/middleware.html#sessions)
    inside nrepl:
        > This allows you to have a different value for *e from different REPL clients (e.g. two separate REPL-y instances)

        and

        > Sessions become even more useful when different nREPL extensions start taking advantage of them. debug-repl uses sessions to store information about the current breakpoint, allowing debugging of two things separately. piggieback uses sessions to allow host a ClojureScript REPL alongside an existing Clojure one.
    3. Meaning e.g. each branch of project should have it's own nREPL since the
    diff of states cannot be retained on the same nREPL. In real world probably
    no one gonna try that anyway.