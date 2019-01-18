declare variable $uri := "/marketing/contact-cs003.xml";

let $doc := fn:doc($uri)
let $envelope := <envelope><source>{$doc}</source></envelope>
where fn:count($doc/envelope) = 0
return xdmp:document-insert($uri, $envelope, (), "envelope")