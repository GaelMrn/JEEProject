(function($) {
    $(document).ready(function() {

    	$("#loader-container").hide();
    	
    	if(
			document.location.pathname == "/JEEProject/signup" ||
			document.location.pathname == "/JEEProject/admin-profil"
		){

    		$(".main-container").css({
    			"background-image" : "url('images/signup_bg.jpg')",
    			"background-size": "cover",
    	    	"background-position": "center"
    		});
    	}
    	
    	if(document.location.pathname == "/JEEProject/login"){

    		$(".main-container").css({
    			"background-image" : "url('images/login_bg.jpg')",
    			"background-size": "cover",
    	    	"background-position": "center"
    		});
    	}
    	
    });
})(jQuery);