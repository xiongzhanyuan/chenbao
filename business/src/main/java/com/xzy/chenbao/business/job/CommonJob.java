package com.xzy.chenbao.business.job;

import com.alibaba.fastjson.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CommonJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String method = context.getJobDetail().getKey().getName();
		JSONObject data = (JSONObject) context.getJobDetail().getJobDataMap().get("data");
	}

}
