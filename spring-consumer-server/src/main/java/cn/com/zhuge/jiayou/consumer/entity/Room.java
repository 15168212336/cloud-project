package cn.com.zhuge.jiayou.consumer.entity;

import java.util.Date;

public class Room {
    private Long id;
    private String roomNo;
    private String roomName;
    private String roomUuid;
    private String roomCode;
    private Long unitId;
    private Long orgId;
    private Long roomOwnerId;
    private Long manageOrgId;
    private double roomArea;
    private String roomPic;
    private Byte layoutType;
    private String cardCryptKey;
    private Byte rentState;
    private Byte isShow;
    private Byte validState;
    private String createBy;
    private Date createTime;
    private String remark;
    private String modifiedBy;
    private Date modifiedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomUuid() {
        return roomUuid;
    }

    public void setRoomUuid(String roomUuid) {
        this.roomUuid = roomUuid;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getRoomOwnerId() {
        return roomOwnerId;
    }

    public void setRoomOwnerId(Long roomOwnerId) {
        this.roomOwnerId = roomOwnerId;
    }

    public Long getManageOrgId() {
        return manageOrgId;
    }

    public void setManageOrgId(Long manageOrgId) {
        this.manageOrgId = manageOrgId;
    }

    public double getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(double roomArea) {
        this.roomArea = roomArea;
    }

    public String getRoomPic() {
        return roomPic;
    }

    public void setRoomPic(String roomPic) {
        this.roomPic = roomPic;
    }

    public Byte getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(Byte layoutType) {
        this.layoutType = layoutType;
    }

    public String getCardCryptKey() {
        return cardCryptKey;
    }

    public void setCardCryptKey(String cardCryptKey) {
        this.cardCryptKey = cardCryptKey;
    }

    public Byte getRentState() {
        return rentState;
    }

    public void setRentState(Byte rentState) {
        this.rentState = rentState;
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public Byte getValidState() {
        return validState;
    }

    public void setValidState(Byte validState) {
        this.validState = validState;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
