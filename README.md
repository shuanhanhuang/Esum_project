# Esum_project

一般使用者帳號/密碼:esum/esum123

管理者帳號/密碼:admin/admin

# 資料庫 MySqL
-- esum project

create database esum;

use esum;

INSERT INTO user (password, username) VALUES
("admin","admin"),
("esum123","esum");

# springboot 
mvn clean install

或是在介面上

maven clean -> maven install


# application.properties 檔案
修改

spring.datasource.username = 你的資料庫帳號

spring.datasource.password = 你的資料庫密碼

# shopcontroller、usercontroller、ordercontroller、ordermanagercontroller 檔案
修改

@CrossOrigin(origins = "你的網頁網址")

# Corsconfig 檔案

修改

config.addAllowedOrigin("你的網頁網址");

# vue
npm install

npm run dev
