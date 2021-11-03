package com.dream.drp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 商品数量
     */
    @TableField(value = "GoodsCount")
    private Integer goodsCount;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Integer warehouseId;

    @TableField(exist = false)
    private String warehouseName;

    @TableField(exist = false)
    private String goodsTypeName;

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
            && (this.getGoodsCount() == null ? other.getGoodsCount() == null : this.getGoodsCount().equals(other.getGoodsCount()))
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
        result = prime * result + ((getGoodsCount() == null) ? 0 : getGoodsCount().hashCode());
        result = prime * result + ((getGoodsLocality() == null) ? 0 : getGoodsLocality().hashCode());
        result = prime * result + ((getGoodsUnit() == null) ? 0 : getGoodsUnit().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

}