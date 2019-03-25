<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    <meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery-1.10.min.js"></script>
  </head>  
  <body>
  <form name="form1" method="post" action="test.jsp" >
    <table width="1531" height="280" border="1" style="height: 263px; width: 1389px; ">
      <tr>
        <td width="147"><div align="right">URL:</div></td>
        <td width="1368">
        <input name="url" id='url' type="text" size="100" value="api/v1/org"></td>
      </tr>
      <tr>
        <td width="147"><div align="right">提交方式：</div></td>
        <td width="1368"><select id='method'>
        	<option value='POST'>POST</option>
        	<option value='GET'>GET</option>
        </select></td>
      </tr>
      <tr>
        <td><div align="right">token：</div></td>
        <td><input name="token" id='token' type="text" size="100" value="33dc3eb18c3c314eed7b395d6e36d07673a489df0e4c7b62138bf29cb636c0c2289a891fd8922b9850873fceb7b3985568a91c7ce7125f7d8a326f80ec0c8b54a3b59b59900e05c8"/></td>
      </tr>
      <tr>
        <td><div align="right">json参数：</div></td>
        <td><textarea name="para" id='para' cols="150" rows="10">{"name":"13012345678","password":"31461AB060AFBB91E561047381356F5B"}</textarea></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><label>
          <input type="button" name="Submit" value="提交" onclick="doPostRequest()">
        </label></td>
      </tr>
 
      <tr>
        <td>&nbsp;</td>
        <td><label>
          <input type="file" id='file' name='file'/>          
          <input type="button" name="Submit" value="上传" onclick="doUpload()">
        </label></td>
      </tr>
      
      <tr>
        <td><div align="right">返回值：</div></td>
        <td><textarea name="output" id='output' cols="150" rows="10">

        </textarea></td>
      </tr>
    </table>
  </form>
  <script>
   var baseUrl = '<%=request.getContextPath()%>/';
  	function doPostRequest(){
  		var url = $('#url').val();
  		var method = $('#method').val();
  		var jsonstr = $('#para').val();
  		var jsonData = $.parseJSON( jsonstr );
  		sendAjaxRequest(method,url,jsonData);
  	}
  	
  	function sendAjaxRequest(method,url,jsonData){
	  	$.ajax({
		  url:url,
		  type:method,
		  data:jsonData,
		  dataType:"json",
		  success: function(data){
			$('#output').text(JSON.stringify(data));
		  }
		})
	}
  	
  </script>
  </body>
</html>
