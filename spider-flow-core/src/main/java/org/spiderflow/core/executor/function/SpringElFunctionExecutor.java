package org.spiderflow.core.executor.function;

import org.checkerframework.checker.units.qual.K;
import org.spiderflow.annotation.Comment;
import org.spiderflow.annotation.Example;
import org.spiderflow.core.expression.ExpressionTemplateContext;
import org.spiderflow.executor.FunctionExecutor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @program: spider-flow
 * @description: springEl表达式使用
 * @author: zhendong.wu
 * @create: 2020-04-18 17:58
 **/
@Component
@Comment("springEL常用方法")
public class SpringElFunctionExecutor implements FunctionExecutor  {
    @Override
    public String getFunctionPrefix() {
        return "spel";
    }
    @Comment("按照spring表达式写法")
    @Example("${spel.spelParser(expression)}")
    public static <T> T  spelParser(String expressionString) {
        SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,null);
        ExpressionParser parser = new SpelExpressionParser(config);
        Expression expression =
                parser.parseExpression(expressionString);
        ExpressionTemplateContext expressionTemplateContext= ExpressionTemplateContext.get();

        EvaluationContext context = new StandardEvaluationContext();
        for(Map.Entry<String, Object> entity :expressionTemplateContext.getVariablesMap().entrySet()){
            context.setVariable(entity.getKey(),entity.getValue());
        }
        //System.out.println(expression.getValue(context));
        return (T) expression.getValue(context);
    }
}
