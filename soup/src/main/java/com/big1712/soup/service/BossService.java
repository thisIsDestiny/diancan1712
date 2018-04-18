package com.big1712.soup.service;

public class BossService {
	
	/**
	 * 获取请求列表地址
	 * e.g 
	 * 	https://www.zhipin.com/c101020100/h_101020100/?query=大数据&page=1&ka=page-2
	 * @param keywords	关键字
	 * @param page		分页页码
	 * @return
	 */
	private String getQueryUrlStr(String keywords ,int page){
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://www.zhipin.com/c101020100/h_101020100/?query=").append(keywords)
		.append("&page=").append(page)
		.append("&ka=page-2");
		return sb.toString();
	}
	
	/**
	 * 获取招聘岗位详细信息
	 * https://www.zhipin.com/job_detail/1419134801.html
	 */
	private String getDetailPageUrlStr(String jobid){
		
		return  "https://www.zhipin.com/job_detail/"+jobid+".html";
	}
	
	/**
	 * 获取列表字段
	 * 职位信息 --
	 * 
	 * 岗位名称
	 * 薪资
	 * 公司
	 * 	所属行业
	 * 	企业资产
	 *	 企业规模
	 * 	地址
	 * 工作经历
	 * 学历
	 * 发布时间
	 */
	
}
