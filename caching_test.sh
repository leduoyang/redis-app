#!/bin/bash

# Variables
PYTHON_SCRIPT="performance_test.py" # Replace with the actual Python script name
BASE_URL="http://localhost:8080/api/v1/tasks"
DURATION=330
QPS_FROM=1
QPS_TO=50
INTERVAL=5

# Calculate total QPS range
NUM_QPS=$(( (QPS_TO - QPS_FROM) / INTERVAL + 1 ))
DURATION_PER_QPS=$(( DURATION / NUM_QPS ))

# Caching scenario
echo "Running test with caching enabled..."
python3 $PYTHON_SCRIPT --url "${BASE_URL}?byCache=true" --qps_from $QPS_FROM --qps_to $QPS_TO --interval $INTERVAL --duration $DURATION

# Non-caching scenario
echo "Running test with caching disabled..."
python3 $PYTHON_SCRIPT --url "${BASE_URL}?byCache=false" --qps_from $QPS_FROM --qps_to $QPS_TO --interval $INTERVAL --duration $DURATION
