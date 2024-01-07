package cn.aqi.chatgpt.application;

public interface IWeiXinValidateService {
    boolean checkSign(String signature, String timestamp, String nonce);

}
