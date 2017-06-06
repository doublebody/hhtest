package com.yunjiacloud.nj.httpserver.proxy.schedule.job;


public interface JobFactory {
	public Job createJob(String jsonRequest);
	
}
