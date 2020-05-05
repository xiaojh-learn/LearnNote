## MySql
### 1. 表的组成

![image-20200401100515253](C:\Users\XH\AppData\Roaming\Typora\typora-user-images\image-20200401100515253.png)

+ employees:表名

+ employee_id:列名，字段，主键

### 2. DQL

1. 基础查询

   + 语法：
     
   ```mysql
     SELECT 查询列表 FROM 表名;
   ```

     + 查询列表可以是：表中的字段，常量值，表达式，函数
     + 查询结果是一个虚拟的表格
   
     ```mysql
     # 使用myemployees库
     USE myemployees;
     
     # 显示表结构
     DESC employees;
     
     #1.查询表中的单个字段
     SELECT last_name FROM employees;
     
     #2.查询表中的多个字段
     SELECT last_name,salary,email FROM employees;
     
     #3.查询表中的所有字段，查询顺序与原表相同，使用着重号`NAME`区分字段与关键字
     SELECT * FROM employees;
     
     #4.查询常量值
     SELECT 100;
     SELECT 'john';
     
     #5.查询表达式
     SELECT 100*98;
     
     #6.查询函数,调用函数得到返回值
     SELECT VERSION();
     
     #7.起别名,便于理解，区分重名的字段
     	#方式一：使用AS
     	SELECT 100%98 AS result;
     	SELECT last_name AS 姓,first_name AS 名, FROM employees;
     	
     	#方式二：使用后空格
     	SELECT last_name 姓,first_name 名 FROM employees;
     	
     	#案例：查询salary，显示为out put
     	SELECT salary AS "out put" FROM employees;
     
     #8.去重
     	#查询员工表中的所有部门编号
     	SELECT DISTINCT department_id FROM employees;
     
     #9.+号的作用，运算符
     	#两个操作数都为数值型，则做加法运算
     	SELECT 100+98;
     	#其中一方为字符型，试图将字符型数值转换成数值型，如果转换成功，则继续做加法运算，
     	如果转换失败，则将字符型数值转换成0
     	SELECT '123'+90; //213
     	SELECT 'jhon'+90; //90
     #10.拼接字符串，NULL与任何字符串拼接都为NULL
     SELECT CONCAT(last_name,first_name) AS name FROM employees;
     SELECT CONCAT(last_name,',',first_name,',',IFNULL(commission_pct,0)) AS out_Put FROM employees;
     	
     ```

2. 条件查询

   + 语法：

     ```mysql
     SELECT 查询列表 FROM 表名 WHERE 筛选条件;
     ```
     
     + 按条件表达式筛选：> < = != <> >= <=
+ 按逻辑表达式筛选：&& || ! and or not
     + 模糊查询：like, between and, in, is null
     
     ```mysql
     #1.按条件表达式筛选
     	#查询工资>12000的员工信息
     	SELECT * FROM employees WHERE salary>12000;
     	#查询部门编号不等于90号的员工名和部门编号
     	SELECT last_name,department_id FROM employees WHERE department_Id<>90;
     #2.按逻辑表达式筛选
     	#查询工资在10000到12000之间的员工名，工资以及奖金
     	SELECT last_name,salary,commission_pct FROM employees WHERE salary>=10000 AND     	  salary<=12000;
     	#查询部门编号不是在90到110之间，或则工资高于15000的员工信息
     	SELECT * FROM employees WHERE NOT(department_id>=90 AND department_id<=110) OR         salary>=15000;
     #3.like
     	#通配符
     		%：任意多个字符，包含0个字符
     		_：任意单个字符
     		ESCAPE：转义字符
     		
     	#查询员工名中包含字符a的员工信息
     	SELECT * FROM employees WHERE last_name LIKE '%a%';
     	
     	#查询员工名中第二个字符为_的员工名
     	SELECT last_name FROM employees WHERE last_name LIKE '_$_%' ESCAPE '$';
     #4.betweeen and
     	#查询员工编号在100到120之间的员工信息
     	SELECT * FROM employees WHERE employee_id BETWEEN 100 AND 120;
     
     #5.in
     	#查询员工的工种编号是IT_PORG,AD_VP,AD_PRES中的一个员工名和工种编号
     	SELECT last_name,job_id FROM employess WHERE job_id IN('IT_PORG', 'AD_VP',  	'AD_PRES');
     #6.is null
	#查询没有奖金的员工名
     	SELECT last_name FROM employees WHERE commission_pct IS NULL;
	
     #7.安全等于<=>，可以判断null
     #查询没有奖金的员工名
     	SELECT last_name FROM employees WHERE commission_pct <=> NULL;
     ```

3. 排序查询

   + 语法：

     ```mysql
     SELECT 查询列表 FROM 表名 WHERE 筛选条件 ORDER BY 排序列表 ASC/DESC
     ```

     + ASC：升序，默认
     + DESC：降序

     ```mysql
     #1.查询员工信息，要求工资从高到低排序
     SELECT * FROM employees ORDER BY salary DESC;
     
     #2.查询部门编号>=90的员工信息，按入职时间的先后进行排序
     SELECT * FROM employees WHERE department_id>=90 ORDER BY hiredate ASC;
     
     #3.按年薪的高低显示员工的信息和年薪
     SELECT *,salary*12*(1+IFNULL(commission_pct,0)) AS 年薪 FROM employees ORDER BY 年薪 DESC;
     
     #4.按函数排序
     SELECT LENGTH(last_name) 字节长度,last_name,salary FROM employees ORDER BY LENGTH(last_name) DESC;
     
     #5.按多个字段排序
     SELECT * FROM employees ORDER BY salary ASC,employee_id DESC;
     ```
   
