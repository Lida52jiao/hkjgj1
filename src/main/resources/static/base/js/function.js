window.onload = function() { //自适应
	document.getElementsByTagName("html")[0].style.fontSize = document.body.clientWidth / 750 * 62.5 + "%";
}

function GetQueryString(name) { //截取url参数
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}

var merMp = GetQueryString("merMp");
var appId = GetQueryString("appId");
var institutionId = GetQueryString("institutionId");

if(appId == '0000' && institutionId == 'T00000000') {
	appName = '还款啦';
	applogosrc = '0000';
	andow = 'http://fir.im/tgby';
	iosdow = 'https://www.pgyer.com/4eV1';
	host = 'http://user.1818pay.cn/yj-mer/';
} else if(appId == '0001' && institutionId == 'T00000000') {
	appName = '快资佳佳';
	applogosrc = '0001';
	andow = 'http://fir.im/u12h';
	iosdow = 'https://www.pgyer.com/4DAV';
	host = 'http://user.1818pay.cn/yj-mer/';
} else if(appId == '0000' && institutionId == 'T00000001') {
	appName = '西呗';
	applogosrc = '0100';
	andow = 'http://fir.im/brh3';
	iosdow = 'https://www.pgyer.com/i3ly';
	host = 'http://xibei.yskjpay.com/xb-mer/';
} else if(appId == '0001' && institutionId == 'T00000001') {
	appName = '金诺卡卡管家';
	applogosrc = '0101';
	andow = 'https://www.pgyer.com/BvH5';
	iosdow = 'https://www.pgyer.com/0bj0';
	host = 'http://xibei.yskjpay.com/xb-mer/';
} else if(appId == '0000' && institutionId == 'T00000002') {
	appName = '易还款';
	applogosrc = '0200';
	andow = 'http://fir.im/gbn9';
	iosdow = 'https://www.pgyer.com/K8sd';
	host = 'http://m.1818pay.cn/yj-mer/';
} else if(appId == '0001' && institutionId == 'T00000002') {
	appName = '宜富2.0';
	applogosrc = '0201';
	andow = 'http://fir.im/b8g3';
	iosdow = 'https://www.pgyer.com/ZIio';
	host = 'http://m.1818pay.cn/yj-mer/';
} else if(appId == '0002' && institutionId == 'T00000002') {
	appName = '融易用';
	applogosrc = '0202';
	andow = 'http://fir.im/n89j';
	iosdow = 'https://www.pgyer.com/kHSO';
	host = 'http://m.1818pay.cn/yj-mer/';
} else if(appId == '0003' && institutionId == 'T00000002') {
	appName = '喔卡付';
	applogosrc = '0203';
	andow = 'http://fir.im/ct7m';
	iosdow = 'https://www.pgyer.com/DH3r';
	host = 'http://m.1818pay.cn/yj-mer/';
} else if(appId == '0000' && institutionId == 'T00000003') {
	appName = '应客智能管家';
	applogosrc = '0300';
	andow = 'https://www.pgyer.com/Qjei';
	iosdow = 'https://www.pgyer.com/kq9p';
	host = 'http://ykzn.huianzhifu.com/yj-mer/';
} else if(appId == '0001' && institutionId == 'T00000003') {
	appName = '惠泰智能管家';
	applogosrc = '0301';
	andow = 'https://www.pgyer.com/BBur';
	iosdow = 'https://www.pgyer.com/00kF';
	host = 'http://ykzn.huianzhifu.com/yj-mer/';
} else if(appId == '0000' && institutionId == 'T00000004') {
	appName = '卡云宝';
	applogosrc = '0400';
	andow = 'https://www.pgyer.com/xM9K';
	iosdow = 'https://www.pgyer.com/cGA7';
	host = 'http://kybs.1818pay.cn/yj-mer/';
} else if(appId == '0000' && institutionId == 'T00000005') {
	appName = '99智能管家';
	applogosrc = '0500';
	andow = 'https://www.pgyer.com/PMBg';
	iosdow = 'https://www.pgyer.com/u3Si';
	host = 'http://99.1818pay.cn/yj-mer/';
} else if(appId == '0000' && institutionId == 'T00000006') {
	appName = '米点智能管家';
	applogosrc = '0600';
	andow = '';
	iosdow = '';
	host = '';
}