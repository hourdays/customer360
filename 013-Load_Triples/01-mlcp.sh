#!/bin/bash
/Users/hjournea/Library/mlcp-9.0.7/bin/mlcp.sh import -host localhost -port 8055 -username admin -password admin -mode local -input_file_path ../data/triples/triples.nt -input_file_type RDF -output_uri_prefix /triplestore/