4. 常见函数

   + 功能：将一组逻辑语句封装在方法体中，对外暴露方法名

   + 好处：隐藏了实现细节，提高代码的重用性

   + 语法：

     ```mysql
     SELECT 函数名(实参列表) FROM 表;
     ```

   + 单行函数 

     + 字符函数

     ```mysql
     #1.length
     #获取参数值的字节个数
     SELECT LENGTH('jhon');
     
     #2.concat
     #拼接字符串
     SELECT CONCAT(last_name,'_',first_name) 姓名 FROM employees;
     
     #3.upper,lower
     #大小写
     SELECT UPPER('jhon');
     SELECT LOWER('JHON');
     
     #4.substr
     #截取字符，注意：索引从1开始
     SELECT SUBSTR('abcdefg',5); //efg
     SELECT SUBSTR('abcdefg',1,3); //abc
     
     #5.instr
     #返回子串的起始索引
     SELECT INSTR('abcdefg','fg'); //6
     
     #6.trim
     #去除字符串前后的某个字符
     SELECT TRIM('a' FROM 'aaabaaabbaaa'); //baaabb
     
     #7.lpad
     #用指定的字符实现左填充
     SELECT LPAD('aaa',5,'*'); //**aaa
     
     #8.rpad
     #用指定的字符实现右填充
     SELECT RPAD('aaa',5,'*'); //aaa**
     
     #9.replace
     #替换字符
     SELECT REPLACE('abccc','cc','de'); //abdec
     ```
     + 数学函数

     ```mysql
     #1.round
     #四舍五入
     SELECT ROUND(1.65); //2
     SELECT ROUND(1.123,1); //1.1
     
     #2.ceil
     #向上取整
     SELECT CEIL(1.53); //2
     
     #3.floor
     #向下取整
     SELECT FLOOR(1.23); //1
     
     #4.truncate
     #截断
     SELECT TRUNCATE(1.33333,1); //1.3
     
     #5.mod
     #取余
     SELECT MOD(-10,3); //-1
     ```

     + 日期函数

     ```mysql
     #1.now
     #返回当前系统日期+时间
     SELECT NOW();
     
     #2.curdate
     #返回当前系统日期，不包含时间
     SELECT CURDATE();
     
     #3.curtime
     #返回当前系统时间，不包含日期
     SELECT CURTIME();
     
     #4.year,month,day,hour,minute,second
     #获取指定的部分，年，月，日，时，分，秒
     SELECT YEAR(NOW());
     SELECT MONTH(NOW());
     SELECT DAY(NOW());
     
     #5.str_to_date
     #将日期格式的字符转换成指定格式的日期
     SELECT STR_TO_DATE('1900-1-1','%Y-%m-%d');
     
     #6.date_format
     #将日期转换成字符
     SELECT DATE_FORMAT(NOW(),'%Y年%m月%d日');
     
     #7.datediff
     #求两个日期之间的天数
     SELECT DATEDIFF(MAX(hiredate),MIN(hiredate));
     ```

     | 序号 | 格式符 | 功能       |
     | ---- | ------ | ---------- |
     | 1    | %Y     | 四位的年份 |
     | 2    | %y     | 2位的年份  |
     | 3    | %m     | 月份01，02 |
     | 4    | %c     | 月份1,2    |
     | 5    | %d     | 日01,02    |
     | 6    | %H     | 24小时制   |
     | 7    | %h     | 12小时制   |
     | 8    | %i     | 分钟       |
     | 9    | %s     | 秒         |
     + 其他函数  

     ```mysql
     #1.version
     #查看版本号
     SELECT VERSION();
      
     #2.database
     #查看数据库
     SELECT DATABSE();
     
     #3.user
     #查看用户
     SELECT USER();
     ```

     + 流程控制函数  

     ```mysql
     #1.if
     #实现if else 效果
     SELECT IF(3>1,'大','小'); //大
     
     #2.case
     /*
     使用一：switch case
     case 要判断的字段或表达式
     when 常量1 then 要显示的值1或语句1
     ...
     else 要显示的值n或语句n
     end
     */
     SELECT salary AS 原始工资,department_id,
     CASE department_id
     WHEN 30 THEN salary*1.1
     WHEN 40 THEN salary*1.2
     WHEN 50 THEN salary*1.3
     ELSE salary
     END AS 新工资
     FROM employees;
     
     /*
     使用二：if elseif
     case 
     when 条件1 then 要显示的值1或语句1；
     when 条件2 then 要显示的值2或语句2；
     ...
     else要显示的值n或语句n;
     end
     */
     SELECT salary,
     CASE
     WHEN salary>20000 THEN 'A'
     WHEN salary>15000 THEN 'B'
     WHEN salary>10000 THEN 'C'
     ELSE 'D'
     END AS 工资级别
     FROM employees;
     ```

   + 分组函数

   ```mysql
   #1.sum
   #求和函数
   #忽略null
   SELECT SUM(salary) FROM employees;
   
   #2.avg
   #求平均值
   #忽略null
   SELECT AVG(salary) FROM employees;
   
   #3.min
   #求最小值
   #忽略null
   SELECT MIN(salary) FROM employees;
   
   #4.max
   #求最大值
   #忽略null
   SELECT MAX(salary) FROM employees;
   
   #5.count
   #求个数
   #不忽略null
   SELECT COUNT(salary) FROM employees;
   #distance搭配,统计种类数
   SELECT COUNT(DISTANCE salary) FROM employees;
   #统计行数
   SELECT COUNT(*) FROM employees;
   
   #6.和分组函数一同查询的字段有限制，要求是group by后的字段
   ```

5. 分组查询

+ 语法：

  ````mysql
  SELCT 分组函数，列(要求出现在group by 的后面) 
  FROM 表 
  WHERE 筛选条件 
  GROUP BY 分组的列表
  ORDER BY 排序
  ````

+ 按字段分组

  ```mysql
  #1.查询每个工种的最高工资
  SELECT MAX(salary),job_id FROM employees GROUP BY job_id;
  
  #2.查询每个位置上的部门个数
  SELECT COUNT(*),location_id FROM departments GROUP BY location_id;
  
  #3.添加分组前筛选条件，查询邮箱中包含a字符的，每个部门的平均工资
  SELECT AVG(salary),department_id,email FROM employees WHERE email LIKE '%a%' GROUP BY department_id;
  
  #4.查询有奖金的每个领导手下员工的最高工资
  SELECT MAX(salary),manager_id FROM employees WHERE commission_pct IS NOT NULL GROUP BY manager_id;
  
  #5.添加分组后筛选条件，查询哪个部门的员工个数>2
  SELECT COUNT(*),department_id FROM employees GROUP BY department_id HAVING COUNT(*)>2;
  
  #6.查询每个工种有奖金的员工的最高工资>12000的工种编号和最高工资
  SELECT MAX(salary),job_id FROM employees WHERE commission_pct IS NOT NULL GROUP BY job_id HAVING MAX(salary)>12000;
  
  #7.查询领导编号>102的每个领导手下的最低工资>5000的领导编号是哪个，以及其最低工资
  SELECT manager_id,MIN(salary) FROM employees WHERE manager_id>102 GROUP BY manager_id HAVING MIN(salary)>5000;
  ```

