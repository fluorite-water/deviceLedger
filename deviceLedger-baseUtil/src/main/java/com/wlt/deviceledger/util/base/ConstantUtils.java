package com.wlt.deviceledger.util.base;


public class ConstantUtils {

	/********************* 审批状态码***********************/
	/**
	 * 0 待审批
	 */
	public static final Integer APPROVAL_STATE0 = 0;
	/**
	 * 1 本部么管理员审批
	 */
	public static final Integer APPROVAL_STATE1 = 1;
	/**
	 * 2 设备主管部门审批
	 */
	public static final Integer APPROVAL_STATE2 = 2;
	/**
	 * 3 公司主管汇总
	 */
	public static final Integer APPROVAL_STATE3 = 3;
	/**
	 * 4 采购部门正在采购中
	 */
	public static final Integer APPROVAL_STATE4 = 4;
	/**
	 * 5  采购完成
	 */
	public static final Integer APPROVAL_STATE5 = 5;
	/**
	 * 9  审批已拒绝
	 */
	public static final Integer APPROVAL_STATE9 = 9;
	
    public static final String SUCCESS_CODE = "200";

    public static final String ERROR_CODE = "10000";

    public static final Boolean SUCCESS_MESSAGE = true;

    public static final Boolean ERROR_MESSAGE = false;

    public static final String MD5_TYPE = "md5";

    public static final String TOP_CODE = "TOP";



    public static <T> ResultData<T> printMessage(String code,Boolean codeMsg, String msg, T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setSuccess(codeMsg);
        resultData.setObj(data);
        return resultData;
    }

    public static <T> ResultData<T> printSuccessMessage(String message, T data) {
        return printMessage(SUCCESS_CODE, SUCCESS_MESSAGE, message, data);
    }

    public static <T> ResultData<T> printErrorMessage(String message) {
        return printMessage(ERROR_CODE, ERROR_MESSAGE , message, null);
    }


    public static <T> ResultData<T> printErrorMessage() {
        return printErrorMessage("网络出现异常");
    }

    /**
     * listToTree
     * <p>方法说明<p>
     * 将List数组转为树状结构
     * @param list 需要转化的数据
     * @return JSONArray
     */
    /*public static List listToTree (List<AuthPermission> list){
        List returnList = new ArrayList();
        Map<String, Object> result = new HashMap<>();

        for(int i=0;i < list.size();i++){
            AuthPermission permission = list.get(i);
            result.put(permission.getId(), permission);
        }

        //遍历结果集
        for(int j = 0;j < list.size();j++){
            //单条记录
            AuthPermission current = list.get(j);

            //在hash中取出key为单条记录中pid的值
            AuthPermission parent = (AuthPermission) result.get(current.getPid());

            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if(parent != null){
                //检查是否有child属性
                if(parent.getPermissionList() != null){
                    List<AuthPermission> permissionList = parent.getPermissionList();
                    permissionList.add(current);

                }else{
                    List<AuthPermission> childList = new ArrayList<>();
                    childList.add(current);
                    parent.setPermissionList(childList);
                }
            } else {
                returnList.add(current);
            }
        }
        return returnList;
    }*/

}
