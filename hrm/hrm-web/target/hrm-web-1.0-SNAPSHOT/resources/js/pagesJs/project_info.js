
 $(document).ready(function(){
                $(".add-row").click(function(){
                    var name = $("#customer").val();
                    var email = $("#description").val();
                    var markup = "<tr><td><input type='checkbox' name='record'></td><td>" + name + "</td><td>" + email + "</td></tr>";
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