+ 按表达式或函数分组

  ```mysql
  #1.按员工姓名的长度分组，查询每一组的员工个数，筛选员工个数>5的有哪些
  SELECT COUNT(*),LENGTH(last_name) FROM employees GROUP BY LENGTH(last_name) HAVING COUNT(*)>5;
  ```

+ 按多个字段

  ```mysql
  #1.查询每个部门每个工种的员工的平均工资
  SELECT AVG(salary),department_id,job_id FROM employees GROUP BY department_id,job_id;
  ```

6. 连接查询

+ 含义：又称多表查询，当查询的字段来自于多个表

+ 笛卡尔乘积现象：表1有m行，表2有n行，结果有m*n行
  + 发生原因：没有有效的连接体哦阿健
  + 如何避免：添加有效的连接条件

+ 分类：

  + 按年代分类：sql92标准，sql99标准

  + 按功能分类：内连接：等值连接，非等值连接，自连接

    ​						外连接：左外连接，右外连接，全外连接

    ​						交叉连接

+ sql92标准

  + 等值连接

  ```mysql
  #1.等值连接
  /*
  1)多表等值连接的结果为多表的交集部分
  2)n表连接，至少需要n-1个连接条件
  3)多表的顺序没有要求
  4)一般需要为表起别名
  5)可以搭配前面介绍的所有子句使用
  */
  #查询女神名和对应的男神名
  SELECT NAME,boyName FROM boys,beauty WHERE beauty.boyfriend_id=boys.id;
  
  #查询员工名和对应的部门名
  SELECT last_name,department_name FROM employees,departments WHERE employees.department_id=departments.department_id;
  
  #2.为表起别名，提高语句的简洁度，区分多个重名的字段
  #查询员工名，工种号，工种名
  #注意：如果为表起了别名，则查询的字段就不能使用原来的表名去限定
  SELECT last_name,e.job_id,job_title FROM employees e,jobs j WHERE e.job_id=j.job_id;
  
  #3.可以加筛选s
  #查询有奖金的员工名，部门名
  SELECT last_name,department_name FROM employees e,departments d WHERE e.department_id=d.department_id AND e.commission_pct IS NOT NULL;
  
  #查询城市名中第二个字符为o的部门名和城市名
  SELECT department_name,city FROM departments d,locations l WHERE d.location_id=l.location_id AND city LIKE '_o%';
  
  #4.分组
  #查询每个城市的部门个数
  SELECT COUNT(*) 个数,city FROM departments d,locations l WHERE d.location_id=l.location_id GROUP BY city;
  
  #查询有奖金的每个部门的部门名和部门的领导编号和该部门的最低工资
  SELECT department_name,e.manager_id,MIN(salary) FROM employees e,departments d WHERE e.department_id=d.department_id AND commission_pct IS NOT NULL GROUP BY department_name,d.department_id;
  
  #5.排序
  #查询每个工种的工种名和员工的个数，并且按员工个数降序
  SELECT job_title,COUNT(*) FROM employees e,jobs j WHERE e.job_id=j.job_id GROUP BY e.job_id,j.job_title ORDER BY COUNT(*) DESC;
  
  #6.实现三表连接
  #查询员工名，部门名和所在的城市
  SELECT last_name,department_name,city FROM employees e,departments d,locations l WHERE e.department_id=d.department_id AND d.location_id=l.location_id AND city LIKE 's%' ORDER BY department_name DESC;
  ```

  + 非等值连接

  ```mysql
  #查询出员工的工资和工资级别
  SELECT salary,grade_level FROM employem  es e,job_grades jg WHERE salary BETWEEN jg.lowest_sal AND jg.highest_sal
  ```

  + 自连接

  ```mysql
  #查询员工名和上级的名称
  SELECT e.employee_id,e.last_name,m.employee_id,m.last_name FROM employees e,employees m WHERE e.manager_id=m.employee_id;
  ```

