<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>

<div id="messages"></div>

<script type="text/javascript">
<!--
	var timer;

	function showReply(i) {
		stopTimer();
		$("#form" + i).toggle();
	}

	function success(data) {
		$("#form" + data.target).toggle();
		$("#alert" + data.target).text("Message sent.");

		startTimer();
	}

	function error() {
		alert("error");
	}

	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	function sendMessage(i, name, email) {

		var text = $("#textbox" + i).val();
		$.ajax({
			"type" : 'POST',
			"url" : '<c:url value="sendmessage" />',
			"data" : JSON.stringify({
				"target": i,
				"text" : text,
				"name" : name,
				"email" : email
			}),
			"success" : success,
			"error" : error,
			"contentType" : "application/json",
			"dataType" : "json"

		});
	}

	function showMessages(data) {

		$("div#messages").html("")

		for (var i = 0; i < data.messages.length; i++) {
			var message = data.messages[i];

			var messageDiv = document.createElement("div");
			messageDiv.setAttribute("class", "message");

			var subjectSpan = document.createElement("span");
			subjectSpan.setAttribute("class", "subject");
			subjectSpan.appendChild(document.createTextNode(message.subject));

			var contentSpan = document.createElement("span");
			contentSpan.setAttribute("class", "messageBody");
			contentSpan.appendChild(document.createTextNode(message.content));

			var nameSpan = document.createElement("span");
			nameSpan.setAttribute("class", "name");
			nameSpan.appendChild(document.createTextNode(message.name + "("));

			var link = document.createElement("a");
			link.setAttribute("class", "replyLink");
			link.setAttribute("href", "#");
			link.setAttribute("onclick", "showReply(" + i + ")");
			link.appendChild(document.createTextNode(message.email));
			nameSpan.appendChild(link);
			nameSpan.appendChild(document.createTextNode(")"));
			
			var alertSpan = document.createElement("span");
			alertSpan.setAttribute("class", "alert");
			alertSpan.setAttribute("id", "alert" + i);
			//alertSpan.appendChild(document.createTextNode("message sent"));

			var replyForm = document.createElement("form");
			replyForm.setAttribute("class", "replyForm");
			replyForm.setAttribute("id", "form" + i)

			var textArea = document.createElement("textarea");
			textArea.setAttribute("class", "replyarea");
			textArea.setAttribute("id", "textbox" + i);

			var replyButton = document.createElement("input");
			replyButton.setAttribute("class", "replyButton");
			replyButton.setAttribute("type", "button");
			replyButton.setAttribute("value", "Reply");
			replyButton.onclick = function(j, name, email) {
				return function() {
					sendMessage(j, name, email);
				}
			}(i, message.name, message.email);

			replyForm.appendChild(textArea);
			replyForm.appendChild(replyButton);

			messageDiv.appendChild(subjectSpan);
			messageDiv.appendChild(contentSpan);
			messageDiv.appendChild(nameSpan);
			messageDiv.appendChild(alertSpan);
			messageDiv.appendChild(replyForm);

			$("div#messages").append(messageDiv);
		}

	}

	function onLoad() {
		updatePage();
		startTimer();
	}

	function startTimer() {
		timer = window.setInterval(updatePage, 10000);
	}

	function stopTimer() {
		window.clearInterval(timer);
	}

	function updatePage() {

		$.getJSON("<c:url value = "/getmessages"/>", showMessages);
	}

	$(document).ready(onLoad);
//-->
</script>