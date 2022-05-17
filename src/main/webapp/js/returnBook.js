//根据账户名搜索未还的结束记录, 并使用js添加到下拉框表单中;

//获取书本详情的Ajax;
function AjaxByBorrow(userName) {
    var xmlHttpReq = new XMLHttpRequest();
    var url = 'ReturnBorrowByUser?userName='+userName;
    xmlHttpReq.open('get', url , false);
    xmlHttpReq.send(null);
    if (xmlHttpReq.readyState == 4 && xmlHttpReq.status==200) {
        var reg = xmlHttpReq.responseText;
        return reg;
    }
}
//将格式化字符串写入下拉框表单
function appendSelect(str) {
    let selectTag = document.getElementById('borrowNum');
    let strs = str.substring(0, str.length-1).split(',');
    //清空一下对象;
    selectTag.innerHTML = "";
    for (let i = 0; i < strs.length; i++) {
        //新建立option节点
        let optionTag = document.createElement('option');
        optionTag.innerHTML = strs[i];
        //添加节点
        selectTag.appendChild(optionTag);
    }
}
//获取input对象
var inputTag = document.getElementsByTagName('input');
var userIdNode;
var flag1 = false;
//当输入userName后进行触发
inputTag[0].onblur = function () {
    //根据内容获取Ajax
    var userId = inputTag[0].value;
    var tegex = /^[a-zA-Z][a-zA-Z0-9]{5,11}$/;
    if (!tegex.test(userId) && userId != "") {
        if (userIdNode == undefined) {
            userIdNode = outputMsg('1-->账号格式错误!');
        }else{
            userIdNode.style.display = 'block';
            userIdNode.innerHTML = '1-->账号格式错误!';
        }
        flag1 = false;
    }else {
        if (userIdNode != undefined) {
            userIdNode.style.display = 'none';
        }
        let str = AjaxByBorrow(userId);
        //下面使用方法将此写入下拉框表单中;
        appendSelect(str);
        if (!/无记录/.exec(str)) {
            flag1 = true;
        }
    }
}

function checkform() {
    if(flag1) {
        return true;
    }
    return false;
}
