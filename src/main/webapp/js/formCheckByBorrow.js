//这里使用Ajax验证是否由用户名与书号;
//显示用户的详细信息与书本详情;

//获取表单0: 用户名 1: 书号;
var inputTag = document.getElementsByTagName('input');

//获取用户详情的Ajax;
function AjaxByUser(userName) {
    var xmlHttpReq = new XMLHttpRequest();
    var url = 'UserAjax?userName='+userName;
    xmlHttpReq.open('get', url , false);
    xmlHttpReq.send(null);
    if (xmlHttpReq.readyState == 4 && xmlHttpReq.status==200) {
        var reg = xmlHttpReq.responseText;

        return reg;
    }
}
//获取书本详情的Ajax;
function AjaxByBookId(bookId) {
    var xmlHttpReq = new XMLHttpRequest();
    var url = 'BookAjax?bookId='+bookId;
    xmlHttpReq.open('get', url , false);
    xmlHttpReq.send(null);
    if (xmlHttpReq.readyState == 4 && xmlHttpReq.status==200) {
        var reg = xmlHttpReq.responseText;

        return reg;
    }
}

//获取表单
var inputTag = document.getElementsByTagName('input');

var flag1 = false;
var userNode;
inputTag[0].onblur = function() {
    var userName = inputTag[0].value;
    var tegex = /^[a-zA-Z][a-zA-Z0-9]{5,11}$/;
    if (tegex.test(userName)) {
        var ajaxUser = AjaxByUser(userName);
        if (userNode == undefined) {
            userNode = outputMsg('1->'+ ajaxUser);
        }else{
            userNode.innerText = '1->'+ ajaxUser;
        }
        flag1 = false;
        if(!/不存在此用户/.exec(userName)) flag1 = true;
    } else {
        if(userNode == undefined) {
            userNode = outputMsg('1->'+'账户格式不正确')
        }else {
            userNode.innerText = '1->'+'账户格式不正确';
        }
        flag1 = false;
    }
}

//验证书号
var flag2 = false;
var bookNode;
inputTag[1].onblur = function() {
    var bookId = inputTag[1].value;
    var tegex = /^[A-Z][0-9]{7}$/;
    if (tegex.test(bookId)) {
        var ajaxBook = AjaxByBookId(bookId);
        if (bookNode == undefined) {
            bookNode = outputMsg('2->'+ ajaxBook);
        }else{
            bookNode.innerText = '2->'+ ajaxBook;
        }
        flag2 = false;
        if(!/不存在此书/.exec(bookId)) flag2 = true;
    } else {
        if(bookNode == undefined) {
            bookNode = outputMsg('1->'+'书号格式不正确')
        }else {
            bookNode.innerText = '1->'+'书号格式不正确';
        }
        flag2 = false;
    }
}

function checkform()
{
    if (flag1 && flag2) {
        return true;
    }else {
        return false;
    }
}
