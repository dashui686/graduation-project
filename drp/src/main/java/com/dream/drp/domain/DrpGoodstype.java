package com.dream.drp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品类型
 * @TableName drp_goodstype
 */
@TableName(value ="drp_goodstype")
@Data
public class DrpGoodstype implements Serializable {
    /**
     * 类型ID
     */
    @TableId(value = "GoodsTypeId", type = IdType.AUTO)
    private Integer goodsTypeId;

    /**
     * 类型名称
     */
    @TableField(value = "GoodsTypeName")
    private String goodsTypeName;

    /**
     * 类型描述
     */
    @TableField(value = "GoodsTypeDesc")
    private String goodsTypeDesc;

    /**
     * 排序号
     */
    @TableField(value = "Sort")
    private Integer sort;

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
        DrpGoodstype other = (DrpGoodstype) that;
        return (this.getGoodsTypeId() == null ? other.getGoodsTypeId() == null : this.getGoodsTypeId().equals(other.getGoodsTypeId()))
            && (this.getGoodsTypeName() == null ? other.getGoodsTypeName() == null : this.getGoodsTypeName().equals(other.getGoodsTypeName()))
            && (this.getGoodsTypeDesc() == null ? other.getGoodsTypeDesc() == null : this.getGoodsTypeDesc().equals(other.getGoodsTypeDesc()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGoodsTypeId() == null) ? 0 : getGoodsTypeId().hashCode());
        result = prime * result + ((getGoodsTypeName() == null) ? 0 : getGoodsTypeName().hashCode());
        result = prime * result + ((getGoodsTypeDesc() == null) ? 0 : getGoodsTypeDesc().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodsTypeId=").append(goodsTypeId);
        sb.append(", goodsTypeName=").append(goodsTypeName);
        sb.append(", goodsTypeDesc=").append(goodsTypeDesc);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}