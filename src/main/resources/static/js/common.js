//页面获取用户cookie值
$(function(){
    //取出cookie中用户名
    var username=getCookie("username");
    var userId=getCookie("userId");
    var email=getCookie("email");
    $(".user.user-menu .hidden-xs").html(username);
    //点击登出时 调用登出接口
    $("#signOut").click(function(){
        $.ajax({
            type:"get",
            url:"login/quit",
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

    //修改邮箱弹窗
    var emailRes= '<div class="modal-dialog">\n' +
        '            <div class="modal-content">\n' +
        '                  <div class="modal-header">\n' +
        '                        <button type="button" id="closeEmail" class="close" data-dismiss="modal" aria-label="Close">\n' +
        '                            <span aria-hidden="true">&times;</span></button>\n' +
        '                        <h4 class="modal-title">修改邮箱</h4>\n' +
        '                      </div>\n' +
        '                  <div class="modal-body">\n' +
        '                      <table class="change-email-table">\n' +
        '                          <tr>\n' +
        '                              <td>原邮箱：</td>\n' +
        '                              <td><input type="text" id="oldEmail" class="emailNotEmpty" data="no" placeholder='+email+' /></td>\n' +
        '                          </tr>\n' +
        '                          <tr>\n' +
        '                              <td>新邮箱：</td>\n' +
        '                              <td><input type="text" id="newEmail" class="emailNotEmpty" data="no"/></td>\n' +
        '                              <td><div class="error-msg1 emailError">新邮箱不能为空！</div></td>\n' +
        '                          </tr>\n' +
        '                      </table>\n' +

        '                  </div>\n' +
        '                  <div class="modal-footer">\n' +
        '                        <button type="button" class="btn btn-default-style" id="EmailChangeTrue">确定</button>\n' +
        '                      </div>\n' +
        '                </div>\n' +
        '          </div>';
    $("#modalChangeEmail").html(emailRes);
    //点击修改邮箱出现弹窗
    $('#changeEmail').click(function () {
        $('#modalChangeEmail').show();
        $('.progress-bg').show();
        var newEmail=getCookie('email');
        $("#oldEmail").val(newEmail);
        $('#newEmail').val('');
        document.body.addEventListener('touchmove',bodyScroll,false);
        $('body').css({'position':'fixed',"width":"100%"});
    })

    //修改邮箱点击确定按钮
    $("#EmailChangeTrue").click(function(){
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        var newEmail=$("#newEmail").val();
        if(newEmail==''||newEmail==null||newEmail==undefined){
            $(".emailError").html('新邮箱不能为空');
            $(".emailError").show();
            return false;
        }
        if(!reg.test(newEmail)){
            $(".emailError").html('新邮箱格式错误');
            $(".emailError").show();
            return false;
        }
        //调用修改邮箱接口
        changeEmail(newEmail,userId);
    });

    //取消修改邮箱
    $('#closeEmail').click(function () {
        $('#modalChangeEmail').hide();
        $('.progress-bg').hide();
        $('#newEmail').val('');
        document.body.removeEventListener('touchmove',bodyScroll,false);
        $("body").css({"position":"initial","height":"auto"});
    })




    //修改密码弹窗
    var res = '<div class="modal-dialog">\n' +
        '            <div class="modal-content">\n' +
        '                  <div class="modal-header">\n' +
        '                        <button type="button" id="closePassword" class="close" data-dismiss="modal" aria-label="Close">\n' +
        '                            <span aria-hidden="true">&times;</span></button>\n' +
        '                        <h4 class="modal-title">修改密码</h4>\n' +
        '                      </div>\n' +
        '                  <div class="modal-body">\n' +
        '                      <table class="change-password-table">\n' +
        '                          <tr>\n' +
        '                              <td>原密码：</td>\n' +
        '                              <td><input type="password" id="oldPassword" class="passwordNotEmpty" data="no"/></td>\n' +
        '                              <td><div class="error-msg1">原密码不能为空！</div></td>\n' +
        '                          </tr>\n' +
        '                          <tr>\n' +
        '                              <td>新密码：</td>\n' +
        '                              <td><input type="password" id="newPassword" class="passwordNotEmpty" data="no"/></td>\n' +
        '                              <td><div class="error-msg1">新密码不能为空！</div></td>\n' +
        '                          </tr>\n' +
        '                          <tr>\n' +
        '                              <td>确认新密码：</td>\n' +
        '                              <td><input type="password" id="newPasswordTrue" class="passwordNotEmpty" data="yes"/></td>\n' +
        '                              <td><div class="error-msg1" id="passwordErrorTrue">确认密码不能为空！</div></td>\n' +
        '                          </tr>\n' +
        '                      </table>\n' +
        '                      <div class="error-msg1" id="errorMsg">原密码不正确！</div>\n' +
        '                  </div>\n' +
        '                  <div class="modal-footer">\n' +
        '                        <button type="button" class="btn btn-default-style" id="btnChangeTrue">确定</button>\n' +
        '                      </div>\n' +
        '                </div>\n' +
        '          </div>';
    $('#modalChangePassword').html(res);

    //修改密码校验
    $('.passwordNotEmpty').blur(function () {
        if ($(this).attr('data') == 'yes') {
            if ($(this).val() == '' || $(this).val() != $('#newPassword').val()) {
                if ($(this).val() != $('#newPassword').val()) {
                    $('#passwordErrorTrue').html('两次密码输入不一致！');
                } else {
                    $('#passwordErrorTrue').html('确认密码不能为空！');
                }
                $(this).parent().next().find('div').show();
            } else {
                $(this).parent().next().find('div').hide();
            }
        } else {
            if ($(this).val() == '') {
                $(this).parent().next().find('div').show();
            } else {
                $(this).parent().next().find('div').hide();
            }
        }
        $('.passwordNotEmpty').each(function () {
            var this_ = $(this);
            if (this_.attr('data') == 'yes') {
                if (this_.val() == '' || $(this).val() != $('#newPassword').val()) {
                    $('#btnChangeTrue').attr('disabled', true);
                    return false;
                } else {
                    $('#btnChangeTrue').attr('disabled', false);
                }
            } else {
                if ($(this).val() == '') {
                    $('#btnChangeTrue').attr('disabled', true);
                    return false;
                } else {
                    $('#btnChangeTrue').attr('disabled', false);
                }
            }
        })
    });

    //修改密码确认
    $('#btnChangeTrue').click(function () {
        var newPassword = $('#newPassword').val();
        var oldPassword = $('#oldPassword').val();
        var newPasswordTrue = $('#newPasswordTrue').val();
        if (oldPassword != '' && newPassword != '' && newPasswordTrue != '' && newPassword == newPasswordTrue) {
            changePassword(oldPassword, newPassword,userId);
            document.body.removeEventListener('touchmove',bodyScroll,false);
            $("body").css({"position":"initial","height":"auto"});
        }
    })
//取消修改密码
    $('#closePassword').click(function () {
        $('#modalChangePassword').hide();
        $('.progress-bg').hide();
        $('#oldPassword').val('');
        $('#newPassword').val('');
        $('#newPasswordTrue').val('');
        document.body.removeEventListener('touchmove',bodyScroll,false);
        $("body").css({"position":"initial","height":"auto"});
    })
    //点击修改密码
    $('#changePassword').click(function () {
        $('#modalChangePassword').show();
        $('.progress-bg').show();
        document.body.addEventListener('touchmove',bodyScroll,false);
        $('body').css({'position':'fixed',"width":"100%"});
    })

})
//调用修改密码的接口
changePassword=function(oldPassword, newPassword,userId){
    $.ajax({
        type:"post",
        url:"login/revisePassword",
        data:{ "userId":userId,"password":oldPassword,"newPassword":newPassword,"email":''},
        xhrFields: {withCredentials: true},
        dataType: "json",
        async:true,
        success:function(data){
            if(data.code==0){
                alert('修改密码成功');
                $('#modalChangePassword').hide();
                $('.progress-bg').hide();
            }
        }
    });
}

//调用修改邮箱接口
changeEmail=function(newEmail,userId){
    $.ajax({
        type:"post",
        url:"login/reviseEmail",
        data:{ "userId":userId,"email":newEmail},
        xhrFields: {withCredentials: true},
        dataType: "json",
        async:true,
        success:function(data){
            if(data.code==0){
                alert('修改邮箱成功');
                $('#modalChangeEmail').hide();
                delCookie('email');
                setCookie('email',newEmail);
                $('.progress-bg').hide();
            }
        }
    });
}
/**禁止页面滚动或者滑动*/
function bodyScroll(event){
    event.preventDefault();
}
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