package com.wlt.deviceledger.util.enums;

/**
 * ClassName: CommonEnum
 * Describe: TODO
 *
 * @Date: 2019/10/13 17:29
 * @Author: 杨开怀
 */
public enum CommonEnum {



    RIGET(0, "不删除"),
    ERROR(1,"删除");

    private Integer code;
    private String description;


    CommonEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
