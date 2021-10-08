$(function() {
	    var local = "http://localhost:8081";
		var productionHost = "http://35.154.160.245:8080/hoboai-service";
    $('#submitEnquiryContact').find('input,textarea').jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
            // additional error messages or events
        },
        submitSuccess: function($form, event) {
            event.preventDefault(); // prevent default submit behaviour
            // get values from FORM
            var name = $("input#name").val();
            var email = $("input#emailofContact").val();
            var message = $("textarea#message").val();
			
            var firstName = name; // For Success/Failure Message
            // Check for white space in name for Success/Fail message
            if (firstName.indexOf(' ') >= 0) {
                firstName = name.split(' ').slice(0, -1).join(' ');
            }
		
			var dataModel = 
				 {
				   name: name,
				   emailorNumber: email,
                    message: message
                 };
				 
            $.ajax({
                 url: productionHost+"/api/hoboai/web/demo-request",
                 type: "POST",
				 crossDomain: true,	
				 headers: { "Accept": "application/json"},
				 contentType: "application/json; charset=utf-8",
                 dataType   : "json",
                 data:JSON.stringify(dataModel),
				 cache: false,
                cache: false,
                success: function() {
				    $('#successContact').show();
                    // Success message
                    $('#successContact').html("<div class='alert alert-success'>");
                    $('#successContact > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#successContact > .alert-success')
                        .append("<strong>Your message has been sent. </strong>");
                    $('#successContact > .alert-success')
                        .append('</div>');

                    //clear all fields
                    $('#submitEnquiryContact').trigger("reset");
                },
                error: function() {
					 $('#successContact').show();
                    // Fail message
                    $('#successContact').html("<div class='alert alert-danger'>");
                    $('#successContact > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#successContact > .alert-danger').append("<strong>Sorry " + firstName + ", it seems that server is not responding. Please try again later!");
                    $('#successContact > .alert-danger').append('</div>');
                    //clear all fields
                    $('#submitEnquiryContact').trigger("reset");
                },
            })
        },
        filter: function() {
            return $(this).is(":visible");
        },
    });

    $("a[data-toggle=\"tab\"]").click(function(e) {
        e.preventDefault();
        $(this).tab("show");
    });
});


/*When clicking on Full hide fail/success boxes */
$('#name').focus(function() {
    $('#success').html('');
});
