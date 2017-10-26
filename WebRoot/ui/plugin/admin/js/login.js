
	var loginIdAdm=document.getElementById("loginId");
	
		loginIdAdm.onfocus=function(){
			if(loginIdAdm.value!=""){
				loginIdAdm.className="admin_css_input1-focus"
			}
		}
		
		loginIdAdm.onblur=function(){
			if(loginIdAdm.value==""){
				loginIdAdm.className="admin_css_input1"
			}else{
				loginIdAdm.className="admin_css_input1-focus"
			}
		}
	
	var loginPasswordAdm=document.getElementById("loginPassword");
		loginPasswordAdm.onfocus=function(){
			if(loginPasswordAdm.value!=""){
				loginPasswordAdm.className="admin_css_input2-focus"
			}
		}
		
		loginPasswordAdm.onblur=function(){
			if(loginPasswordAdm.value==""){
				loginPasswordAdm.className="admin_css_input2"
			}else{
				loginPasswordAdm.className="admin_css_input2-focus"
			}
		}
		
	var validateCodeAdm=document.getElementById("validateCode");
		validateCodeAdm.onfocus=function(){
			if(validateCodeAdm.value!=""){
				validateCodeAdm.className="admin_css_input3-focus"
			}
		}
		
		validateCodeAdm.onblur=function(){
			if(validateCodeAdm.value==""){
				validateCodeAdm.className="admin_css_input3"
			}else{
				validateCodeAdm.className="admin_css_input3-focus"
			}
		}