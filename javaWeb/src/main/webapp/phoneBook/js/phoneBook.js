
$(document).ready(function() {
	initEventHandlers();
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
		// TODO
	});
}








