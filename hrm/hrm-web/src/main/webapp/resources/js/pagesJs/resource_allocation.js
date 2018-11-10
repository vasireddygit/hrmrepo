$(document).ready(function(){
   
    /////////Initialisation////////////////////
     var btn_edit = $("#edit");
     var btn_delete_elements = $("#deletelements");
     var btn_delete = $("#delete");
     var btn_add = $("#add");
     var checkboxCheckedValue = $('.checkbox:checked');
     var checkbox = $('.checkbox');
     var valueToEdit = $(".valuetoedit");
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
            $(this).parent("div").siblings("div").each(function(){
                $(this).children("input.valuetoedit").prop("disabled", true);
            });
        }

        if (checkboxCheckedValue.length == checkbox.length ){ 
            $("#selectAll")[0].checked = true; 
        }

        $('.checkbox:checked').length == 0 ? disableButtons() : enableButtons();
    });

    //////////////Auto Complete///////////////////////
    var availableTags = [
        "Arun",
        "Aamani",
        "Bob",
        "Sree",
        "Srinivas",
        "Chenchu",
        "Ravi"
      ];
      $("#tags").autocomplete({
        source: availableTags,
        select: function (event, ui) {
            debugger;
            var label = ui.item.label;
            var name = ui.item.value;
            var content = "<div class='form-group row resourcesdata'><div style='width:2.333333%;' class='col-xs-1'><input  type='checkbox'  class='checkbox' value='' ></div><div class='col-xs-1'><input class='form-control' type='text'  value='001' disabled></div><div class='col-xs-2'><input class='form-control' type='text' value="+name+" disabled></div><div class='col-xs-2'><input class='form-control' type='text' value='Digital' disabled></div><div class='col-xs-1'><input class='form-control' type='text' value='BGW' disabled></div><div class='col-xs-1'><input class='form-control valuetoedit' type='text' value='40' disabled></div><div class='col-xs-1'><input class='form-control valuetoedit' type='text' value='40' disabled></div><div class='col-xs-1'><input class='form-control valuetoedit' type='text' value='40' disabled></div><div class='col-xs-1'><input class='form-control valuetoedit'  type='text' value='40' disabled></div><div class='col-xs-1'><input class='form-control valuetoedit' type='text' value='40' disabled></div></div>";
            $(content).insertBefore($(".resourcesdata")[0]);

            $("#myModal").modal('hide');
        }
      }); 

      function enableButtons()
      {
         btn_edit.prop("disabled", false);
         btn_delete.prop("disabled",false);

      }
      function disableButtons()
      {
        btn_edit.prop("disabled", true);
        btn_delete.prop("disabled",true);
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
                    $(this).parent("div").siblings("div").each(function(){
                           $(this).children("input.valuetoedit").prop("disabled", false);
                    }); 
                   }
               });
           }
      });

      btn_delete_elements.on('click',function()
      {
        if($("#selectAll")[0].checked == true)
        {
            $(".resourcesdata").remove();
        }
        else
        {
            $(".checkbox").each(function()
            {
                debugger;
                if(this.checked == true)
                {
                 $(this).parents("div.resourcesdata").remove(); 
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
            debugger;
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
          if(finalDates.length==6)
          {
            $(".displayOnCondition").show();
            $(".js-adjustwidth").each(function(){
               if($(this).hasClass("col-xs-2"))
               {
                   $(this).removeClass("col-xs-2");
                   $(this).addClass("col-xs-1")
               }
               else if($(this).hasClass("col-xs-3"))
               {
                   $(this).removeClass("col-xs-3");
                   $(this).addClass("col-xs-2");
               }
            });
          }
          else if(finalDates.length==5 && $(".displayOnCondition").is(":visible"))
          {
            $(".displayOnCondition").hide();
            $(".js-adjustwidth").each(function(){
                if($(this).hasClass("col-xs-1"))
                {
                    $(this).removeClass("col-xs-1");
                    $(this).addClass("col-xs-2")
                }
                else if($(this).hasClass("col-xs-2"))
                {
                    $(this).removeClass("col-xs-2");
                    $(this).addClass("col-xs-3");
                }
             });
          }
          $(".display-dates").each(function(index){
              $(this).html(finalDates[index]);
          });
          
     }  
});

    // Weekly hours Calculation coode starts here

        $(function () {
            var textBox1 = $('input:text[id$=TextBox1]').keyup(foo);
            var textBox2 = $('input:text[id$=TextBox2]').keyup(foo);
            var textBox3 = $('input:text[id$=TextBox3]').keyup(foo);
            var textBox4 = $('input:text[id$=TextBox4]').keyup(foo);
            var textBox5 = $('input:text[id$=TextBox5]').keyup(foo);

            function foo() {
                var value1 = textBox1.val();
                var value2 = textBox2.val();
                var value3 = textBox3.val();
                var value4 = textBox4.val();
                var value5 = textBox5.val();
                var sum = add(value1, value2,value3,value4,value5);
                $('input:text[id$=TextBox6]').val(sum);
            }

            function add() {
                var sum = 0;
                for (var i = 0, j = arguments.length; i < j; i++) {
                    if (IsNumeric(arguments[i])) {
                        sum += parseFloat(arguments[i]);
                    }
                }
                return sum;
            }
            function IsNumeric(input) {
                return (input - 0) == input && input.length > 0;
            }
        });

    // Weekly hours Calculation coode ends here