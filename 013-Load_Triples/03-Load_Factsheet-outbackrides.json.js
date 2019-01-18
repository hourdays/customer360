'use strict'; 
declareUpdate(); 

const uri = '/factsheet/outbackrides.json' 
const cols = xdmp.documentGetCollections(uri); 
const obj = cts.doc(uri).toObject(); 

obj.factsheet.triples = [{ 
  triple: { 
    subject: 'http://procycling.com/resource/Outback Rides', 
    predicate: 'http://procycling.com/ontology/acquiredBy', 
    object: 'http://procycling.com/resource/Sam_Stone' 
  } 
}, { 
  triple: { 
    subject: 'http://procycling.com/resource/Outback Rides', 
    predicate: 'http://procycling.com/ontology/acquiredIn', 
    object: { 
      datatype: 'http://www.w3.org/2001/XMLSchema#string', 
      value: 2016 
    } 
  } 
}]; 

const result = xdmp.documentInsert(uri, obj, { collections: cols }); 
result === null ? 'Inserted' : `Error: ${result}`;