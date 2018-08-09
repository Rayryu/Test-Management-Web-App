/*<![CDATA[*/
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
	var reussisLabel = /*[[${Reussis}]]*/ '';
	var echouesLabel = /*[[${Echoues}]]*/ '';
	var bloquesLabel = /*[[${Bloques}]]*/ '';
  var data = google.visualization.arrayToDataTable([
	  
			  ['Task', 'Hours per Day'],
			  ['Réussis', reussisLabel],
			  ['Echoués', echouesLabel],
			  ['Bloqués', bloquesLabel]
			]);

  var options = {'title':'Status de la dernier execution des cas de test', 'width':550, 'height':400};

  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
}
/*]]>*/