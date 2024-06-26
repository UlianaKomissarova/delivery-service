DROP TABLE IF EXISTS vendors CASCADE;
DROP TABLE IF EXISTS deliveries CASCADE;
DROP TABLE IF EXISTS delivery_details CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS product_types CASCADE;
DROP TABLE IF EXISTS reports CASCADE;
DROP TABLE IF EXISTS report_details CASCADE;

CREATE TABLE IF NOT EXISTS vendors (
	vendor_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	name VARCHAR(250),
	CONSTRAINT pk_vendors PRIMARY KEY (vendor_id)
);

CREATE TABLE IF NOT EXISTS deliveries (
	delivery_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	vendor_id BIGINT NOT NULL REFERENCES vendors(vendor_id),
	arrival_time TIMESTAMP,
	CONSTRAINT pk_deliveries PRIMARY KEY (delivery_id)
);

CREATE TABLE IF NOT EXISTS product_types (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	name VARCHAR(250) NOT NULL,
	CONSTRAINT pk_product_types PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products (
	product_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	product_type_id BIGINT NOT NULL REFERENCES product_types(id),
	CONSTRAINT pk_products PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS delivery_positions (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	delivery_id BIGINT NOT NULL REFERENCES deliveries(delivery_id),
	product_id BIGINT NOT NULL REFERENCES products(product_id),
	price BIGINT,
	quantity integer,
	CONSTRAINT pk_delivery_positions PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS reports (
	report_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	start_date TIMESTAMP,
	end_date TIMESTAMP,
	CONSTRAINT pk_reports PRIMARY KEY (report_id)
);

CREATE TABLE IF NOT EXISTS report_details (
	detail_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    report_id BIGINT NOT NULL REFERENCES reports(report_id),
    vendor_id BIGINT NOT NULL,
    vendor_name VARCHAR(250) NOT NULL,
    product VARCHAR(250) NOT NULL,
    total_price BIGINT,
    total_quantity INTEGER,
	CONSTRAINT pk_report_details PRIMARY KEY (detail_id)
);