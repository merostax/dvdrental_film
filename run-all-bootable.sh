#!/bin/bash

chmod +x run-bootable.sh
./run-bootable.sh

cd .. && cd dvdrental-store
chmod +x run-bootable.sh
./run-bootable.sh


cd .. && cd dvdrental-customer
chmod +x run-bootable.sh
./run-bootable.sh

