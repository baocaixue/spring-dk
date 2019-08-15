package com.isaac.ch13;

import lombok.extern.slf4j.Slf4j;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 自定义测试监听器
 *
 * TestExecutionListener接口定义了一个监听器API，可以拦截测试用例执行的各个阶段（如类被测试之前和之后，方法被测试前后等）中的事件。
 *
 * 在测试服务层，将为新引入的@DataSets注解实现一个自定义的监听器，目的是通过对测试用例做简单注解支持测试数据的填充
 */
@Slf4j
public class ServiceTestExecutionListener implements TestExecutionListener {
    /*DbUnit提供的接口，支持基于给定数据库连接或数据源的数据库操作*/
    private IDatabaseTester databaseTester;

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        DataSets dataSetAnnotation = testContext.getTestMethod().getAnnotation(DataSets.class);
        String dataSetName = Optional.ofNullable(dataSetAnnotation).map(DataSets::setUpDataSet).orElse("");
        if(StringUtils.isEmpty(dataSetName)) {
            log.warn("current test method has not DataSets annotation ...");
            return;
        }
        ApplicationContext ctx = testContext.getApplicationContext();
        databaseTester = ctx.getBean(IDatabaseTester.class);
        XlsDataFileLoader xlsDataFileLoader = ctx.getBean(XlsDataFileLoader.class);
        //加载文件数据
        IDataSet dataSet = xlsDataFileLoader.load(dataSetName);

        //设置测试数据
        databaseTester.setDataSet(dataSet);
        //触发数据库填充
        databaseTester.onSetup();
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        if (databaseTester != null) {
            //清理数据
            databaseTester.onTearDown();
        }
    }
}
