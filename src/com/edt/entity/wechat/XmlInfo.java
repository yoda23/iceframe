package com.edt.entity.wechat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlInfo {
        private Integer id;

        @XmlElement(name="ToUserName")
        private String toUserName;

        @XmlElement(name="FromUserName")
        private String fromUserName;

        @XmlElement(name="CreateTime")
        private Integer createTime;

        @XmlElement(name="MsgType")
        private String msgType;

        @XmlElement(name="Event")
        private String event;

        @XmlElement(name="EventKey")
        private String eventKey;

        @XmlElement(name="Content")
        private String content;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getToUserName() {
            return toUserName;
        }

        public void setToUserName(String toUserName) {
            this.toUserName = toUserName;
        }

        public String getFromUserName() {
            return fromUserName;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public Integer getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Integer createTime) {
            this.createTime = createTime;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getEventKey() {
            return eventKey;
        }

        public void setEventKey(String eventKey) {
            this.eventKey = eventKey;
        }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
