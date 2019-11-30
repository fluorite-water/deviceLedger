package com.wlt.deviceledger.settings.shrio;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * ClassName: StatelessDefaultSubjectFactory
 * Describe: TODO
 *
 * @Date: 2019/11/26 21:51
 * @Author: 杨开怀
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }


}
