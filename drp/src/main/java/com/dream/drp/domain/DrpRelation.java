package com.dream.drp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName drp_relation
 */
@TableName(value ="drp_relation")
@Data
public class DrpRelation implements Serializable {
    /**
     * ID
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "Name")
    private String name;

    /**
     * 联系人
     */
    @TableField(value = "Contacts")
    private String contacts;

    /**
     * 联系人职务
     */
    @TableField(value = "ContactsPoition")
    private String contactsPoition;

    /**
     * 联系人电话
     */
    @TableField(value = "ContactsPhone")
    private String contactsPhone;

    /**
     * 地址
     */
    @TableField(value = "Address")
    private String address;

    /**
     * 银行卡号
     */
    @TableField(value = "BankAccount")
    private String bankAccount;

    /**
     * 开户名称
     */
    @TableField(value = "BankName")
    private String bankName;

    /**
     * 最后交易日期
     */
    @TableField(value = "LasttradingDate")
    private LocalDateTime lasttradingDate;

    /**
     * 最后交易金额
     */
    @TableField(value = "LasttradingMoney")
    private BigDecimal lasttradingMoney;

    /**
     * 创建日期
     */
    @TableField(value = "CreateDate")
    private LocalDateTime createDate;

    /**
     * 创建人
     */
    @TableField(value = "CreateUsers")
    private Integer createUsers;

    /**
     * 更新日期
     */
    @TableField(value = "UpdateDate")
    private LocalDateTime updateDate;

    /**
     * 更新人
     */
    @TableField(value = "UpdateUsers")
    private Integer updateUsers;

    /**
     * 状态
     */
    @TableField(value = "Status")
    private Integer status;

    /**
     * 联系人部门
     */
    @TableField(value = "ContactsDepartment")
    private String contactsDepartment;

    /**
     * 关系类型
     */
    @TableField(value = "Type")
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DrpRelation other = (DrpRelation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getContacts() == null ? other.getContacts() == null : this.getContacts().equals(other.getContacts()))
            && (this.getContactsPoition() == null ? other.getContactsPoition() == null : this.getContactsPoition().equals(other.getContactsPoition()))
            && (this.getContactsPhone() == null ? other.getContactsPhone() == null : this.getContactsPhone().equals(other.getContactsPhone()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getBankAccount() == null ? other.getBankAccount() == null : this.getBankAccount().equals(other.getBankAccount()))
            && (this.getBankName() == null ? other.getBankName() == null : this.getBankName().equals(other.getBankName()))
            && (this.getLasttradingDate() == null ? other.getLasttradingDate() == null : this.getLasttradingDate().equals(other.getLasttradingDate()))
            && (this.getLasttradingMoney() == null ? other.getLasttradingMoney() == null : this.getLasttradingMoney().equals(other.getLasttradingMoney()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateUsers() == null ? other.getCreateUsers() == null : this.getCreateUsers().equals(other.getCreateUsers()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateUsers() == null ? other.getUpdateUsers() == null : this.getUpdateUsers().equals(other.getUpdateUsers()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getContactsDepartment() == null ? other.getContactsDepartment() == null : this.getContactsDepartment().equals(other.getContactsDepartment()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getContacts() == null) ? 0 : getContacts().hashCode());
        result = prime * result + ((getContactsPoition() == null) ? 0 : getContactsPoition().hashCode());
        result = prime * result + ((getContactsPhone() == null) ? 0 : getContactsPhone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getBankAccount() == null) ? 0 : getBankAccount().hashCode());
        result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
        result = prime * result + ((getLasttradingDate() == null) ? 0 : getLasttradingDate().hashCode());
        result = prime * result + ((getLasttradingMoney() == null) ? 0 : getLasttradingMoney().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateUsers() == null) ? 0 : getCreateUsers().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateUsers() == null) ? 0 : getUpdateUsers().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getContactsDepartment() == null) ? 0 : getContactsDepartment().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", contacts=").append(contacts);
        sb.append(", contactsPoition=").append(contactsPoition);
        sb.append(", contactsPhone=").append(contactsPhone);
        sb.append(", address=").append(address);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", bankName=").append(bankName);
        sb.append(", lasttradingDate=").append(lasttradingDate);
        sb.append(", lasttradingMoney=").append(lasttradingMoney);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUsers=").append(createUsers);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUsers=").append(updateUsers);
        sb.append(", status=").append(status);
        sb.append(", contactsDepartment=").append(contactsDepartment);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}