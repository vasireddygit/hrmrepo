$(document).ready(function(){

    var btn_edit = $("#edit");
    var checkboxCheckedValue = $('.checkbox:checked');
     var checkbox = $('.checkbox');
     var valueToEdit = $(".valueToEdit");
     var currentSelectedYear = $("#year option:selected").val();
     var currentSelectedMonth = $("#month option:selected").val();
     $(".displayOnCondition").hide();

    ///////////////SeleteAll event/////////////////
    $("#selectAll").change(function(){  
        var status = this.checked;
         $('.checkbox').each(function(){ 
             this.checked = status; 
         });
          if(status===true)
           { 
              enableButtons();
           } 
           else
           {
              disableButtons();
              valueToEdit.each(function(){
                 $(this).prop("disabled", true);
              });
           }
     });
 
     //////////////////CheckBox change Event////////////
     $('.checkbox').change(function(){ 
     
         if(this.checked == false){ 
             $("#selectAll")[0].checked = false; 
             $(this).parent("td").siblings("td").each(function(){
                $(this).children("input.valueToEdit").prop("disabled", false);
             });
         }
 
         if (checkboxCheckedValue.length == checkbox.length ){ 
             $("#selectAll")[0].checked = true; 
         }
 
         $('.checkbox:checked').length == 0 ? disableButtons() : enableButtons();
     });

     function enableButtons()
     {
        btn_edit.prop("disabled", false);
    
     }
     function disableButtons()
     {
       btn_edit.prop("disabled", true);
   
     }
     btn_edit.on('click',function(){
        if($("#selectAll")[0].checked == true)
        {
            valueToEdit.each(function(){
               $(this).prop("disabled", false);
            });
        }
        else
        {
            $(".checkbox").each(function()
            {
                if(this.checked == true)
                {
                  
                  $(this).parent("td").siblings("td").each(function(){
                        $(this).children("input.valueToEdit").prop("disabled", false);
                 }); 
                }
            });
        }
   });

   $("#year").on("change",getWeeksInMonth);
     $("#month").on("change",getWeeksInMonth);
     getWeeksInMonth();

      function getWeeksInMonth(){
        var month = $("#month option:selected").val();
        var year =  $("#year option:selected").val();

        startDateToEvaluate =new Date(year, month, 1);
        startDateToDisplay = new Date(year,month,1);
        var mondays=[];
        var finalDates=[]
        while (startDateToEvaluate.getDay() !== 1) {
            startDateToEvaluate.setDate(startDateToEvaluate.getDate() + 1);
        }
    
        // Get all the other Mondays in the month
        while (startDateToEvaluate.getMonth() == month) {
            mondays.push(new Date(startDateToEvaluate.getTime()));
            startDateToEvaluate.setDate(startDateToEvaluate.getDate() + 7);
        }
        for(var i = 0; i<mondays.length; i++)
        {
            if( i==0 && startDateToDisplay.getDate() == mondays[i].getDate())
            {
                var date = mondays[i].getDate();
                var month_value = parseInt(month)+1;
                finalDates.push(date +"/"+month_value);
            }
            else if(i==0 && startDateToDisplay.getDate() != mondays[i].getDate())
            {
                var date =startDateToDisplay.getDate();
                var month_value = parseInt(month)+1;
                finalDates.push(date +"/"+ month_value);
                finalDates.push(mondays[i].getDate()+"/"+month_value);
            }
            if(i>0)
            {
                var date = mondays[i].getDate();
                var month_value = parseInt(month)+1;
                finalDates.push(date +"/"+ month_value);
            }
        }
          $(".display-dates").each(function(index){
              $(this).html(finalDates[index]);
          });
          
     }  

});