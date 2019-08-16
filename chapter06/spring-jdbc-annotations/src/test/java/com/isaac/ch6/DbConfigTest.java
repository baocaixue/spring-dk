// package com.isaac.ch6;

// import com.isaac.ch6.config.DbConfig;
// import org.junit.Test;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import org.springframework.context.support.GenericApplicationContext;
// import org.springframework.context.support.GenericXmlApplicationContext;

// import javax.sql.DataSource;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertTrue;

// public class 
//     Test {
//     private static Logger logger = LoggerFactory.getLogger(DbConfigTest.class);

//     //@Test
//     public void testXmlConfigDataSource() {
//         GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//         ctx.load("classpath:spring/datasource-dbcp.xml");
//         ctx.refresh();

//         DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
//         assertNotNull(dataSource);

//         testDataSource(dataSource);
//         ctx.close();
//     }

//     //@Test
//     public void testJavaConfigDataSource() {
//         GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);

//         DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
//         assertNotNull(dataSource);
//         testDataSource(dataSource);

//         ctx.close();
//     }

//     private void testDataSource(DataSource dataSource) {
//         Connection connection = null;
//         try {
//             connection = dataSource.getConnection();
//             PreparedStatement statement =  connection.prepareStatement("SELECT 1");
//             ResultSet resultSet = statement.executeQuery();
//             while (resultSet.next()) {
//                 int result = resultSet.getInt("1");
//                 assertTrue(result== 1);
//             }
//             statement.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//             logger.error("Something unexpected happened."+ e);
//         } finally {
//             if (connection != null) {
//                 try {
//                     connection.close();
//                 } catch (SQLException e) {
//                     logger.error("connection close error " + e.getMessage());
//                 }
//             }
//         }
//     }
// }