+ sql99标准

  + 语法：

  ```mysql
  SELECT 查询列表 FROM 表1 别名 [连接类型] join 表2 别名 on 连接条件 
  WHERE 筛选条件 
  GROUP BY 分组 
  HAVING 筛选条件 
  ORDER BY 排序列表
  ```

  + 连接类型：
    + 内连接：inner
    + 外连接：左外：left [outer]，右外：right[outer]，全外：full[outer]
    + 交叉连接：cross
  + 内连接

  ```mysql
  /*
  语法：
  SELECT 查询列表
  FROM 表1,别名
  INNER JOIN 表2,别名
  ON 连接条件;
  */
  #1.等值连接
  #查询员工名，部门名
  SELECT last_name,department_name 
  FROM employees e
  INNER JOIN departments d
  ON e.department_id=d.department_id;
  
  #查询名字中包含e的员工名和工种名
  SELECT last_name,job_title
  FROM employees e
  INNER JOIN jobs j
  ON e.job_id=j.job_id
  WHERE e.last_name LIKE '%e%';
  
  #查询部门个数>3的城市名和部门个数
  SELECT city,COUNT(*) 部门个数
  FROM departments d
  INNER JOIN locations l
  ON d.location_id=l.location_id
  GROUP BY city
  HAVING 部门个数>3;
  
  #查询哪个部门的部门员工个数>3的部门名和员工个数，并按个数降序
  SELECT COUNT(*),d.department_name
  FROM employees e
  INNER JOIN departments d
  ON e.department_id=d.department_id
  GROUP BY e.department_id
  HAVING COUNT(*)>3
  ORDER BY COUNT(*) DESC;
  
  #查询员工名，部门名，工种名，并按部门名降序
  SELECT last_name,department_name,job_title
  FROM employees e
  INNER JOIN departments d
  ON e.department_id=d.department_id
  INNER JOIN jobs j
  ON e.job_id=j.job_id
  ORDER BY department_name DESC;
  
  #2.非等值连接
  #查询员工的工资级别
  SELECT salary,grade_level
  FROM employees e
  JOIN job_grades g
  ON e.salary BETWEEN g.lowest_sal AND g.highest_sal;
  
  #查询工资级别的个数>2的个数，并且按工资级别降序
  SELECT COUNT(*),grade_level
  FROM employees e
  JOIN job_grades g
  ON e.salary BETWEEN g.lowest_sal AND g.highest_sal
  GROUP BY grade_level
  HAVING COUNT(*)>20
  ORDER BY grade_level DESC;
  
  #3.自连接
  #查询员工名，上级名
  SELECT e.last_name,m.last_name
  FROM employees e
  INNER JOIN employees m
  ON e.manager_id=m.employee_id;
  ```

  + 外连接

  ```mysql
  /*
  应用场景：查询一个表中有另一个表中没有的数据
  特点：
  1.外连接的查询结果为主表中的所有记录
    如果从表中有和它匹配的，则显示匹配的值
    如果从表中没有和它匹配的，则显示null
    外连接查询结果=内连接结果+主表中有而从表没有的记录
  2.左外连接，left join左边的是主表
    右外连接，right join右边的是主表
  3.左外和右外交换两个表的顺序，可以实现同样的效果
  */
  
  #查询没有男朋友不在男神表的女神名
  SELECT b.name,bo.*
  FROM beauty b
  LEFT OUTER JOIN boys bo
  ON b.boyfriend_id=bo.id
  WHERE bo.id IS NULL;
  
  #查询哪个部门没有员工
  #左外
  SELECT d.*,e.employee_id
  FROM departments d
  LEFT OUTER JOIN employees e
  ON e.department_id=d.department_id
  WHERE e.employee_id IS NULL;
  ```

  + 交叉连接

  ```mysql
  #笛卡尔乘积
  SELECT b.*,bo.*
  FROM beauty b
  CROSS JOIN boys bo;
  ```

7. 子查询

   + 概念：出现在其他语句内部的select语句，称为子查询或内查询，内部嵌套其他select语句的查询，称为外查询或主查询

   + 分类：

     + 按子查询出现的位置：
       + select后面：标量子查询
       + from后面：表子查询
       + where或having后面：标量子查询，列子查询，行子查询
       + exists后面：表子查询

     + 按结果集的行列数不同
       + 标量子查询，结果集只有一行一列
       + 列子查询，结果集只有一列多行
       + 行子查询，结果集有一行多列
       + 表子查询，结果集一般为多行多列

   + 案例

   ```mysql
   #where或having后面
   /*
   特点：
   -子查询放在小括号内
   -子查询一般放在条件的右侧
   -标量子查询，一般搭配着单行操作符使用
   -列子查询，一般搭配着多行操作符使用
   -子查询的执行优先于主查询
   */
   
   #1.标量子查询
   #谁的工资比Abel高
   SELECT * 
   FROM employees
   WHERE salary>(
   	SELECT salary
   	FROM employees
   	WHERE last_name='Abel'
   );
   
   #返回job_id与141号员工相同，salary比143号员工多的员工姓名，job_id和工资
   SELECT last_name,job_id,salary
   FROM employees
   WHERE job_id=(
   	SELECT job_id 
   	FROM employees
   	WHERE employee_id=141
   )
   AND salary>(
   	SELECT salary
   	FROM employees
   	WHERE employee_id=143
   );
   
   #返回公司工资最少的员工的last_name,job_id和salsry
   SELECT last_name,job_id,salary
   FROM employees
   WHERE salary=(
   	SELECT MIN(salary)
   	FROM employees
   );
   
   #查询最低工资大于50号部门最低工资的部门id和其最低工资
   SELECT department_id,MIN(salary)
   FROM employees
   GROUP BY department_id
   HAVING MIN(salary)>(
   	SELECT MIN(salary)
   	FROM employees
   	WHERE department_id=50
   );
   
   #2,列子查询
   /*
   使用多行比较操作符
   -IN/NOT IN：等于列表中的任意一个
   -ANY|SOME：和子查询返回的某一个值比较
   -ALL：和子查询返回的所有值比较
   */
   
   #返回location_id是1400或1700的部门中的所有员工姓名
   SELECT last_name
   FROM employees
   WHERE department_id IN(
   	SELECT department_id
   	FROM departments 
   	WHERE location_id IN(1400,1700)
   );
   
   #返回其它工种中比job_id为‘IT_PROG'部门任一工资低的员工的：工号，姓名，job_id，以及salary
   SELECT employee_id,last_name,job_id,salary
   FROM employees
   WHERE salary<ANY(
   	SELECT DISTINCT salary
   	FROM employees
   	WHERE job_id='IT_PROG'
   ) AND job_id<>'IT_PROG';
   
   #返回其它工种中比job_id为‘IT_PROG'部门所有工资低的员工的：工号，姓名，job_id，以及salary
   SELECT employee_id,last_name,job_id,salary
   FROM employees
   WHERE salary<ALL(
   	SELECT DISTINCT salary
   	FROM employees
   	WHERE job_id='IT_PROG'
   ) AND job_id<>'IT_PROG';
   
   #3.行子查询
   #查询员工编号最小并且工资最高的员工信息
   SELECT *
   FROM employees
   WHERE (employee_id,salary)=(
   	SELECT MIN(employee_id),MAX(salary)
   	FROM employees
   );
   
   #select后面
   #查询每个部门的员工个数
   SELECT d.*,(
   	SELECT COUNT(*)
   	FROM employees e
   	WHERE e.`department_id`=d.`department_id`
   ) 个数
   FROM departments d;
   
   #from后面
   #查询每个部门的平均工资的工资等级
   SELECT  ag_dep.*,g.`grade_level`
   FROM (
   	SELECT AVG(salary) ag,department_id
   	FROM employees
   	GROUP BY department_id
   ) ag_dep
   INNER JOIN job_grades g
   ON ag_dep.ag BETWEEN g.`lowest_sal` AND g.`highest_sal`;
   
   #exists后面，相关子查询
   #SELECT EXISTS(完整的查询语句) //结果返回0或1
   #查询有员工的部门名
   SELECT department_name
   FROM departments d
   WHERE EXISTS(
   	SELECT * 
   	FROM employees e
   	WHERE d.`department_id`=e.`department_id`
   );
   ```

