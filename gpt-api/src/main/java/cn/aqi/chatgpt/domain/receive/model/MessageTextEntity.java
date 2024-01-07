package cn.aqi.chatgpt.domain.receive.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @Title: MessageTextEntity
 * @Author Xu Jing
 * @Package cn.aqi.chatgpt.domain.receive.model
 * @Date 2024/1/6 17:29
 * @description:微信请求接受实体类
 */
@Data
public class MessageTextEntity {

    //@XStreamAlias注解实现xml转换为java对象
    @XStreamAlias("MsgId")
    private String msgId;
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;
    @XStreamAlias("Content")
    private String content;
    @XStreamAlias("Event")
    private String event;
    @XStreamAlias("EventKey")
    private String eventKey;

}
