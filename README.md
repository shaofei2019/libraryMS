本项目仅完成了数据库的设计，和部分后台业务逻辑的实现。

## 一、开发思路

### 1、前台相关

> · 设计前台界面
>
> · 前台提供输入后台提供输出，前台将后台的输出渲染到界面显示

- 基本信息管理，数据的增、删、改、查
- 基本信息的统计，统计图方式显示
- 数据的备份与恢复
- 用户权限的管理

### 2、后台相关（数据库操作程序设计）
> ·  写SQL语句
> · 将SQL语句与程序结合

- 每个成员至少完成对 2 个表的基本操作
- 每个小组成员至少写 5 个sql语句，完成对表的增、删、改
- 每个小组成员学习使用T-SQL，分析设计出数据库后台需要处理的功能，至少写 1 个存储过程和一个触发器，并部署与测试成功。

### 3、数据库相关
> · 概念模型设计
> · 逻辑模型设计

- 概念模型 ER图的设计与分析（小组讨论，汇总所有实体、属性、联系，共同设计E-R模型）
- 完成逻辑模型关系模式的设计
- 将ER模型转换成相应的关系模式
- 并定义出相应的表结构和相关约束，表名列名统一，定义外键及其类型，完成逻辑模型设计
- 生成sql文件



## 二、数据库设计

1. 数据库设计

   数据表(共8张表，4张主表，4张参照表，主键下划线加粗，外键斜体)：

   - 用户权限表purview(**<u>id</u>**,name)
     - 1，读者身份
     - 2，管理员身份
   - 用户状态表user_status(**<u>id</u>**,name)
     - 1，待激活
     - 2，已激活，正常使用
     - 3，已激活，禁止使用
	- 借阅卡状态表card_status(**<u>id</u>**,name)
     - 1，待激活
     - 2，已激活，正常使用
     - 3，已激活，禁止使用
   - **借阅卡信息表**card(**<u>id</u>**,<u>*status_id*</u>)
     - 借阅卡ID，借阅卡状态
   - **用户信息表**user(**<u>id</u>**,username,password,email,<u>*purview_id*</u>,<u>*card_id*</u>,<u>*user_status_id*</u>)
     - 用户id，用户名，密码，邮箱，权限ID，借阅卡ID，用户状态ID
   - 图书分类信息表category(**<u>id</u>**,ISBN)
     - 分类ID，书籍唯一标识符
	- **图书基本信息表**book(**id**,author，title,isbn,<u>*category_id*</u>,present,pub_year,language,leftAmount)
     - 图书ID，作者，图书名称，isbn号，分类ID，出版社，出版年，语言，图书剩余数量
   - **借阅信息表**borrow(**<u>id</u>**,<u>*borrower_user_id*</u>,<u>borrow_book_id</u>，BDate,isReturn,RDate)
     - 借阅记录ID，借阅者ID，借阅的图书ID，借阅日期，是否归还（0未归还，1已经归还）,归还日期
   
## 三、后台设计

功能模块划分：

- 用户管理
  0. 用户信息视图

  ```sql
  CREATE VIEW userInfoView AS
  SELECT
  	`user`.`id` AS `id`,
  	`user`.`username` AS `username`,
  	`user`.`email` AS `email`,
  	`user_status`.`name` AS `user_status`,
  	`card`.`id` AS `card_id`,
  	`card_status`.`name` AS `card_status`,
  	`purview`.`name` AS `pur_name` 
  FROM
  	((((
  					`user`
  					JOIN `user_status` ON ((
  							`user`.`user_status_id` = `user_status`.`id` 
  						)))
  				JOIN `purview` ON ((
  						`user`.`purview_id` = `purview`.`id` 
					)))
  			JOIN `card` ON ((
  					`user`.`card_id` = `card`.`id` 
  				)))
		JOIN `card_status` ON ((
  			`card`.`status_id` = `card_status`.`id` 
	)))
  ```
  1. 用户添加（注册）
  
   ```sql
     insert into user (username,password,email,purview_id,card_id,user_status_id) values(#{username},#{password},#{email},#{purview_id},#{card_id},#{user_status_id})
   ```
  
  2. 用户删除（注销）
  
     ```sql
     delete from user where username = #{username}
     ```
  
  
