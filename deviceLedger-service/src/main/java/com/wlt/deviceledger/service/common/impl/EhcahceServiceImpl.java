package com.wlt.deviceledger.service.common.impl;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.service.common.EhcahceService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * ClassName: EhcahceServiceImpl
 * Describe: TODO
 *
 * @Date: 2019/10/24 21:01
 * @Author: 杨开怀
 */
public class EhcahceServiceImpl implements EhcahceService {


    // value的值和ehcache.xml中的配置保持一致
    @Cacheable(value="HelloWorldCache", key="#param")
    @Override
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

    @Cacheable(value="HelloWorldCache", key="#key")
    @Override
    public String getDataFromDB(String key) {
        System.out.println("从数据库中获取数据...");
        return key + ":" + String.valueOf(Math.round(Math.random()*1000000));
    }

    @CacheEvict(value="HelloWorldCache", key="#key")
    @Override
    public void removeDataAtDB(String key) {
        System.out.println("从数据库中删除数据");
    }

    @CachePut(value="HelloWorldCache", key="#key")
    @Override
    public String refreshData(String key) {
        System.out.println("模拟从数据库中加载数据");
        return key + "::" + String.valueOf(Math.round(Math.random()*1000000));
    }

    // ------------------------------------------------------------------------
    @Cacheable(value="UserCache", condition="#userId.length()<12")
    public boolean isReserved(String userId) {
        System.out.println("UserCache:"+userId);
        return false;
    }

    //清除掉UserCache中某个指定key的缓存
    @CacheEvict(value="UserCache",key="'user:' + #userId")
    public void removeUser(String userId) {
        System.out.println("UserCache remove:"+ userId);
    }

    //清除掉UserCache中全部的缓存
    @CacheEvict(value="UserCache", allEntries=true)
    public void removeAllUser() {
        System.out.println("UserCache delete all");
    }

}
