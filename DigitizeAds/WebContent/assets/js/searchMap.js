$(document).ready(function () {
  'use strict';

  function liefletMapInIt() {
    if((document.getElementById('searchmap')) != null){
        var container = L.DomUtil.get('searchmap');
          if(container != null){
              container._leaflet_id = null;
          }
        L.HtmlIcon = L.Icon.extend({
          options: {

          },

          initialize: function (options) {
            L.Util.setOptions(this, options);
          },

          createIcon: function () {
            var div = document.createElement('div');
             div.innerHTML = this.options.html;
            if (div.classList)
              div.classList.add('leaflet-marker-icon');
            else
              div.className += ' ' + 'leaflet-marker-icon';
            return div;
          },

          createShadow: function () {
            return null;
          }
        });

        var tiles = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}', {
          attribution: 'Tiles &copy; Esri &mdash; Esri, DeLorme, NAVTEQ',
          maxZoom: 16
        }),
        latlng = L.latLng(40.716593, -74.0012097);



        var map = L.map('searchmap', {
          center: latlng,
          zoom: 14,
          scrollWheelZoom: false,
          layers: [tiles]
        });

        var markers = L.markerClusterGroup();
        var k = 1;
        for (var i = 0; i < addressPoints.length; i++) {
          var a = addressPoints[i];
            
          if(k == 20){
            k = 1;
          }

           var markerHTML = new L.HtmlIcon({
              html : "<img class='leaflet-marker-icon leaflet-zoom-animated leaflet-interactive' src='images/marker/"+k+".png' alt='markericon' />", 
          });

          markers.on('clusterclick', function() {
            k = 1;
            var markerHTML = new L.HtmlIcon({
                html : "<img class='leaflet-marker-icon leaflet-zoom-animated leaflet-interactive' src='images/marker/"+k+".png' alt='markericon' />", 
            });
          });


          var title = a[2];
          var marker = L.marker(new L.LatLng(a[0], a[1]), {icon: markerHTML});
          marker.bindPopup(title, {offset: new L.Point(0, -170)});
          markers.addLayer(marker);
          k++;
        }

        map.addLayer(markers);
    }
  }

  liefletMapInIt();


}); 