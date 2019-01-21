//页面获取用户cookie值
$(function(){
    //取出cookie中用户名
    var username=getCookie("username");
    $(".user.user-menu .hidden-xs").html(username);
    //点击登出时 调用登出接口
    $(".user-footer .btn-flat").click(function(){
        $.ajax({
            type:"get",
            url:"http://localhost/login/quit",
            xhrFields: {withCredentials: true},
            data:{},
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            async:true,
            success:function(data){
                delCookie("username")
                window.location.href="index";
            }
        });
    });

})
//判断页面是否有cookie值 没有的话跳转登录页
function judgeCookie(){
    if(getCookie('username')==null||getCookie('username')==''||getCookie('username')==undefined){
        window.location.href="index";
    }
}

//获取cookie
function getCookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
        return unescape(arr[2]);
    }
    else{
        return null;
    }
}

//删除cookie
function delCookie(name){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null){
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    }
}

//设置cookie
function setCookie(name,value){
    var Days = 1;
    var exp = new Date();
    // exp.setTime(exp.getTime() + Days*24*60*60*1000);
    exp.setTime(exp.getTime() + Days*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}