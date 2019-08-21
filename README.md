# Spring is a wonderful season  
> [![Build Status](https://travis-ci.org/baocaixue/spring-dk.svg?branch=master)](https://travis-ci.org/baocaixue/spring-dk) [![Gitter](https://badges.gitter.im/spring-dk/community.svg)](https://gitter.im/spring-dk/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

This repository is a reading note according to [Pro Spring 5th Edition](http://www.apress.com/9781484228074) by Iuliana Cosmina, Rob Harrop, Chris Schaefer, Clarence Ho (Apress, 2017). 

https://github.com/Apress/pro-spring-5

****
# Catalog  
- [3 IOC DI](#Chapter03-ioc-di)...........................................[IOC-DI](./chapter03)
- [4 Spring配置和SpringBoot](#Chapter04-Spring-Config-And-Spring-Boot) ...........................................[Config](./chapter04)
- [5 Spring AOP](#Chapter05-Spring-AOP) ...........................................[AOP](./chapter05)
- [6 Spring JDBC](#Chapter06-Spring-JDBC)...........................................[JDBC](./chapter06)
- [7 Spring中使用Hibernate](#Chapter07-Hibernate)...........................................[Hibernate](./chapter07)
- [8 Spring中使用JPA2](#Chapter08-JPA2) ...........................................[JPA2](./chapter08)
- [9 事务管理](#Chapter09-Transaction)...........................................[Transaction](./chapter09)
- [10 类型转换和格式化进行验证](#Chapter10-Conversion-And-Validator)...........................................[Conversion-Validator](./chapter10)
- [11 任务调度](#Chapter11-TaskScheduler)...........................................[Task-Scheduler](./chapter11)
- [12 Spring远程处理](#Chapter12-Spring-Remoting)...........................................[Remoting](./chapter12)
- [13 Spring测试](#Chapter13-Spring-Test) ...........................................[Test](./chapter13)
- [14 Spring中脚本支持](#Chapter14-Spring-Script) ...........................................[Script](./chapter14)
- [15 应用程序监控](#Chapter15-Application-Monitoring) ...........................................[Monitoring](./chapter15)
- [16 Web应用程序]()
- [17 WebSocket]()
- [18 Spring项目：批处理、集成和XD等]()

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
***
## Chapter03-ioc-di
  - [ApplicationContextAware](./chapter03/bean-autowiring/src/main/java/com/isaac/ch3/annotated/Singer.java)  
***
## Chapter04-Spring-Config-And-Spring-Boot
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
 *  &nbsp;&nbsp;&nbsp;&nbsp;当使用封装了DefaultListableBeanFactory接口的ApplicationContext是实现（例如，通过getDefaultListableBeanFactory()方法获取的GenericXmlApplicationContext）时，
 可以通过调用ConfigurableBeanFactory.destroySingletons()向BeanFactory发出信号，告知销毁所有单例实例。通常，在应用程序关闭时执行此操作，并允许清理bean可能保持打开的任何
 资源，从而使应用程序可以正常关闭。此外，在该回调中还可以将存储在内存中的任何数据刷新到持久存储库中，并允许bean关闭可能已启动的长时间运行的任何进程。
 为了让bean接收到destroySingletons()被调用的通知，存在三种选择——方法、实现DisposableBean接口、JSR-250 @PreDestroy注解
 * `销毁解析顺序：Spring首先调用@PreDestroy注解的方法，然后调用DisposableBean.destroy()，最后调用XML定义中配置的destroy()方法`
 * [使用关闭钩子](./chapter04/shutdown-hook/src/main/java/com/isaac/ch4/DestructiveBeanWithHook.java)
### FactoryBean 
`创建并注入不能简单地使用new运算符创建的依赖项。FactoryBean是一个Bean，在ApplicationContext中配置，但当Spring使用FactoryBean接口来满足依赖或查找请求时，它并不返回FactoryBean，而是调用FactoryBean.getObject()方法并返回调用结果`
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
    
***    
## Chapter05-Spring-AOP 
`不能通知最终的类，它们不能被覆盖，不能被代理`   
### AOP概念
* 连接点：应用程序执行期间明确定义的一个点。连接点的典型示例包括方法的调用、方法调用本身、类初始化和对象实例化。连接点是AOP的核心概念，并且定义了在应用程序中可以使用AOP插入其他逻辑的点
* 通知：在特定连接点执行的代码，由类中的方法定义。
* 切入点：用于定义何时执行通知的连接点集合。通过创建切入点，可以更细致地控制如何将通知应用于应用程序中的组件。如前所述，典型的连接点是方法调用，或是特定类中的所有方法调用的集合。通常情况，可以在复杂的关系中插入切入点，从而进一步限制执行通知的时间
* 切面：封装在类中的通知和切入点的组合。这种组合定义了应该包含在应用程序中的逻辑及其应该执行的位置
* 织入：*织入是在适当的位置将切面插入到应用程序代码中的过程。对于编译时AOP解决方案，织入过程通常在生成时完成。同样，对于运行时AOP解决方案，织入过程在运行时动态执行。此外，AspectJ还支持一种称为加载时织入（LTW）的织入机制，在该机制中，拦截底层的JVM类加载器，并在类加载器加载字节码时向其提供织入功能*
* 目标对象：执行流由AOP进程修改的对象称为目标对象。也叫通知对象
* 引入：通过引入其他方法或字段来修改对象结构的过程。通过引入AOP来使任何对象实现特定的接口，而无需对象的类显式地实现该接口
### 静态AOP && 动态AOP 
### [AOP Alliance](http://aopalliance.sourceforge.net/)
&nbsp;&nbsp;&nbsp;&nbsp;为AOP实现定义了一组标准接口，Spring应该使用AOP Alliance接口而不是定义自己的接口
### Spring AOP架构
&nbsp;&nbsp;&nbsp;&nbsp;Spring AOP的核心架构**基于代理**。要创建一个类的被通知实例，必须使用**ProxyFactory**创建该类的代理实例，要向ProxyFactory提供想要织入到代理的所有切面。使用ProxyFactory是创建AOP代理的纯程序化方法。在Spring中可以依赖Spring所提供的声明式AOP配置机制（**ProxyFactoryBean类、aop名称空间、@AspectJ样式注解**）来完成声明式代理的创建。  
在运行时，Spring会分析ApplicationContext中的bean定义的横切关注点，并动态生成代理bean（封装了底层目标bean）。此时不会直接调用目标bean，而是将调用者注入代理bean。然后，代理bean分析运行条件（即连接点、切入点、通知）并相应地织入适当的通知。Spring有两个代理实现：**JDK动态代理和CGLIB代理** 默认情况下，当被通知的目标对象实现一个接口时，Spring将使用JDK动态代理来创建目标的代理实例。但是，当被通知目标对象没实现接口时，将使用CGLIB来创建代理实例。（tips：JDK动态代理仅支持接口代理）
- Spring中的连接点  
  Spring AOP最明显的简化是`只支持一种连接点类型：方法调用`。
- Spring中的切面  
  在Spring AOP中切面由`实现了Advisor接口的类的实例表示`。Spring提供了可以在应用程序中重复使用、便捷的Advisor实现，从而无需创建自定义的Advisor实现。Advisor有两个子接口：PointCutAdvisor和IntroductionAdvisor。所有的Advisor实现都实现了PointcutAdvisor接口，这些实现使用切入点来控制应用于连接点的通知。在Spring中，引言被视为特殊类型的通知，通过使用IntroductionAdvisor接口，可以控制将引言用于哪些类。  
- ProxyFactory类  
- Spring中创建通知      

    | 通知名称 | 接口 | 描述   
    |---|---|--- 
    | 前置通知 | org.springframework.aop.MethodBeforeAdvice | 通过使用前置通知，可以在连接点执行之前完成自定义处理。因为Spring中的连接点就是方法调用，所以通常允许方法执行之前执行预处理。虽然前置通知可以完全访问方法调用的目标以及传递给方法的参数，但却无法控制方法本身的执行。如果前置通知抛出异常，那么拦截器链（以及目标方法）的进一步执行将被终止，并且异常将传回拦截器链  
    | 后置返回通知 | org.springframework.aop.AfterReturningAdvice | 在连接点的方法调用完成执行并返回一个值后执行后置返回通知。后置返回通知可以访问方法调用的目标、传递方法参数以及返回值。由于方法在调用后置返回通知时已执行，因此无法控制方法调用。如果目标方法抛出异常，则不会运行后置返回通知，并且异常将照常传回调用堆栈  
    | 后置通知 | org.springframework.aop.AfterAdvice | 仅当被通知方法正常完成时才执行后置通知。然而，无论被通知方法的结果如何，后置通知都会被执行。即使被通知方法失败并抛出异常，后置通知也会执行  
    | 环绕通知 | org.aopalliance.intercept.MethodInterceptor | 在Spring中，环绕通知使用方法拦截器的AOP Alliance标准进行建模。环绕通知允许在方法调用之前和之后执行，并且可以控制允许进行方法调用的点。如果需要，可以完全绕过方法，从而提供自己的逻辑实现  
    | 异常通知 | org.springframework.aop.ThrowsAdvice | 异常通知在方法调用返回后执行，但只有在该调用抛出异常时执行。异常通知只能捕获特定的异常，如果使用异常通知，则可以访问抛出异常的方法、传递的参数以及调用的目标  
    | 引入通知 | org.springframework.aop.IntroductionInterceptor | Spring将引入建模为特殊类型的拦截器。通过使用引入拦截器，可以指定由引入通知引入的方法的实现  
    
### Spring中使用顾问和切入点  
  &nbsp;&nbsp;&nbsp;&nbsp;ProxyFactory类提供了一种简单的方法来获取和配置自定义用户代码中的AOP代理实例。ProxyFactory.addAdvice()方法用于配置代理的通知，此方法在后台委托给addAdvisor()，创建**DefaultPointcutAdvisor**的实例并使用指向所有方法的切入点对其进行配置，这样的通知适用于目标对象上的所有方法。对于限制通知适用的方法，如果在通知中检查是可以的，但是会有一系列问题，如性能、代码耦合性，而适用切入点可以很好的解决这类问题。
  `对于通知与目标之间的耦合-目标关联性。一般来说，当通知与目标关联性很小或没有，应该使用切入点；而关联性较强时，应该在通知内检查通知是否被正确使用。此外，还要避免不必要的通知方法，这会导致调用速度明显下降`
- Pointcut接口  
    ```java
    package org.springframework.aop;
    public interface PointCut {
        ClassFilter getClassFilter();
        MethodMatcher getMethodMatcher();
    }
    ```
    &nbsp;&nbsp;&nbsp;&nbsp;Spring提供了一些可供选择的Pointcut实现，它们覆盖了大部分用例。  
    当确定Pointcut是否适用于特定的方法时，Spring 首先使用Pointcut.getClassFilter()返回的ClassFilter实例检查是否适用。ClassFilter接口如下：
    ```java
    package org.springframework.aop;
    public interface MethodMatcher { 
        boolean matches(Method m, Class<?> targetClass);
        boolean isRuntime();
        boolean matches(Method m, Class<?> targetClass, Object[] args);
    }
    ```
    &nbsp;&nbsp;&nbsp;&nbsp;Spring支持两种类型的MethodMatcher——静态和动态的MethodMatcher，具体哪种由isRuntime()的返回值决定，false表示静态、true表示动态。
    对于静态切入点，Spring会针对目标上的每个方法调用一次MethodMatcher的matches(Method, Class<T>)方法，并缓存返回值，以便后续调用。
    使用动态切入点，在第一次调用方法来确定方法的整体适用性时，也是通过matches(Method, Class<T>)执行静态检查。如果返回true，Spring将使用matches(Method, Class<T>, Object[])对每个方法调用执行进一步的检查。
    
    一般来说，静态切入点比动态切入点执行得更好，但动态切入点更灵活。应尽可能使用静态切入点，但是，如果所使用的通知增加了大量开销，那么使用动态切入点避免不必要的通知是比较明智的。  
- 可用的切入点实现  

    | 实现类 | 描述   
    |---|---
    |org.springframework.aop.support.annotation.AnnotationMatchingPointcut | 此实现在类或方法上查找特定的Java注解。该类需要JDK5或更高版本  
    |org.springframework.aop.aspectj.AspectJExpressionPointcut | 此实现使用AspectJ织入器以AspectJ语法评估切入点表达式  
    |org.springframework.aop.support.ComposablePointcut | ComposablePointcut类使用诸如union()和intersection()等操作组合两个或多个切入点  
    |org.springframework.aop.support.ControlFlowPointcut | ControlFlowPointcut是一种特殊的切入点，它们匹配另一个方法的控制流中的所有方法，即任何作为另一个方法的结果而直接或间接调用的方法  
    |org.springframework.aop.support.DynamicMethodMatcherPointcut | 此实现旨在作为构建动态切入点的基类  
    |org.springframework.aop.support.JdkRegexpMethodPointcut | 该实现允许JDK1.4中正则表达式支持定义切入点  
    |org.springframework.aop.support.NameMatchMethodPointcut | 可以创建一个切入点，对方法名称列表执行简单匹配  
    |org.springframework.aop.support.StaticMethodMatcherPointcut | 用作构建静态切入点的基础 
    
### 代理  
&nbsp;&nbsp;&nbsp;&nbsp;`JDK Proxy类创建的JDK代理 和 使用CGLIB Enhancer类创建的基于CGLIB的代理`  
  &nbsp;&nbsp;&nbsp;&nbsp;代理的核心目标是**拦截方法调用**，并在必要时执行适用于特定方法的通知链。通知的管理和调用基本上是独立于代理的，由Spring AOP管理的。而代理主要负责拦截所有对方法的调用，并将它们根据需要传递给AOP框架，以便应用通知。
  除了上述核心功能，代理还必须支持一组附加功能。可以通过AopContext类（抽象类）配置代理以公开自己，以便可以检索代理并从目标对象调用代理上的被通知方法。当通过`ProxyFactory.setExposeProxy()`启动该功能时，代理负责确保代理类被适当地公开。另外，所有代理类默认实现`Advised`接口，从而允许在创建代理之后更改通知链。
  代理还必须确保任何返回代理类（返回代理目标）的方法实际上返回的是代理而不是目标。
  
- JDK动态代理  
    &nbsp;&nbsp;&nbsp;&nbsp;JDK代理是Spring中最基本的代理，**JDK代理只能生成接口的代理**，而不能生成类的代理。代理的任何对象都必须至少实现一个接口，并且生成的代理将是实现该接口的对象。
    当使用JDK代理时，所有方法调用都会被JVM拦截并路由到代理的invoke()方法。然后由invoke()方法确定是否通知有关方法（根据由切入点定义的规则），如果确定要通知，则通过使用反射调用通知链，然后调用方法本身。
    在调用invoke()之前，JDK代理无法区分被通知方法和未被通知方法。这意味着对于代理上的未被通知的方法，invoke()方法仍然会被调用，所有检查仍然会执行，并且仍然可以通过使用反射进行调用。显然，每次调用方法时，都会导致运行时开销，即使代理不会执行额外的处理，而只是通过反射调用未被通知的方法。
    *通过使用`setInterfaces()`（位于由ProxyFactory类间接扩展的AdvisedSupport类中）指定要代理的接口列表，从而指示ProxyFactory使用JDK代理*

- CGLIB代理  
    &nbsp;&nbsp;&nbsp;&nbsp;CGLIB会为每个代理动态生成新类的字节码，并尽可能重用以生成的类。**所生成的代理类型将是目标对象类的子类**
    首次创建CGLIB代理时，CGLIB会询问Spring如何处理每个方法，这意味着每次调用JDK代理上的invoke()时所执行的许多决策对于CGLIB代理来说只会执行一次。由于CGLIB生成实际的字节码，因此在处理方法的方式上有更多的灵活性。例如，CGLIB代理可以生成适当的字节码来直接调用任何未被通知方法，从而减少代理所带来的开销。另外CGLIB代理可以确定一个方法是否返回代理，如果不返回，则允许直接调用方法，从而进一步减少运行时的开销
    CGLIB代理还以不同于JDK代理的方式处理固定的通知链。固定通知链是在代理生成后不会更改的链。默认情况下，即使在代理创建后，也可以更改代理上的顾问和通知，虽然很少这么做。CGLIB代理以特定方式处理固定通知链，从而减少执行通知链的运行时间开销

- 选择要使用的代理    
    CGLIB代理可以代理类和接口，而JDK代理只能代理接口。在性能方面，除非在冻结模式下使用CGLIB，否则JDK和CGLIB标准模式之间没有显著差异（至少在运行被通知和未被通知的方法时没有显著差异）。在这种情况下，通知链不能更改且CGLIB在冻结模式下会进行进一步的优化。当需要代理类时，CGLIB代理是默认选择，因为它是唯一能够生成类代理的代理。如果想要在代理接口时使用CGLIB代理，必须使用setOptimize()方法将ProxyFactory中的optimize标志设为true

### 引入  
  &nbsp;&nbsp;&nbsp;&nbsp;引入（Introduction）是Spring中可用的AOP功能集的重要组成部分。通过使用引入，可以动态的向现有对象引入新功能。在Spring中，可以将任何接口的实现引入现有对象。  
  &nbsp;&nbsp;&nbsp;&nbsp;Spring将引入视为一种特殊类型的通知，更具体地说，将其作为一种特殊类型的环绕通知。由于引入仅适用于类级别，因此不饿能在引入时使用切入点；在语义上讲，两者不匹配。**引入将新的接口实现添加到类中，而切入点定义了通知适用于哪些方法**。  
  &nbsp;&nbsp;&nbsp;&nbsp;可以通过实现`IntroductionInterceptor`接口来创建引入，该接口扩展了MethodInterceptor和DynamicIntroductionAdvice接口。MethodInterceptor接口定义了invoke()方法，通过使用此方法，可以为所引入的接口提供实现，并根据需要对任何其他方法执行截取操作。_Spring提供了**DelegatingIntroductionInterceptor**的IntroductionInterceptor的默认实现_。可以创建一个既继承了DelegatingIntroductionInterceptor，又实现了想要引入的接口的类。然后，DelegatingIntroductionInterceptor实现简单地将所有引入方法的调用委托给相应的方法。  
  &nbsp;&nbsp;&nbsp;&nbsp;当使用标准通知（不是引入）时，可能会将相同的通知实例用于多个对象。Spring文档将其称为`基于类型的声明周期（per-class life cycle）`，可以为许多类使用单个通知实例。对于引入来说，引入通知构成了被通知对象的状态的一部分，因此，针对每个被通知的对象都有一个独立的引入实例。这被称为`基于实例的生命周期（per-instance life cycle）`。因为必须确保每个被通知对象都有一个独立的引入实例，所以通常最好创建DefaultIntroductionAdvisor的一个子类，它负责创建引入通知。
  
***
## Chapter06-Spring-JDBC
&nbsp;&nbsp;&nbsp;&nbsp;lambda表达式是使用Spring JDBC支持的理想方式

### Spring JDBC基础结构
- 概述以及要使用的包  

    | 包 | 描述   
    |---|---
    | org.springframework.jdbc.core | 该包包含Spring中JDBC类的基础，包括核心JDBC类JdbcTemplate，它简化了使用JDBC编写数据库操作的过程。几个子包提供了JDBC数据访问支持，具有更多特定用途（例如，支持命名参数的JdbcTemplate类）以及相关的支持类  
    | org.springframework.jdbc.datasource | 该包包含辅助类和DataSource实现，可用来在JEE容器外运行JDBC代码。几个子包提供了对嵌入式数据库、数据库初始化和各种数据源查找机制的支持  
    | org.springframework.jdbc.object | 该包包含有助于将数据库返回的数据转换为对象或对象列表的类。这些对象和列表是纯Java对象，因此于数据库断开连接  
    | org.springframework.jdbc.support | 该包中最重要的类是SQLException翻译支持。它允许Spring识别数据库所使用的错误代码并将它们映射到更高级别的异常  
    | org.springframework.jdbc.config | 该包包含支持Spring的ApplicationContext中JDBC配置的类。例如，它包含用于jdbc名称空间（如：\<jdbc:embeddeddatabase\>标记）的处理程序类  
 

### 数据库连接和数据源  
&nbsp;&nbsp;&nbsp;&nbsp;可以提供一个实现了`javax.sql.DataSource`的bean，从而使用Spring来帮助管理数据库连接。_DataSource和Connection之间的区别在于DataSource可以提供并管理Connection。  
&nbsp;&nbsp;&nbsp;&nbsp;`DriverManagerDataSource`（位于org.springframework.jdbc.datasource中）是DataSource的最简单实现，它只是调用DriverManager来获得连接。_因为DriverManagerDataSource不支持数据库连接池，因此此类不适用于除测试外的其他应用_。


### 建模JDBC操作的Spring类
- MappingSqlQuery<T> 允许将查询字符串和mapRow()方法一起封装到一个类中
- SqlUpdate 封装任何SQL更新语句，同时还提供了许多有用的功能，以便绑定SQL参数，在插入新的记录后检索RDBMS生成的键等
- BatchSqlUpdate 允许执行批量更新操作。例如，可以遍历Java List对象并让BatchSqlUpdate对记录进行排序，然后批量提交更新语句。可以随时设置批量大小并刷新操作
- SqlFunction<T> 允许使用参数和返回类型调用数据库中的存储函数。此外，还可以使用另一个类StoredProcedure来帮助调用存储过程
- 使用注解来设置JDBC DAO

***
## Chapter07-Hibernate
***
问题：
```text
...
Caused by: org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
	at org.springframework.orm.hibernate5.SpringSessionContext.currentSession(SpringSessionContext.java:137)
	at org.hibernate.internal.SessionFactoryImpl.getCurrentSession(SessionFactoryImpl.java:456)
	at com.isaac.ch7.dao.InstrumentDaoImpl.save(InstrumentDaoImpl.java:17)
	at com.isaac.ch7.config.InitDb.afterPropertiesSet(InitDb.java:32)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1753)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1690)
	... 35 more
```
解决启用注解声明事务@EnableTransactionManagement,[详见](https://stackoverflow.com/questions/26203446/spring-hibernate-could-not-obtain-transaction-synchronized-session-for-current)  

***
## Chapter08-JPA2
&nbsp;&nbsp;&nbsp;&nbsp;在使用ORM方法实现数据访问逻辑时使用Hibernate和Spring，其中，Hibernate的使用方式是：配置SessionFactory，使用Session接口进行数据操作。但是，还有另一种Hibernate的使用方式：使用Hibernate作为**标准Java持久化API（JPA）** 的持久化提供程序  
&nbsp;&nbsp;&nbsp;&nbsp;JPA提供了标准化的概念，可以轻松在JPA持久化提供程序（如Hibernate、EclipseLink、Oracle TopLink和Apache OpenJpa）之间转换。  

### JPA2.1
&nbsp;&nbsp;&nbsp;&nbsp;与其他Java规范请求（JSR）一样，JPA2.1规范（JSR-338）的目标是在JSE和JEE环境中**对ORM编程模型进行标准化**。 它定义了JPA持久化提供程序应该实现的一组通用概念、注解、接口和其他服务。按JPA标准进行编程，可以随意切换底层提供程序。   
&nbsp;&nbsp;&nbsp;&nbsp;在JPA中，**核心概念是EntityManager接口**，它是来自EntityManagerFactory类型的工厂。EntityManager的主要工作是维护一个持久化上下文，在该上下文中存储由其管理的所有实体实例。EntityManager的配置被定义为一个持久化单元，并且在应用程序中可以有多个持久化单元。如果使用的是Hibernate，那么可以像使用Session接口一样使用持久化上下文。同样，EntityManagerFactory等同于SessionFactory。在Hibernate中，托管实体存储在会话中，可以通过Hibernate的SessionFactory或Session接口直接与会话进行交互。但是，在JPA中，不能直接与持久化上下文交互。需要依靠EntityManager来完成相关工作。   
### 配置JPA的EntityManagerFactory
Spring支持三种类型的EntityManagerFactory的配置    
- LocalEntityManagerFactoryBean类，这是最简单的一种，只需持久化单元名称，但不支持DataSource注入，因此无法参与全局事务，只能适用于简单的开发目的    
- 用于JEE兼容的容器，其中应用程序服务器根据部署描述符中信息启动JPA持久化单元。这样就允许Spring通过JNDI查找来查找实体管理器。下面代码描述了通过JNDI查找实体管理器所需的元素      
    ```
    <beans ...>
        <jee:jndi-lookup id="emf" jndi-name="persistence/xxx"/>
    </beans>
    ```   
- LocalContainerEntityManagerFactoryBean类，支持DataSource注入并可以参与本地和全局事务   

### 使用JPA2 Criteria API进行条件查询   
&nbsp;&nbsp;&nbsp;&nbsp;在JPA2中，引入的一个主要新功能是强类型的Criteria API查询。在新的Criteria API中，传递到查询中的条件是**基于映射的实体类的元模型**。因此，所指定的每个条件都是强类型的，并且在编译而不是运行时会发现错误。   
&nbsp;&nbsp;&nbsp;&nbsp;在JPA Criteria API中，实体类的元模型由具有下划线（_）后缀的实体类名称表示。https://docs.jboss.org/hibernate/jpamodelgen/1.3/reference/en-US/html_single/#d0e302   
```java
@javax.annotation.Generated(value = "org.hibernate.jpamodelgen.JPAModelEntityProcessor")
@StaticMetamodel(Singer.class)
public abstract class Singer_{
    public static volatile SingularAttribute<Singer, String> firstName;
    public static volatile SingularAttribute<Singer, String> lastName;
    public static volatile SetAttribute<Singer, Album> albums;
    public static volatile SetAttribute<Singer, Instrument> instruments;
    public static volatile SingularAttribute<Singer, Long> id;
    public static volatile SingularAttribute<Singer, Integer> version;
    public static volatile SingularAttribute<Singer, Date> birthDay;
}
```   

### Spring Data JPA的审计功能（跟踪实体类的变化）  
&nbsp;&nbsp;&nbsp;&nbsp;Spring Data JPA项目以实体监视器的形式提供了该功能，可以帮助自动跟踪审计信息。在Spring4之后，实体类要实现**Auditable<U, ID extends Serializable, T extends TemporalAccessor> extends Persistable<ID>** 接口或扩展实现该接口的类。   
```java
package org.springframework.data.domain;
import java.io.Serializable;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public interface Auditable<U, ID extends Serializable, T extends TemporalAccessor> extends Persistable<ID> {
    Optional<U> getCreatedBy();
    void setCreatedBy(U createdBy);
    Optional<T> getCreatedDate();
    void setCreatedDate(T createdDate);
    Optional<U> getLastModifiedBy();
    void setLastModifiedBy(U lastModifiedBy);
    Optional<T> getLastModifiedDate();
    void setLastModifiedDate(T lastModifiedDate);
}
```   
`Spring5 开始实现Auditable不再是必须的，@CreatedBy、@CreatedDate、@LastModifiedBy、@LastModifiedDate注解`  

### Hibernate Envers实现实体版本自动化
&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;Envers审计策略  
 
| 审计策略 | 描述   
|---|---  
| 默认策略 | Envers维护一个用于修改记录的列。每次插入或更新记录时，会使用从数据库序列或表中检索到的版本号将新记录插入到历史记录表中 
| 有效性审计策略 | 该策略将存储每条历史记录的开始和最终版本。每次插入或更新记录时，都会使用开始版本号将新记录插入到历史纪录表中。同时，以前的记录用最终版本号进行更新。也可以配置Envers，记录将最终版本更新到以前的历史纪录中的时间戳  

为支持有效性审计策略，需要为每个历史记录表添加四列：AUDIT_REVISION(INT) 历史记录的开始版本、ACTION_TYPE(INT) 操作类型（0：添加，1：修改，2：删除）、AUDIT_REVISION_END(INT) 历史记录的最终版本、AUDIT_REVISION_END_TS(TIMESTAMP) 最终版本被更新的时间戳   
还需要另一个表来跟踪版本号和创建每个版本的时间戳REVINFO   
```sql
CREATE TABLE REVINFO (
  REVTSTMP BIGINT NOT NULL,
  REV INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (REVTSTMP, REV)
)
```   
***
## Chapter09-Transaction
&nbsp;&nbsp;&nbsp;&nbsp;容器管理事务（CMT）以声明方式管理事务 && 选择Bean管理事务（BMT）以编程式管理事务（Java Transaction API(JTA)进行编程）   
* Spring事务抽象层：事务抽象类的基本组件，这些类来控制事务的属性
* 声明式事务：XML配置文件及Java注解
* 编程式事务：TransactionTemplate类，可以完全控制事务管理代码
* 使用JTA实现全局事务：对于需要跨越多个后端资源的全局事务，使用JTA在Spring中配置和实现全局事务。`PS:在Java世界中，全局事务是通过JTA实施的`  
```text
    在使用事务时，必须选择是使用全局事务还是本地事务。本地事务特定于单个事务资源（如JDBC连接），而全局事务由容器管理并且可以跨越多个事务资源。
    
    全局事务说明：
    于JTA兼容的事务管理器通过各自的资源管理器连接到多个事务资源，而这些资源管理器能够通过XA协议（一种定义了分布式事务的开放标准）与事务管理器进行通信，并使用 2 Phase Commit（2PC）机制确保后端数据源被更新或回滚。整个机制由Java Transaction Service（JTS）规范指定。 
```  

### 事务的属性（ACID属性：原子性、一致性、隔离性、持久性）   
&nbsp;&nbsp;&nbsp;&nbsp;对于事务的原子性、一致性、持久性是无法控制的，但是，可以控制事务的传播和超时，以及配置事务是否应为只读并指定隔离级别。   

#### TransactionDefinition接口（控制事务的属性）   
```java
    package org.springframework.transaction;
    import java.sql.Connection;
    
    public interface TransactionDefinition {
        // Variable declaration statements omitted
        ...
        //指定事务调用时所发生的事情，具体取决于是否存在活动的事务（事务传播类型）
        int getPropagationBehavior();
        //控制着其他事务能够看到的数据更改（事务隔离级别）
        int getIsolationLevel();
        //返回事务必须完成的时间
        int getTimeout();
        //指示事务是否只读
        boolean isReadOnly();
        //事务名称
        String getName();
    }
```
事务的隔离级别（可以使用TransactionDefinition接口中定义的静态值来表示隔离级别）   

| 隔离级别 | 描述   
|---|---  
| ISOLATION_DEFAULT | 底层数据存储的默认隔离级别  
| ISOLATION_READ_UNCOMMITTED | 最低的隔离级别；它几乎不是事务，因为它允许一个事务查看由其他未提交事务修改的事务   
| ISOLATION_READ_COMMITTED | 大多数数据库的默认级别，它确保一个事务不能读取其他事务未提交的数据。但是，一旦数据可以被一个事务读取，那么数据就可以由其他事务更新  
| ISOLATION_REPEATABLE_READ | 比ISOLATION_READ_COMMITTED更严格；它可以确保一旦选择了数据，就可以再次选择相同的数据集。即使其他事务插入新数据，也仍然可以选择新插入的数据 
| ISOLATION_SERIALIZABLE | 最严格且最可靠的隔离级别，所有的事务都被视为一个接一个地执行      

事务传播类型（可以使用TransactionDefinition接口中定义的静态值表示传播类型）   

| 传播类型 | 描述   
|---|---  
| PROPAGATION_REQUIRED | 支持一个已经存在的事务。如果没有事务，则开始一个新的事务   
| PROPAGATION_SUPPORTS | 支持一个已经存在的事务。如果没有事务，则以非事务方式执行   
| PROPAGATION_MANDATORY | 支持一个已经存在的事务。如果没有活动事务，则抛出异常  
| PROPAGATION_REQUIRES_NEW | 始终开始新的事务。如果活动事务已经存在，将其暂停  
| PROPAGATION_NOT_SUPPORTED | 不支持事务的执行。始终以非事务方式执行并暂停任何现有事务  
| PROPAGATION_NEVER | 即使存在活动事务，也始终以非事务方式执行。如果存在活动事务，则抛出异常  
| PROPAGATION_NESTED | 如果存在活动事务，则在嵌套事务中运行。如果没有活动事务，则与PROPAGATION_REQUIRED相同   

#### TransactionStatus接口（允许事务管理器控制事务的执行）  
```java
    package org.springframework.transaction;
    
    public interface TransactionStatus extends SavepointManager {
        boolean isNewTransaction();
        
        //指示事务内部是否携带保存点（也就是说，事务是基于保存点而创建的嵌套事务）
        boolean hasSavepoint();
        
        //会导致回滚并结束活动事务
        void setRollbackOnly();
        
        boolean isRollbackOnly();
        
        //如果可用，会将底层会话存储到数据存储区
        void flush();
        
        //事务是否已提交或回滚
        boolean isCompleted();
    }
```   
### 全局事务   
`跨越多个后端资源的事务被称为全局（分布式）事务`
&nbsp;&nbsp;&nbsp;&nbsp;全局事务一个主要特征是保证了**原子性**。还包括应该由事务管理器处理的复杂协调和同步逻辑。在Java中，JTA是实现全局事务的事实标准   

***
## Chapter10-Conversion-And-Validator
* Spring类型转换系统和Formatter服务提供程序接口（SPI）：通用类型转换系统和Formatter SPI，来替代以前的PropertyEditor支持，以及它们如何在任何Java类型之间进行转换   
* Spring中的验证：Spring如何支持域对象验证。Spring自己的Validator接口，以及重点关注JSR-349（bean验证）支持   

### 类型转换   
`注册的ConversionServiceFactoryBean的Bean Name必须是conversionService，否则会抛出ConversionNotSupportedException`
任意类型之间的转换用途举例：   
`一种可能的场景是有两个系统需要更新相同的歌手信息，但是数据库结构不同（例如，系统A中的姓氏对应系统B中的名字，等等），可以在保存之前使用类型转换系统来转换对象`   
从Spring3.0开始，Spring MVC大量使用了转换服务（以及Formatter SPI）。在Web应用上下文配置中，如果声明了标记\<mvc:annotation-driven/>或在Java配置类中使用@EnableWebMvc，那么将自动注册所有默认转换器（例如，StringToArrayConverter、StringToBooleanConverter、StringToLocaleConverter，它们都位于org.springframework.core.convert.support包中）和格式化器（例如CurrencyFormatter、DateFormatter和NumberFormatter，它们都位于org.springframework.format包的各种子包中）。

### Spring中的字段格式化（Formatter SPI）   
&nbsp;&nbsp;&nbsp;&nbsp;在Formatter SPI中，实现格式化器的主要接口是org.springframework.format.Formatter<T>。Spring提供了一些了常用类型的实现：   
* CurrencyFormatter
* DateFormatter
* NumberFormatter
* PercentFormatter    

### Spring中的验证   
`当完成数据绑定并构建完域对象时，才会对该对象进行验证，同时返回任何错误并显式给用户。如果验证成功，该对象将被持久保存到数据库`   
&nbsp;&nbsp;&nbsp;&nbsp;Spring支持两种主要类型的验证。第一种验证类型是由Spring提供的，可以通过实现**org.springframework.validation.Validator**接口来创建自定义验证器。另一种类型是通过Spring对**JSR-349(Bean Validation)**的支持实现的   
#### 验证说明   
* 自定义验证注意事项：   
&nbsp;&nbsp;&nbsp;&nbsp;对于JSR-349中的自定义验证，应该使用哪种方法：自定义验证器还是@AssertTrue注解？通常，@AssertTrue方法实现起来更简单，可以在域对象的代码中看到验证规则。但是，对于具有更复杂逻辑的验证器（例如，假设需要注入一个服务类，访问数据库并检查有效值），实现自定义验证是不错的方法，因为可能不想将服务层对象添加到域对象中。而且，自定义验证其可以在相似的域对象中重用   
* 使用哪种验证API   
&nbsp;&nbsp;&nbsp;&nbsp;JSR-349是最好的选择，主要原因如下：   
    * JSR-349是JEE标准，并得到许多前/后端框架（如Spring、JPA2、SpringMVC和GWT）的广泛支持
    * JSR-349提供的标准验证API隐藏了底层提供程序，因此不受限于特定的提供程序
    * Spring从版本4开始就与JSR-349紧密集成。例如，在Spring MVC Web控制器中，可以使用@Valid注解（位于javax.validation包中）在方法中注解参数，Spring将在数据绑定过程中自动调用JSR-349验证。此外，在Spring MVC Web应用程序上下文配置中，可以使用一个名为\<mvc:annotation-driven/>的简单标记将Spring配置为自动启用Spring类型转换系统和字段格式化，以支持JSR-349（Bean Validation） 
    * 如果使用的是JPA2，那么提供程序会在持久化之前自动对实体执行JSR-349验证，从而提供另一层保护   
    
*** 
## Chapter11-TaskScheduler  
&nbsp;&nbsp;&nbsp;&nbsp;任务调度主要由三部分组成：任务（即需要在特定时间运行或定期运行的业务逻辑块）、触发器（指定任务应该执行的条件）以及调度程序（根据来自触发器的信息执行任务）。   
* Spring中的任务调度：Spring3中引入的TaskScheduler抽象。调度的场景，如固定间隔调度和cron表达式
* 异步任务执行：在Spring中使用@Async注解来异步执行任务
* Spring中的任务执行：Spring的TaskExecutor接口以及如何执行任务   

### Spring中的任务调度   
&nbsp;&nbsp;&nbsp;&nbsp;在Spring应用程序中可以使用多种方法触发任务的执行。   
&nbsp;&nbsp;&nbsp;&nbsp;一种方法是通过已存在于应用程序部署环境中的调度系统从外部触发作业。例如，使用一些商业系统（例如Control-M或CA AutoSys）来调度任务。如果应用程序在Linux/UNIX平台上运行，则可以使用**crontab**调度程序。作业触发可以通过向Spring应用程序发送RESTful-WS请求并让Spring的MVC控制器触发任务来完成   
&nbsp;&nbsp;&nbsp;&nbsp;另一种方法是Spring中使用任务调度支持。Spring在任务调度方面提供了三个选项   
* 支持JDK定时器：Spring支持用于任务调度的JDK的Timer对象
* 与Quartz集成：[Quartz Scheduler](https://www.quartz-scheduler.org)是一个流行的开源调度库
* Spring自己的Spring TaskScheduler抽象：Spring3引入了TaskScheduler抽象，它提供一种简单的方法来调度任务并支持大多数典型的需求   
#### Spring TaskScheduler抽象介绍   
&nbsp;&nbsp;&nbsp;&nbsp;Spring的TaskScheduler抽象主要有三个参与者   
* Trigger接口：org.springframework.scheduling.Trigger接口为定义触发机制提供了支持。Spring提供了两个Trigger实现。CronTrigger类支持基于cron表达式的触发，而PeriodicTrigger类支持基于初始延迟和固定时间间隔的触发
* 任务：任务是需要调度的业务逻辑。在Spring中，可以将任务指定为任何Spring bean中的方法
* TaskScheduler接口：org.springframework.scheduling.TaskScheduler接口为任务调度提供支持。Spring提供了TaskScheduler接口的三个实现类。TimerManagerTaskScheduler类（在org.springframework.scheduling.commonj包中）封装了CommonJ的commonj.timers.TimerManager接口，该接口常用于商业JEE应用服务器（如WebSphere和WebLogic）。ConcurrentTaskScheduler和ThreadPoolTaskScheduler类（都在org.springframework.scheduling.concurrent包中）封装了java.util.concurrent.ScheduledThreadPoolExecutor类，这两个类都支持从共享线程池执行任务   

### Spring中任务的执行  
&nbsp;&nbsp;&nbsp;&nbsp;Spring自2.0以来，就提供了一个通过TaskExecutor接口执行任务的抽象。下面是一些常见的TaskExecutor实现。
* SimpleAsyncTaskExecutor：在每次调用时创建新线程，不重用现有的线程
* SyncTaskExecutor：不会异步执行，调用发生在调用线程中
* SimpleThreadPoolTaskExecutor：Quartz的SimpleThreadPool的子类，当需要Quartz和非Quartz组件之间共享线程池时使用
* ThreadPoolTaskExecutor：TaskExecutor的一种实现，提供了通过bean属性配置ThreadPoolExecutor并将其作为Spring TaskExecutor公开的功能   
`虽然每种TaskExecutor实现都有自己的目的，但调用约定是相同的。唯一的变化是在配置中，需要定义所使用的TaskExecutor实现及其属性（如果有的话）`   


***
## Chapter12-Spring-Remoting  
```text
    远程处理概述：
    自Java首次创建以来，就一直支持远程处理。在早期（Java1.x），大多数远程需求是通过使用传统的TCP套接字或Java远程方法调用（Java Remote Method Invocation，RMI）来实现的。
    在J2EE出现后，EJB和JMS成为应用程序服务器通信的常用选择。XML和互联网的快速发展促成了在HTTP上使用XML实现远程支持，包括针对基于XML的RPC（JAX-RPC）的Java API、针对XML Web Services（JAX-WS）的Java API以及基于HTTP的技术（例如Hessian和Burlap）。
    此外，Spring还提供了自己的基于HTTP的远程支持，称为Spring HTTP调用器。
    近年来为了应对互联网的爆炸性增长以及对更具响应性的Web应用程序的需求（例如，通过Ajax），应用程序的更轻量级和高效的远程支持已经成为企业成功的关键。
    因此，针对RESTful Web Service(JAX-RS)的Java API被推出并迅速流行开来。其他协议，比如Comet和HTML5 WebSocket也吸引了很多开发人员。
```
&nbsp;&nbsp;&nbsp;&nbsp;在远程处理方面，Spring提供了自己的支持（通过Spring HTTP调用器）。
* Spring HTTP调用器：如果两个需要通信的应用程序都是基于Spring的，那么Spring HTTP调用器提供了一种简单且有效的方法来调用其他应用程序所公开的服务。
* Spring中使用JMS：Java消息服务（JMS）提供了另一种在应用程序之间交换消息的异步且松散耦合的方法。
* Spring中使用RESTful Web服务：RESTful Web服务是专门围绕HTTP设计的，是为应用程序提供远程支持以及支持使用Ajax的高度交互式Web应用程序的最常用前端技术。
* Spring中使用AMQP：Spring高级消息队列协议（AMQP）项目提供了围绕AMQP的、典型的、类似Spring的抽象以及RabbitMQ实现。该项目提供了一组丰富的功能，而这里，将通过RPC项目支持重点关注其远程处理功能。   


### Spring中使用JMS  
&nbsp;&nbsp;&nbsp;&nbsp;使用面向消息中间件（MQ服务器）是另一种支持**应用程序间通信**的流行方法。消息队列（MQ）服务器的主要优点在于为应用程序集成提供了**异步**和**松耦合**的方式。_在Java世界中，**JMS**是连接到MQ服务器以发送或接收消息的标准_。以下是队列和主题之间差异的主要说明：   
* **队列**：队列用于支持点对点的消息交换模型。当一名生产者向队列发送消息时，MQ服务器会将消息保留在队列中，并在消费者下次连接时将消息传递给一名消费者（且只能传递给一名消费者）   
* **主题**：主题用于支持发布-订阅模型。任何数量的客户端都可以订阅主题中的消息。当某条消息到达主题时，MQ服务器会将其发送给订阅了该消息的所有客户端。当多个应用程序对同一条消息（如新闻提要）感兴趣时，此模型是非常有用的  
[HornetQ](https://hornetq.jboss.org/)配置文件中创建一个队列（位置：config/stand-alone/non-clustered hornetq-jms.xml）:   
```xml
<configuration xmlns="urn:hornetq"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema"
        xsi:schemaLocation="urn:hornetq/schema/hornetq-jms.xsd"
        ...
    <queue name="isaac">
        <entry name="/queue/isaac"/>
    </queue>
</configuration>        
```   
&nbsp;&nbsp;&nbsp;&nbsp;Spring中通常有两个配置类：一个用于**消息发送器**，一个用于**消息监听器**，以用来连接到此服务器并访问队列。   

### Spring Boot Artemis启动器  
&nbsp;&nbsp;&nbsp;&nbsp;当Spring Boot检测到Artemis在类路径可用时，可以自动配置javax.jms.ConnectionFactory Bean。嵌入式 JMS代理是自动启动和配置的。Artemis可以在多种模式中使用，并且可以使用application.properties文件中设置特殊Artemis属性进行配置。   
Artemis能够以native模式使用，可以连接到由Netty协议提供的代理，application.properties文件如下：   
```properties
spring.artemis.mode=native
spring.artemis.host=0.0.0.0
spring.artemis.port=61617
spring.artemis.user=isaac
spring.artemis.password=isaac
```    
使用SpringBoot和Artemis创建JMS应用程序最简单方法是使用嵌入式服务器，所需的只是用于保存消息的队列名称，application.properties如下：  
```properties
spring.artemis.mode=embedded
spring.artemis.embedded.queues=isaac
```   

&nbsp;&nbsp;&nbsp;&nbsp;将spring-boot-starter-artemis声明为依赖，而无需使用@EnableJms来处理用@JmsListener注解的方法。jmsTemplate bean也由Spring Boot创建，默认配置由application.properties文件中设置的属性提供，它不仅可以发送消息，还可以接收消息（使用receive()方法），但这是同步完成的，意味着jmsTemplate会被阻塞。`这就是显式配置JmsListenerContainerFactory bean来创建DefaultMessageListenerContainer的原因——能够以最高连接效率异步的使用消息`。


### Spring Security保护RESTful-WS   
&nbsp;&nbsp;&nbsp;&nbsp;使用Spring Security来保护RESTful-WS共分为三步   
1. 首先，需要在Web应用程序部署描述符（web.xml）中添加一个名为**springSecurityFilterChain**的安全过滤器。而纯Java配置中，可以将过滤器替换为扩展了**AbstractSecurityWebApplicationInitializer**的类。此类注册了DelegatingFilterProxy，以便在其他注册的过滤器之前使用springSecurityFilterChain。  
2. 然后，需要为安全性添加一个Spring配置类，用于声明谁可以访问应用程序。该类使用`@EnableWebSecurity`进行注解，从而在Spring Web应用程序中启用安全行为。并在configure(..)方法中声明需要保护的资源，以及保护的方式。在configureGlobal(..)中定义身份验证信息，可以通过数据库或LDAP查找完成身份验证
3. 最后，要将SecurityConfig添加到根上下文，以及rest应用程序上下文   

### AMQP  
&nbsp;&nbsp;&nbsp;&nbsp;远程处理也可以通过使用以高级队列协议（AMQP）作为传输协议的远程过程调用（RPC）通信来完成。AMQP是实现面向消息中间件（MOM）的开放标准协议   
&nbsp;&nbsp;&nbsp;&nbsp;`JMS应用程序适用于任何操作系统环境，但仅支持Java平台。所以，所有通信应用程序必须用Java开发。AMQP标准可用于开发用于多种语言应用程序的通信`  
&nbsp;&nbsp;&nbsp;&nbsp;与使用JMS相似，AMQP也使用消息代理来交换消息。可以使用RabbitMQ作为AMQP服务器。Spring本身并没有在核心框架中提供远程处理功能，相反，而是由一个名为Spring AMQP的姊妹项目来处理，将其用作底层通信API。Spring AMQP项目提供了关于AMQP的基本抽象以及与RabbitMQ进行通信的实现。  
&nbsp;&nbsp;&nbsp;&nbsp;Spring AMQP项目由两部分组成：spring-amqp是基本抽象，springrabbit是RabbitMQ实现。[RabbitMQ](https://www.rabbitmq.com/download.html)

***

## Chapter13-Spring-Test  
&nbsp;&nbsp;&nbsp;&nbsp;

### 测试类别介绍  

| 测试类别 | 描述 | 常用工具    
|---|--- |--- 
| 逻辑单元测试 | 逻辑单元测试需要一个对象并自行测试，而不必担心它在周围系统中所扮演的角色 | 单元测试：JUnit、TestNG <br>  模拟对象：Mockito、EasyMock  
| 集成单元测试 | 集成单元测试专注于在“接近真实”的环境中测试组件之间的交互。这些测试将执行与容器（嵌入式数据库、Web容器等）的交互 | 嵌入式数据库：H2 <br>  数据库测试：DbUnit <br> 内存Web容器：Jetty  
| 前端单元测试 | 前端单元测试侧重于测试用户界面。目标是确保每个用户界面对用户的操作做出反应并按预期产生输出给用户 | Selenium  
| 持续构建和代码质量测试 | 应用程序代码库应定期构建，以确保代码质量符合标准（例如，在适当的地方写上注释，没有空的异常捕获块等）。此外，测试的覆盖范围应尽可能广以确保开发的所有代码行都经过测试 | 代码质量：PMD、Check-style、FindBugs、Sonar <br> 测试覆盖范围：Cobertura、EclEmma <br> 构建工具：Gradle、Maven <br> 持续构建：Hudson、Jenkins  
| 系统集成测试 | 系统集成测试验证新系统中所有程序之间以及新系统与所有外部接口之间通信的准确性。系统集成测试还必须证明新系统可以在操作环境中根据功能规范有效地执行，同时不会对其他系统产生不利的影响 | IBM Rational Functional Tester、HP Unified Functional Testing  
| 系统质量测试 | 系统质量测试旨在确保所开发的应用程序满足那些非功能性需求。在大多数情况下，主要是测试应用程序的性能，以确保满足系统的并发用户和工作负载的目标需求。其他非功能性需求包括安全性、高可用性功能等 | Apache JMeter、HP LoadRunner  
| 用户验收测试 | 用户验收测试模拟新系统的实际工作条件，包括用户手册和程序。大量的用户参与这个测试阶段，从而为用户提供操作新系统的宝贵经验。此外，还有利于程序员或设计人员了解新程序的用户体验。这种联合参与确保用户和操作人员都赞成对系统所做的修改 | IBM Rational TextManager、HP Quality Center  

### 使用Spring测试注解  
| 注解 | 描述  
|---|---  
| @ContextConfiguration | 类级注解，用于确定如何为集成测试加载和配置ApplicationContext  
| @WebAppConfiguration | 类级注解，用于指示加载的ApplicationContext应该是WebApplicationContext  
| @ContextHierarchy | 类级注解，用于指示哪个bean配置文件应该处理活动状态  
| @DirtiesContext | 类级和方法级注解，用于指示上下文在执行测试期间以某种方式被修改或损坏，并且应该关闭和重新构建以供后续测试  
| @TestExecutionListeners | 类级注解，用于配置应该使用TestContextManager注册时TestExecutionListeners  
| @TransactionConfiguration | 类级注解，用于指示事务配置，例如回滚设置和事务管理器（假设期望的事务管理器没有名为transactionManager的bean）  
| @Rollback | 类级和方法级注解，用于指示是否应该针对所注解的方法回滚事务。此外，它还是用于测试类的默认设置的类级注释  
| @BeforeTransaction | 方法级注解，指示在为使用@Transactional注解标记的测试方法启动事务之前，应该调用@BeforeTransaction所注解的方法    
| @AfterTransaction | 方法级注解，指示在为使用@Transactional注解标记的测试方法结束事务之后，应该调用@AfterTransaction所注解的方法  
| @IfProfileValue | 类级和方法级注解，用于指示应该为一组特定的环境条件启用测试方法  
| @ProfileValueSourceConfiguration | 类级注解，用于指定@IfProfileValue所使用的ProfileValueSource。如果该注解未在测试中声明，则将SystemProfileValueSource用作默认值  
| @Timed | 方法级注解，用于指示测试必须在指定的时间段内完成  
| @Repeat | 方法级注解，用于指示注解的测试方法应该重复指定的次数    

    
***  

## Chapter14-Spring-Script  
&nbsp;&nbsp;&nbsp;&nbsp;Spring对脚本语言的支持：  
* Java中的脚本支持：在JCP中，JSR-223（用于Java平台的脚本）支持Java中的脚本语言；自SE6以来，它就可以在Java中使用。
* Groovy：与Java一起使用的最流行脚本语言之一。
* 在Spring中使用Groovy：Spring框架为脚本语言提供的全面支持。自3.1版本以来，Spring提供对Groovy、JRuby和BeanShell的开箱即用支持。  
```text
Java存在的缺点，如静态类型的局限性，以及语言结构的局限性和在大规模并行处理等领域缺乏全面的支持。
而脚本语言，譬如下面所示：
Scala将函数式编程和OO模式相结合，并支持具有Actor和消息传递等概念的更全面且可扩展的并发编程模型
Groovy提供了一个简化的编程模型，并支持特定与领域语言（DSL）的实现
```  
> 闭包：脚本语言带来的一个重要概念就是闭包。闭包（closure）是封装到对象中的一段代码（或块）。闭包是可执行的，可以接收参数并返回对象和值。另外，闭包还是普通的对象，可以通过引用传递。  

### Groovy简介  
* Groovy（以及其他许多脚本语言）和Java之间的一个主要区别是支持变量的**动态类型化**。
* 简化的语法
    * 语句的末尾不需要分号
    * 在方法中，关键字return可选
    * 所有方法和类默认都为公共的
    * 在一个类中，Groovy会为声明的属性自动生成getter/setter方法。访问属性还可以不需要get/set前缀
* [闭包](https://www.groovy-lang.org/closures.html)的支持  


## Chapter15-Application-Monitoring  
&nbsp;&nbsp;&nbsp;&nbsp;在Java应用程序中，各个区域都可能会导致性能问题或服务器资源（如CPU、内存或IO）过载。例如，Java代码效率低下、**内存泄漏（比如，在不释放引用的情况下继续分配新对象的Java代码，并且阻止底层JVM在垃圾收集过程中释放内存）**、错误计算的JVM参数、错误计算的线程池参数、过多的数据源配置（例如，允许的并发数据库连接太多）、不正确的数据库设置以及长时间运行的SQL查询。  
&nbsp;&nbsp;&nbsp;&nbsp;在Java中很多工具可以帮助监控JEE应用程序的详细运行时行为，它们中的大多数都建立在Java管理扩展（Java Management Extensions,JMX）技术之上。
* JMX的Spring支持：Spring对JMX的全面支持，使用JMX工具公开用于监控的Spring bean。使用Java可执行文件[jvisualvm](https://visualvm.github.io/?Java_VisualVM)作为应用程序监控工具
* 监控Hibernate统计信息：Hibernate和许多其他包为使用JMX公开操作状态和性能指标提供了支持类和基础结构。在Spring驱动的JEE应用程序中启用那些常用组件的JMX监控  
* Spring Boot JMX支持：Spring Boot为JMX支持提供了一个启动器库，该库提供了全新的默认配置

### Spring中的JMX支持  
&nbsp;&nbsp;&nbsp;&nbsp;在JMX中，用于公开JMX监控和管理的类被称为*托管bean*(MBean)。Spring支持多种公开MBean的机制。如，将Spring bean（被开发为简单的POJO）公开为MBean，从而进行JMX监控。  

