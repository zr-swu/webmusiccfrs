<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<aside id="left-panel" class="left-panel">
   <nav class="navbar navbar-expand-sm navbar-default">
       <div id="main-menu" class="main-menu collapse navbar-collapse">
           <ul class="nav navbar-nav">
               <li class="" style="border-bottom: 1px solid #fafafa;">
                   <a href="${pageContext.request.contextPath}/admin_index_content" class="self-a-font" target="admin_iframe">首页</a>
               </li>
               <li class="menu-item-has-children dropdown" style="border-bottom: 1px solid #fafafa;">
                   <a href="javascript:void(null)" class="dropdown-toggle self-a-font" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">用户管理</a>
                   <ul class="sub-menu children dropdown-menu">                            
                   	<li><a href="${pageContext.request.contextPath}/admin_user_list" target="admin_iframe">用户列表</a></li>
                   </ul>
               </li>
               <li class="menu-item-has-children dropdown" style="border-bottom: 1px solid #fafafa;">
                   <a href="javascript:void(null)" class="dropdown-toggle self-a-font" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">歌单管理</a>
                   <ul class="sub-menu children dropdown-menu">                            
                   	<li><a href="${pageContext.request.contextPath}/admin_playlist_list" target="admin_iframe">歌单列表</a></li>
                   </ul>
               </li>
               <li class="menu-item-has-children dropdown" style="border-bottom: 1px solid #fafafa;">
                   <a href="javascript:void(null)" class="dropdown-toggle self-a-font" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">音乐管理</a>
                   <ul class="sub-menu children dropdown-menu">                            
                   	<li><a href="${pageContext.request.contextPath}/admin_music_list" target="admin_iframe">音乐列表</a></li>
                   </ul>
               </li>
               <li class="menu-item-has-children dropdown" style="border-bottom: 1px solid #fafafa;">
                   <a href="javascript:void(null)" class="dropdown-toggle self-a-font" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">音乐评分管理</a>
                   <ul class="sub-menu children dropdown-menu">                            
                   	<li><a href="${pageContext.request.contextPath}/admin_score_list" target="admin_iframe">音乐评分列表</a></li>
                   </ul>
               </li>
               <li class="menu-item-has-children dropdown" style="border-bottom: 1px solid #fafafa;">
                   <a href="javascript:void(null)" class="dropdown-toggle self-a-font" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">音乐收藏管理</a>
                   <ul class="sub-menu children dropdown-menu">
                       <li><a href="${pageContext.request.contextPath}/admin_collection_list" target="admin_iframe">音乐收藏列表</a></li>
                   </ul>
               </li>
               <li class="menu-item-has-children dropdown" style="border-bottom: 1px solid #fafafa;">
                   <a href="javascript:void(null)" class="dropdown-toggle self-a-font" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">歌单评论管理</a>
                   <ul class="sub-menu children dropdown-menu">
                       <li><a href="${pageContext.request.contextPath}/admin_comment_list" target="admin_iframe">歌单评论列表</a></li>
                   </ul>
               </li>
           </ul>
       </div><!-- /.navbar-collapse -->
    </nav>
</aside>
<script>
	
</script>