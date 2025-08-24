#!/bin/bash
until pg_isready -h postgres -p 5432 -U admin -d ecommerce; do
  echo "Waiting for Postgres..."
  sleep 2
done

exec "$@"