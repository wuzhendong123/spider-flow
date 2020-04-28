package org.spiderflow.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("sp_task")
@Data
public class Task {

	@TableId(type = IdType.UUID)
	private String id;

	private String flowId;

	private Date beginTime;

	private Date endTime;

}
