package com.wlt.deviceledger.bean.declare;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 材料申报bean
 * @author Administrator
 *
 */
@TableName(value = "tbl_mater_declare")  // 指定表名
public class MaterDeclareBean implements Serializable {

	private static final long serialVersionUID = -6526184407139138773L;

	@TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 材料申报数量
     */
    private Integer materNum;

    /**
     * 材料id
     */
    private Integer materId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 审批状态
     */
    private Integer approvalState;

    /**
     * 创建日期
     */
    private String createTime;

    /**
     * 是否加急0否，1是
     */
    private Integer isSpeed;

    /**
     * 加急原因
     */
    private String speedCase;

    /**
     * 是否撤回0撤回，1不撤回
     */
    private Integer isRecall;

    /**
     * 0删除，1存在
     */
    private Integer isDelete;

    /**
     * 0待采购，1已采购
     */
    private Integer isPurchase;

    /**
     * 审批人id
     */
    private Integer approvalUserId;
    /**
     * 采购完成时间
     */
    private String purchaseFinishTime;
    /**
     * 申报原因
     */
    private String declareReason;

   

    /**
     * 获取
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取材料申报数量
     * @return mater_num 材料申报数量
     */
    public Integer getMaterNum() {
        return materNum;
    }

    /**
     * 设置材料申报数量
     * @param materNum 材料申报数量
     */
    public void setMaterNum(Integer materNum) {
        this.materNum = materNum;
    }

    /**
     * 获取材料id
     * @return mater_id 材料id
     */
    public Integer getMaterId() {
        return materId;
    }

    /**
     * 设置材料id
     * @param materId 材料id
     */
    public void setMaterId(Integer materId) {
        this.materId = materId;
    }

    /**
     * 获取用户id
     * @return user_id 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取审批状态
     * @return approval_state 审批状态
     */
    public Integer getApprovalState() {
        return approvalState;
    }

    /**
     * 设置审批状态
     * @param approvalState 审批状态
     */
    public void setApprovalState(Integer approvalState) {
        this.approvalState = approvalState;
    }

    /**
     * 获取创建日期
     * @return create_time 创建日期
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     * @param createTime 创建日期
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否加急0否，1是
     * @return is_speed 是否加急0否，1是
     */
    public Integer getIsSpeed() {
        return isSpeed;
    }

    /**
     * 设置是否加急0否，1是
     * @param isSpeed 是否加急0否，1是
     */
    public void setIsSpeed(Integer isSpeed) {
        this.isSpeed = isSpeed;
    }

    /**
     * 获取加急原因
     * @return speed_case 加急原因
     */
    public String getSpeedCase() {
        return speedCase;
    }

    /**
     * 设置加急原因
     * @param speedCase 加急原因
     */
    public void setSpeedCase(String speedCase) {
        this.speedCase = speedCase;
    }

    /**
     * 获取是否撤回0撤回，1不撤回
     * @return is_recall 是否撤回0撤回，1不撤回
     */
    public Integer getIsRecall() {
        return isRecall;
    }

    /**
     * 设置是否撤回0撤回，1不撤回
     * @param isRecall 是否撤回0撤回，1不撤回
     */
    public void setIsRecall(Integer isRecall) {
        this.isRecall = isRecall;
    }

    /**
     * 获取0删除，1存在
     * @return is_delete 0删除，1存在
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置0删除，1存在
     * @param isDelete 0删除，1存在
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取0待采购，1已采购
     * @return is_purchase 0待采购，1已采购
     */
    public Integer getIsPurchase() {
        return isPurchase;
    }

    /**
     * 设置0待采购，1已采购
     * @param isPurchase 0待采购，1已采购
     */
    public void setIsPurchase(Integer isPurchase) {
        this.isPurchase = isPurchase;
    }

    /**
     * 获取审批人id
     * @return approval_user_id 审批人id
     */
    public Integer getApprovalUserId() {
        return approvalUserId;
    }

    /**
     * 设置审批人id
     * @param approvalUserId 审批人id
     */
    public void setApprovalUserId(Integer approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

	public String getPurchaseFinishTime() {
		return purchaseFinishTime;
	}

	public void setPurchaseFinishTime(String purchaseFinishTime) {
		this.purchaseFinishTime = purchaseFinishTime;
	}

	public String getDeclareReason() {
		return declareReason;
	}

	public void setDeclareReason(String declareReason) {
		this.declareReason = declareReason;
	}
    
}