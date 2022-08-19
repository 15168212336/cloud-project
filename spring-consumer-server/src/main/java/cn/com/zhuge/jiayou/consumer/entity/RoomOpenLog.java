package cn.com.zhuge.jiayou.consumer.entity;

import java.util.Date;

public class RoomOpenLog {
    private Long id;
    private Long roomId;
    private Date openLockTime;
    private short openLockType;
    private Long cardId;
    private Long fingerprintId;
    private Date createTime;

    private short openLockResult;

    public short getOpenLockResult() {
        return openLockResult;
    }

    public void setOpenLockResult(short openLockResult) {
        this.openLockResult = openLockResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Date getOpenLockTime() {
        return openLockTime;
    }

    public void setOpenLockTime(Date openLockTime) {
        this.openLockTime = openLockTime;
    }

    public short getOpenLockType() {
        return openLockType;
    }

    public void setOpenLockType(short openLockType) {
        this.openLockType = openLockType;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getFingerprintId() {
        return fingerprintId;
    }

    public void setFingerprintId(Long fingerprintId) {
        this.fingerprintId = fingerprintId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
