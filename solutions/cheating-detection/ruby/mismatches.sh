#!/bin/bash
# Count mismatched lines between expected and actual output.
# Useful for debugging expected percentage of failed test cases.

ruby approach1.rb < "$1_input.txt" > temp.txt
diff "$1_output.txt" temp.txt | grep "^>" | wc -l
