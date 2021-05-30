package com.gpfei.recruit.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author gpfei
 * @since 2021-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Jobinfo对象", description="")
public class Jobinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "被查看的人数")
    private Integer count;

    @ApiModelProperty(value = "职位详情")
    private String detail;

    @ApiModelProperty(value = "工作地址")
    private String place;

    @ApiModelProperty(value = "工资薪水")
    private String salary;

    @ApiModelProperty(value = "工作标题")
    private String title;

    @ApiModelProperty(value = "类型（兼职，实习，全职）")
    private String kind;

    @ApiModelProperty(value = "发布公司id")
    private String username;

    @ApiModelProperty(value = "公司名称")
    private String companyname;

    private Boolean isdeleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;

}
