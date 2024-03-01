CREATE TABLE IF NOT EXISTS products (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255),
    category VARCHAR(255),
    photo_url VARCHAR(255),
    description TEXT,
    price DOUBLE
    );