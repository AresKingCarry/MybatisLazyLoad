# MybatisLazyLoad
Mybatis懒加载

使用懒加载，进行查询， 当用数据的时候才会相关的执行sql语句


开启懒加载：
在mybatis的全局文件中，配置打开懒加载：SqlMapConfig.xml


   <settings>
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="aggressiveLazyLoading" value="false"/>
    </settings>
	
	
	
级联查询，mapper：


  <resultMap id="OrderMap" type="com.dmsd.pojo.Orders">
    <id column="id" property="id"/>
    <result column="user_id" property="userId"/>
    <result column="number" property="number"/>
    <result column="createtime" property="createtime"/>
    <result column="note" property="note"/>

    <association property="user" javaType="com.dmsd.pojo.User" select="com.dmsd.dao.UserMapper.selectByPrimaryKey" column="user_id">
    </association>

  </resultMap>

  <select id="queryOrderMap" resultMap="OrderMap">
    SELECT * FROM orders
  </select>
  
  
  
  测试方法：
  
  
      @Test
    public void testqueryOrderMap(){
        List<Orders> ordersList = orderService.queryOrderMap();

        System.out.println("------------------------------------------------------");

        System.out.println(ordersList);
    }
  
  
  
  
  
  
  
  
  
  
  
  查询结果：
  2018-03-08 16:24:50,341 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- ==> Parameters: 
######2018-03-08 16:24:50:344 | took 2ms | statement | connection 0
 SELECT * FROM orders;
2018-03-08 16:24:50,483 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- <==      Total: 3
2018-03-08 16:24:50,484 DEBUG org.mybatis.spring.SqlSessionUtils.closeSqlSession(SqlSessionUtils.java:168) main |- Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@78e16155]
2018-03-08 16:24:50,484 DEBUG org.springframework.jdbc.datasource.DataSourceUtils.doReleaseConnection(DataSourceUtils.java:327) main |- Returning JDBC Connection to DataSource
------------------------------------------------------
2018-03-08 16:26:53,208 DEBUG org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection(DataSourceUtils.java:110) main |- Fetching JDBC Connection from DataSource
2018-03-08 16:26:53,242 DEBUG org.mybatis.spring.transaction.SpringManagedTransaction.openConnection(SpringManagedTransaction.java:86) main |- JDBC Connection [com.p6spy.engine.wrapper.ConnectionWrapper@1c025cb] will not be managed by Spring
2018-03-08 16:26:53,243 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- ==>  Preparing: select id, username, birthday, sex, address from user where id = ? 
2018-03-08 16:26:53,243 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- ==> Parameters: 1(Integer)
######2018-03-08 16:26:53:244 | took 0ms | statement | connection 1
 select 
     
    id, username, birthday, sex, address
   
    from user
    where id = 1;
2018-03-08 16:26:53,245 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- <==      Total: 1
2018-03-08 16:26:53,246 DEBUG org.springframework.jdbc.datasource.DataSourceUtils.doReleaseConnection(DataSourceUtils.java:327) main |- Returning JDBC Connection to DataSource
2018-03-08 16:26:53,247 DEBUG org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection(DataSourceUtils.java:110) main |- Fetching JDBC Connection from DataSource
2018-03-08 16:26:53,247 DEBUG org.mybatis.spring.transaction.SpringManagedTransaction.openConnection(SpringManagedTransaction.java:86) main |- JDBC Connection [com.p6spy.engine.wrapper.ConnectionWrapper@703feacd] will not be managed by Spring
2018-03-08 16:26:53,247 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- ==>  Preparing: select id, username, birthday, sex, address from user where id = ? 
2018-03-08 16:26:53,247 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- ==> Parameters: 1(Integer)
######2018-03-08 16:26:53:248 | took 0ms | statement | connection 2
 select 
     
    id, username, birthday, sex, address
   
    from user
    where id = 1;
2018-03-08 16:26:53,249 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- <==      Total: 1
2018-03-08 16:26:53,250 DEBUG org.springframework.jdbc.datasource.DataSourceUtils.doReleaseConnection(DataSourceUtils.java:327) main |- Returning JDBC Connection to DataSource
2018-03-08 16:26:53,250 DEBUG org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection(DataSourceUtils.java:110) main |- Fetching JDBC Connection from DataSource
2018-03-08 16:26:53,250 DEBUG org.mybatis.spring.transaction.SpringManagedTransaction.openConnection(SpringManagedTransaction.java:86) main |- JDBC Connection [com.p6spy.engine.wrapper.ConnectionWrapper@5ecba515] will not be managed by Spring
2018-03-08 16:26:53,250 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- ==>  Preparing: select id, username, birthday, sex, address from user where id = ? 
2018-03-08 16:26:53,251 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- ==> Parameters: 10(Integer)
######2018-03-08 16:26:53:252 | took 1ms | statement | connection 3
 select 
     
    id, username, birthday, sex, address
   
    from user
    where id = 10;
2018-03-08 16:26:53,253 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:139) main |- <==      Total: 1
2018-03-08 16:26:53,253 DEBUG org.springframework.jdbc.datasource.DataSourceUtils.doReleaseConnection(DataSourceUtils.java:327) main |- Returning JDBC Connection to DataSource
[Orders{id=3, userId=1, number='1000010', createtime=Wed Feb 04 13:22:35 CST 2015, note='null'}, Orders{id=4, userId=1, number='1000011', createtime=Tue Feb 03 13:22:41 CST 2015, note='null'}, Orders{id=5, userId=10, number='1000012', createtime=Thu Feb 12 16:13:23 CST 2015, note='null'}]
  
  
	
