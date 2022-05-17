// 进行表单验证
//使用正则表达式进行表单验证, 匹配正确的字符串
function inputCheckBlur(obj, matchStr) {
    var value = obj.value;
    return matchStr.match(value);
}

//获取表单添加失去焦点事件, 给id 为jsPoint的div添加子元素, 然后进行浮动, 将字体放小, 给出提示信息;
//0: 账号 1.用户名 2.密码 3.性别 4.地址 5.电话 6.权限 7. 提交;
var inputtag = document.getElementsByTagName('input');

//用于查询数据库是否有同名用户名;
function AjexUserName(userName) {
    var xmlHttpReq = new XMLHttpRequest();
    var url = 'SameUserName?userName='+userName;
    xmlHttpReq.open('get', url , false);
    xmlHttpReq.send(null);
    if (xmlHttpReq.readyState == 4 && xmlHttpReq.status==200) {
        var reg = xmlHttpReq.responseText;

        return reg;
    }
}

//验证账号, 如果不正确, 返回创建的对象并给一个变量, 如果修改成功了, 那么将创建的display设置为none
var flag1 = false;//标记表单验证是否正确;
var userIdNode;
inputtag[0].onblur = function(){
    var userId = inputtag[0].value;
    var tegex = /^[a-zA-Z][a-zA-Z0-9]{5,11}$/;
    if (!tegex.test(userId) && userId != "") {
        if (userIdNode == undefined) {
            userIdNode = outputMsg('1-->账号错误!');
        }else{
            userIdNode.innerHTML = '1-->账号错误!';
        }
        flag1 = false;
    }else {
        if (AjexUserName(userId) == 'true') {
            if (userIdNode == undefined) {
                userIdNode = outputMsg('1-->账号同名!');
            } else {
                userIdNode.innerHTML = '1-->账号同名!';
            }
            flag1 = false;
        } else {
            if(userIdNode != undefined) {
                userIdNode.style.display = 'none';
            }
            flag1 = true;
        }
    }
}
//验证用户名
var flag2 = false;
var nikNameNode;
inputtag[1].onblur = function () {
    var nikName = inputtag[1].value;
    var tegex = /^[\u4e00-\u9fa5a-zA-Z0-9]{4,10}$/;
    if(!tegex.test(nikName) && nikName != "") {
        if (nikNameNode == undefined) {
            nikNameNode = outputMsg("2-->用户名格式错误!");
        } else {
            nikNameNode.innerHTML = '2-->用户名格式错误!';
        }
        flag2 = false;
    }else {
        if (nikNameNode != undefined) {
            nikNameNode.style.display = 'none';
        }
        flag2 = true;
    }
}

//验证密码
var flag3 = false;
var passWordNode;
inputtag[2].onblur = function () {
    var passWord = inputtag[2].value;
    var tegex = /^[a-zA-Z0-9]{6,20}$/;
    if(!tegex.test(passWord) && passWord != "") {
        if (passWordNode == undefined) {
            passWordNode = outputMsg("3-->密码格式错误!");
        } else {
            passWordNode.innerHTML = '3-->密码格式错误!';
        }
        flag3 = false;
    }else {
        if (passWordNode != undefined) {
            passWordNode.style.display = 'none';
        }
        flag3 = true;
    }
}

//验证密码
var flag4 = false;
var phoneNode;
inputtag[7].onblur = function () {
    var phone= inputtag[7].value;
    var tegex = /^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8]|8[0-9]|9[1,8,9])\d{8}$/;
    if(!tegex.test(phone) && phone != "") {
        if (phoneNode == undefined) {
            phoneNode = outputMsg("6-->电话格式错误!");
        } else {
            phoneNode.innerHTML = '6-->电话格式错误!';
        }
        flag4 = false;
    }else {
        if (phoneNode != undefined) {
            phoneNode.style.display = 'none';
        }
        flag4 = true;
    }
}
//验证地址 inputNode[6]
var flag5 = false;
inputtag[6].onblur = function () {
    var address = inputtag[6].value;
    if (address == undefined || address == "") {
        flag5 = false;
    }else {
        flag5 = true;
    }
}
//表单
function checkform() {
    if (flag1 && flag2 && flag3 && flag4 && flag5) {
        return true;
    }else {
        return false;
    }
}