8. 分页查询

   + 应用场景：当要显示的数据，一页显示不全，需要分页提交sql请求
   + 语法：

   ```mysql
   SELECT 查询列表
   FROM 表
   [JOIN TYPE] JOIN 表2
   ON 连接条件
   WHERE 筛选条件
   GROUP BY 分组字段
   HAVING 分组后的筛选
   ORDER BY 排序的字段
   LIMIT OFFSET,SIZE;
   
   OFFSET：显示条目的其实索引(起始索引从0开始)
   SIZE：显示的条目个数
   
   /*
   特点：
   -limit语句放在查询语句的最后
   -公式
    要显示的页数page，每页的条目数size
    SELECT 查询列表
    FROM 表
    limit page*size,size; 
   */
   
   #查询前五条员工信息
   SELECT * 
   FROM employees
   LIMIT 0,5;
   ```

   + 执行顺序

	```mysql
SELECT 查询列表			  7
FROM 表			       1
[JOIN TYPE] JOIN 表2    2	
ON 连接条件			    3
WHERE 筛选条件	       4
GROUP BY 分组字段	  5
HAVING 分组后的筛选	6
ORDER BY 排序的字段  8
LIMIT OFFSET,SIZE; 9
	```

9. 联合查询

   + 概念：将多条查询语句的结果合并成一个结果
   + 语法：

   ````mysql
   /*
   查询语句1
   union
   查询语句2
   union
   ...;
   
   应用场景：
   要查询的结果来自于多个表，且多个表没有直接的连接关系，但查询的信息一致时
   
   特点：
   -要求多条查询语句的查询列数是一致的
   -要求多条查询语句的查询的每一列的类型和顺序最好是一致的
   -union关键字默认去重，如果使用union all可以包含重复项
   */
   
   #查询部门编号>90或邮箱包含a的员工信息
   SELECT * FROM employees WHERE email LIKE '%a%'
   UNION
   SELECT * FROM employees WHERE department_id>90;
   
   #查询中国用户中男性的信息以及外国用户中男性的信息
   SELECT id,cname,csex FROM t_ca WHERE csex='男'
   UNION
   SELECT t_id,tName,tGender FROM t_ua WHERE tGender='male';
   ````

### 3. DML

1. 插入语句

   + 语法：

   ```mysql
   #方式一：
   INSERT INTO 表名(列名1,...) VALUES(值1,...),VALUES(值2,...)...;
   /*
   -插入的值的类型要与列的类型一致或兼容
   -不可以为null的列必须插入值，可以为null的列值可为null，也可省略列名，值，必须同时省略
   -列的顺序可调换
   -列数和值的个数必须一致
   -可以省略列名，默认所有列，而且列的顺序和表中列的顺序一致
   -支持插入多行
   -支持子查询
   */
   #beauty插入一行数据
   INSERT INTO beauty(id,name,sex,borndate,phone,photo,boyfriend_id)
   VALUES(13,'唐艺昕','女','1990-4-23','18988888888',NULL,2);
   
   #子查询
   INSERT INTO beauty(id,name,phone) SELECT(26,'宋茜','11809866');
   
   #方式二：
   INSERT INTO 表名 SET 列名1=值1,列2名=值2,...;
   
   #beauty插入一行数据
   INSERT INTO beauty SET id=19,name='刘涛',phone='999';
   ```

2. 修改语句

   + 语法

   ```mysql
   #1.修改单表的记录
   UPDATE 表名 SET 列=新值,列=新值,... WHERE 筛选条件;
   
   #修改beauty表中唐艺昕的电话为13899889988
   UPDATE beauty SET phone='13899889988' WHERE id=13;
   
   #2.修改多表的记录
   /*
   sql92语法：
   UPDATE 表1 别名,表2 别名
   SET 列=值,...
   WHERE 连接条件
   AND 筛选条件;
   
   sql99语法：
   UPDATE 表1 别名
   INNER|LEFT|RIGHT JOIN 表2 别名
   ON 连接条件
   SET 列=值,...
   WHERE 筛选条件;
   */
   
   #修改张无忌的女朋友手机号为114
   UPDATE boys bo
   INNER JOIN beauty b
   ON bo.id = b.boyfriend_id
   SET b.phone='114'
   WHERE bo.boyName='张无忌';
   
   #修改没有男朋友的女神的男朋友编号都为2号
   UPDATE boys bo
   RIGHT JOIN beauty b
   ON bo.id=b.boyfriend_id
   SET b.boyfriend_id=2
   WHERE bo.id IS NULL;
   ```

3. 删除语句

   + 语法

   ```mysql
   #1.delete
   #单表的删除
   DELETE FROM 表名 WHERE 筛选条件;
   #删除手机号以9结尾的女神信息
   DELETE FROM beauty WHERE phone LIKE '%9';
   
   #多表的删除
   /*
   sql92语法：
   DELETE 表1的别名,表2的别名
   FROM 表1 别名,表2 别名
   WHERE 连接条件
   AND 筛选条件;
   
   sql99语法：
   DELETE 表1的别名,表2的别名
   FROM 表1 别名,表2 别名
   INNER|LEFT|RIGHT JOIN 表2 别名 ON 连接条件
   WHERE 筛选条件;
   */
   
   #删除张无忌女朋友的信息
   DELETE b
   FROM beauty b
   INNER JOIN boys bo 
   ON b.boyfriend_id=bo.id
   WHERE bo.boyName='张无忌';
   
   #2.truncate
   TRUNCATE TABLE 表名;
   
   /*
   区别：
   -delete 可以加where条件，truncate不能加
   -truncate删除，效率高一丢丢
   -加入要删除的表中有自增长列，
    如果用delete删除后，再插入数据，自增长列的值从断点开始，
    而truncate删除后，再插入数据，自增长列的值从1开始
   -delete删除有返回值，truncate删除没有返回值
   -delete删除可以回滚，truncate删除不可以回滚
   */  
   ```

