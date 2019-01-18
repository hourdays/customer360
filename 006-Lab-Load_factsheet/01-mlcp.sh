#!/bin/bash
/Users/hjournea/Library/mlcp-9.0.7/bin/mlcp.sh import -host localhost -port 8055 -username admin -password admin -mode local -input_file_path ../data/sales-system/factsheets -output_uri_replace "^.*/,'/factsheet/'" -output_collections "raw"
