package com.isaac.ch3;

/**
 * CDL通过让组件实现类似以下的代码片段接口来进行工作
 * 通过实现该接口，一个组件可以向容器发送它想要获得依赖项的信号。容器通常由底层应用程序或框架（例如Tomcat、JBoss或Spring）提供
 *
 * 当容器准备将依赖项传递给组件时，会依此调用每个组件的performLookup方法，然后，组件可以使用Container接口查找所需的依赖项
 */
public interface ManagedComponent {
    void performLookup(Container container);
}
