-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Час створення: Чрв 10 2024 р., 09:00
-- Версія сервера: 10.4.32-MariaDB
-- Версія PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `codeit`
--

-- --------------------------------------------------------

--
-- Структура таблиці `client`
--

CREATE TABLE `client` (
  `client_id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `contact_person` varchar(100) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `phone_number` varchar(13) NOT NULL,
  `address` varchar(100) NOT NULL,
  `registration_date` date NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `password` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `client`
--

INSERT INTO `client` (`client_id`, `name`, `contact_person`, `email`, `phone_number`, `address`, `registration_date`, `description`, `password`) VALUES
('91c4413932', 'NaUKMA', 'Oksana Kyrienko', 'naukma@ukma.edu.ua', '3552828355', 'Kyiv, Kontraktova ploshcha', '2024-06-10', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s', '12345678ukma'),
('fgfkgkug67', 'ZaharCorp', 'Zahar Maslak, CEO', 'z.maslak@gmail.com', '0977777777', 'Hurto', '2024-06-04', NULL, '12345678z'),
('gftyluif82', 'Zakneni', 'Maksym, Manager', 'm.horbunov@gmail.com', '0933333333', 'Hurto', '2024-06-04', 'Quest Company', '12345678m');

-- --------------------------------------------------------

--
-- Структура таблиці `employee`
--

CREATE TABLE `employee` (
  `employee_id` varchar(32) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  `specialisation` varchar(100) DEFAULT NULL,
  `salary` decimal(13,4) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone_number` varchar(13) NOT NULL,
  `address` varchar(100) NOT NULL,
  `hire_date` date NOT NULL,
  `birth_date` date NOT NULL,
  `password` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `employee`
--

INSERT INTO `employee` (`employee_id`, `first_name`, `last_name`, `role`, `specialisation`, `salary`, `email`, `phone_number`, `address`, `hire_date`, `birth_date`, `password`) VALUES
('053591ab41', 'Petro', 'Poroshenko', 'Tester', 'Good Tester', 50000.0000, 'p.poroshenko@gmail.com', '0666666666', 'Roshen', '2024-06-10', '1989-07-20', '12345678p'),
('dbvkbvkr90', 'Liam', 'Brown', 'Developer', 'Bad Developer', 30000.0000, 'l.brown@gmail.com', '0677777777', 'Spain', '2024-06-06', '2003-08-12', '12345678l'),
('dcgiewgf73', 'Vladyslav', 'Hibskyi', 'CEO', 'Perfect CEO', 600000.0000, 'v.hibskyi@gmail.com', '0988487880', 'Hurto', '2024-06-01', '2005-07-10', '12345678a'),
('gyuguyyu78', 'Dasha', 'Filozop', 'Tester', 'Good Tester', 40000.0000, 'd.filozop@gmail.com', '0988888888', 'Pryluki', '2024-06-03', '2005-07-20', '12345678d'),
('hdvegwgu43', 'Yulia', 'Skip', 'Developer', 'Best Dev', 50000.0000, 'y.skip@gmail.com', '0999999999', 'Burkaniv', '2024-06-03', '2005-12-01', '12345678y'),
('hefwueif20', 'Michael', 'Faraday', 'Project Manager', 'Bad PM', 30000.0000, 'm.faraday@gmail.com', '0500000000', 'Britain', '2024-06-02', '1900-01-18', '12345678f'),
('hgefgiwe39', 'Emma', 'Johnson', 'Developer', 'Good Dev', 40000.0000, 'e.johnson@gmail.com', '0555555555', 'USA', '2024-06-05', '2000-02-02', '12345678j'),
('huvrbhve49', 'Isaac', 'Newton', 'Project Manager', 'Best PM', 70000.0000, 'i.newton@gmail.com', '0955555555', 'Britain', '2024-06-01', '2000-01-01', '12345678i'),
('sdhvvurh44', 'Tetiana', 'Triukhan', 'Project Manager', 'Good PM', 60000.0000, 't.triukhan@gmail.com', '0931424561', 'Hurto', '2024-06-02', '2005-01-17', '12345678b');

-- --------------------------------------------------------

--
-- Структура таблиці `order`
--

CREATE TABLE `order` (
  `order_id` varchar(32) NOT NULL,
  `client_id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` blob NOT NULL,
  `creation_date` date NOT NULL,
  `due_date` date NOT NULL,
  `cost` decimal(13,4) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `order`
--

INSERT INTO `order` (`order_id`, `client_id`, `name`, `description`, `creation_date`, `due_date`, `cost`, `status`) VALUES
('0f3c82ce11', 'gftyluif82', 'Website for Zakneni', 0x5765627369746520666f7220776f726b696e672077697468207175657374732e, '2024-06-10', '2024-07-07', 20000.0000, 'Developing'),
('8c19b7e402', 'fgfkgkug67', 'BeReal', 0x536f6369616c204e6574776f726b, '2024-06-10', '2024-09-07', 40000.0000, 'Accepted'),
('8eea49602c', 'gftyluif82', 'Cooking ChatBot', 0x4368617420426f7420666f722072656369706573436f6e747261727920746f20706f70756c61722062656c6965662c204c6f72656d20497073756d206973206e6f742073696d706c792072616e646f6d20746578742e2049742068617320726f6f747320696e2061207069656365206f6620636c6173736963616c204c6174696e206c6974657261747572652066726f6d2034352042432c206d616b696e67206974206f7665722032303030207965617273206f6c642e2052696368617264204d63436c696e746f636b2c2061204c6174696e2070726f666573736f722061742048616d7064656e2d5379646e657920436f6c6c65676520696e2056697267696e69612c206c6f6f6b6564207570206f6e65206f6620746865206d6f7265206f627363757265204c6174696e20776f7264732c20636f6e73656374657475, '2024-06-10', '2024-08-23', 30000.0000, 'Rejected'),
('9575a00f0a', 'fgfkgkug67', 'CodeIT', 0x4372656174652041524d20666f7220495420436f6d70616e79, '2024-06-10', '2024-06-20', 60000.0000, 'Developing'),
('chsdlvlwl47', 'fgfkgkug67', 'ChatBot', 0x4368617420626f7420666f722073636564756c65, '2024-06-07', '2024-06-22', 5000.0000, 'Done'),
('fbvebvrvj38', 'fgfkgkug67', 'ZaharPage', 0xefbbbf506572736f6e616c2077656273697465, '2024-06-08', '2024-07-19', 3000.0000, 'Pending');

-- --------------------------------------------------------

--
-- Структура таблиці `project`
--

CREATE TABLE `project` (
  `project_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `manager_id` varchar(32) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `description` blob NOT NULL,
  `project_link` varchar(100) NOT NULL,
  `budget` decimal(13,4) NOT NULL,
  `start_date` date NOT NULL,
  `due_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `project`
--

INSERT INTO `project` (`project_id`, `order_id`, `manager_id`, `name`, `description`, `project_link`, `budget`, `start_date`, `due_date`, `end_date`, `status`) VALUES
('0d9a51d704', '0f3c82ce11', 'sdhvvurh44', 'Zakneni Project', 0x4372656174206561737920696e7465726661636520666f72205a616b6e656e69, 'https://teams.microsoft.com', 60000.0000, '2024-06-10', '2024-07-14', NULL, 'Created'),
('3e7b556edc', '9575a00f0a', 'sdhvvurh44', 'CodeIT Project', 0x4372656174652050726f6a656374, 'https://github.com', 70000.0000, '2024-06-10', '2024-06-30', NULL, 'Developing'),
('99f881ed0f', 'chsdlvlwl47', 'sdhvvurh44', 'ChatBotProject', 0x50726f6a65637420666f72204368617420426f74, 'https://www.youtube.com', 5000.0000, '2024-06-10', '2024-06-13', '2024-06-10', 'Awaiting for Confirmation');

-- --------------------------------------------------------

--
-- Структура таблиці `task`
--

CREATE TABLE `task` (
  `task_id` varchar(32) NOT NULL,
  `project_id` varchar(32) NOT NULL,
  `developer_id` varchar(32) DEFAULT NULL,
  `tester_id` varchar(32) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `description` blob NOT NULL,
  `task_link` varchar(100) NOT NULL,
  `start_date` date NOT NULL,
  `due_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  `comment` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `task`
--

INSERT INTO `task` (`task_id`, `project_id`, `developer_id`, `tester_id`, `name`, `description`, `task_link`, `start_date`, `due_date`, `end_date`, `status`, `comment`) VALUES
('4c3e3de0f1', '99f881ed0f', 'hdvegwgu43', 'gyuguyyu78', 'Planning', 0x506c616e20612070726f6a656374, 'https://me.com', '2024-06-10', '2024-06-12', '2024-06-10', 'Testing', 'Test passed'),
('83f8e914eb', '3e7b556edc', 'hdvegwgu43', 'gyuguyyu78', 'Planning', 0x506c616e6e696e672c20617263686974656374757265, 'https://distedu.ua', '2024-06-10', '2024-06-13', NULL, 'Developing', NULL),
('9f81b3d135', '3e7b556edc', 'dbvkbvkr90', '053591ab41', 'Backend', 0x4261636b656e6420646576656c6f706d656e74, 'https://vuer.cjoi', '2024-06-10', '2024-06-25', NULL, 'Awaiting for Confirmation', NULL),
('ef0030a128', '3e7b556edc', 'hgefgiwe39', 'gyuguyyu78', 'Frontend', 0x46726f6e74656e6420646576656c6f706d656e74, 'https://front.com', '2024-06-10', '2024-06-28', NULL, 'Testing', NULL);

--
-- Індекси збережених таблиць
--

--
-- Індекси таблиці `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`);

--
-- Індекси таблиці `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Індекси таблиці `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `order_client` (`client_id`);

--
-- Індекси таблиці `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `project_order` (`order_id`),
  ADD KEY `project_manager` (`manager_id`);

--
-- Індекси таблиці `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `task_project` (`project_id`),
  ADD KEY `task_developer` (`developer_id`),
  ADD KEY `task_tester` (`tester_id`);

--
-- Обмеження зовнішнього ключа збережених таблиць
--

--
-- Обмеження зовнішнього ключа таблиці `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `project_manager` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`employee_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `project_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_developer` FOREIGN KEY (`developer_id`) REFERENCES `employee` (`employee_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `task_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `task_tester` FOREIGN KEY (`tester_id`) REFERENCES `employee` (`employee_id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
