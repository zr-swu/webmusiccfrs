<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index_content.jsp' starting page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap3.min.css">
  
  	<style>
    .text-left {
	    text-align: center!important;
	}
	.row .card{
		background: #f1f2f7;
	}
    </style>
  </head>
  <body>
    <div class="col-lg-6" style="max-width:100%;padding:0">
       <div class="card">
           <div class="card-header">
               <strong>首页</strong>
           </div>
           <div class="card-body card-block">
           
           <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <div class="stat-widget-five">
                                    <div class="stat-content">
                                        <div class="text-left dib">
                                            <div class="stat-text"><span class="count">${userCount }</span></div>
                                            <div class="stat-heading">用户数量</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <div class="stat-widget-five">
                                    <div class="stat-content">
                                        <div class="text-left dib">
                                            <div class="stat-text"><span class="count">${playlistCount }</span></div>
                                            <div class="stat-heading">歌单数量</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6" style="margin-top:20px">
                        <div class="card">
                            <div class="card-body">
                                <div class="stat-widget-five">
                                    <div class="stat-content">
                                        <div class="text-left dib">
                                            <div class="stat-text"><span class="count">${musicCount }</span></div>
                                            <div class="stat-heading">音乐数量</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6" style="margin-top:20px">
                        <div class="card">
                            <div class="card-body">
                                <div class="stat-widget-five">
                                    <div class="stat-content">
                                        <div class="text-left dib">
                                            <div class="stat-text"><span class="count">${scoreCount }</span></div>
                                            <div class="stat-heading">音乐评分数量</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6" style="margin-top:20px">
                        <div class="card">
                            <div class="card-body">
                                <div class="stat-widget-five">
                                    <div class="stat-content">
                                        <div class="text-left dib">
                                            <div class="stat-text"><span class="count">${collectionCount }</span></div>
                                            <div class="stat-heading">音乐收藏数量</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6" style="margin-top:20px">
                        <div class="card">
                            <div class="card-body">
                                <div class="stat-widget-five">
                                    <div class="stat-content">
                                        <div class="text-left dib">
                                            <div class="stat-text"><span class="count">${commentCount }</span></div>
                                            <div class="stat-heading">歌单评论数量</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    

                </div>
           
           
           </div><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
       </div>
   </div>
  </body>
</html>