### 4. DDL

1. 数据定义语言
   + 功能：
     + 库的管理：创建，修改，删除
     + 表的管理：创建，修改，删除

2. 库的管理

   + 语法：

   ```mysql
   #1.创建库
   CREATE DATABASE IF NOT EXISTS books;
   
   #2.更改库的字符集
   ALTER DATABASE books CHARACTER SET gbk;
   
   #3.库的删除
   DROP DATABASE IF EXISTS books;
   ```

3. 表的管理

   + 语法：

   ```mysql
   #1.表的创建
   CREATE TABLE 表名(
   		列名 列的类型[(长度) 约束],
       	列名 列的类型[(长度) 约束],
       	列名 列的类型[(长度) 约束],
       	...
   )
   
   #创建表book
   CREATE TABLE book(
   	id INT,#编号
   	bName VARCHAR(20),#图书名
   	price DOUBLE,#价格
   	authorId INT,#作者编号
   	publishDate DATETIME #出版日期时间
   );
   
   #创建表author
   CREATE TABLE author(
   	id INT,
   	au_name VARCHAR(20),
   	nation VARCHAR(20)
   );
   
   #2.表的修改
   /*
   ALTER TABLE 表名 CHANGE|MODIFY|ADD|DROP 列名 类型;
   */
   
   #修改列名，修改表book列publishDate为pubDate
   ALTER TABLE book CHANGE [COLUMN] publishDate pubDate DATETIME;
   
   #修改类型，修改表book列pubDate类型为TIMESTAMP
   ALTER TABLE book MODIFY [COLUMN] pubDate TIMESTAMP;
   
   #添加新列，添加表author列annual DOUBLE
   ALTER TABLE author ADD [COLUMN] annual DOUBLE;
   
   #删除列，删除表author列annual
   ALTER TABLE author DROP [COLUMN] annual;
   
   #修改表名，修改表author表名为book_author
   ALTER TABLE author RENAME TO book_author;
   
   #3.表的删除
   /*
   DROP TABLE 表名;
   */
   #删除表book_author
   DROP TABLE IF EXISTS book_author;
   
   #4.表的复制
   INSERT INTO author VALUES
   (1,'树上春树','日本'),
   (2,'莫言','中国'),
(3,'冯唐','中国')
   (4,'金庸','中国');
   
   #仅仅复制表的结构
   CREATE TABLE copy LIKE author;
   
   #复制表的结构+数据
   CREATE TABLE copy2
   SELECT * FROM author;
   
   #只复制部分结构
   CREATE TABLE copy3
   SELECT id,au_name
   FROM author
   WHERE 0;
   
   #只复制部分数据
   CREATE TABLE copy4
   SELECT id,au_name
   FROM author
   WHERE nation='中国';
   ```

4. 常见的数据元素

   + 数值型

     + 整型

       | 整型类型    | 字节 | 范围                                   |
       | ----------- | ---- | -------------------------------------- |
       | Tinyint     | 1    | 有符号：-2^7~2^7-1；无符号：0~2^8-1    |
       | Samallint   | 2    | 有符号：-2^15~2^15-1；无符号：0~2^16-1 |
       | Mediumint   | 3    | 有符号：-2^23~2^23-1；无符号：0~2^24-1 |
       | Int,Integer | 4    | 有符号：-2^31~2^31-1；无符号：0~2^32-1 |
       | Bigint      | 8    | 有符号：-2^63~2^63-1；无符号：0~2^64-1 |

       ```mysql
       #1.设置有无符号
       t1 INT, //默认有符号
       t2 INT UNSIGNED, //无符号
       
       #2.超出范围时自动填充临界值
       SET t1=2^31, //t1=2^31-1
       
       #3.不设置长度，默认长度
       t3 INT(5) ZEROFILL, //显示结果的宽度，不够宽度0填充，int为无符号类型
       ```

     + 小数

       | 浮点数类型 | 字节 | 精度    |
       | ---------- | ---- | ------- |
       | float      | 4    | 6~7位   |
       | double     | 8    | 15~16位 |

       | 定点数类型            | 字节 | 精度                                             |
       | --------------------- | ---- | ------------------------------------------------ |
       | dec(m,d),decimal(m,d) | m+2  | 最大取值范围与double相同，有效取值范围由m和d决定 |

       ```mysql
       /*
       浮点数：
       float(M,D)
       double(M,D)
       定点型：
       dec(M,D)
       decimal(M,D)
       
       1.M,D作用
       M：整数部和小数部的宽度
       D：小数部的精度
       如果超出范围，则插入临界值
       
       2.M,D都可省略
       如果是decimal，则M默认为10，D默认为0
       如果是float和double，则会根据插入的数值的精度来决定精度
       
       3.定点型的精确度较高，如果要求插入数值的精度较高如货币运算等则考虑使用
       */
       ```

     + 字符型

       | 字符串类型 | 最多字符数 | 描述及存储           | 特点     | 效率 | 空间 |
       | ---------- | ---------- | -------------------- | -------- | ---- | ---- |
       | char(M)    | M          | M为0~255之间的整数   | 固定长度 | 高   | 低   |
       | varchar(M) | M          | M为0~65535之间的整数 | 长度可变 | 低   | 高   |

     + 位类型

       | 位类型 | 字节 | 范围          |
       | ------ | ---- | ------------- |
       | bit(M) | 1~8  | bit(1)~bit(8) |

     + 日期型

       | 日期和时间类型 | 字节 | 最小值              | 最大值              | 受时区影响 |
       | -------------- | ---- | ------------------- | ------------------- | ---------- |
       | date           | 4    | 1000-01-01          | 9999-12-31          |            |
       | datetime       | 8    | 1000-01-01 00:00:00 | 9999-12-31 23:59:59 |            |
       | timestamp      | 4    | 19700101080001      | 2038年的某个时刻    | 是         |
       | time           | 3    | -838:59:59          | 838:59:59           |            |
       | year           | 1    | 1901                | 2155                |            |

