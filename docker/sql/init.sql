grant all privileges on RefurbMarket.* to refurb@'%';
drop table if exists User CASCADE;
create table User
(
    id bigint AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    phoneNumber VARCHAR(255),
    createdAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    primary key (id)
) comment = "회원";

drop table if exists User_Address CASCADE;
create table User_Address
(
    id bigint AUTO_INCREMENT,
    userId bigint,
    isDefault TINYINT(1),
    address VARCHAR(255),
    createdAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    primary key (id),
    foreign key (userId) references User(id)
        on delete cascade
        on update cascade

) comment = "회원의 배송지 주소";

drop table if exists Seller CASCADE;
create table Seller
(
    id bigint AUTO_INCREMENT,
    name VARCHAR(255),
    storeName VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    phoneNumber VARCHAR(255),
    createdAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    primary key (id)
) comment = "판매자";
INSERT INTO Seller (name, storeName, email, password, phoneNumber)
VALUES ("김하나", "지누스", "zinus@email.com", "asdf", "01011111111");
INSERT INTO Seller (name, storeName, email, password, phoneNumber)
VALUES ("김둘", "까사미아", "casamia@email.com", "asdf", "01022222222");
INSERT INTO Seller (name, storeName, email, password, phoneNumber)
VALUES ("김셋", "한샘", "hanssem@email.com", "asdf", "01033333333");


drop table if exists Category CASCADE;
create table Category
(
    id bigint AUTO_INCREMENT,
    depth bigint,
    parentId bigint,
    name VARCHAR(255),
    createdAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    primary key (id),
    foreign key (parentId) references Category(id)
        on delete cascade
        on update cascade
) comment = "카테고리";
INSERT INTO Category (depth, parentId, name)
VALUES (1, null, "침대"); -- 1
INSERT INTO Category (depth, parentId, name)
VALUES (1, null, "매트리스·토퍼"); -- 2
INSERT INTO Category (depth, parentId, name)
VALUES (1, null, "테이블·책상·식탁"); -- 3
INSERT INTO Category (depth, parentId, name)
VALUES (1, null, "소파"); -- 4
INSERT INTO Category (depth, parentId, name)
VALUES (1, null, "서랍·수납장"); -- 5

INSERT INTO Category (depth, parentId, name)
VALUES (2, 1, "침대프레임"); -- 6
INSERT INTO Category (depth, parentId, name)
VALUES (2, 1, "침대부속가구"); --7
INSERT INTO Category (depth, parentId, name)
VALUES (2, 2, "매트리스"); -- 8
INSERT INTO Category (depth, parentId, name)
VALUES (2, 2, "토퍼"); -- 9
INSERT INTO Category (depth, parentId, name)
VALUES (2, 3, "사이드테이블"); -- 10
INSERT INTO Category (depth, parentId, name)
VALUES (2, 3, "식탁"); -- 11
INSERT INTO Category (depth, parentId, name)
VALUES (2, 3, "책상"); -- 12
INSERT INTO Category (depth, parentId, name)
VALUES (2, 3, "좌식테이블"); -- 13
INSERT INTO Category (depth, parentId, name)
VALUES (2, 4, "일반소파"); -- 14
INSERT INTO Category (depth, parentId, name)
VALUES (2, 4, "소파베드"); -- 15
INSERT INTO Category (depth, parentId, name)
VALUES (2, 4, "좌식소파"); -- 16
INSERT INTO Category (depth, parentId, name)
VALUES (2, 5, "수납장"); -- 17
INSERT INTO Category (depth, parentId, name)
VALUES (2, 5, "서랍장"); --18

INSERT INTO Category (depth, parentId, name)
VALUES (3, 6, "일반침대"); -- 19
INSERT INTO Category (depth, parentId, name)
VALUES (3, 6, "저상형침대"); -- 20
INSERT INTO Category (depth, parentId, name)
VALUES (3, 8, "스프링매트리스"); -- 21
INSERT INTO Category (depth, parentId, name)
VALUES (3, 12, "일반책상"); -- 22

