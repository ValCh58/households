var ret = 0;//Для присвоения id <canvas id='myChart'+idx>
var i = 0;
var arrChartMain = [ [ [] ] ];
var arrChart = [ [ [] ] ];
var chartObj = [];


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
	arrChartMain[0].length = 0;
	arrChart[0].length = 0;
}



function myChart(label, backColor, borderColor){
	
 arrChartMain[0][ret] = new Array();
 arrChart[0][ret] = new Array();

/**
 * Заполнение данными массивов для построения диаграммы
 */
_.each(listFlow, function(list) {
	    arrChartMain[0][ret][i] = list.diffCountW == undefined ? list.diffGKalor : list.diffCountW;
		var str = list.numAcnt;
		arrChart[0][ret][i] = str;
		if(i == seriesLen){
			ret = makeCharts(ret, arrChart[0][ret], arrChartMain[0][ret], label, backColor, borderColor);//Каждые 40 измерений из таблицы данных
		    i = -1;
		    arrChartMain[0][ret] = new Array();
		    arrChart[0][ret] = new Array();
        }
		i++;
});
	   
/**
* Заполним оставшуюся часть серии баров 0-ми
* для сохранения ширины баров
*/
   if(i <= seriesLen && i != 0){
	   for(j=(++i); j <= seriesLen; j++){
		   arrChartMain[0][ret][j] = 0;
		   arrChart[0][ret][j] = "";
	   }
	   makeCharts(ret, arrChart[0][ret], arrChartMain[0][ret], label, backColor, borderColor);//Последнее измерение при i < 40
	   ret = 0;
	   i = 0;
   } 	
}


function makeCharts(idx, arrChart, arrChartMain, label, backColor, borderColor){
	addCanvas(idx);
	chartObj[idx] = addChart(idx, arrChart, arrChartMain, label, backColor, borderColor);
	return (++idx);
}


function addCanvas(idx){
	var div = document.getElementById("main_div");
	var canvas = document.createElement('canvas');
	canvas.setAttribute('id', 'myChart'+idx);
	canvas.setAttribute('width', '400');
	canvas.setAttribute('height', '100');
	div.appendChild(canvas);
}

function addChart(idx, arrayLabel, arrayData, label, backColor, borderColor){
var ctx = null;
var myChart = null;

ctx = document.getElementById('myChart'+idx).getContext('2d');
myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: arrayLabel,
        datasets: [{
	        minBarLength: 2,
            label: label,
            data: arrayData,
            backgroundColor: [
                backColor
            ],
            borderColor: [
                borderColor
            ],
            borderWidth: 1
        }]
    },
    options: {
	    
       scales: {
        	x: {
              },
            y: {
                beginAtZero: true,
                
            }
        }
    }
});
 return myChart;
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