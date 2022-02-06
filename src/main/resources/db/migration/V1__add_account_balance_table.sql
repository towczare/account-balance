CREATE TABLE `account` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `account_id` varchar(20),
    `balance` numeric(20,2),
    `currency` varchar(3)
);