#!/bin/bash
/Users/hjournea/Library/mlcp-9.0.7/bin/mlcp.sh import -host localhost -port 8055 -username admin -password admin -mode local -input_file_path ../data/marketing-system/contacts.xml -input_file_type aggregates -aggregate_record_element contact -output_uri_prefix /marketing/contact- -uri_id id -output_uri_suffix .xml -output_collections "raw"
