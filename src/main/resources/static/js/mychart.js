function myChart(){
	
/**
 * Заполнение данными массивов для построения диаграммы
 */
_.each(listFlow, function(list) {
		i++; 
		arrChartMain[i] = list.diffCountW;
		var str = list.numAcnt;
		arrChart[i] = str;
		if(i == seriesLen){
			ret = makeCharts(ret);//Каждые 40 измерений из таблицы данных
			arrChartMain = [];
			arrChart = [];
            i = 0;
		}
	   });
	   
/**
* Заполним оставшуюся часть серии баров 0-ми
* для сохранения ширины баров
*/
if(i <= seriesLen && i != 0){
	for(j=(++i); j <= seriesLen; j++){
		arrChartMain[j] = 0;
		arrChart[j] = "";
	}
	ret = makeCharts(ret);//Последнее измерение при i < 40
}	

}


function makeCharts(idx){
	addCanvas(idx);
	addChart(idx);
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

function addChart(idx){
const ctx = document.getElementById('myChart'+idx).getContext('2d');
const myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: arrChart,
        datasets: [{
            label: 'Расход холодной воды',
            data: arrChartMain,
            backgroundColor: [
                'rgba(1, 1, 255, 0.4)'
            ],
            borderColor: [
                'rgba(1, 1, 255, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
    	
        scales: {
        	x: {
                stacked: false
              },
            y: {
                beginAtZero: true,
                stacked: false
            }
        }
    }
});
}

/**
    * Вывод текущего времени в формате YYYY-MM-DD 
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