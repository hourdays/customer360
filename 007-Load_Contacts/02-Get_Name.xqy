let $company-name := "Campus Bikes" 

let $co-results := 
  for $co-doc in /contact[co/text()=$company-name] 
  let $co-doc-first := $co-doc/name/first 
  let $co-doc-last := $co-doc/name/last 
  return fn:concat($co-doc-first/text(), " ", $co-doc-last/text()) 

let $company-results := 
  for $company-doc in /contact[company/text()=$company-name]
  let $company-doc-first-name := $company-doc/first-name
  let $company-doc-last-name := $company-doc/last-name
  return fn:concat($company-doc-first-name/text(), " ", $company-doc-last-name/text())

return ($co-results, $company-results)