package com.isaac.ch3;

/**
 * 上下文依赖查找（CDL）在某些方面与依赖拉取类似，但在CDL中，查找是针对管理资源的容器执行的，而不是某个中央注册表，并且通常在某个设定点执行。
 *
 * 提供依赖查找服务的简单Container接口
 */
public interface Container {
    Object getDependency(String key);
}
