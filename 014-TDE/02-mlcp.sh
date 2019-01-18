#!/bin/bash
/Users/hjournea/Library/mlcp-9.0.7/bin/mlcp.sh import -host localhost -port 8055 -username admin -password admin -mode local -input_file_path ../data/accounting-source02 -output_uri_replace "^.*/,'/accounting-source02/'" -output_collections "raw"
