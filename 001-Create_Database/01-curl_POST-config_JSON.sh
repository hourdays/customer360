#!/bin/bash
curl --anyauth --user admin:admin -X POST -d@"customer360-config.json" -i -H "Content-type: application/json" http://localhost:8002/v1/rest-apis
