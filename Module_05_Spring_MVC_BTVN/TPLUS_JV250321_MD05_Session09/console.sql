create schema md05_ss09;
use md05_ss09;

CREATE TABLE categories_vi
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    description   TEXT
);

CREATE TABLE categories_en
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    description   TEXT
);

DELIMITER $$
CREATE PROCEDURE add_category(
    IN p_category_name_vi VARCHAR(255),
    IN p_description_vi TEXT,
    IN p_category_name_en VARCHAR(255),
    IN p_description_en TEXT
)
BEGIN
    INSERT INTO categories_vi (category_name, description)
    VALUES (p_category_name_vi, p_description_vi);

    INSERT INTO categories_en (category_name, description)
    VALUES (p_category_name_en, p_description_en);
END $$
DELIMITER ;

-- Gọi procedure để thêm 5 dòng dữ liệu
CALL add_category('Điện tử', 'Sản phẩm điện tử giá tốt', 'Electric', 'Nice cheap products');
CALL add_category('Thực phẩm', 'Thực phẩm sạch', 'Food', 'Clean food');
CALL add_category('Mỹ phẩm', 'Hàng mỹ phẩm chất lượng', 'Cosmetics', 'High-quality cosmetics');
CALL add_category('Thời trang', 'Quần áo thời trang mới nhất', 'Fashion', 'Latest fashion clothes');
CALL add_category('Đồ gia dụng', 'Đồ dùng gia đình tiện lợi', 'Household', 'Convenient household items');