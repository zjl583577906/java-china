Object.prototype.count = (
    Object.prototype.hasOwnProperty('__count__')
) ? function () {
    return this.__count__;
} : function () {
    var count = 0;
    for (var i in this) if (this.hasOwnProperty(i)) {
      count ++;
    }
    return count;
};

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

function emoji(content){
	if(content){
		if(content.indexOf(":") == -1){
			return content;
		}
		return content.replace(/:([a-z\\-]{2,30}):/g, "<i class=\"twa twa-lg twa-$1\"></i>");
	}
	return "";
}

////////////////////帖子操作:START//////////////////////
var topic = {};
// 发布帖子
topic.add = function(){
	var formData = $('#topic-add').serialize();
	$.post(BASE + '/topic/add', formData, function(response){
		if(response){
			 if(response.status == 200){
				 window.location.href = BASE + '/topic/' + response.data;
			 } else if(response.status == 401){
				 go_signin();	
			 } else {
				 alertError(response.msg);
			 }
		}
	});
};

//编辑帖子
topic.edit = function(){
	var formData = $('#topic-edit').serialize();
	$.post(BASE + '/topic/edit', formData, function(response){
		if(response){
			 if(response.status == 200){
				 window.location.href = BASE + '/topic/' + response.data;
			 } else if(response.status == 401){
				 go_signin();	
			 } else {
				 alertError(response.msg);
			 }
		}
	});
};

//帖子点赞
$('.topic-footer .heart').on('click', function(){
	var A = $(this).attr("id");
	var B=A.split("like");
	var messageID=B[1];
	var C=parseInt($("#likeCount").text());
	$(this).css("background-position","")
	var D = $("#likeCount");
    
	var _this = $(this);
	var tid = $(this).attr('tid');
	$.post(BASE + '/favorite', {type:'love',event_id : tid}, function(response){
		if(response){
			if(response.status == 200){
				if(response.data == 1){
					D.text(C+1);
					_this.addClass("heartAnimation").attr("rel","unlike");
				}
				if(response.data == -1){
					D.text(C-1);
					_this.removeClass("heartAnimation").attr("rel","like");
					_this.css("background-position","left");
				}
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

//帖子收藏
$('.topic-footer .follow').on('click', function(){
	var tid = $(this).attr("tid");
	var _this = $(this);
	$.post(BASE + '/favorite', {type:'topic', event_id : tid}, function(response){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

//设置精华帖
$('.topic-footer .essence').on('click', function(){
	var tid = $(this).attr("tid");
	var _this = $(this);
	$.post(BASE + '/essence', {tid : tid}, function(data){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

//分享到微博
$('.topic-footer .share-weibo').on('click', function(){
	var share_url = 'http://service.weibo.com/share/share.php?url=' + window.location.href + '&title=' + $('.topic-detail-heading .panel-title').text();
	openWindow(share_url, '', 550, 422);
});

//at用户
$('.comment-list .at-user').on('click', function(){
	var user_name = $(this).attr('alt');
	var ctn = $('#comment-form #content');
	var text = ctn.val();
	var at = '@' + user_name + ' ';
	if(text == at){
		ctn.val('');		
	} else{
		var br = text ? '\r\n' : '';
		ctn.val(text + br + at);
	}
	ctn.focus();
});
////////////////////帖子操作:END//////////////////////

////////////////////个人设置:START//////////////////////
var user = {};
$('.profile .following').on('click', function(){
	var uid = $(this).attr("uid");
	var _this = $(this);
	$.post(BASE+'/favorite', {type:'following', event_id : uid}, function(response){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

var info_data = {};
$("#info_form :input").change(function (){
	var key = $(this).attr('name');
	info_data[key] = $(this).val();
});

// 修改个人基本信息
$("#info_form .submit").on('click', function(){
	if(info_data.count() > 0){
		$.post(BASE+'/settings?type=info', info_data, function(response){
			info_data = {};
			if(response){
				if(response.status == 200){
					window.location.reload();	
				} else if(response.status == 401){
					go_signin();
				} else{
					alertError(response.msg);
				}
			}
		});
	}
});

// 修改头像
user.update_avatar = function(){
	var avatar = $("#avatar_form #user_avatar").val();
	if(avatar && avatar != ''){
		$.post(BASE+'/settings', {type:'avatar', avatar : avatar}, function(response){
			if(response){
				if(response.status == 200){
					alertOk("头像更换成功!");	
				} else if(response.status == 401){
					go_signin();
				} else{
					alertError(response.msg);
				}
			}
		});
	}
};

//修改密码
user.update_pwd = function(){
	var formData = $('#pwd_form').serialize();
	$.post(BASE + '/settings?type=pwd', formData, function(response){
		if(response){
			if(response.status == 200){
				alertOk("密码修改成功!");	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
}

////////////////////个人设置:END//////////////////////
