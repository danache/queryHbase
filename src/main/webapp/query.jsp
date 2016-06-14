<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="danache.demo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询HBase数据库</title>
</head>
<%
String []prolist = dict.ProName;
//kAIoDvNSIzyZlBY8WjKgCbGFu7nkm5WY
%>
<body>

	<center>
		<form name = form0 mothod="post" action="showData.jsp">
		
					
			<div>
				<table>
					<tr>
						<h3>查询历史天气</h3>
					</tr>
					<form name=form1>
						<select name=province onchange="cityName(this.selectedIndex)">
							<option value="">请选择省名</option>
						</select> <select name=city>
							<option value="">请选择城名</option>
						</select>
					</form>
				</table>
			</div>
			<div>
				<table>
					<tr align="left">
						<td align="left">起始时间 <input name="beginDate" class="Wdate"
							id="d1"
							onfocus="WdatePicker({readOnly:true,minDate:'2016-5-10',maxDate:'%y-%M-#{%d}'})" />
						</td>
						<br>
					</tr>

					<td align="right">结束时间 <input name="endDate" class="Wdate"
						onfocus="WdatePicker({readOnly:true,minDate:'2016-5-10',maxDate:'%y-%M-#{%d}'})" />
					</td>

					<tr>
						<td><input type="submit" value="查询"></td>
					</tr>
				</table>
			</div>
		</form>
	</center>
</html>



<script language=javascript>  
 
var  city1  =  ["beijing"];  
var  city2  =  ["shanghai"];  
var  city3  =  ["tianjin"];  
var  city4  =  ["chongqing"];    
var  city5  =  ["maanshan","wuhu","bengbu","huainan","huaibei","tongling","anqing","huangshan" ,"chuzhou","suzhouah","liuan","fuyangah","xuancheng","chizhou","haozhou","hefei"];  
var  city6  =  [ "fuzhou" , "xiamen" , "quanzhou",  "zhangzhou" ,"putian" ,"ningde", "nanping" ,"sanming" ,"longyan"];  
var  city7  =  ["lanzhou" , "baiyin","zhangye","qingyang", "linxia","jiayuguan","tianshui","pingliang","dinxi","gannan","jinchang","wuwei","jiuquan","longnan"];  
var  city8  =  ["foshan","huizhou","shaoguan","qingyuan","jiangmen","dongguan","shantou","zhanjiang","shenzhen","zhuhai","zhaoqing","zhongshan","heyuan","maoming"
,"meizhou","shanwei","yangjiang","chaozhou","jieyang","yunfu"];
var  city9  =  ["nanning","guilin","hezhou","chongzuo","qinzhou","liuzhou","yulingx","hechi","wuzhou","guigang","beihai","baise","laibin","fangchenggang"];
var  city10  =  ["guiyang","bijie","zunyi","anzhun","liupanshui","qianxinan","qiandongnan","qiannan","tongren"];
var  city11  =  ["haikou","sanya"];
var  city12  =  ["shijiazhuang","xingtai","hengshui","tangshan","qinhuangdao","baoding","zhangjiakou","cangzhou","handan","chengde","langfang"];
var  city13  =  ["haerbin","mudanjiang","shuangyashan","qitaihe","daxinganling","qiqihaer","jixi","yichun","heihe","daqing","hegang","jiamusi","suihua"];
var  city14  =  ["zhengzhou","pingdingshan","sanmenxia","xinyang","xinxiang","kaifeng","jiaozuo","xuchang","zhoukou","puyang","luoyang","anyang","nanyang","hebi","luohe","shangqiu","zhumadian"];
var  city15  =  ["wuhan","huangshi","huanggang","enshi","xiangyang","jingzhou","ezhou","xianning","jingmen"
         		,"yichang","xiaogan","shiyan","suizhou"];
