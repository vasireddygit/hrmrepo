 
 // Add delete code starts here
 $(document).ready(function(){
                $(".add-row").click(function(){
                    var cname = $("#customer").val();
                    var pname = $("#name").val();
                     var padmin = $("#padmin").val();
                    var markup = "<tr><td><input type='checkbox' name='record'></td><td>" + cname + "</td><td>" + pname + "</td><td>" + padmin + "</td></tr>";
                    $("table tbody").append(markup);
                });
                
                // Find and remove selected table rows
                $(".delete-row").click(function(){
                    $("table tbody").find('input[name="record"]').each(function(){
                        if($(this).is(":checked")){
                            $(this).parents("tr").remove();
                        }
                    });
                });
            });

// Add delete code ends here

// Add textbox in the pop code starts here
//  $(function () {
//     $("#btnAdd").bind("click", function () {
//         var div = $("<div />");
//         div.html(GetDynamicTextBox(""));
//         $("#TextBoxContainer").append(div);
//     });
//     $("#btnGet").bind("click", function () {
//         var values = "";
//         $("input[name=DynamicTextBox]").each(function () {
//             values += $(this).val() + "\n";
//         });
//         alert(values);
//     });
//     $("body").on("click", ".remove", function () {
//         $(this).closest("div").remove();
//     });
// });
// function GetDynamicTextBox(value) {
//     return '<input name = "DynamicTextBox" type="text" value = "' + value + '" />&nbsp;' +
//             '<input type="button" value="Remove" class="remove" />'
// }

// Add delete code ends here
function add_field()
{
  var total_text=document.getElementsByClassName("input_text");
  total_text=total_text.length+1;
  document.getElementById("field_div").innerHTML=document.getElementById("field_div").innerHTML+
  "<p id='input_text"+total_text+"_wrapper'><input type='text' class='input_text' id='input_text"+total_text+"' placeholder='Enter Text'><input type='button' value='Remove' onclick=remove_field('input_text"+total_text+"');></p>";
}
function remove_field(id)
{
  document.getElementById(id+"_wrapper").innerHTML="";
}