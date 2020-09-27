<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<title></title>
</head>	
<body>
<%@ page import="core.*"  %>
<%@ page import="conf.*"  %>
<% Project.init(request, response); %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
	<div class="row">
		<div class="span12">
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="http://www.04007.cn">04007主页</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#"><%=Config.Site.SITE_NAME %></a> <span class="divider">/</span>
					</li>
					<li class="active">本页面及Api接口使用java开发</li>
				</ul>
			</div>
			
			<div class="page-header">
				<h2>
					<%=Config.Site.SITE_NAME %> <small><%=Project.info.get("site_info").get("pro_name")%></small>
				</h2>
			</div>
			
			<h3>1：接口全局返回格式说明</h3>
			<p style="line-height:30px;">
				<strong>URI:</strong><em>请求URI</em>  &nbsp;&nbsp;
				<strong>请求参数：</strong>参数说明。<br>
				接口统一返回格式：<pre>{
	"code": 0,		#接口响应结果：正常响应返回0，异常时返回1或者其它约定的错误码
	"msg": "ok",		#接口响应提示：正常响应返回ok,异常时返回错误信息
	"result": [{}],	        #<strong>返回的数据部分，下方各接口只示例此部分数据。</strong>
	"info": {		#请求的相关信息，比如全局请求唯一ID，响应服务器的信息(方便排查)，此处只是示意展示一下。
		"request_info": {
			"requ_ip": "0:0:0:0:0:0:0:1",
			"requ_length": "-1",
			"requ_uri": "/04007api/ArticleList",
			"requ_method": "GET",
			"requ_query": "page=2",
			"requ_proto": "HTTP/1.1",
			"requ_port": "8080"
		},
		"site_info": {
			"pro_author": "04007.cn",
			"pro_name": "04007Api项目"
		}
	}
}</pre></p>

			<h3>2：文章列表接口 </h3>
			<p style="line-height:30px;">
				<strong>URI:</strong><em>/ArticleList</em>  &nbsp;&nbsp;
				<strong>请求参数：</strong><em>int page</em>,请求第几页； <em>int cid</em>,文章分类ID，见文章分类接口，默认为0即所有类文章。<br>
				<strong>接口示例：</strong><em><a target="_blank" href="http://api.04007.cn/ArticleList?page=2&cid=0">http://api.04007.cn/ArticleList?page=2&cid=0</a></em> <br>
				返回全站文章列表。result部分返回示例：
				<pre>{
    "result": [{
        "articles": [{
		"ar_title": "Java微服务架构"     #文章标题
		"ar_tags": "Java，微服务架构",   #文章关键词
		"ar_view": "20288",               #文章浏览次数
		"ar_cid": "18",                   #文章分类ID
		"id": "857",                      #文章ID
		"ar_comments": "12",              #文章评论数
		"ar_time": "1600912869",          #文章发表时间
		"ar_text": "...微服务架构把一个大型的单级..."  #文章内容
	}],
}</pre>
			</p>
			
			<h3>3：文章详情接口 </h3>
            <p style="line-height:30px;">
                <strong>URI:</strong><em>/ArticleDetail</em>  &nbsp;&nbsp;
                <strong>请求参数：</strong><em>int id</em>,文章ID； <br>
                <strong>接口示例：</strong><em><a target="_blank" href="http://api.04007.cn/ArticleDetail?id=2">http://api.04007.cn/ArticleDetail?id=02</a></em> <br>
                返回具体文章详情。result部分返回示例：
                <pre>{
    "result": [{
        "article": {
        #内容结构同上文章列表
    },
}</pre>
            </p>
            
            <h3>4：文章分类接口 </h3>
            <p style="line-height:30px;">
                <strong>URI:</strong><em>/CategoryList</em>  &nbsp;&nbsp;
                <strong>请求参数：</strong><em>无需传参</em><br>
                <strong>接口示例：</strong><em><a target="_blank" href="http://api.04007.cn/CategoryList">http://api.04007.cn/CategoryList</a></em> <br>
                返回全站文章分类数据。result部分返回示例：
                <pre>{
    "result": [{
        "categorys": [{
            "class_fname": "JAVA/print/Spring Boot", #完全分类名称
            "id": "1",          #分类ID
            "class_name": "JAVA" #简洁分类名称
        }, ....]
    }],
}</pre>
            </p>
            
            <h3>5：评论接口 </h3>
            <p style="line-height:30px;">
                <strong>URI:</strong><em>/CommentList</em>  &nbsp;&nbsp;
                <strong>请求参数：</strong><em>int page</em> 返回第几页数据。<br>
                <strong>接口示例：</strong><em><a target="_blank" href="http://api.04007.cn/CommentList?page=2">http://api.04007.cn/CommentList?page=2</a></em> <br>
                分页返回评论列表。result部分返回示例：
                <pre>{
    "result": [{
        "comments": [{
            "com_text": "mysql5.6.25这个版本也有这样的问题。",  #评论详情
            "com_time": "1594360512",               #评论时间
            "com_arid": "348"                       #评论的文章id
        },.....]
    }],
}</pre>
            </p>
		</div>
	</div>
    <p><br></p>
	<div class="span12">
	     <hr>
         <p class="text-center">
            <div class="span8 text-center">
                <em>04007.cn</em> Copyright © 2020-2025<strong></strong>
            </div>
         </p>
     </div>
</div>
</body>
</html>