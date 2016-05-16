var csrfToken;
var csrfHeader;
var header;
var profileResponse = '';

$(function() {
	// Load the datatable when Search Profile tab is active
	$("#tabs").tabs({
		activate: function(event, ui) {
			if(ui.newPanel.selector == "#tabs-searchProfile") {
				searchResults();
		    }
		}
	});
	
	// Make field editable to update profile
	$('#editProfileBtn').click(function() {
		$('#firstName').removeAttr("disabled");
		$('#lastName').removeAttr("disabled");
		$('#email').removeAttr("disabled");
		$('#phone').removeAttr("disabled");
		$('#changeProfileBtn').removeAttr("disabled");
	});
	
	// Phone validation to allow only numeric keys, back space, delete
	$("#phone").keydown(function(e) {
		isNumeric(e);
	});
	
	// Email validation
	$('#email').blur(function(){
		validateEmail($(this).val());		
	});
	
	// Make ajax call to submit profile details
	$('#changeProfileBtn').click(function(){
		var student = new Student();
		student.firstName = $('#firstName').val();
		student.lastName = $('#lastName').val();
		student.email = $('#email').val();
		student.phone = $('#phone').val();
		student.rollNo = $('#rollNo').val();
		
		var isValidEmail = validateEmail(student.email);
		
		// Make ajax call if required validation has passed
		if(isValidEmail) {
			$.ajax({
				url   : "profile/update/" + student.rollNo,
				type  : "POST",
				contentType: "application/json; charset=utf-8",
				data  : JSON.stringify(student),
				success: function(result) {
					console.log('Profile Saved Successfully.');
					// Disable once profile is saved in database
					$('#firstName').attr("disabled","disabled");
					$('#lastName').attr("disabled","disabled");
					$('#email').attr("disabled","disabled");
					$('#phone').attr("disabled","disabled");
					$('#changeProfileBtn').attr("disabled","disabled");
				},
				error: function(result){
					//hideLoader();
				} 
			});
		}
	});
});

// Function to for phone number validation
function isNumeric(e) {
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
         // Allow: Ctrl+A, Command+A
        (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
         // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
             // let it happen, don't do anything
             return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
}

// Function to validate email
function validateEmail(email) {
    var re = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    var isValid = re.test(email);
    
    if(!isValid) {
		$('#emailError').html("<font color='red'>*Incorrect Email Format!</font>");
	}else {
		$('#emailError').html("");
	}
    
    return isValid;
}

// Make ajax call to fetch profile details for logged in user
function getUserProfile() {
	//showLoader();
	var rollNo = $('#rollNoHidden').val();
	$.ajax({
		url   : "profile/user/" + rollNo,
		type  : "GET",
		contentType: "application/json; charset=utf-8",
		success: function(response) {
			console.log('Profile Fetched Successfully.');
			console.log(response.phone);
			// Populate form fields of my profile tab
			populateProfileForm(response);
			//hideLoader();
		},
		error: function(result){
			// To DO
			//hideLoader();
		} 
	});
}

// Function to populate form fields on my profile tab
function populateProfileForm(profileResponse) {
	$('#firstName').val(profileResponse.firstName);
	$('#lastName').val(profileResponse.lastName);
	$('#email').val(profileResponse.email);
	$('#phone').val(profileResponse.phone);
	$('#rollNo').val(profileResponse.rollNo);
	$('#voteCount').val(profileResponse.votes);
	$('#branch').val(profileResponse.branchName);
}

// Student function object
function Student() {
	this.rollNo = '';
	this.firstName = '';
	this.lastName = '';
	this.email = '';
	this.phone = '';
}

$(document).ready(function() {
	// Load profile details as user lands on home page
	getUserProfile();
});