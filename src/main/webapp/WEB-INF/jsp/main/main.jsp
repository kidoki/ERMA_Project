<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	
	<script src="js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript">
	    $(document).ready(function(){
	      $("#button").click(function(){
	          callAjax();
	      });	      
	    });
	    
	    function callAjax(){
	    	
	        $.ajax({
		        type: "post",
		        url : "main/aptGet.do",
		        data: {
		        	regionCode : $('#regionCode').val(),
	       			month : $('#month').val(),
		        },
		        success: whenSuccess,
		        error: whenError
	     	});
	    }
	    
	    function whenSuccess(resdata){
	    	 $("#aptName").val(resdata.name);
	    	 $("#aptFloor").val(resdata.floor);
	    	 $("#aptAmount").val(resdata.amount);
	    }
	    
	    function whenError(){
	        alert("Error");
	    }
  </script>
</head>
<body>
	<form>
		지역코드 : <input type="text" id="regionCode" value=${map.LAWD_CD}>
		계 약 월  : <input type="text" id="month" value=${map.DEAL_YMD}>
	</form>
	<input id="button" type="button" value="조회">
	<form>
		아파트명 : <input type="text" id="aptName">
		층      수 : <input type="text" id="aptFloor">
		가      격 : <input type="text" id="aptAmount">
	</form>
</body>
</html>
