package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学校信息对象 school
 * 
 * @author ruoyi
 * @date 2019-10-11
 */
public class School extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学校ID */
    private Long scId;

    /** 学校名称 */
    @Excel(name = "学校名称")
    private String scName;

    /** 学校位置 */
    @Excel(name = "学校位置")
    private String scLoation;

    /** 是否是重点学校（0是，1否） */
    @Excel(name = "是否是重点学校", readConverterExp = "0=是，1否")
    private Integer scPoint;

    /** 校长 */
    @Excel(name = "校长")
    private String scMaster;

    /** 更新者 */
    @Excel(name = "更新者")
    private String scCreateby;

    /** 删除标志(0已删除，1未删除) */
    private Integer scDelflag;

    /** 是否是公立学校(0 是，1否) */
    @Excel(name = "是否是公立学校(0 是，1否)")
    private Integer scIspublic;

    public void setScId(Long scId) 
    {
        this.scId = scId;
    }

    public Long getScId() 
    {
        return scId;
    }
    public void setScName(String scName) 
    {
        this.scName = scName;
    }

    public String getScName() 
    {
        return scName;
    }
    public void setScLoation(String scLoation) 
    {
        this.scLoation = scLoation;
    }

    public String getScLoation() 
    {
        return scLoation;
    }
    public void setScPoint(Integer scPoint) 
    {
        this.scPoint = scPoint;
    }

    public Integer getScPoint() 
    {
        return scPoint;
    }
    public void setScMaster(String scMaster) 
    {
        this.scMaster = scMaster;
    }

    public String getScMaster() 
    {
        return scMaster;
    }
    public void setScCreateby(String scCreateby) 
    {
        this.scCreateby = scCreateby;
    }

    public String getScCreateby() 
    {
        return scCreateby;
    }
    public void setScDelflag(Integer scDelflag) 
    {
        this.scDelflag = scDelflag;
    }

    public Integer getScDelflag() 
    {
        return scDelflag;
    }
    public void setScIspublic(Integer scIspublic) 
    {
        this.scIspublic = scIspublic;
    }

    public Integer getScIspublic() 
    {
        return scIspublic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scId", getScId())
            .append("scName", getScName())
            .append("scLoation", getScLoation())
            .append("scPoint", getScPoint())
            .append("scMaster", getScMaster())
            .append("scCreateby", getScCreateby())
            .append("scDelflag", getScDelflag())
            .append("scIspublic", getScIspublic())
            .toString();
    }
}
