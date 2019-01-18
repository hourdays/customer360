'use strict';

// step 1
fn.doc('/marketing/contact-cs003.xml');


// step 2
declareUpdate();
xdmp.nodeInsertBefore(cts.doc('/marketing/contact-cs003.xml').xpath('/envelope/source'),
    fn.head(xdmp.unquote('<canonical></canonical>')).root);

// step 3
declareUpdate();
const doc = cts.doc('/marketing/contact-cs003.xml');
const company = doc.xpath('/envelope/source/contact/company/text()');
const firstName = doc.xpath('/envelope/source/contact/first-name/text()');
const lastName = doc.xpath('/envelope/source/contact/last-name/text()');

xdmp.nodeInsertChild(cts.doc('/marketing/contact-cs003.xml').xpath('/envelope/canonical'),
    fn.head(xdmp.unquote('<company>' + company + '</company>')).root);
xdmp.nodeInsertChild(cts.doc('/marketing/contact-cs003.xml').xpath('/envelope/canonical'),
    fn.head(xdmp.unquote('<firstName>' + firstName + '</firstName>')).root);
xdmp.nodeInsertChild(cts.doc('/marketing/contact-cs003.xml').xpath('/envelope/canonical'),
    fn.head(xdmp.unquote('<lastName>' + lastName + '</lastName>')).root);

// step 4
declareUpdate();
xdmp.documentSetCollections('/marketing/contact-cs003.xml', ['canonical']);

// step 5
declareUpdate();
const docs = cts.search(cts.andQuery(
            [cts.collectionQuery('envelope'),cts.elementValueQuery('co', 'Campus Bikes')]));

for (const doc of docs) {   
    const co = doc.xpath('envelope/source/contact/co/text()');
    const first = doc.xpath('envelope/source/contact/name/first/text()');
    const last = doc.xpath('envelope/source/contact/name/last/text()');

    xdmp.nodeInsertBefore(doc.xpath('envelope/source'),
    fn.head(xdmp.unquote(
        `<canonical> 
           <company>${co}</company>
           <firstName>${first}</firstName>
           <lastName>${last}</lastName>
        </canonical>`
        )
    ).root);

    xdmp.documentSetCollections(fn.baseUri(doc), 'canonical')
};

// step 6
declareUpdate();
xdmp.nodeInsertBefore(cts.doc('/marketing/contact-cs003.xml').xpath('/envelope/source'),
    fn.head(xdmp.unquote('<metadata><leadSource>2017 Los Angeles Cycle Show</leadSource><leadDate>2017-04-15</leadDate><collectedBy>Raffle bowl</collectedBy></metadata>')).root);