drop table if exists Furniture CASCADE;
create table Furniture
(
    id bigint AUTO_INCREMENT,
    sellerId bigint,
    categoryId bigint,
    name VARCHAR(255),
    imageUrl VARCHAR(255),
    price bigint,
    stock int,
    deliveryFee int,
    createdAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    primary key (id),
    foreign key (sellerId) references Seller(id)
        on delete cascade
        on update cascade,
    foreign key (categoryId) references Category(id)
        on delete cascade
        on update cascade
) comment = "가구";
INSERT INTO Furniture (sellerId, categoryId, name, imageUrl, price, stock, deliveryFee)
VALUES (1, 19, "퀵스냅 침대 프레임", "https://aaa.com/image1", 3000000, 300, 5000);
INSERT INTO Furniture (sellerId, categoryId, name, imageUrl, price, stock, deliveryFee)
VALUES (1, 20, "퍼프 저상형 패브릭 침대 프레임", "https://aaa.com/image2", 5000000, 200, 10000);
INSERT INTO Furniture (sellerId, categoryId, name, imageUrl, price, stock, deliveryFee)
VALUES (1, 21, "그린티 하이브리드 스프링 침대 매트리스", "https://aaa.com/image3", 10000000, 100, 40000);

INSERT INTO Furniture (sellerId, categoryId, name, imageUrl, price, stock, deliveryFee)
VALUES (2, 14, "캄포 클래식 4인 카우치소파", "https://aaa.com/image4", 100000000, 200, 10000);
INSERT INTO Furniture (sellerId, categoryId, name, imageUrl, price, stock, deliveryFee)
VALUES (2, 14, "캄포 미니 4인 소파", "https://aaa.com/image5", 20000000, 100, 10000);
INSERT INTO Furniture (sellerId, categoryId, name, imageUrl, price, stock, deliveryFee)
VALUES (2, 22, "헬싱키 티테이블", "https://aaa.com/image6", 200000, 400, 5000);


INSERT INTO Furniture (sellerId, categoryId, name, imageUrl, price, stock, deliveryFee)
VALUES (3, 18, "샘베딩 스테디 서랍장 5단", "https://aaa.com/image7", 100000, 5000, 2000);
INSERT INTO Furniture (sellerId, categoryId, name, imageUrl, price, stock, deliveryFee)
VALUES (3, 18, "크린트 모던 높은 거실장 120cm 서랍형 수납장", "https://aaa.com/image8", 1000000, 2000, 4000);
INSERT INTO Furniture (sellerId, categoryId, name, imageUrl, price, stock, deliveryFee)
VALUES (3, 19, "포엠 호텔침대", "https://aaa.com/image9", 100000000, 2000, 30000);

drop table if exists Order CASCADE;
create table Order
(
    id bigint AUTO_INCREMENT,
    userId bigint,
    receiverName VARCHAR(255),
    receiverAddress VARCHAR(255),
    receiverPhone VARCHAR(255),
    status ENUM('PENDING', 'SHIPPED', 'DELIVERED', 'CANCELLED') NOT NULL,
    createdAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    primary key (id),
    foreign key (userId) references User(id)
        on delete cascade
        on update cascade
) comment = "주문";

drop table if exists Order_Item CASCADE;
create table Order_Item
(
    id bigint AUTO_INCREMENT,
    orderId bigint,
    furnitureId bigint,
    quantity int,
    pricePerItem bigInt,
    createdAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    primary key (id),
    foreign key (orderId) references Order(id)
        on delete cascade
        on update cascade,
    foreign key (furnitureId) references Furniture(id)
        on delete cascade
        on update cascade
) comment = "주문 상품";

drop table if exists Payment CASCADE;
create table Payment
(
    id bigint AUTO_INCREMENT,
    orderId bigint,
    type ENUM('CREDIT_CARD', 'PAYPAL', 'BANK_TRANSFER') NOT NULL,
    status ENUM('PENDING', 'COMPLETED', 'FAILED') NOT NULL,
    totalPrice bigint,
    discountPrice bigint,
    createdAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    primary key (id),
    foreign key (orderId) references Order(id)
        on delete cascade
        on update cascade
) comment = "결제 기록";

