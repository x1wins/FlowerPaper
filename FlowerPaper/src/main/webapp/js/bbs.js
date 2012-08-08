var file_count = 2;

function addFileNode(){
	var node = document.getElementById("file");
	var p = document.createElement("p");
	var file = document.createElement("input");

	p.setAttribute("id","file"+file_count);
	
	file.setAttribute("type","file");
	file.setAttribute("name","file");
	file.setAttribute("class","formbutton");

	node.appendChild(p);
	p.appendChild(file);
	file_count++;
}

function delFileNode(){
	
	if(file_count>2){
		var node = document.getElementById("file");
		var lastChild = node.lastChild;
		node.removeChild(lastChild);
		file_count--;
	}
}

function deleteFile(root, num, message){
	
	var delurl = root+"file/delete/bbs/"+num;
	
//	if(confirm("정말 삭제 하시겠습니까?"+delurl)){
	if(confirm(message)){
		//id="progressbar"
		var node = document.getElementById(num);
		var img = document.createElement("img");
		img.setAttribute("src",root+"/images/viewLoading.gif");
		node.appendChild(img);
		
		//viewLoading.gif
//		node.setAttribute("id", "progressbar");
//		node.innerHTML = "";
//		
//		$( "#progressbar" ).progressbar({
//			value: 100
//		});
		
		
		
		$.ajax({
		   	type: "GET",
		   	url: delurl,
//		   	data: "num="+num+"&bbs_num="+bbs_num,
			dataType: "text",
			async: true,
		   	success: function(result)
		   	{
		   		//$('#progress').removeClass('ld');			   
				//alert(result);
			  	try{
//			  		alert(result);
//			  		document.getElementById("login").innerHTML = result;
			  		
					node.parentNode.removeChild(node);
					
					//alert(document.getElementById("replyCount").value);
					//alert($('#replyCount').text());
					var count = eval($('#replyCount').text());
					//document.getElementById("replyCount").innerHTML = (count-1);
					//document.getElementById("replyCount2").innerHTML = "("+(count-1)+")";
					$('#replyCount').html(count-1);
					$('#replyCount2').html("("+(count-1)+")");
					
			  	}catch(e){
			  		alert(e);
			  	}
	   		}
		});
			
	}else{
		return false;
	}
}