var  city16  =  ["changsha","yueyang","hengyang","loudi","chenzhou","zhuzhou","zhangjiajie","shaoyang","yongzhou","xiangxi","xiangtan","changde","yiyang","huaihua"];
var  city17  =  ["nanjing","changzhou","lianyungang","yangzhou","suqian","wuxi","suzhou","huaian","zhenjiang","kunshan","xuzhou","nantong","yancheng","taizhou","wujiang","changshu","jiangyin","liyang","zhangjiagang","yixing","haimen","jurong","jintan","taicang"];
var  city18  =  ["yichunjx","fuzhoujx","nanchang","yingtan","jingdezhen","jiujiang","ganzhou","pingxiang","xinyu","jian","shangrao"];
var  city19  =  ["changchun","liaoyuan","songyuan",,"tonghua","baicheng","siping","baishan","yanbian"];
var  city20  =  ["shenyang","yingkou","anshan","jinzhou","liaoyang","dalian","panjin","fushun","wafangdian","tieling","dandong","huludao","benxi","fuxin","chaoyang"];
var  city21  =  ["huhehaote","chifeng","wuhai","bayannaoer","baotou","wulanchabu","tongliao","xingan","eeds","xilingele","hulunbeier","alashan"];
var  city22  =  ["yinchuan","guyuan","shizuishan","zhongwei","wuzhong"];
var  city23  =  ["xining"];
var  city24  =  ["xian","tongchuan","baoji","xianyang","weinan","yanan","hanzhong","shangluo","yulinsx","ankang"];
var  city25  =  ["taiyuan","changzhi","shuozhou","xinzhou","datong","linfen","jinzhong","lvliang","yangquan","jincheng","yuncheng"];
var  city26  =  ["jinan","binzhou","heze","dongying","jining","qingdao"
,"liaocheng","zibo","yantai","taian","rizhao","dezhou"
,"zaozhuang","weifang","weihai","laiwu","wendeng","laixi","jiaozhou","zhaoyuan","linyi","rushan","pingdu","shouguang","penglai","rongcheng","jimo","jiaonan","laizhou","zhangqiu"];
var  city27  =  ["chengdu","luzhou","nanchong","neijiang","guangan","zigong","deyang","yibin","meishan","dazhou","panzhihua","mianyang","suining","leshan","ziyang","guangyaun","aba","yaan","ganmu","bazhong","liangshan"];
var  city28  =  ["lasa"];
var  city29  =  ["wulumuqi","tulufan","hami","kezhou","tacheng","shihezi"
,"kelamayi","changji","boertala","kashi","aletai","kuerle"
,"yili","akesu","hetian","wujiaqu"];
var  city30  =  ["kunming","shaotong","honghe","puer","xishuangbanna","nujiang"
,"yuxi","lijiang","diqing","lincang","dali","qujing","chuxiong","baoshan","wenshan","dehong"];
var city31 = ["hangzhou","jiaxing","zhoushan","shaoxing","ningbo","huzhou"
,"taizhou2","jinhua","wenzhou","quzhou","lishui","linan","fuyang","zhuji","yiwu" ]
var  provinceName  =  ["北京", "上海", "天津", "重庆", "安徽", "福建", "甘肃", "广州", "广西", "贵州", "海南", "河北", "黑龙江", "河南", "湖北", "湖南", "江苏", "江西", "吉林", "辽宁", "内蒙古", "宁夏", "青海", "陕西", "山西", "山东", "四川", "西藏", "新疆", "云南", "浙江"];  
 

function  province()  
{  
 
       var  e  =  document.form0.province;  
       for  (var  i=0;  i<provinceName.length;  i++)  
             e.options.add(new  Option(provinceName[i],  provinceName[i]));  
}  
function  cityName(n)  
{  
       var  e  =  document.form0.city;
       
       for  (var  i=e.options.length;  i>0;  i--)    e.remove(i);  
       if  (n  ==  0)  return;  
       var  a  =  eval("city"+  n);  //得到城市的数组名  
       for  (var  i=0;  i<a.length;  i++)  e.options.add(new  Option(a[i],  a[i]));  
}  

window.onload=province;  //初始时给省名下拉菜单赋内容  

</script>
