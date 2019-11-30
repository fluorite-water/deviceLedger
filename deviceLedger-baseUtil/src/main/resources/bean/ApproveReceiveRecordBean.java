package bean;

import java.io.Serializable;

public class ApproveReceiveRecordBean implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 领取id
     */
    private Integer receiveId;

    /**
     * 审批拒绝原因
     */
    private String receiveReason;

    /**
     * 0拒绝1通过
     */
    private Integer approveState;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * tbl_approve_receive_record
     */
    private static final long serialVersionUID = 1L;

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
     * 获取领取id
     * @return receive_id 领取id
     */
    public Integer getReceiveId() {
        return receiveId;
    }

    /**
     * 设置领取id
     * @param receiveId 领取id
     */
    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    /**
     * 获取审批拒绝原因
     * @return receive_reason 审批拒绝原因
     */
    public String getReceiveReason() {
        return receiveReason;
    }

    /**
     * 设置审批拒绝原因
     * @param receiveReason 审批拒绝原因
     */
    public void setReceiveReason(String receiveReason) {
        this.receiveReason = receiveReason;
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