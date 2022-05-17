//查找数据库书号书否重复的Ajax
function AjexBookId(bookId) {
    var xmlHttpReq = new XMLHttpRequest();
    var url = 'SameBookId?bookId='+bookId;
    xmlHttpReq.open('get', url , false);
    xmlHttpReq.send(null);
    if (xmlHttpReq.readyState == 4 && xmlHttpReq.status==200) {
        var reg = xmlHttpReq.responseText;

        return reg;
    }
}

//获取表单中的所有input节点对象, 其中
//bookId = 0; bookName = 1; type = 2; publisher = 3; author = 4; place = 5; stock = 6; time = 7;
var inputtag = document.getElementsByTagName('input');

//验证账号, 如果不正确, 返回创建的对象并给一个变量, 如果修改成功了, 那么将创建的display设置为none
var flag1 = false;//标记表单验证是否正确;
var bookIdNode;
inputtag[0].onblur = function(){
    var bookId = inputtag[0].value;
    var tegex = /^[A-Z][0-9]{7}$/;
    if (!tegex.test(bookId) && bookId != "") {
        if (bookIdNode == undefined) {
            bookIdNode = outputMsg('1-->书号错误!');
        }else{
            bookIdNode.style.display = 'block';
            bookIdNode.innerHTML = '1-->书号错误!';
        }
        flag1 = false;
    }else {
        if (AjexBookId(bookId) == 'true') {
            if (bookIdNode == undefined) {
                bookIdNode = outputMsg('1-->书号同名!');
            } else {
                bookIdNode.style.display = 'block';
                bookIdNode.innerHTML = '1-->书号同名!';
            }
            flag1 = false;
        } else {
            if(bookIdNode != undefined) {
                bookIdNode.style.display = 'none';
            }
            flag1 = true;
        }
    }
}

//匹配位置
var flag2 = false;
var placeStrNode;
inputtag[5].onblur = function () {
    var placeStr = inputtag[5].value;
    var tegex = /^[A-D][0-9]{1,2}[D-G][0-9]{1,3}$/;
    if(!tegex.test(placeStr) && placeStr != "") {
        if (placeStrNode == undefined) {
            placeStrNode = outputMsg("5-->位置格式错误!");
        } else {
            placeStrNode.style.display = 'block';
            placeStrNode.innerHTML = '5-->位置格式错误!';
        }
        flag2 = false;
    }else {
        if (placeStrNode != undefined) {
            placeStrNode.style.display = 'none';
        }
        flag2 = true;
    }
}
//判断1-4不为空
//1
var flag3 = false;
var num1 = 1;
var input1;
inputtag[num1].onblur = function () {
    if (inputtag[num1].value == '') {
        if (input1 == undefined) {
            input1 = outputMsg(num1+1+"-->不能为空!");
        } else {
            input1.style.display = 'block';
            input1.innerHTML = num1+1+"-->不能为空!";
        }
        flag3 = false;
    }else {
        if (input1 != undefined) {
            input1.style.display = 'none';
        }
        flag3 = true;
    }
}
//2
var flag4 = false;
var num2 = 2;
var input2;
inputtag[num2].onblur = function () {
    if (inputtag[num2].value == '') {
        if (input2 == undefined) {
            input2 = outputMsg(num2+1+"-->不能为空!");
        } else {
            input2.style.display = 'block';
            input2.innerHTML = num2+1+"-->不能为空!";
        }
        flag4 = false;
    }else {
        if (input2 != undefined) {
            input2.style.display = 'none';
        }
        flag4 = true;
    }
}
//3
var flag5 = false;
var num3 = 3;
var input3;
inputtag[num3].onblur = function () {
    if (inputtag[num3].value == '') {
        if (input3 == undefined) {
            input3 = outputMsg(num3+1+"-->不能为空!");
        } else {
            input3.style.display = 'block';
            input3.innerHTML = num3+1+"-->不能为空!";
        }
        flag5 = false;
    }else {
        if (input3 != undefined) {
            input3.style.display = 'none';
        }
        flag5 = true;
    }
}
//4
var flag6 = false;
var num4 = 4;
var input4;
inputtag[num4].onblur = function () {
    if (inputtag[num4].value == '') {
        if (input4 == undefined) {
            input4 = outputMsg(num4+1+"-->不能为空!");
        } else {
            input4.style.display = 'block';
            input4.innerHTML = num4+1+"-->不能为空!";
        }
        flag6 = false;
    }else {
        if (input4 != undefined) {
            input4.style.display = 'none';
        }
        flag6 = true;
    }
}
//判断条件提交内容分
function checkform() {
    if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6) {
        return true;
    }else {
        return false;
    }
}