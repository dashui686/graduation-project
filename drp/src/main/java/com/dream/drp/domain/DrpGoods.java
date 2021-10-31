package com.dream.drp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 商品表
 * @TableName drp_goods
 */
@TableName(value ="drp_goods")
@Data
public class DrpGoods implements Serializable {
    /**
     * 商品ID
     */
    @TableId(value = "GoodsId", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 商品名称
     */
    @TableField(value = "GoodsName")
    private String goodsName;

    /**
     * 商品分类
     */
    @TableField(value = "GoodsTypeId")
    private Integer goodsTypeId;

    /**
     * 商品编号
     */
    @TableField(value = "GoodsNo")
    private String goodsNo;

    /**
     * 商品型号
     */
    @TableField(value = "GoodsModel")
    private String goodsModel;

    /**
     * 商品产地
     */
    @TableField(value = "GoodsLocality")
    private String goodsLocality;

    /**
     * 商品单位
     */
    @TableField(value = "GoodsUnit")
    private String goodsUnit;

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
        DrpGoods other = (DrpGoods) that;
        return (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getGoodsTypeId() == null ? other.getGoodsTypeId() == null : this.getGoodsTypeId().equals(other.getGoodsTypeId()))
            && (this.getGoodsNo() == null ? other.getGoodsNo() == null : this.getGoodsNo().equals(other.getGoodsNo()))
            && (this.getGoodsModel() == null ? other.getGoodsModel() == null : this.getGoodsModel().equals(other.getGoodsModel()))
            && (this.getGoodsLocality() == null ? other.getGoodsLocality() == null : this.getGoodsLocality().equals(other.getGoodsLocality()))
            && (this.getGoodsUnit() == null ? other.getGoodsUnit() == null : this.getGoodsUnit().equals(other.getGoodsUnit()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getGoodsTypeId() == null) ? 0 : getGoodsTypeId().hashCode());
        result = prime * result + ((getGoodsNo() == null) ? 0 : getGoodsNo().hashCode());
        result = prime * result + ((getGoodsModel() == null) ? 0 : getGoodsModel().hashCode());
        result = prime * result + ((getGoodsLocality() == null) ? 0 : getGoodsLocality().hashCode());
        result = prime * result + ((getGoodsUnit() == null) ? 0 : getGoodsUnit().hashCode());
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
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsTypeId=").append(goodsTypeId);
        sb.append(", goodsNo=").append(goodsNo);
        sb.append(", goodsModel=").append(goodsModel);
        sb.append(", goodsLocality=").append(goodsLocality);
        sb.append(", goodsUnit=").append(goodsUnit);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}