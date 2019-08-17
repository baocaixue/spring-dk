package com.isaac.ch14.jsr_223;

import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Slf4j
public class ListScriptEngines {
    /**
     * JDK6中的JSR-233支持
     */
    public static void main(String[] args){
        ScriptEngineManager mgr = new ScriptEngineManager();
        listScriptEngines(mgr);
        ScriptEngine groovyEngine = mgr.getEngineByName("Groovy");
        evalGroovyScript(groovyEngine, "println 'Hello World!'");
    }

    /**
     * 评估脚本表达式
     */
    private static void evalGroovyScript(ScriptEngine scriptEngine, String script) {
        try {
            scriptEngine.eval(script);
        } catch (ScriptException e) {
            log.error("Groovy script cant be evaluated!", e);
        }
    }

    /**
     * 检索脚本引擎列表
     */
    private static void listScriptEngines(ScriptEngineManager mgr) {
        mgr.getEngineFactories().forEach(factory -> {
            String engineName = factory.getEngineName();
            String languageName = factory.getLanguageName();
            String languageVersion = factory.getLanguageVersion();
            log.info("Engine name: " + engineName + " Language: " + languageName + " Version: " + languageVersion);
        });
    }
}
