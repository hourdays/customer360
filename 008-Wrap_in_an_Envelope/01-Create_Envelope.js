'use strict';
declareUpdate();
 
const uri = '/marketing/contact-cs003.xml';
const docroot = cts.doc(uri).root.xpath('/element()');
const root = xdmp.unquote(`<envelope><source>${cts.doc(uri).xpath('/contact')}</source></envelope>`);
 
if (fn.name(docroot) === 'envelope') {
  'envelope root node already exists'
} else {
  xdmp.documentInsert(uri, root, [], ['envelope']);
}