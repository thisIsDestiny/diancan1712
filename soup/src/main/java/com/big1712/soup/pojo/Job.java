package com.big1712.soup.pojo;

public class Job {

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
	 * 地址
	 * 工作经历
	 * 学历
	 * 发布时间
	 */
	
	private String name;
	private String salary;
	private Company company;
	private String address;
	private String publish_date;
	
	class JobRequirment{
		
		private String exp;
		private String education;
		public String getExp() {
			return exp;
		}
		public void setExp(String exp) {
			this.exp = exp;
		}
		public String getEducation() {
			return education;
		}
		public void setEducation(String education) {
			this.education = education;
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	
	
	
}
