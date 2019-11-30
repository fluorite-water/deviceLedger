package com.wlt.deviceledger.bean.materialInfo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "tbl_material_info")  // 指定表名
public class MaterialInfoBean implements Serializable {
    /**
     * 主键
     */
	@TableId(value = "id",type = IdType.AUTO)
    private String id;

    /**
     * 材料名称
     */
    private String materialName;

    /**
     * 材料编号
     */
    private String materialNo;

    /**
     * 型号规格
     */
    private String standards;

    /**
     * 生成厂家
     */
    private String factory;

    /**
     * 出厂日期
     */
    private String leaveDate;

    /**
     * 进厂日期
     */
    private String comeDate;

    /**
     * 状态  0在使用 1闲置
     */
    private String status;

    /**
     * 价格
     */
    private String price;

    /**
     * 折旧率
     */
    private String oldRate;

    /**
     * 折旧年限
     */
    private String oldYear;

    /**
     * 材料数量
     */
    private String materialNum;

    /**
     * 使用地点
     */
    private String usePlace;

    /**
     * 材料类型：0普通 1加急
     */
    private String type;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否删除：0删除，1存在
     */
    private String isDelete;

    /**
     * material_info
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     * @return id 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取材料名称
     * @return material_name 材料名称
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * 设置材料名称
     * @param materialName 材料名称
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * 获取材料编号
     * @return material_no 材料编号
     */
    public String getMaterialNo() {
        return materialNo;
    }

    /**
     * 设置材料编号
     * @param materialNo 材料编号
     */
    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    /**
     * 获取型号规格
     * @return standards 型号规格
     */
    public String getStandards() {
        return standards;
    }

    /**
     * 设置型号规格
     * @param standards 型号规格
     */
    public void setStandards(String standards) {
        this.standards = standards;
    }

    /**
     * 获取生成厂家
     * @return factory 生成厂家
     */
    public String getFactory() {
        return factory;
    }

    /**
     * 设置生成厂家
     * @param factory 生成厂家
     */
    public void setFactory(String factory) {
        this.factory = factory;
    }

    /**
     * 获取出厂日期
     * @return leave_date 出厂日期
     */
    public String getLeaveDate() {
        return leaveDate;
    }

    /**
     * 设置出厂日期
     * @param leaveDate 出厂日期
     */
    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    /**
     * 获取进厂日期
     * @return come_date 进厂日期
     */
    public String getComeDate() {
        return comeDate;
    }

    /**
     * 设置进厂日期
     * @param comeDate 进厂日期
     */
    public void setComeDate(String comeDate) {
        this.comeDate = comeDate;
    }

    /**
     * 获取状态  0在使用 1闲置
     * @return status 状态  0在使用 1闲置
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态  0在使用 1闲置
     * @param status 状态  0在使用 1闲置
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取价格
     * @return price 价格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置价格
     * @param price 价格
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 获取折旧率
     * @return old_rate 折旧率
     */
    public String getOldRate() {
        return oldRate;
    }

    /**
     * 设置折旧率
     * @param oldRate 折旧率
     */
    public void setOldRate(String oldRate) {
        this.oldRate = oldRate;
    }

    /**
     * 获取折旧年限
     * @return old_year 折旧年限
     */
    public String getOldYear() {
        return oldYear;
    }

    /**
     * 设置折旧年限
     * @param oldYear 折旧年限
     */
    public void setOldYear(String oldYear) {
        this.oldYear = oldYear;
    }

    /**
     * 获取材料数量
     * @return material_num 材料数量
     */
    public String getMaterialNum() {
        return materialNum;
    }

    /**
     * 设置材料数量
     * @param materialNum 材料数量
     */
    public void setMaterialNum(String materialNum) {
        this.materialNum = materialNum;
    }

    /**
     * 获取使用地点
     * @return use_place 使用地点
     */
    public String getUsePlace() {
        return usePlace;
    }

    /**
     * 设置使用地点
     * @param usePlace 使用地点
     */
    public void setUsePlace(String usePlace) {
        this.usePlace = usePlace;
    }

    /**
     * 获取材料类型：0普通 1加急
     * @return type 材料类型：0普通 1加急
     */
    public String getType() {
        return type;
    }

    /**
     * 设置材料类型：0普通 1加急
     * @param type 材料类型：0普通 1加急
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     * @return create_time 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否删除：0删除，1存在
     * @return is_delete 是否删除：0删除，1存在
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除：0删除，1存在
     * @param isDelete 是否删除：0删除，1存在
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}