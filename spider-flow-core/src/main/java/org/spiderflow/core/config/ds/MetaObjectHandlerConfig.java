package org.spiderflow.core.config.ds;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: spider-flow
 * @description:
 * @author: zhendong.wu
 * @create: 2020-04-28 11:40
 **/
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createDate",metaObject);
        if(createTime==null){
            setFieldValByName("createDate", new Date(), metaObject);
        }
        Object optimistic = getFieldValByName("optimistic",metaObject);
        if(optimistic==null){
            setFieldValByName("optimistic", 0, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
