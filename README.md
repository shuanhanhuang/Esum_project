# Esum_project

一般使用者帳號/密碼：

(1)  esum/esum123

(2)  alan/alan123

(3)  han/han123

管理者帳號/密碼：admin/admin

# 資料庫 MySqL
-- esum project
```
create database esum;
```

```
use esum;
```

下方兩段資料庫指令等執行  java  後再到資料庫輸入
```
INSERT INTO user (password, username) VALUES
("admin","admin"),
("esum123","esum"),
("alan123","alan"),
("han123","han");
```

```
INSERT INTO shop (name, pid, price, quantity) VALUES
("osii 舒壓按摩椅","P001",98000,5),
("網友最愛起司蛋糕","P002",1200,50),
("真愛密碼項鍊","P003",8500,20);
```

# vue
```
npm install
```
```
npm run dev --5173
```

# springboot

```
mvn clean install
```

或是在java IDE介面上：maven clean -> maven install

1.  application.properties 檔案  修改

```
spring.datasource.username = 你的資料庫帳號
spring.datasource.password = 你的資料庫密碼
```

2.  shopcontroller、usercontroller、ordercontroller、ordermanagercontroller 檔案  修改

```
@CrossOrigin(origins = "你的網頁網址")
```

3.  Corsconfig 檔案  修改

```
config.addAllowedOrigin("你的網頁網址");
```

