#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER product_user WITH PASSWORD 'product_password';
    CREATE USER stock_user WITH PASSWORD 'stock_password';

    CREATE DATABASE cdc;

    GRANT ALL PRIVILEGES ON DATABASE cdc TO product_user;
    GRANT ALL PRIVILEGES ON DATABASE cdc TO stock_user;

    \c cdc

    CREATE SCHEMA IF NOT EXISTS product_schema;
    CREATE SCHEMA IF NOT EXISTS stock_schema;

    GRANT ALL PRIVILEGES ON SCHEMA product_schema TO product_user;
    GRANT ALL PRIVILEGES ON SCHEMA stock_schema TO stock_user;

    ALTER ROLE product_user SET search_path = product_schema;
    ALTER ROLE stock_user SET search_path = stock_schema;

EOSQL