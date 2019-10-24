package com.wlt.deviceledger.service.common;


import com.wlt.deviceledger.bean.user.UserBean;

/**
 * ClassName: EhcahceService
 * Describe: TODO
 *
 * @Date: 2019/10/24 21:01
 * @Author: 杨开怀
 */
public interface EhcahceService {


    // 测试失效情况，有效期为5秒
    public String getTimestamp(String param);

    public String getDataFromDB(String key);

    public void removeDataAtDB(String key);

    public String refreshData(String key);


    public boolean isReserved(String userId);

    public void removeUser(String userId);

    public void removeAllUser();


}
