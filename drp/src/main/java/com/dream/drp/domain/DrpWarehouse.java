package com.dream.drp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName drp_warehouse
 */
@TableName(value ="drp_warehouse")
@Data
public class DrpWarehouse implements Serializable {
    /**
     * 仓库ID
     */
    @TableId(value = "WarehouseId", type = IdType.AUTO)
    private Integer warehouseId;

    /**
     * 仓库名称
     */
    @TableField(value = "WarehouseName")
    private String warehouseName;

    /**
     * 仓库描述
     */
    @TableField(value = "WarehouseDesc")
    private String warehouseDesc;

    /**
     * 排序号
     */
    @TableField(value = "Sort")
    private Integer sort;

    /**
     * 
     */
    @TableField(value = "CreateDate")
    private LocalDateTime createDate;

    /**
     * 
     */
    @TableField(value = "UpdateDate")
    private LocalDateTime updateDate;

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
        DrpWarehouse other = (DrpWarehouse) that;
        return (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()))
            && (this.getWarehouseName() == null ? other.getWarehouseName() == null : this.getWarehouseName().equals(other.getWarehouseName()))
            && (this.getWarehouseDesc() == null ? other.getWarehouseDesc() == null : this.getWarehouseDesc().equals(other.getWarehouseDesc()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        result = prime * result + ((getWarehouseName() == null) ? 0 : getWarehouseName().hashCode());
        result = prime * result + ((getWarehouseDesc() == null) ? 0 : getWarehouseDesc().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", warehouseName=").append(warehouseName);
        sb.append(", warehouseDesc=").append(warehouseDesc);
        sb.append(", sort=").append(sort);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}