5. 常见约束

   + 含义：一种限制，用于限制表中的数据，为了保证表中的数据的准确和可靠性
   + 分类：
     + NOT NULL：非空，用于保证该字段的值不能位空
     + DEFAULT：默认，用于保证该字段有默认值
     + PRIMARY KEY：主键，用于保证该字段的值具有唯一性，并且非空
     + UNIQUE：唯一，用于保证该字段的值具有唯一性，可以为空
     + CHECK：检查约束[mysql不支持]
     + FOREIGN KEY：外键，限制两个表的关系，用于保证该字段的值必须来自于主表的关联列的值			

   + 添加约束的时机：
     + 创建表时
     + 修改表时

   + 约束的添加分类：
     + 列级约束：六大约束语法上都支持，但外键约束没有效果
     + 表级约束：除了NULL，DEFAULT，其它的都支持

   ```mysql
   #1.创建表时添加列级约束
   CREATE TABLE stuinfo(
   	id INT PRIMARY KEY,#主键
   	stuName VARCHAR(20) NOT NULL,#非空
   	gender CHAR(1) CHECK(gender='男' OR gender='女'),
   	seat INT UNIQUE,#唯一
   	age INT DEFAULT 18,#默认约束
   	majorId INT REFERENCES major(id) #外键
   );
   
   
   CREATE TABLE major(
   	id INT PRIMARY KEY,
   	majorName VARCHAR(20)
   );
   
   #2.创建表时添加表级约束
   CREATE TABLE stuinfo(
   	id INT,
   	stuName VARCHAR(20) NOT NULL,
   	gender CHAR(1),
   	seat INT,
   	age INT,
   	majorid INT,
   	
   	CONSTRAINT pk PRIMARY KEY(id),#主键
   	CONSTRAINT uq UNIQUE(seat),#唯一键
   	CONSTRAINT ck CHECK(gender='男' OR gender='女'),#检查
   	CONSTRAINT fk_stuinfo_major FOREIGN KEY (majorid) REFERENCES major(id)#外键
   );
   
   CREATE TABLE major(
   	id INT PRIMARY KEY,
   	majorName VARCHAR(20)
   );
   
   /*
   外键：
   -要求在从表设置外键关系
   -从表的外键列的类型和主表的关联列的类型要求一致或兼容，名称无要求
   -表的关联列必须是一个key
   -插入数据时，先插入主表，再插入从表
    删除数据时，先删除从表，再删除主表
    */
    
   #3.删除约束
   ALTER TABLE stuinfo DROP PRIMARY KEY;
   ```

   + 标识列

     + 又称为自增长列，可以不用手动的插入值，系统提供默认的序列值

     + 标识列必须搭配key
     + 一个表至多有一个标识列
     + 标识列的类型只能时数值型
     + 标识列可以通过SET auto_increment_increment=设置步长
  + 通过插入数据可以更改起始值

   ```mysql
   #1.创建表时添加标识列
   CREATE TABLE tab_identity(
   	id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(20)
   );
   
   #2.修改表时设置标识列
   ALTER TABLE tab_identity MODIFY COLUMN id INT PRIMARY KEY AUTO_INCREMENT;
   
   #3.修改表时删除标识列
   ALTER TABLE tab_identity MODIFY COLUMN id INT PRIMARY;
   
   ```

### 5. TCL

1. 定义：事务控制语言

   + 事务：一个或一组sql语句组成一个执行单元，这个执行单元要么全部执行，要么全部不执行

   + 支持事务的存储引擎：inndb

   + 事务的ACID属性

     1. 原子性：是指事务时一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生

     2. 一致性：事务必须使数据库从一个一致性状态变换到另外一个一致性状态

     3. 隔离性：是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数

        ​			    据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰

     4. 持久性：持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响

2. 事务的创建

   + 隐式事务：事务没有明显的开启和结束的标记，例如insert,update,delete语句

   + 显式事务：事务具有明显的开启和结束的标记，必须先设置自动提交功能为禁用

     ```Mysql
     #步骤1.开启事务
     set autocommit=0;
     start transaction;可选的
     
     #步骤2：编写事务中的sql语句(select,insert,update,delete)
     语句1;
     语句2;
     ...
     
     #步骤3：结束事务
     commit;提交事务
     rollback;回滚事务
     ```

3. 数据库的隔离级别

   + 类别

   	| 隔离级别         | 描述                                                         | 问题                   |
   | ---------------- | :----------------------------------------------------------- | ---------------------- |
   | READ UNCOMMITTED | 允许事务读物未被同其他事务提交的变更                         | 脏读，不可重复读，幻读 |
   | READ COMMITED    | 只允许事务读取已经被其他事务提交的变更                       | 不可重复读，幻读       |
   | REPEATABLE READ  | 确保事务可以多次从一个字段中读取相同的值，在这个事务持续期间，禁止其他事务对这个字段进行更新 | 幻读                   |
   | SERIALZABLE      | 确保事务可以从一个表中读取相同的行，在这个事务持续期间，禁止其他事务对该表执行插入，更新和删除操作 | 性能低                 |

   + 语法：

     ```mysql
     #1.查看当前隔离级别
     SELECT @@tx_isolation;

     #2.设置当前mysql连接的隔离级别
     SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
     
     #3.设置数据库系统的全局隔离级别
     SET GLOBAL TRANSACTION ISOLATION LEVEL READ COMMITTED;
     ```
     

4. 保存点

   + 语法：

     ```mysql
     #1.打开事务
     SET autocommit=0;
     START TRANSACTION;
     
     #2.编写sql语句
     sql语句1;
     SAVEPOINT a;
     sql语句2;
     
     #3.回滚到a点
     ROLLBACK TO a;
     ```

