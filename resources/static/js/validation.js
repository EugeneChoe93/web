/**
 * 
 */
function checkAddProduct() {
	
	var productId = document.getElementById("productId");
	var name = document.getElementById("name");
	var price = document.getElementById("price");
	var soldout = document.getElementById("soldout");
	var description = document.getElementById("description");
	
	if(!check(/[0-9]{4,12}$/, productId, "[상품 코드]\n숫자를 4~12자까지 입력하세요")){
		return false;
	}
	
	if(name.value.length < 3 || name.value.length > 50){
		alert("[상품명]\n최소 3자에서 최대 50자까지 입력하세요.");
		name.focus();
		return false;
	}
	
	if(price.value.length == 0 || isNaN(price.value)){
		alert("[가격]\n숫자만 입력하세요.");
		price.focus();
		return false;
	}
	
	if(price.value < 0){
		alert("[가격]\n음수를 입력할 수 없습니다.");
		unitPrice.focus();
		return false;
	}
	
	if(isNaN(soldout.value)){
		alert("[재고있음]\nO,X");
		soldout.focus();
		return false;
	}
	
	if(description.value.length < 5){
		alert("[상세설명]\n최소 5자 이상 입력하세요.");
		description.focus();
		return false;
	}
	
	function check(regExp, e, msg){
		if(regExp.test(e.value)){
			return true;
		}
		alert(msg);
		e.focus();
		return false;
	}
	
	document.newProduct.submit();	
}







