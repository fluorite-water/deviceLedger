package com.wlt.deviceledger.settings.shrio;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * ClassName: ShiroSpringCacheManager
 * Describe: TODO
 *
 * @Date: 2019/10/20 21:44
 * @Author: 杨开怀
 */
public class ShiroSpringCacheManager implements CacheManager, Destroyable {


    private org.springframework.cache.CacheManager cacheManager;

    public org.springframework.cache.CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void destroy() throws Exception {
        cacheManager = null;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name)  {
        if (name == null ){
            return null;
        }
        return new ShiroSpringCache<K,V>(name,getCacheManager());
    }


}
