package io.kimmking.rpcfx.client;

/**
 * @Classname RpcfxException
 * @Description TODO
 * @Date 2021/8/27 4:27 下午
 * @Created by sunchangji
 */
public class RpcfxException extends RuntimeException{

    private int errorCode;

    private String errorMsg;

    public RpcfxException(int errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
