<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style>
	html, body { 
		margin: 0; 
		padding: 0 
	}
	body { 
		background-color: #f9f9f9; 
	}
	section.content { 
		max-width: 1024px; 
		padding: 1rem;
		margin: 0 auto; 
		margin-top: 4rem; 
		background-color: #fff; 
		border: 1px solid #eee;
	}
	textarea.inputString, input.combindedUnit {
		width: 100%;
	}
	abbr {
		float: right;
	}
	button.printBtn {
		text-align: right;
	}
	.result {
		word-break: break-all
	}
</style>
<title>WMP assignment</title>
</head>
<body>
	<section class="content">
		<div>
			<p>
				<label for="inputString">
					<span>입력</span>
					<textarea id="inputString" name="inputString" class="inputString" rows="10"></textarea>
					<strong><abbr title="required, any string">*</abbr></strong>
				</label>
			</p>
			<p>
				<label for="combindedUnit">
					<span>출력묶음단위</span>
					<input id="combindedUnit" name="combindedUnit" type="text" value="1" class="combindedUnit" />
					<strong><abbr title="required, natural number">*</abbr></strong>
				</label>
			</p>
			<button id="printBtn" class="printBtn" type="button" onClick="printBtnClick()">출력</button>
		</div>
		
		<div>
			<p>
				<div id="error" style="color: red"></div>
				
				<h4>몫</h4>
				<div id="quotient" class="result"></div>
				
				<h4>나머지</h4>
				<div id="remainder" class="result"></div>
			<p>
		</div>
	</section>
	<script>
		function printBtnClick() {
			var inputString = document.getElementById('inputString').value;
			var combindedUnit = document.getElementById('combindedUnit').value;
			
			const params = new URLSearchParams()
			params.append('inputString', inputString);
			params.append('combindedUnit', combindedUnit);
			
			axios({
				method: 'post',
				url: '/task',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data: params
			})
			.then(function (response) {
				console.log(response.data);
				var { quotient, remainder } = response.data;

				document.getElementById('quotient').innerHTML = typeof quotient !== 'undefined' ? quotient : '';
				document.getElementById('remainder').innerHTML = typeof remainder !== 'undefined' ? remainder : '';
				
				document.getElementById('error').innerHTML = '';
			}).catch(function (error) {
				console.log(error.response.data);
				var { errMsg } = error.response.data;
				
				document.getElementById('error').innerHTML = typeof errMsg !== 'undefined' ? errMsg : '';
				
				document.getElementById('quotient').innerHTML = '';
				document.getElementById('remainder').innerHTML = '';
			});
		}
	</script>
</body>
</html>