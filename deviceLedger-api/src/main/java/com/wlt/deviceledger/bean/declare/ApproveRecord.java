package com.wlt.deviceledger.bean.declare;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 审批记录bean
 * @author Administrator
 *
 */
@TableName(value = "tbl_approve_record")  // 指定表名
public class ApproveRecord implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3611307398115766783L;

	/**
     * 
     */
	@TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 申报id
     */
    private Integer declareId;

    /**
     * 审批拒绝原因
     */
    private String declareReason;

    /**
     * 0拒绝1通过
     */
    private Integer approveState;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * tbl_approve_record
     */


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
     * 获取申报id
     * @return declare_id 申报id
     */
    public Integer getDeclareId() {
        return declareId;
    }

    /**
     * 设置申报id
     * @param declareId 申报id
     */
    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    /**
     * 获取审批拒绝原因
     * @return declare_reason 审批拒绝原因
     */
    public String getDeclareReason() {
        return declareReason;
    }

    /**
     * 设置审批拒绝原因
     * @param declareReason 审批拒绝原因
     */
    public void setDeclareReason(String declareReason) {
        this.declareReason = declareReason;
    }

    /**
     * 获取0拒绝1通过
     * @return approve_state 0拒绝1通过
     */
    public Integer getApproveState() {
        return approveState;
    }

    /**
     * 设置0拒绝1通过
     * @param approveState 0拒绝1通过
     */
    public void setApproveState(Integer approveState) {
        this.approveState = approveState;
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
}