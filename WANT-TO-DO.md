# Want to do

The `nrepl/client` doesn't run on `babashka/small-clojure-interpreter`, we need
to either make the client work, or make bb compatible with `nrepl` (since they
don't split between client and server, may be worthwhile to make both work).

Let's list the ideal level:
1. bb can run `nrepl` fully, both client and server (today both are not running)
2. since today bb has its own nrepl-server, make nrepl-client as much compatible
with bb/nrepl-server as possible.
    a. And see how many middleware can be attached onto bb/nrepl-server along the
    way.
3. have nrepl client running from clj not from sci. But one may argue this is even
worse than discard bb as a whole, obviously the charm of bb is it's portable.

Let's aim at lv2 for now. Which should simplify the scope, and obviously if we
already have bb/nrepl-server, then nrepl-client would also work regardless.

Since this is for `mjs-ns` we should aim as much compatible with bb as possible,
not try to tweak bb to fit `mjs-ns`, which will be the next phase of mjs: 



Need to happen:
1. Can send to nrepl server various messages. Right now 