5. 视图

   + 概念：一种虚拟存在的表，行和列的数据来自定义视图的查询中使用的表，并且是在使用视图时动态生成的，只保存了sql逻辑，不保存查询结果
   + 应用场景：
     + 多个地方用到同样的查询结果
     + 该查询结果使用的sql语句较复杂

   + 示例

     ```mysql
     #1.查询邮箱中包含a字符的员工名，部门名，和工种信息
     #创建
     CREATE VIEW myv1
     AS
     SELECT last_name,department_name,job_title
     FROM employee e
     JOIN departments d ON e.department_id=d.department_id
     JOIN jobs j ON j.job_id=e.job_id;
     
     #使用
     SELECT * FROM myv1 WHERE last_name LIKE '%a%';
     ```

   + 视图的修改

     ```mysql
     #方式一：
     /*
     CREATE OR REPLACE VIEW 视图名
     AS  
     查询语句;
     */
     
     #方式二：
     /*
     ALTER VIEW 视图名
     AS
     查询语句;
     */
     ```

   + 视图的删除

     ```mysql
     DROP VIEW 视图名,视图名,...;
     ```

   + 视图的查看

     ```mysql
     DESC 视图名;
     
     SHOW CREATE VIEW 视图名;
     ```

   + 不能更新的视图SELECT 语句不能包含

     + 分组函数，distinct，group by，having，union，union all
     + 常量视图
     + select包含子查询
     + join
     + from一个不能更新的视图
     + where子句的子查询引用了from子句中的表

### 6.变量

1. 变量的分类：

   + 系统变量：由系统提供的，不用自定义

     + 全局变量：必须拥有super权限才能为系统变量赋值，作用域为整个服务器，也就是针对于所有连接（会话）有效

     + 会话变量：服务器为每一个连接的客户端都提供了系统变量，作用域为当前的连接(会话）

     + 语法：

       ```
       #1.查看系统变量
       SHOW [GLOBAL|SESSION] VARIABLES LIKE ''; 如果没有显示声明global还是session,则默认是seesion
       
       #2.查看指定的系统变量的值
       SELECT @@[GLOABL|SESSION].变量名; 如果没有显示声明global还是session,则默认是seesion
       
       #3.为系统变量赋值
       SET [GLOBAL|SEESSION] 变量名=值;
       SET @@GLOBAL.变量名=值;
       SET @@变量名=值;
       ```

   + 自定义变量

     + 用户变量：针对于当前连接（会话）生效，位置BEGIN END 里边，也可以放在外边

       ```
       #1.声明并赋值：
       SET @变量名=值;
       SET @变量名:=值;
       SELECT @变量名:=值;
       
       #2.更新值
       方式一:
       SET @变量名=值;
       SET @变量名:=值;
       SELECT @变量名:=值;
       
       方式二:
       SELECT xx INTO @变量名 FROM table;
       
       #3.使用
       SELECT @变量名;
       ```

     + 局部变量：只能放在BEGIN END 中有效，而且只能放在第一句

       ```
       #1.声明
       DECLARE 变量名 类型 [DEAFULT 值];
       
       #2.赋值或更新
       方式一:
       SET 变量名=值;
       SET 变量名:=值;
       SELECT 变量名:=值;
       
       方式二:
       SELECT xx INTO 变量名 FROM table;
       
       #3.使用
       SELECT 变量名;
       ```

### 7.存储过程,函数

1. 定义：类似于JAVA中的方法，将一组完成特定功能的逻辑语句包装起来，对外暴露名字
2. 好处：
   + 提高了重用性
   + sql语句简单
   + 减少了和数据库服务器连接的次数，提高了效率

3. 存储过程使用

   + 创建

     ```
     CREATE PROCEDURE 存储过程名(参数模式 参数名 参数类型)
     BEGIN 
     	存储过程体
     END
     
     注意：
     1.参数模式：in,out,inout,其中in可以省略
     2.存储过程体的每一条sql语句都需要用分号结尾
     ```

   + 调用

     ```
     call 存储过程名(实参列表)
     
     举例：
     调用IN模式的参数：call sp1('值');
     调用OUT模式的参数：set @name; call sp1(@name); select @name;
     调用INOUT模式的参数：set @name=值; call sp1(@name); select @name;
     ```

   + 查看

     ```
     SHOW CREATE PROCEDURE 存储过程名;
     ```

   + 删除

     ```
     DROP PROCEDURE 存储过程名;
     ```

4. 函数使用

   + 创建

     ```
     CREATE FUNCTION 函数名(参数名 参数类型) RETURNS 返回类型
     BEGIN
     	函数体
     END
     
     注意：函数体中肯定需要有return语句
     ```

   + 调用

     ```
     SELECT 函数名(实参列表);
     ```

   + 查看

     ```
     SHOW CREATE FUNCTION 函数名;
     ```

   + 删除

     ```
     DROP FUNCTION 函数名;
     ```

     

### 8.流程控制结构

1. 结构分类
   + 顺序结构：程序从上往下一次执行
   + 分支结构：程序按条件进行选择执行，从两条或多条路径中选择一条执行
   + 循环结构：程序满足一定条件下，重复执行一组语句

2. 分支结构：

   + if函数

     ```
     功能：实现简单双分支
     语法：
     if(条件,值1,值2)
     位置：
     可以作为表达式放在任何位置
     ```

   + case结构

     ```
     功能：实现多分支
     
     语法1：
     case 表达式或字段
     when 值1 then 语句1;
     when 值2 then 语句2;
     ...
     else 语句n;
     end [case];
     
     语法2：
     case 
     when 条件1 then 语句1;
     when 条件2 then 语句2;
     ...
     else 语句n;
     end [case];
     
     位置：
     可以放在任何位置，
     如果放在begin end 外面，作为表达式结合着其他语句使用
     如果放在begin end 里面，一般作为独立的语句使用
     ```

   + if结构

     ```
     功能：实现多分支
     
     语法：
     if 条件1 then 语句1;
     elseif 条件2 then 语句2;
     ...
     else 语句n;
     end if;
     
     位置：
     只能放在begin end中
     ```

3. 循环结构

   + 只能放在begin end 中，都能实现循环结构

   + 对比：

     + 三种循环都可省略名称，但如果循环中添加了循环控制语句（leave 或 iterate）则必须添加名称

     + loop 一般用于实现简单的死循环

       while 先判断后执行

       repeat 先执行后判断，至少执行一次

   + 三种分支结构

     ```
     #1.while
     语法：
     [名称:] while 循环条件 do
     			循环体
     end while [名称];
     
     #2.loop
     语法:
     [名称:] loop
     		循环体
     end loop [名称];
     
     #3.repeat
     语法：
     [名称:] repeat
     		循环体
     until 结束条件
     end repeat [名称];
     ```

4. 循环控制语句
   + leave：类似于break，用于跳出所在的循环
   + iterate：类似于continue，用于结束本次循环，继续下一次   