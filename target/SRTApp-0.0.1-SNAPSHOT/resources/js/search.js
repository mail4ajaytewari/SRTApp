$(function() {
	$('#searchBtn').click(function(){
		searchResults();
	});
	
	$("#dialog-voting").dialog({
		autoOpen : false,
		height : 150,
		width : 100,
		modal : true,
		buttons : {
			"Vote" : function() {
				var rollNo = $(this).data('data').rollNo;
				var voteCount = $(this).data('data').voteCount;
				var vote = $('input[name=vote]:checked').val();
				
				if(vote === 'like')
					voteCount += 1;
				else
					voteCount -= 1;
				
				updateVote(rollNo,voteCount);				
				
				$(this).dialog("close");
			},
			'Cancel' : function() {
				$(this).dialog("close");
			}
		}
	});	
});

function searchResults(){
	var rollNo = $('#rollNoHidden').val();
	$("#searchResultsTable").dataTable().fnDestroy();
	$('#searchResultsTable').dataTable({
		iDisplayLength : 5,
		"aLengthMenu": [[5, 10, 20],[5, 10, 20]],
		//"bLengthChange": false,
		"bServerSide" : true,
		"bStateSave" : false,
		"contentType" : "application/json; charset=utf-8",
		"sAjaxSource" : "search/profiles",
		"bProcessing" : true,
		"bAutoWidth" : true,
		"bJQueryUI" : true,
		"sServerMethod" : "POST",
		//"bPaginate": true,
		//"bSort" : true,
		"sPaginationType" : "full_numbers",
		"aaSorting": [[0, 'asc']],
		"oLanguage": {
		      "sLengthMenu": "_MENU_ Records Per Page",
		      "sSearch": "<span><i class='ui-icon-search'></i></span>Search Profile:",
		      "sZeroRecords": "No Matching Records Found",
		},
		    "aoColumns" :[
		     		     {
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 1);
		     		         }
		     		     },{
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 2);
		     		         }
		     		     },{
		     		    	 "fnRender": function (oObj){
		     		    		 if(rollNo === oObj.aData[3])
		     		    			 return getEmptyString(oObj, 3);
		     		    		 return "<a class=\"rateProfileLaunch\" id='" + oObj.aData[3] + "' href=\"javascript:votePopupHandler(" + oObj.aData[3] + "," + oObj.aData[7] + ");\">" + oObj.aData[3] + "</a>";
		     		         }
		     		     },{
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 4);
		     		         }
		     		     },{
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 5);
		     		         }
		     		     },{
		     		    	 "fnRender": function (oObj){
		     		    		 return getEmptyString(oObj, 6);
		     		         }
		     		     },{
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
            aoData.push( { "name": "rollNo", "value": $('#rollNoSearch').val() } );
            aoData.push( { "name": "firstName", "value": $('#fNameSearch').val() } );
            aoData.push( { "name": "lastName", "value": $('#lNameSearch').val() } );
        },
	});
}

function votePopupHandler(rollNo, voteCount) {
	var data = {"rollNo" : rollNo,
				"voteCount" : voteCount};
	$("#dialog-voting").data('data',data).dialog('open');
}

function getEmptyString(oObj, index){
	//console.log('aaData: ' + oObj.aData);
	var val = oObj.aData[index];
	//console.log(val + ' : ' + index);
	if(val == null || val == "")
		val = '';
    return  val;
}

function updateVote(rollNo, voteCount) {
	var vote = new Vote();
	vote.voteCount = voteCount;
	
	$.ajax({
		url   : "vote/update/" + rollNo,
		type  : "POST",
		contentType: "application/json; charset=utf-8",
		data  : JSON.stringify(vote),
		success: function(result) {
			console.log('Vote Saved Successfully.');
			
			var voteColId = rollNo + '_vote';
			$('#' + rollNo).attr('href','javascript:votePopupHandler(' + rollNo + ',' + voteCount + ');');
			$('#' + voteColId).text(voteCount);
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