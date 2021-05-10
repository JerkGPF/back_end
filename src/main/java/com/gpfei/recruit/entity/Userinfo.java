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
@ApiModel(value="Userinfo对象", description="")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "头像")
    private String head;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "qq")
    private String qq;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "出生日期")
    private String birthday;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "个人简介")
    private String profile;

    @ApiModelProperty(value = "工作经验")
    private String experience;

    @ApiModelProperty(value = "昵称")
    private String nick;

    @ApiModelProperty(value = "个性签名")
    private String motto;

    @ApiModelProperty(value = "个人简历")
    private String file;

    @ApiModelProperty(value = "登录id")
    private Integer loginid;


}
