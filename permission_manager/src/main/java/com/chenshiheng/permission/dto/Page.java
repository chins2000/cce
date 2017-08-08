package com.chenshiheng.permission.dto;

import java.util.LinkedList;
import java.util.List;

public class Page {

	private int totalNumber;
	
	private int pageNumber;
	
	private int totalpage;
	
	private int currentPage;
	
	private int dbIndex;
	
	private int dbNumber;
	
	private Integer[] showpages;
	
	public Page(){
		this.dbIndex=0;
		this.dbNumber=5;
		this.pageNumber=5;
		this.currentPage=1;
	}
	
	/**
	 * 
	 * @param 每页条数
	 */
	public Page(int pageNumber){
		this.dbIndex=0;
		this.dbNumber=pageNumber;
		this.pageNumber=pageNumber;
		this.currentPage=1;
	}
	
	public Page(int totalNumber, int pageNumber, int totalpage, int currentPage) {
		this.totalNumber = totalNumber;
		this.pageNumber = pageNumber;
		this.totalpage = totalpage;
		this.currentPage = currentPage;
		this.count();
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	
	public void setTotalNumberOther(int totalNumber) {
		this.totalNumber = totalNumber;
		this.totalpage=(totalNumber%this.pageNumber==0)?(totalNumber/this.pageNumber):(totalNumber/this.pageNumber+1);
		List<Integer> list =new LinkedList<Integer>();
		for(int i =-3;i<4;i++ ){
			if(this.currentPage+i>0 && this.currentPage+i<=this.totalpage){
				list.add(this.currentPage+i);
			}
		}
		this.showpages=list.toArray(new Integer[]{});
	}
	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer[] getShowpages() {
		return showpages;
	}

	public void setShowpages(Integer[] showpages) {
		this.showpages = showpages;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getDbIndex() {
		return dbIndex;
	}

	public int getDbNumber() {
		return dbNumber;
	}

	/**
	 * 根据当前Page对象中的其他属性设置limit的参数
	 */
	public void count(){
		int totalPageTemp = this.totalNumber/this.pageNumber;
		int plus = (this.totalNumber%this.pageNumber) == 0?0:1;
		totalPageTemp +=plus;
		if(totalPageTemp <= 0){
			totalPageTemp = 1;
		}
		this.totalpage=totalPageTemp;
		
		if(this.totalpage<this.currentPage){
			this.currentPage=this.totalpage;
		}
		
		if(this.currentPage<1){
			this.currentPage=1;
		}
		
		this.dbIndex = (this.currentPage-1)*this.pageNumber;
		this.dbNumber = this.pageNumber;
	}
	
}
