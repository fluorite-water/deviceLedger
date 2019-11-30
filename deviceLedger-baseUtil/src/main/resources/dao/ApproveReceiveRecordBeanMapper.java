package dao;

import bean.ApproveReceiveRecordBean;
import bean.ApproveReceiveRecordBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApproveReceiveRecordBeanMapper {
    int countByExample(ApproveReceiveRecordBeanExample example);

    int deleteByExample(ApproveReceiveRecordBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApproveReceiveRecordBean record);

    int insertSelective(ApproveReceiveRecordBean record);

    List<ApproveReceiveRecordBean> selectByExample(ApproveReceiveRecordBeanExample example);

    ApproveReceiveRecordBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApproveReceiveRecordBean record, @Param("example") ApproveReceiveRecordBeanExample example);

    int updateByExample(@Param("record") ApproveReceiveRecordBean record, @Param("example") ApproveReceiveRecordBeanExample example);

    int updateByPrimaryKeySelective(ApproveReceiveRecordBean record);

    int updateByPrimaryKey(ApproveReceiveRecordBean record);
}