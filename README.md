# Spring is a wonderful season

This repository is a reading note according to [Pro Spring 5th Edition](http://www.apress.com/9781484228074) by Iuliana Cosmina, Rob Harrop, Chris Schaefer, Clarence Ho (Apress, 2017). 

https://github.com/Apress/pro-spring-5

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
## [Chapter03 IOC&&DI](./chapter03)  
  - [ApplicationContextAware](./chapter03/bean-autowiring/src/main/java/com/isaac/ch3/annotated/Singer.java)  
***
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
## [chapter05 Spring AOP](./chapter05) 
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
## [chapter06 Spring JDBC](./chapter06)  
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
## [chapter07 Spring 中使用 Hibernate](./chapter07)
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
## [chapter08 在Spring中使用JPA2进行数据访问](./chapter08)
&nbsp;&nbsp;&nbsp;&nbsp;在使用ORM方法实现数据访问逻辑时使用Hibernate和Spring，其中，Hibernate的使用方式是：配置SessionFactory，使用Session接口进行数据操作。但是，还有另一种Hibernate的使用方式：使用Hibernate作为**标准Java持久化API（JPA）** 的持久化提供程序  
&nbsp;&nbsp;&nbsp;&nbsp;JPA提供了标准化的概念，可以轻松在JPA持久化提供程序（如Hibernate、EclipseLink、Oracle TopLink和Apache OpenJpa）之间转换。  

### JPA2.1
&nbsp;&nbsp;&nbsp;&nbsp;与其他Java规范请求（JSR）一样，JPA2.1规范（JSR-338）的目标是在JSE和JEE环境中**对ORM编程模型进行标准化**。 它定义了JPA持久化提供程序应该实现的一组通用概念、注解、接口和其他服务。按JPA标准进行编程，可以随意切换底层提供程序。   
&nbsp;&nbsp;&nbsp;&nbsp;在JPA中，**核心概念是EntityManager接口**，它是来自EntityManagerFactory类型的工厂。EntityManager的主要工作是维护一个持久化上下文，在该上下文中存储由其管理的所有实体实例。EntityManager的配置被定义为一个持久化单元，并且在应用程序中可以有多个持久化单元。如果使用的是Hibernate，那么可以像使用Session接口一样使用持久化上下文。同样，EntityManagerFactory等同于SessionFactory。在Hibernate中，托管实体存储在会话中，可以通过Hibernate的SessionFactory或Session接口直接与会话进行交互。但是，在JPA中，不能直接与持久化上下文交互。需要依靠EntityManager来完成相关工作。   
#### 配置JPA的EntityManagerFactory
Spring支持三种类型的EntityManagerFactory的配置    
- LocalEntityManagerFactoryBean类，这是最简单的一种，只需持久化单元名称，但不支持DataSource注入，因此无法参与全局事务，只能适用于简单的开发目的    
- 用于JEE兼容的容器，其中应用程序服务器根据部署描述符中信息启动JPA持久化单元。这样就允许Spring通过JNDI查找来查找实体管理器。下面代码描述了通过JNDI查找实体管理器所需的元素      
    ```
    <beans ...>
        <jee:jndi-lookup id="emf" jndi-name="persistence/xxx"/>
    </beans>
    ```   
- LocalContainerEntityManagerFactoryBean类，支持DataSource注入并可以参与本地和全局事务   
    
    

