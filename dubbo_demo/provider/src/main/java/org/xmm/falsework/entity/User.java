package org.xmm.falsework.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName(value = "t_user")
public class User {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 姓名
     */
    @TableField
    private String name;

    /**
     * 年龄
     */
    @TableField
    private Integer age;

    /**
     * 邮箱
     */
    @TableField(value="e_mail")
    private String eMail;
}
