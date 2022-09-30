
$(document).ready(function() {
	'use strict';

  /*---------------------------------------------
    Dashboard Sidenav
  ---------------------------------------------*/

	$('#dashboard-nav .dropdown').on('click', function (event) {
	  var clickover = $(event.target);
	  $(this).siblings('.dropdown').children('.dropdown-menu').slideUp();
	  $(this).children('.dropdown-menu').slideToggle();
	  // $('#dashboard-nav .dropdown').removeClass('show');
	  if($(this).hasClass('show')) {
	  	$(this).removeClass('show');
	  } else {
	  	$(this).addClass('show');
	  }
	});

	$("#dashboard-nav .dropdown > a ").on('click', function(e) {
		e.preventDefault();
	});

	/*---------------------------------------------
		chart.Js
	---------------------------------------------*/

  if ($("#view-chart").length > 0) {
    var ctx = document.getElementById("view-chart").getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: ["JAN", "MAR", "MAY", "JUL", "SEP", "NOV"],
        datasets: [{
          label: '# of Votes',
          data: [170, 100, 220, 150, 180, 360],
          backgroundColor: 'rgba(36, 109, 248, .2)',
          borderColor: '#07bfa7',
          borderWidth: 1
        }]
      },
      options: {
        title: {
          display: false
        },
        legend: {
          display: false
        },
        tooltips: {
          mode: 'index',
          intersect: true
        },
        responsive: true,
        scales: {
          xAxes: [{
            // gridLines: false,
            stacked: true,
          }],
          yAxes: [{
            // gridLines: false,
            stacked: true,
          }]
        }
      }
    });
  }

  /*---------------------------------------------
    Photo Upload
  ---------------------------------------------*/

  $('.upload-profile-photo .file-input').change(function(){
    var curElement = $(this).parent().parent().find('.image');
    var reader = new FileReader();

    reader.onload = function (e) {
      curElement.attr('src', e.target.result);
    };

    reader.readAsDataURL(this.files[0]);
  });

})