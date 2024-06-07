# Esum_project
esum_project

# 資料庫 MySqL
-- esum project
create database esum;

use esum;

# springboot 
mvn clean install

或是在介面上

maven clean -> maven install


# application.properties 檔案
修改

spring.datasource.username = 你的帳號

spring.datasource.password = 你的帳號

# shopcontroller、usercontroller、ordercontroller、ordermanagercontroller 檔案
修改

@CrossOrigin(origins = "你的網頁網址")

# vue
npm install

npm run dev
