
var ret = 0;//Для присвоения id <canvas id='myChart'+idx>
var i = 0;
var arrChartMainT1 = [ [ [] ] ];
var arrChartMainT2 = [ [ [] ] ];
var arrChartT1 = [ [ [] ] ];
var chartObj = [];
var namObj;

/**
 * Создание меню объектов УК
 */
 function makeMenu(data) {
     $.each(data, function (idx, obj) {
	   $("#menuTreeTable").append(
       '<tr data-tt-id="' + obj.id_link_object + '" data-tt-parent-id="' + obj.id_parent + '" id="link' + obj.id_link_object + '" ><td style="text-align: center">' + obj.name_object + '</td>' +
       + '<td style="display: none;">' + obj.id_object + '</td><td style="display: none;">' + obj.id_type_object + '</td>'
       + '<td style="display: none;">' + obj.name_object + '</td>'
       + '<TD style="width:8%;" align=center>'
       + '<button type="button" onclick="onChart(' + obj.id_link_object + ',' + obj.id_type_object + ',' + 'this' + ');" class="btn btn-light btn-sm" id="btn' 
       + obj.id_link_object+ '"><i class="fas fa-cogs"></i></button>'
       + '</div></div>'
       + '</TD>'
       + '</tr>');
       
       //var myId = $('#link'+obj.id_link_object);
       //var a = myId.hasClass("leaf");
              
     });
     $("#menuTreeTable").treetable({
         expandable: true,
         initialState: "expanded",
         clickableNodeNames: true,
         indent: 10
      });
      //setScrollToPos();
};

function myChart(lab1, lab2, backColorT1, borderColorT1, backColorT2, borderColorT2){

	arrChartMainT1[0][ret] = new Array();
    arrChartT1[0][ret] = new Array();
    arrChartMainT2[0][ret] = new Array();
    var strLblT;
    
/**
 * Заполнение данными массивов для построения диаграммы
 */
_.each(listWater, function(list) {
	  if(list.typeCount == 1){
	    arrChartMainT1[0][ret][i] = list.diffCountW;
	    strLblT = list.room;
		
      }	
      
	  if(list.typeCount == 2){	
		arrChartMainT2[0][ret][i] = list.diffCountW;
	    strLblT = list.room;
		
	  } 	
	  
	  arrChartT1[0][ret][i] = strLblT;
	  	  	
	  if(i == seriesLen){
			ret = makeCharts(ret, arrChartT1[0][ret], arrChartMainT1[0][ret], arrChartMainT2[0][ret],  
			                 lab1, lab2, backColorT1, borderColorT1, backColorT2, borderColorT2);
		    i = -1;
		    arrChartMainT1[0][ret] = new Array();
		    arrChartT1[0][ret] = new Array();
		    arrChartMainT2[0][ret] = new Array();
		    
      }
      
	  i++;
});

/**
* Let's fill in the rest of the series of bars 0th
* to save the width of bars
*/
   if(i <= seriesLen && i != 0){
	   for(j=(++i); j <= seriesLen; j++){
		   arrChartMainT1[0][ret][j] = 0;
		   arrChartMainT2[0][ret][j] = 0;
		   arrChartT1[0][ret][j] = "";
	   }
	   makeCharts(ret, arrChartT1[0][ret], arrChartMainT1[0][ret], arrChartMainT2[0][ret],  
			                 lab1, lab2, backColorT1, borderColorT1, backColorT2, borderColorT2);
	   ret = 0;
	   i = 0;
   } 	

	
};

function makeCharts(idx, arrChart, arrChartMainT1, arrChartMainT2, 
                    lab1, lab2, backColorT1, borderColorT1, backColorT2, borderColorT2){
	addCanvas(idx);
	chartObj[idx] = addChart(idx, arrChart, arrChartMainT1, arrChartMainT2,
	                         lab1, lab2, backColorT1, borderColorT1, backColorT2, borderColorT2);
	return (++idx);
}

function addChart(idx, arrayLabel, arrayDataT1, arrayDataT2, lab1, lab2, backColorT1, borderColorT1, backColorT2, borderColorT2){
var ctx = null;
var myChart = null;

ctx = document.getElementById('myChart'+idx).getContext('2d');
myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: arrayLabel,
        datasets: [{
	        minBarLength: 2,
            label: lab1,
            data: arrayDataT1,
            backgroundColor: [ backColorT1 ],
            borderColor: [ borderColorT1 ],
            borderWidth: 1
            
        },
        {
	        minBarLength: 2,
            label: lab2,
            data: arrayDataT2,
            backgroundColor: [ backColorT2 ],
            borderColor: [ borderColorT2 ],
            borderWidth: 1
        }
        ]
    },
    options: {
	    
       scales: {
		         
        	x: {
        	grid: {offset: true},
	        ticks: {minRotation: 75}, // angle in degrees
            stacked: true,
            offset: true
           },
            y: {
                beginAtZero: true,
                stacked: true
               }
        }
    }
});
 return myChart;
}



function addCanvas(idx){
	var div = document.getElementById("main_div");
	var canvas = document.createElement('canvas');
	canvas.setAttribute('id', 'myChart'+idx);
	canvas.setAttribute('width', '400');
	canvas.setAttribute('height', '100');
	div.appendChild(canvas);
}


/**
 * Удаление all myCharts
 */
function destroyChart(){
	var j = chartObj.length;
	var i;
	for(i = 0; i < j; i++){
		chartObj[i].destroy();
	}
	chartObj.lenght = 0;
	arrChartMainT1[0].length = 0;
	arrChartT1[0].length = 0;
	arrChartMainT2[0].length = 0;
}

/**
 * Вывод текущей даты в формате YYYY-MM-DD 
 */
   function myDateNow(){
 	  var mlsk = Date.now();
 	  var nowDate = new Date(mlsk);
 	  var year = nowDate.getFullYear().toString();
 	  var month = (nowDate.getMonth() + 1).toString().length > 1 ? (nowDate.getMonth() + 1).toString() : '0'+(nowDate.getMonth() + 1).toString();
 	  var day = nowDate.getDate().toString().length > 1 ? nowDate.getDate().toString() : '0'+nowDate.getDate().toString();  
 	  var dat = year + '-' + month + '-' + day;
 	  return dat;
   }
   
   



