#!/bin/bash
# check-kafka-topics-created.sh

apt-get update -y
yes | apt-get install kafkacat

kafkacatResult=$(kafkacat -L -b kafka-broker-1:9092)

echo "Kafkacat result: " $kafkacatResult

while [[ ! $kafkacatResult == *"twitter-topic"* ]]; do
  >&2 echo "Kafka topic not created yet"
  sleep 2
  kafkacatResult=$(kafkacat -L -b kafka-broker-1:9092)
done

./cnb/lifecycle/launcher