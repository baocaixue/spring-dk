# Spring is a wonderful season

Spring5 Advance && Spring Review . 

  `declaration`: spring-dk refer to pro-spring-5    
[refer](https://github.com/Apress/pro-spring-5) .  

****

# Spring模块
| 模块 | 描述 |  
|---|---  
| aop | 该模块包含在应用程序中使用Spring的AOP功能时所需的所有类。如果打算在Spring中使用其他使用了AOP的功能，如声明式事务管理，则需要在应用程序中包含此JAR文件。此外，支持与AspectJ集成的类也封装在此模块中  
| aspect | 该模块包含与AspectJ AOP库进行高级集成的所有类。例如，为完成Spring配置而使用Java类，并且需要AspectJ风格的注解驱动的事务管理，则需要此模块
| beans | 该模块包含所有支持Spring对Spring bean进行操作的类。该模块中的大多数类都支持Spring的bean工厂实现。例如，处理Spring XML配置文件和Java注解所需的类被封装到此模块中
| beans-groovy | 此模块包含用于支持Spring对Spring bean进行操作的Groovy类  
| context | 该模块包含为Spring Core提供许多扩展的类。你会发现所有类都需要使用Spring的ApplicationContext功能以及Enterprise JavaBeans（EJB）、Java Naming and Directory Interface（JNDI）和Java Management Extensions（JMX）集成的类。此模块中还包含Spring远程处理的类，与动态脚本语言（例如 JRuby、Groovy和BeanShell）、JSR-303（Bean Validation）、调度和任务执行等集成的类  
| context-indexer | 该模块包含一个索引器的实现，它提供对META-INF/spring.components中定义的候选项的访问功能，但核心类CandidateComponentsIndex并不能在外部使用  
| context-support | 该模块包含对spring-context模块的进一步扩展。在用户界面方面，有一些用于支持邮件并与模板引擎（例如Velocity、FreeMarker和JasperReports）集成的类。此外，还包括各种任务和调度库（包括CommonJ和Quartz）的集成
| core | 这是每个Spring应用程序都需要的主要模块。在该JAR文件中，可以找到所有其他Spring模块（例如，用于访问配置文件的类）所共享的所有类。另外，在该JAR文件中，会发现在整个Spring代码库中都使用的非常有用的实用程序类，可以在自己的应用程序中使用它们  
| expression | 该模块包含Spring Expression Language（SpEL）的所有支持类  
| instrument | 该模块包含用于JVM启动的Spring工具代理，如果在Spring应用中使用AspectJ实现加载时织入，那么该模块是必须的  
| dbc | 该模块包含所有JDBC支持类。对于需要数据库访问的所有应用程序，都需要此模块。支持数据源、JDBC数据类型、JDBC模板、本地JDBC连接等的类都被打包在此模块中  、
| jms | 该模块包含了JMS支持的所有类  
| orm | 该模块扩展了Spring的标准JDBC功能集，支持流行的ORM工具，包括Hibernate、JDO、JPA和数据映射器iBATIS。该JAR文件中的许多类都依赖于spring-jdbc JAR文件中所包含的类，因此也需要把它包含在应用程序中  
| oxm | 该模块为Object/XML映射（OXM）提供支持。用于抽象XML编组和解组以及支持Castor、JAXB、XMLBean和XSteam等常用工具的类都包含在此模块中  
| test | Spring提供一组模拟类来帮助测试应用程序，并且许多模拟类都在Spring测试套件中使用，所以它们都经过了很好的测试，从而使测试应用程序变得简单。在对Web应用程序进行单元测试时会发现模拟HttpServletRequest和HttpServletResponse类所带来的好处。另一方面，Spring提供了与JUnit单元测试框架的紧密集成，并且在该模块中提供了许多支持JUnit测试用例开发的类；例如，SpringJUnit4ClassRunner提供了一种在单元测试环境中引导Spring ApplicationContext的简单方法
| tx | 该模块提供支持Spring事务基础架构的所有类。可以从事务抽象层找到相应的类来支持Java Transaction API（JTA）以及与主要供应商的应用程序服务器的集成  
| web | 此模块包含在Web应用中使用Spring所需的核心类，包括用于自动加载ApplicationContext功能的类、文件上传支持类以及一些用于执行重复任务（比如从查询字符串解析整数）的有用类  
| web-reactive | 该模块包含Spring Web Reactive模型的核心接口和类  
| web-mvc | 该模块包含Spring自己的MVC框架的所有类。如果想要为应用程序使用单独的MVC框架，则不需要此JAR文件中的任何类  
| webfsocket | 该模块提供对JSR-356（WebSocket的Java API）的支持  


## Note  
## [Chapter03 IOC&&DI](./chapter03)  
  - [ApplicationContextAware](./chapter03/bean-autowiring/src/main/java/com/isaac/ch3/annotated/Singer.java)   
## [Chapter04 Spring Config && Spring Boot](./chapter04)
### Bean生命周期——bean创建
 * `创建解析顺序：Spring首先调用使用了@PostConstruct注解的方法，然后调用afterPropertiesSet()方法，最后调用配置文件中指定的初始化方法`
 * （1）首先调用构造函数来创建bean
 * （2）注入依赖项（setter）
 * （3）现在bean已经存在并提供了依赖项，预初始化的BeanPostProcessor基础结构bean将被查询。这些特定于Spring的基础架构bean，它们在创建后
  执行bean修改操作。@PostConstruct注解由CommonAnnotationBeanPostProcessor注册，所以该bean将调用使用了@PostConstruct注解的方法。
  该方法在bean被构建之后，在类被投入使用之前且在bean的实际初始化之前（在afterPropertiesSet()和init-method之前）执行
 * （4）InitializingBean的afterPropertiesSet()方法在注入依赖之后立即执行。如果BeanFactory设置了提供的所有Bean属性并且满足BeanFactoryAware
 * 和ApplicationContextAware，将会调用afterPropertiesSet()方法
 * （5）最后执行init-method属性，这是因为它是bean的实际初始化方法
###  Bean生命周期——bean销毁
 *  当使用封装了DefaultListableBeanFactory接口的ApplicationContext是实现（例如，通过getDefaultListableBeanFactory()方法获取的GenericXmlApplicationContext）时，
 可以通过调用ConfigurableBeanFactory.destroySingletons()向BeanFactory发出信号，告知销毁所有单例实例。通常，在应用程序关闭时执行此操作，并允许清理bean可能保持打开的任何
 资源，从而使应用程序可以正常关闭。此外，在该回调中还可以将存储在内存中的任何数据刷新到持久存储库中，并允许bean关闭可能已启动的长时间运行的任何进程。
 为了让bean接收到destroySingletons()被调用的通知，存在三种选择——方法、实现DisposableBean接口、JSR-250 @PreDestroy注解
 * `销毁解析顺序：Spring首先调用@PreDestroy注解的方法，然后调用DisposableBean.destroy()，最后调用XML定义中配置的destroy()方法`
 * [使用关闭钩子](./chapter04/shutdown-hook/src/main/java/com/isaac/ch4/DestructiveBeanWithHook.java)
### FactoryBean `创建并注入不能简单地使用new运算符创建的依赖项。FactoryBean是一个Bean，在ApplicationContext中配置，但当Spring使用FactoryBean接口来满足依赖或查找请求时，它并不返回FactoryBean，而是调用FactoryBean.getObject()方法并返回调用结果`
* 用途：创建事务代理、JNDI上下文自动获取资源等    
### 属性编辑器 Spring PropertyEditor  
  
| PropertyEditor | 描述 |  
|---|---  
| ByteArrayPropertyEditor | 将字符串值换为相应的字节表示形式   
| CharacterEditor | 从String值填充Character或char类型的属性   
| ClassEditor | 从完全限定的类名转换为Class实例。当使用此PropertyEditor时，请小心在使用GenericXmlApplicationContext时，不要在类名的任何一侧包含任何多多余的空格，这会导致ClassNotFoundException    
| CustomBooleanEditor | 将字符串转换为Java Boolean类型  
| CustomCollectionEditor | 将源集合（例如，由Spring中util名称空间表示）转换为Collection类型  
| CustomDateEditor | 将日期的字符串表示形式转换为java.util.Date值。需要在Spring的ApplicationContext中以所需的日期格式注册CustomDateEditor实现  
| FileEditor | 将String文件路径转换为File实例。Spring不检查文件是否存在  
| InputStreamEditor | 将资源的字符串表示（例如，使用file:D:/temp/test.txt或classpath:test.txt的文件资源）转换为输入流属性  
| LocalEditor | 将语言环境字符串表示形式（如en-GB）转换为java.util.Local实例  
| PatternEditor | 将字符串转换为JDK Pattern对象  
| PropertiesEditor | 以格式key1=value1 key2=value2 keyn=valuen将字符串转换为配置了相应属性的java.util.Properties实例  
| StringTrimmerEditor | 在注入前对字符串值进行修建。需要明确注册该编辑器  
| URLEditor | 将URL的字符串表示形式转换为java.net.URL的实例  
    
## [chapter05 Spring AOP](./chapter05) 
`不能通知最终的类，它们不能被覆盖，不能被代理`   
### AOP概念
* 连接点：应用程序执行期间明确定义的一个点。连接点的典型示例包括方法的调用、方法调用本身、类初始化和对象实例化。连接点是AOP的核心概念，并且定义了在应用程序中可以使用AOP插入其他逻辑的点
* 通知：在特定连接点执行的代码，由类中的方法定义。
* 切入点：用于定义何时执行通知的连接点集合。通过创建切入点，可以更细致地控制如何将通知应用于应用程序中的组件。如前所述，典型的连接点是方法调用，或是特定类中的所有方法调用的集合。通常情况，可以在复杂的关系中插入切入点，从而进一步限制执行通知的时间
* 切面：封装在类中的通知和切入点的组合。这种组合定义了应该包含在应用程序中的逻辑及其应该执行的位置
* 织入：织入是在适当的位置将切面插入到应用程序代码中的过程。对于编译时AOP解决方案，织入过程通常在生成时完成。同样，对于运行时AOP解决方案，织入过程在运行时动态执行。此外，AspectJ还支持一种称为加载时织入（LTW）的织入机制，在该机制中，拦截底层的JVM类加载器，并在类加载器加载字节码时向其提供织入功能
* 目标对象：执行流由AOP进程修改的对象称为目标对象。也叫通知对象
* 引入：通过引入其他方法或字段来修改对象结构的过程。通过引入AOP来使任何对象实现特定的接口，而无需对象的类显式地实现该接口
### 静态AOP && 动态AOP 
### [AOP Alliance](http://aopalliance.sourceforge.net/)
为AOP实现定义了一组标准接口，Spring应该使用AOP Alliance接口而不是定义自己的接口
    
