package com.property.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.property.sys.model.Article;
import com.property.sys.model.Comment;
import com.property.sys.model.Option;
import com.property.sys.service.ArticleService;
import com.property.sys.service.CommentService;
import com.property.sys.service.OptionService;
import com.property.sys.utils.DataTableParams;
import com.property.sys.utils.Page;
import com.sechand.platform.base.BaseAction;

public class ArticleAction extends BaseAction {
	private static final long serialVersionUID = -3008564197229636900L;
	
	private ArticleService articleService;
	private CommentService commentService;
	private OptionService optionService;
	private int currentPage=1;
	private int pageSize=9;
	private int type;
	private Article article;
	private String options;
	private int id;
	private String dataTableParams;//表单参数,json格式
	private String ids;
	/**
	 * 
	 * @Author:Helen  
	 * 2015-3-18下午9:15:28
	 * @return
	 * String
	 * @TODO 获取文章
	 */
	public String list(){
		List<Article> articles=articleService.listPageRowsByType(currentPage, pageSize,type);
		Page page=new Page(currentPage, articleService.countByType(type), pageSize);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", articles);
		map.put("curent", page.getCrr());
		map.put("pg", page.getEnd());
		json.setSuccess(true);
		json.setMsg(map);
		return SUCCESS;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2015-3-18下午9:17:25
	 * @return
	 * String
	 * @TODO 发布文章
	 */
	public String publish(){
		String msg=articleService.add(article, options);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(false);
		}else{
			json.setMsg("发布成功");
			json.setSuccess(true);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2015-3-23下午9:34:35
	 * @return
	 * String 
	 * @TODO 查看文章
	 */
	public String look(){
		Article a=articleService.getById(id);
		if(a!=null){
			List<Comment> comments=commentService.listPageRowCommentsByArticleId(a.getId(), currentPage, pageSize);
			List<Option> options=optionService.listByArticleId(a.getId());
			Page page=new Page(currentPage, commentService.countByArticelId(a.getId()), pageSize);
			Map<String, Object> dataMap=new HashMap<String, Object>();
			dataMap.put("article", a);
			dataMap.put("comments",comments);
			dataMap.put("options",options);
			dataMap.put("page", page);
			json.setMsg(dataMap);
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2015-3-23下午10:47:39
	 * @return
	 * String
	 * @TODO 获取文章列表
	 */
	public String listArticleByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Article> applications=articleService.listPageRowsArticlesByKeyword(params.current_page, params.page_size, params.keyword,true);
		int count=articleService.countByKeyword(params.keyword,true);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
		dataMap.put("data", applications);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2015-3-23下午10:47:39
	 * @return
	 * String
	 * @TODO 获取文章列表
	 */
	public String listArticleByUser(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Article> applications=articleService.listPageRowsArticlesByKeyword(params.current_page, params.page_size, params.keyword,false);
		int count=articleService.countByKeyword(params.keyword,false);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
		dataMap.put("data", applications);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * @author lixiaowei
	 * 2015-3-30 下午2:01:46
	 * @return 
	 * TODO 批量删除文章
	 */
	public String deleteArticleByIds(){
		if(!StringUtils.isBlank(ids)){
			String[] idList=ids.split(",");
			articleService.deleteByIds(idList);
			json.setMsg("删除成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("删除失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataTableParams() {
		return dataTableParams;
	}
	public void setDataTableParams(String dataTableParams) {
		this.dataTableParams = dataTableParams;
	}
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public OptionService getOptionService() {
		return optionService;
	}
	public void setOptionService(OptionService optionService) {
		this.optionService = optionService;
	}
}
