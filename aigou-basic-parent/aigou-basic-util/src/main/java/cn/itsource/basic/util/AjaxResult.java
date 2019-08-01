package cn.itsource.basic.util;

//返回前台回掉ajax信息
public class AjaxResult {

    private boolean success = true;

    private String msg = "操作成功！";

    private Object restObj;

    private Integer errorCode;

    //私有化构造器
    private AjaxResult(){}

    //提供一个获取AjaxResult对象方法
    public static AjaxResult getAjaxResult(){
        return new AjaxResult();
    }

    public boolean isSuccess() {
        return success;
    }

    //链式添加返回结果信息
    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getRestObj() {
        return restObj;
    }

    public AjaxResult setRestObj(Object restObj) {
        this.restObj = restObj;
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public AjaxResult setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
