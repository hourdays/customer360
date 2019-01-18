#!/bin/bash
/Users/hjournea/Library/mlcp-9.0.7/bin/mlcp.sh import -host localhost -port 8055 -username admin -password admin -mode local -input_file_path ex06_doc.xml -transform_module /ex06_transformation_module.xqy -transform_namespace "http://marklogic.com/mlu/transform-date" -transform_param "value" -output_uri_replace "^.*/,''"
