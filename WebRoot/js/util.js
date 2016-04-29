//################for the jsp in tester/admin/examinee/addExaminee.jsp################
//function checkOverlap()
var theURL = "checkoverlap?examinee_id=";
var http = getHTTPObject();

function handleHttpResponse() {
	if (http.readyState == 4) {
   		checkoverlap.innerHTML = http.responseText;
		//alert(http.responseText);
	}
}

function checkOverlap() {
	var examinee_id = document.forms[0].elements[0].value;
	if ((null == examinee_id)||("" == examinee_id)) {
		checkoverlap.innerHTML = "请输入准考证号!";
		return;
	}
	http.open("POST", theURL + examinee_id, false);
	http.setRequestHeader("Pragma", "no-cache");
	http.setRequestHeader("Cache-Control", "no-cache");
	http.onreadystatechange = handleHttpResponse;
	http.send(null);
}

function getHTTPObject() {
	var xmlhttp;
	try {
		xmlhttp = new ActiveXObject("MSXML2.XMLHTTP");
	} catch (e) {
		try {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
			xmlhttp = false;
		}
	}

	if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
		try {
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
			xmlhttp = false;
		}
	}

	return xmlhttp;
}


//######for the jsp tester/exam/showTest.jsp#####
//function onTimer()

var normalelapse = timer - 120000;
var nextelapse = normalelapse;
var counter;
var startTime;
var start = "00:01:00:00";
var finish = "00:00:00:00";
var timer = null;

function run() {
  counter = 0;
  // init begin time
  startTime = new Date().valueOf();

  timer = window.setInterval("onTimer()", nextelapse);
}

function onTimer()
{

if (start == finish)
{
  window.clearInterval(timer);
  alert("离考试结束还有一分种!");
  return;
}

var hms = new String(start).split(":");
var ms = new Number(hms[3]);
var s = new Number(hms[2]);
var m = new Number(hms[1]);
var h = new Number(hms[0]);

ms -= 10;
if (ms < 0)
{
  ms = 90;
  s -= 1;
  if (s < 0)
  {
    s = 59;
    m -= 1;
  }

  if (m < 0)
  {
    m = 59;
    h -= 1;
  }
}

var ms = ms < 10 ? ("0" + ms) : ms;
var ss = s < 10 ? ("0" + s) : s;
var sm = m < 10 ? ("0" + m) : m;
var sh = h < 10 ? ("0" + h) : h;

start = sh + ":" + sm + ":" + ss + ":" + ms;


window.clearInterval(timer);


counter++;
var counterSecs = counter * 100;
var elapseSecs = new Date().valueOf() - startTime;
var diffSecs = counterSecs - elapseSecs;
nextelapse = normalelapse + diffSecs;

if (nextelapse < 0) nextelapse = 0;
timer = window.setInterval("onTimer()", nextelapse);
}

//######for the jsp tester/exam/showTest.jsp#####
//function sub()
function sub(){

  window.setTimeout("document.frm.submit()", timer);
  alert("考试时间为" + timer/60000 +"分钟" + "考试时间结束，考卷将会自动提交!");
}

//###############for tester/admin/baseinfo/baseinfo.jsp###########
//edit_function()  function add_function()  del_function()
