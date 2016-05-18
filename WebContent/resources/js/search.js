$(function() {
	// Search profile on click on Search buuton
	$('#searchBtn').click(function(){
		searchResults();
	});
	
	// Dialog to vote for a profile
	$("#dialog-voting").dialog({
		autoOpen : false,
		height : 150,
		width : 100,
		modal : true,
		buttons : {
			"Vote" : function() {
				/* Get the vote count in grid, based on 
				/ user selection increment or decrement
				/ the vote count */
				var rollNo = $(this).data('data').rollNo;
				var voteCount = $(this).data('data').voteCount;
				var vote = $('input[name=vote]:checked').val();
				
				if(vote === 'like')
					voteCount += 1;
				else
					voteCount -= 1;
				
				// Update vote count in DB
				updateVote(rollNo,voteCount);				
				
				$(this).dialog("close");
			},
			'Cancel' : function() {
				$(this).dialog("close");
			}
		}
	});	
});

// Function to display all or selected profiles based on search filter
function searchResults(){
	var rollNo = $('#rollNoHidden').val();
	//Destroy datatable instance to avoid multiple instance in memory
	$("#searchResultsTable").dataTable().fnDestroy();
	
	// Define the datatable
	var oTable = $('#searchResultsTable').dataTable({
		iDisplayLength : 25, // Default number of records to be displayed on datatable load
		"aLengthMenu": [[25, 50, 100],[25, 50, 100]], // Number of records selection menu
		//"bLengthChange": false,
		"bServerSide" : true,
		"bStateSave" : false,
		"contentType" : "application/json; charset=utf-8",
		"sAjaxSource" : "search/profiles", // URI to make ajax call
		"bProcessing" : true,// Show Processing... to user if there is a data load
		"bAutoWidth" : true,
		"bJQueryUI" : true, // Apply default CSS supplied by datatable plugin
		"sServerMethod" : "POST",
		//"bPaginate": true,
		"bSort" : true,
		"sPaginationType" : "full_numbers", // Enable pagination
		"aaSorting": [[0, 'asc']], // Default sorting by 1st columns
		"oLanguage": {
		      "sLengthMenu": "_MENU_ Records Per Page",
		      "sSearch": "<span><i class='ui-icon-search'></i></span>Search Profile:",
		      "sZeroRecords": "No Matching Records Found",
		}, // Define columns and their rendering logic
		    "aoColumns" :[
		     		     {
		     		    	 "sWidth" : "15%",
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 1);
		     		         }
		     		     },{
		     		    	 "sWidth" : "15%",
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 2);
		     		         }
		     		     },{
		     		    	 "sWidth" : "15%",
		     		    	 "fnRender": function (oObj){
		     		    		 // Disable voting link to avoid self voting
		     		    		 if(rollNo === oObj.aData[3])
		     		    			 return getEmptyString(oObj, 3);
		     		    		 // Render voting link to vote for other profiles
		     		    		 return "<a class=\"rateProfileLaunch\" id='" + oObj.aData[3] + "' href=\"javascript:votePopupHandler(" + oObj.aData[3] + "," + oObj.aData[7] + ");\">" + oObj.aData[3] + "</a>";
		     		         }
		     		     },{
		     		    	 "sWidth" : "15%",
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 4);
		     		         }
		     		     },{
		     		    	 "sWidth" : "15%",
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 5);
		     		         }
		     		     },{
		     		    	 "sWidth" : "15%",
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 6);
		     		         }
		     		     },{
		     		    	 "sWidth" : "10%",
		     		    	 "fnRender": function (oObj){
		     		    		 var id = oObj.aData[2];
		     		    		 //console.log('ID: ' + id);
		     		    		 id = id.substring(id.indexOf('>') + 1, id.indexOf('</a>'));
		     		    		 //console.log('Id inside: ' + id);
		     		    		 return "<div id='" + id + "_vote'>" + oObj.aData[7] + "</div>";
		     		         }
		     		     }	     		
		     		],
		"fnDrawCallback": function( oSettings ) {	
			
		},
		"fnServerParams": function ( aoData ) {
			// Pass search parameters to AJAX call to load profiles
            aoData.push( { "name": "rollNo", "value": $('#rollNoSearch').val() } );
            aoData.push( { "name": "firstName", "value": $('#fNameSearch').val() } );
            aoData.push( { "name": "lastName", "value": $('#lNameSearch').val() } );
        },
	});
	
	//oTable.fnAdjustColumnSizing();
}

// Open voting dialog
function votePopupHandler(rollNo, voteCount) {
	var data = {"rollNo" : rollNo,
				"voteCount" : voteCount};
	$("#dialog-voting").data('data',data).dialog('open');
}

// Validate data from backend before rendering in datatable
function getEmptyString(oObj, index){
	//console.log('aaData: ' + oObj.aData);
	var val = oObj.aData[index];
	//console.log(val + ' : ' + index);
	if(val == null || val == "")
		val = '';
    return  val;
}

// Make ajax call to update vote and sync with UI
function updateVote(rollNo, voteCount) {
	var vote = new Vote();
	vote.voteCount = voteCount;
	
	$.ajax({
		url   : "vote/update/" + rollNo,
		type  : "POST",
		contentType: "application/json; charset=utf-8",
		data  : JSON.stringify(vote),
		success: function(response) {
			console.log('Vote Saved Successfully.' + response);
			
			response = JSON.parse(response);
			
			if(response.success === "success") {
				var voteColId = rollNo + '_vote';
				$('#' + rollNo).attr('href','javascript:votePopupHandler(' + rollNo + ',' + voteCount + ');');
				$('#' + voteColId).text(voteCount);
			}			
		},
		error: function(result){
			//hideLoader();
		} 
	});
}

function Vote() {
	this.voteCount = '';
	this.rollNo = '';
}

$(document).ready(function() {
	// Load all profile on first time load
	searchResults();
});