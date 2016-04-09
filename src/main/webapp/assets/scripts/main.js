function go_signin(){
	swal({
		title:"提示信息", 
		text:"您没有登录，3秒后跳转到登录页面！", 
		type:"warning",
		confirmButtonText:"点击跳转",
		timer: 3000
	},function(isConfirm){
		if (isConfirm) {
			setTimeout(function(){
				window.location.href= BASE + '/signin';
			}, 300);
		} else{
			window.location.href= BASE + '/signin';
		}
	});
}

/* 
弹出窗口居中 
*/  
function openWindow(url,name,iWidth,iHeight) {  
    var url;                             //转向网页的地址;  
    var name;                            //网页名称，可为空;  
    var iWidth;                          //弹出窗口的宽度;  
    var iHeight;                         //弹出窗口的高度;  
    //获得窗口的垂直位置  
    var iTop = (window.screen.availHeight-30-iHeight)/2;         
    //获得窗口的水平位置  
    var iLeft = (window.screen.availWidth-10-iWidth)/2;            
    window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');  
}  

function alertError(msg){
	swal({
		title:"提示信息", 
		text: msg, 
		type:"error",
		timer: 3000
	});
}

function alertOk(msg){
	swal({
		title:"提示信息", 
		text: msg, 
		type:"success",
		timer: 3000
	});
}

function dispatch() {
    var q = document.getElementById("q");
    if (q.value != "") {
        var url = 'https://www.google.com/search?q=site:java-china.org/topic%20' + q.value;
        if (navigator.userAgent.indexOf('iPad') > -1 || navigator.userAgent.indexOf('iPod') > -1 || navigator.userAgent.indexOf('iPhone') > -1) {
            location.href = url;
        } else {
            window.open(url, "_blank");
        }
        return false;
    } else {
        return false;
    }
}