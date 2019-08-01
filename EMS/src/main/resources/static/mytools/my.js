var tpos1,lpos1;
var tpos2,lpos2;
var tpos3,lpos3;
var heightflag1=0;
var widthflag1=0;
var heightflag2=1;
var widthflag2=0;
var heightflag3=0;
var widthflag3=1;
var bodyWidth,bodyHeight;
$(function() {
	// 让登录框在2000毫秒以内淡入
	$("#container").fadeIn(2500);
	
	//获取浏览器可视区域的宽度和高度
	bodyWidth= document.documentElement.clientWidth; 
	bodyHeight= document.documentElement.clientHeight; 
});
//让一个函数(changePos函数)间隔500毫秒不停的执行
self.setInterval("changePos1(img1)", 20)
self.setInterval("changePos2(img2)", 20)
self.setInterval("changePos3(img3)", 20)
function changePos1(id) {
	//获取图片当前离上边的距离
	tpos1 = $(id).offset().top;
	//获取图片当前离左边的距离
	lpos1 = $(id).offset().left;
	//当碰了右边，宽度就要开始减了
	if(tpos1+227==bodyHeight){
		heightflag1=1;
	}
	//当碰了左边，宽度就要开始加了
	if(tpos1==0){
		heightflag1=0;
	}
	//当碰了下边，高度就要开始减了
	if(lpos1+113==bodyWidth){
		widthflag1=1;
	}
	//当碰了上边，高度就要开始加了
	if(lpos1==0){
		widthflag1=0;
	}
	if(heightflag1==1&&widthflag1==1){
		//改变气球的位置
		$(id).offset({
			top : tpos1-1,
			left : lpos1-1
		});
	}
	if(heightflag1==0&&widthflag1==1){
		//改变气球的位置
		$(id).offset({
			top : tpos1+1,
			left : lpos1-1
		});
	}
	if(heightflag1==1&&widthflag1==0){
		//改变气球的位置
		$(id).offset({
			top : tpos1-1,
			left : lpos1+1
		});
	}
	if(heightflag1==0&&widthflag1==0){
		//改变气球的位置
		$(id).offset({
			top : tpos1+1,
			left : lpos1+1
		});
	}
}
function changePos2(id) {
	//获取图片当前离上边的距离
	tpos2 = $(id).offset().top;
	//获取图片当前离左边的距离
	lpos2 = $(id).offset().left;
	//当碰了右边，宽度就要开始减了
	if(tpos2+227==bodyHeight){
		heightflag2=1;
	}
	//当碰了左边，宽度就要开始加了
	if(tpos2==0){
		heightflag2=0;
	}
	//当碰了下边，高度就要开始减了
	if(lpos2+113==bodyWidth){
		widthflag2=1;
	}
	//当碰了上边，高度就要开始加了
	if(lpos2==0){
		widthflag2=0;
	}
	if(heightflag2==1&&widthflag2==1){
		//改变气球的位置
		$(id).offset({
			top : tpos2-1,
			left : lpos2-1
		});
	}
	if(heightflag2==0&&widthflag2==1){
		//改变气球的位置
		$(id).offset({
			top : tpos2+1,
			left : lpos2-1
		});
	}
	if(heightflag2==1&&widthflag2==0){
		//改变气球的位置
		$(id).offset({
			top : tpos2-1,
			left : lpos2+1
		});
	}
	if(heightflag2==0&&widthflag2==0){
		//改变气球的位置
		$(id).offset({
			top : tpos2+1,
			left : lpos2+1
		});
	}
}
function changePos3(id) {
	//获取图片当前离上边的距离
	tpos3 = $(id).offset().top;
	//获取图片当前离左边的距离
	lpos3 = $(id).offset().left;
	//当碰了右边，宽度就要开始减了
	if(tpos3+227==bodyHeight){
		heightflag3=1;
	}
	//当碰了左边，宽度就要开始加了
	if(tpos3==0){
		heightflag3=0;
	}
	//当碰了下边，高度就要开始减了
	if(lpos3+113==bodyWidth){
		widthflag3=1;
	}
	//当碰了上边，高度就要开始加了
	if(lpos3==0){
		widthflag3=0;
	}
	if(heightflag3==1&&widthflag3==1){
		//改变气球的位置
		$(id).offset({
			top : tpos3-1,
			left : lpos3-1
		});
	}
	if(heightflag3==0&&widthflag3==1){
		//改变气球的位置
		$(id).offset({
			top : tpos3+1,
			left : lpos3-1
		});
	}
	if(heightflag3==1&&widthflag3==0){
		//改变气球的位置
		$(id).offset({
			top : tpos3-1,
			left : lpos3+1
		});
	}
	if(heightflag3==0&&widthflag3==0){
		//改变气球的位置
		$(id).offset({
			top : tpos3+1,
			left : lpos3+1
		});
	}
}

