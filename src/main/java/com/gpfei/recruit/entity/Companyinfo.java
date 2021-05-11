package com.gpfei.recruit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="Companyinfo对象", description="")
public class Companyinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "福利待遇")
    private String free;

    @ApiModelProperty(value = "成立日期")
    private String birthday;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "公司简介")
    private String profile;

    @ApiModelProperty(value = "公司邮箱")
    private String email;

    @ApiModelProperty(value = "登录id")
    private String username;


}
