## 实现JTA示例的基础结构  
***
嵌入式的H2数据库并不完全支持XA标注，所以，用MySQL作为后端数据库。   

对于演示如何在独立应用程序或Web容器环境中使用JTA实现全局事务，使用了[Atomikos](https://www.atomikos.com/Main/TransactionsEssentials)，这式一个被广泛使用的开源JTA事务管理器，主要用于非JEE环境。   

为了说明全局事务是如何工作的，至少还需要两个后端资源。为了简单，将使用一个MySQL数据库，但使用两个JPA实体管理器来模拟。这样的效果是相同的，因为可以使用多个JPA持久化单元来区分后端数据库

JTA实现全局事务的Spring配置是[XAJpaConfig](../base-dao/src/main/java/com/isaac/ch9/config/XAJpaConfig.java)