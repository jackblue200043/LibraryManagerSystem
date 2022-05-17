//函数输出错误信息
function outputMsg(msg) {
    var board = document.getElementById('jsPoint');
    //创建元素节点, 文本节点
    var newDiv = document.createElement('p');
    var text = document.createTextNode(msg);
    newDiv.setAttribute('id', 'text');
    newDiv.appendChild(text);
    board.appendChild(newDiv);
    return newDiv;
}