
$(document).ready(function() {
	initEventHandlers();
	
	readList(function(list) {
		showList(list);
	});
}); 


function initEventHandlers() {
	$("#btn_register").on("click", function() {
		openRegisterPopup();
	});
	
	$(".registerLayer #btn_close").on("click", function() {
		$(".registerLayer").hide();
	});
	
	$(".registerLayer #btn_save").on("click", function() {
		doRegister();
	});
}

function openRegisterPopup() {
	$(".registerLayer").show();
	
	$(".registerLayer [name='name']").val("");
	$(".registerLayer [name='phoneNumber']").val("");
}

function doRegister() {
	var param = {
		name: $(".registerLayer [name='name']").val(),
		phoneNumber: $(".registerLayer [name='phoneNumber']").val()
	}
	console.log("doRegister", param);
	
	$.post("register.do", param, function(response) {
		console.log(response);
		if (response.resultCode == "Success") {
			$(".registerLayer").hide();
			
			// TODO list 갱신
		} else {
			alert("등록에 실패했습니다. " + response.resultMessage);
		}
	});
}

function readList(callback) {
	/*$.get("list.do", null, function(response) {
		if (response.resultCode == "Success") {
			callback(response.data);
		} else {
			alert("서버에서 데이터를 읽어 올 수 없습니다. " + response.resultMessage);
		}
	});*/
	var list = [
		{idx: 1, name: "홍길동", phoneNumber: "010-0000-0012"},
		{idx: 2, name: "임꺽정", phoneNumber: "010-1111-0012"},
		{idx: 3, name: "장길산", phoneNumber: "010-1234-5678"},
	];
	
	callback(list);
}

function showList(list) {
	$(".list tbody").empty();
	
	for (var i in list) {
		showLine(list[i]);
	}
}

function showLine(data) {
	var tr = $("<tr/>");
	
	// checkbox
	var td1 = $("<td></td>");
	var checkbox = $("<input name='checkSelect' type='checkbox'>");
	checkbox.val(data.idx);
	td1.append(checkbox);
	tr.append(td1);
	
	// name
	var td2 = $("<td></td>");
	td2.html(data.name);
	tr.append(td2);
	// TODO 이름 클릭 -> update
	
	// phone number
	var td3 = $("<td></td>");
	td3.html(data.phoneNumber);
	tr.append(td3);
	
	$(".list tbody").append(tr);
}