3. 用户修改
  
     ``` sql
     update user set username = #{newName} where username = #{oldName}
      
     update user set password = #{password} where username = #{username}
   ```
  
  4. 用户查询
  
     ```sql
     select * from userinfoview where username = #{username}
     ```
   ```
  
  5. 用户登录
  
   ```sql
     select * from user where (username = #{username} and password = #{password})
   ```

  6. 用户角色添加

   ```sql
     insert into purview (name) values (#{name})
   ```

  7. 用户角色名称修改
  
     ```sql
     update purview set name = #{newName} where name = #{oldName}
     ```

- 图书管理
  1. 图书添加
  
     ```sql
     insert into book (author,category_id,present,pub_year,language,leftAmount) values(#{author},#{category_id},#{present},#{pub_year},#{language},#{leftAmount})
     ```
  
  2. 图书删除
  
     ```sql
     delete from book where isbn = #{isbn}
     ```
  
  3. 图书修改
  
     ```sql
     update book set title = #{title} where isbn = #{isbn}
     ```
  
  4. 图书查询
  
     ```sql
     select * from book where isbn = #{isbn}
     ```
     
  5. 图书分类添加
  
     ```sql
     insert into category (name) values (#{name})
     ```
  
  6. 图书分类删除
  
     ```sql
     delete from category where name = #{name}
     ```
  
     
  
- 借阅管理
  
  1. 新增借阅
  
     ```sql
     insert into borrow (borrow_user_id,BDate,isReturn,RDate) values(#{borrow_user_id},#{BDate},#{isReturn},#{RDate})
     ```
  
  2. 图书归还
  
     ```sql
     update borrow set isReturn = 1, RDate = #{RDate} where id = #{id}
     ```
     
  3. 新增借阅卡
  
     ```sql
     insert into card (status_id) values(#{status_id})
     ```
  
  4. 修改借阅卡状态
  
     ```sql
     update card set status_id = #{new_status_id} where id = #{id}
     ```
  
  5. 删除借阅卡
  
     ```sql
     delete from card where id = {#id}
     ```
  
     

## 四、前台设计

参考后台设计部分，使用后台提供的接口，设计界面。



时间紧，弄不出来，我们弄几个链接测试一下后端接口。



### 五、功能演示
- 用户注册

  http://localhost:8088/signIn?username=admin&password=admin&email=admin@qq.com&purview_id=2

  ![image-20210118143631863](\img\image-20210118143631863.png)

- 用户删除

  http://localhost:8088/deleteUser?username=123123&password=123123

  ![image-20210118143707134](\img\image-20210118143707134.png)

- 用户修改

  http://localhost:8088/updatePassword?username=admin&oldPassword=admin&newPassword=123

  ![image-20210118143909617](\img\image-20210118143909617.png)

- 用户查询

  http://localhost:8088/getUserInfo?username=123

  ![image-20210118173402254](\img\image-20210118173402254.png)

- 用户登录

  http://localhost:8088/login?username=123&password=123
  
  ![image-20210118143950281](\img\image-20210118143950281.png)


- 用户角色添加

  http://localhost:8088/addPurview?purviewName=%E8%AF%BB%E8%80%85

  ![image-20210118152019018](\img\image-20210118152019018.png)

- 用户角色修改

  http://localhost:8088/updatePurview?oldPurViewName=%E8%AF%BB%E8%80%851&newPurViewName=%E8%AF%BB%E8%80%85

  ![image-20210118153142838](\img\image-20210118153142838.png)


- 图书添加

  http://localhost:8088/addBook?title=%E5%8D%81%E4%B8%87%E4%B8%AA%E4%B8%BA%E4%BB%80%E4%B9%88&author=%E4%BD%9A%E5%90%8D&ISBN=1209878075&category_id=1&present=%E6%9F%90%E6%9F%90%E6%9F%90%E5%87%BA%E7%89%88%E7%A4%BE&pub_year=2021&language=%E4%B8%AD%E6%96%87&leftAmount=10

  ![image-20210118155511286](\img\image-20210118155511286.png)

- 图书删除

- 图书修改

- 图书查询

  http://localhost:8088/queryBook?ISBN=1209878075

  ![image-20210118173929429](\img\image-20210118173929429.png)

- 图书分类添加

- 图书分类删除

- 办理借阅卡

  http://localhost:8088/getCard?username=123

  ![image-20210118172455485](\img\image-20210118172455485.png)

  ![image-20210118172534689](\img\image-20210118172534689.png)


- 新增借阅

  http://localhost:8088/addBorrow?borrow_user_id=3&borrow_book_id=1

  ![image-20210118163656111](\img\image-20210118163656111.png)

- 图书归还

  http://localhost:8088/returnBook?borrow_id=8

  ![image-20210118165911442](\img\image-20210118165911442.png)

  ![image-20210118165934043](\img\image-20210118165934043.png)

<u>1）用户注册、登录、删除、修改操作</u>

2）用户角色添加、修改操作

<u>3）图书基本情况的录入、修改、删除等基本操作。</u>

<u>4）办理借书卡模块。</u>

<u>5）实现借书功能。</u>

<u>6）实现还书功能。</u>

<u>7）能方便的对图书进行查询。</u>

8）对超期的情况能自动给出提示信息。