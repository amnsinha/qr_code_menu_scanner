$(function() {
  	    var local = "http://localhost:8081";
		var productionHost = "http://35.154.160.245:8080/hoboai-service";
      $('#submitEnquiryHotel').find('input').jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
            // additional error messages or events
        },
        submitSuccess: function($form, event) {
            event.preventDefault(); // prevent default submit behaviour
            // get values from FORM
          
            var companytext = $("input#companytext").val();
            var addresstext = $("input#addresstext").val();
            var name = $("input#yourname").val();
            var mobileNumber = $("input#mobileNumber").val();
            var email = $("input#email").val();
            var firstName = name; // For Success/Failure Message
            // Check for white space in name for Success/Fail message
            if (firstName.indexOf(' ') >= 0) {
                firstName = name.split(' ').slice(0, -1).join(' ');
            }
			
			var dataModel = 
				 {
					hotelName : companytext,
					hotelAddress : addresstext,
					hotelAddress : addresstext,
					 
					hotelContactNo : mobileNumber,
					hotelEmailAddress : email,
				    hotelAdmin : name,
					subscriptionduration : "6 months"
                 };
		 
			
            $.ajax({
                url: productionHost+"/api/hoboai/web/save-enquire",
                type: "POST",	
				headers: { "Accept": "application/json"},
				contentType: "application/json; charset=utf-8",
                dataType   : "json",
				crossDomain: true,				
                data:JSON.stringify(dataModel),
                cache: false,
				
                success: function() {
				    $('#success').show();
                    // Success message
                    $('#success').html("<div class='alert alert-success'>");
                    $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-success')
                        .append("<strong>Thanks, We will reach you soon</strong>");
                    $('#success > .alert-success')
                        .append('</div>');

                    //clear all fields
                    $('#submitEnquiryHotel').trigger("reset");
                },
                error: function() {
				    $('#success').show();
                    // Fail message
                    $('#success').html("<div class='alert alert-danger'>");
                    $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-danger').append("<strong>Sorry " + firstName + ", it seems that server is not responding. Please try again later!");
                    $('#success > .alert-danger').append('</div>');
                    //clear all fields
                    $('#submitEnquiryHotel').trigger("reset");
                },
            })
        },
        filter: function() {
            return $(this).is(":visible");
        },
    });

});


/*When clicking on Full hide fail/success boxes */
$('#name').focus(function() {
    $('#success').html('');
});
