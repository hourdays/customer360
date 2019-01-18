#!/bin/bash

# Run with an options file
#
# To use this script, copy your CORB and XCC JARs to the lib directory under
# your project's home directory

# The first parameter is the options file to use
OPTIONS=$1

SCRIPT_DIR=`dirname $0`

CLASSPATH=../corb/lib/marklogic-corb-2.4.2.jar:../corb/lib/marklogic-xcc-9.0.7.jar

java -server \
  -cp $CLASSPATH \
  -DOPTIONS-FILE=$OPTIONS \
  com.marklogic.developer.corb.Manager