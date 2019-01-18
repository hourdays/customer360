#!/bin/bash
/Users/hjournea/Library/mlcp-9.0.7/bin/mlcp.sh import -host localhost -port 8055 -username admin -password admin -mode local -input_file_path ../data/support-system/tech-support-calls_20170123.csv -input_file_type delimited_text -output_uri_prefix /tech-support/ -uri_id id -output_uri_suffix .json -document_type json -output_collections "raw,from_csv"
