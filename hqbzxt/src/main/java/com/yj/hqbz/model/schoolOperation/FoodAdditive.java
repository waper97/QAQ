package com.yj.hqbz.model.schoolOperation;
/**
 *
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class FoodAdditive {
    private String id;
    //记录编号
    private String code;
    //责任人ID
    private String liableid;
    //责任人名称
    private String persionLiable;
    //餐食类型
    private Short menutype;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-mm-dd")
    private Date usedate;
    //机构ID
    private BigDecimal fkOrgid;
    //出库明细ID
    private String fkOutdetailid;
    //使用量
    private BigDecimal qty;
    //百分比
    private BigDecimal percent;

    private String remark;

    private Integer status;
    //创建人
    private String creator;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-mm-dd")
    private Date createdate;
    //最后操作人
    private String lastopuser;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-mm-dd")
    private Date lastopdate;
}