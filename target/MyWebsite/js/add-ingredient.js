/**
 * 
 */
jQuery(function(){
	jQuery('a.add-ingredient').click(function(event){
            event.preventDefault();
            var unitsList = ["", "pinch", "tsp", "tbls", "cup", "oz", "lbs"];
            var newRow = jQuery('<tr style="border: none;">' + 
            					'<td style="border: none;">' + 
            						'<input style="width:50px; text-align: right;" type="number" name="ingredientAmt1" min="0" max="500" value="0"/>' + 
            						'<select style="width:50px; padding:2px" id="amt" name="ingredientAmt2">' + 
            							'<c:forEach var="fraction" items="${fractionsList}"><option value="${fraction}">${fraction}</option></c:forEach></select></td>' + 
            					'<td style="border: none;">' + 
            						'<select style="width:80px"id="unit" name="ingredientUnit">' + 
            							'<c:forEach var="unit" items="${unitsList}"><option value="${unit}">${unit}</option></c:forEach></select></td>' + 
            					'<td style="border: none;"><input type="text" name="ingredientName" placeholder="Enter Ingredient" size="25"/></td></tr>');
             jQuery('table.ingredient-list').append(newRow);

    });
});