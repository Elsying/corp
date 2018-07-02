<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <title>Lz_CMS-后台管理中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/lib/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/global.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/lib/layui/layui.js"></script>
     <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<style type="text/css">html{background: #f3f3f3;}</style>
<div class="login_page">
    <img class="logo-login" src="${pageContext.request.contextPath }/images/logo-login.png" alt="logo">
    <h1>欢迎使用 Lz</h1>
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-input-inline input-custom-width">
                <input type="text" name="username" lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline input-custom-width">
                <input type="password" name="password" lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline input-custom-width">
                <input type="text" name="randomcode" required="" lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
                <div class="captcha"><img src="${pageContext.request.contextPath }/user/getVCode" id="kaptchaImage" alt="captche" title='点击切换' onclick="changeVerifyCode()"></div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline input-custom-width">
              <button class="layui-btn input-custom-width" lay-submit="" lay-filter="login">立即登陆</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
function changeVerifyCode(){  
    document.getElementById("kaptchaImage").src="${pageContext.request.contextPath }/user/getVCode";  
}  

layui.use('form',function(){
     var form = layui.form;
              // layer.msg('玩命卖萌中', function(){
              //   //关闭后的操作
              //   });
              //监听提交
               
              form.on('submit(login)', function(data){
            	  //写在里面
            	  var name = $("input[name='username']").val();
                  var password = $("input[name='password']").val();
                  var randomcode = $("input[name='randomcode']").val();
            	  $.ajax({
                      url : "login",
                      type : "POST",
                      dataType : "json",
                      data :{name:name, password:password,randomcode:randomcode},
                      success : function(result) {
                          console.log(result.code);
                          if(result.code==200){                        
                        	  layer.open({
                        		  content: result.message
                        		  ,btn: '确定'
                        		  ,yes: function(index, layero){
                        			  window.location.href = "${pageContext.request.contextPath}/user/index";
                        		  }
                        		});
                              
                          }
                          else {
                        	  
                        	  layer.alert(result.message, {icon: 6},function () {
                              parent.layer.close(layer.index);});                                
                          }
                      },
                      error : function(rtn) {
                      	alert("erro");
                      }
                  });            	              	             	  
            	  
                // alert(888)
               // layer.msg(JSON.stringify(data.field),function(){e
                //    location.href='index.html'
               // });
                return false;
              });
            });
</script>
</body>
</html>