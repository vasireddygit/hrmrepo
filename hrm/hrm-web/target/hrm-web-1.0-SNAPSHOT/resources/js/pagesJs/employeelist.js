$(document).ready(function() {
    $('#example').DataTable({
        "bPaginate": false,
        "bInfo" : false,
        "searching": false,
        "order": [[ 1, 'asc' ]] 
    });

    $("#selectAll").change(function(){  
	var status = this.checked;
	$('.checkbox').each(function(){ 
		this.checked = status; 
	});
});

$('.checkbox').change(function(){ 

	if(this.checked == false){ 
		$("#selectAll")[0].checked = false; 
	}
	
	
	if ($('.checkbox:checked').length == $('.checkbox').length ){ 
		$("#selectAll")[0].checked = true; 
	}
});
});