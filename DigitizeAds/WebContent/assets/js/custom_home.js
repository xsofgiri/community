
$(document).ready(function() {
	'use strict';

    /*-----------------------------------
    Navbar
    -----------------------------------*/
    $('.dropdown-menu a.dropdown-toggle').on('click', function(e) {
      var $el = $(this);
      var $parent = $(this).offsetParent(".dropdown-menu");
      if (!$(this).next().hasClass('show')) {
        $(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
      }
      var $subMenu = $(this).next(".dropdown-menu");
      $subMenu.toggleClass('show');
      
      $(this).parent("li").toggleClass('show');

      $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function (e) {
        $('.dropdown-menu .show').removeClass("show");
      });
      
      if (!$parent.parent().hasClass('navbar-nav')) {
        $el.next().css({"top": $el[0].offsetTop, "left": $parent.outerWidth() - 4});
      }

      return false;
    });

    if($(window).width() < 1200) {
      $(document).on('click', function(event) {
        var clickover = $(event.target);
        var _opened = $('#navbarSupportedContent').hasClass('show');
        if(_opened === true && !(clickover.is('.navbar-nav li, .navbar-nav .dropdown *, .listing-browse-dropdown, .listing-browse-dropdown .listing-browse-dropdown-toggle, .listing-browse-dropdown .dropdown-menu, .listing-browse-dropdown .dropdown-menu .browse-search, .listing-browse-dropdown *'))) {
          $('button.navbar-toggler').trigger('click');
        }
      });
    }

    /*--------------------------------------------
    Navbar Search Button
    --------------------------------------------*/

    $('.nav-search-toggle').on('click', function(e) {
      e.preventDefault();
      $('.nav-search form').slideToggle();
    })

    /*--------------------------------------------
    Bookmark
    --------------------------------------------*/

    $('.listing-browse-dropdown-toggle').on('click', function() {
      $('.listing-browse-dropdown .dropdown-menu').slideToggle();
    })

    /*--------------------------------------------
    Bookmark
    --------------------------------------------*/

    $('.lrn-listing-wrap .favourite').on('click', function(e) {
      e.preventDefault();
      $(this).toggleClass('active');
    })

    /*--------------------------------------------
    Listing Status
    --------------------------------------------*/

    $('.listing-filter-block .status a').on('click', function(e) {
      e.preventDefault();
      $(this).toggleClass('active');
    })

    /*--------------------------------------------
    Share Listing
    --------------------------------------------*/

    $('.listing-share-button').on('click', function(e) {
      e.preventDefault();
      $(this).siblings('.share-icons').fadeToggle();
    })

    /*--------------------------------------------
    Listing Search Filter
    --------------------------------------------*/

    $('.collapse-button').on('click', function(e) {
      e.preventDefault();
      $('.listing-filter-block').slideToggle();
    })

    /*--------------------------------------------
    Listing View Controller
    --------------------------------------------*/

    $('.search-result-view-control .view-change').on('click', function(e) {
      e.preventDefault();
      $('.search-result-view-control .view-change').removeClass('active');
      $(this).addClass('active');

      if($('.search-result-view-control .grid-view').hasClass('active')) {
        
        $('.listing-result-block .row .result-item').removeClass('col-12').addClass('col-md-6');
        $('.result-item .lrn-listing-wrap').removeClass('listing-list');

        $('.listing-result-block .row .map-top-result-item').removeClass('col-12').addClass('col-lg-4 col-md-6');
        $('.map-top-result-item .lrn-listing-wrap').removeClass('listing-list');

      } else if($('.search-result-view-control .list-view').hasClass('active')){

        $('.listing-result-block .row .result-item').removeClass('col-md-6').addClass('col-12');
        $('.result-item .lrn-listing-wrap').addClass('listing-list');

        $('.listing-result-block .row .map-top-result-item').removeClass('col-lg-4 col-md-6').addClass('col-12');
        $('.map-top-result-item .lrn-listing-wrap').addClass('listing-list');
      }
    })

    /*--------------------------------------------
    FAQ Generator
    --------------------------------------------*/

    if($('#tab-generator').length > 0) {
      var navTab = $('#nav-tab');
      var navTabContent = $('#nav-tabContent');
      var countTab = $('#nav-tab').length;

      $('#tab-generator').on('click', '#addnew-tab', function(e){
        e.preventDefault();
        countTab++;
        var tab = '<a class="nav-item nav-link" id="tab_' + countTab + '" data-toggle="tab" href="#nav_' + countTab + '" role="tab" aria-controls="nav-home" aria-selected="true">FAQ ' + countTab + '</a>';
        var content = '<div class="tab-pane fade" id="nav_' + countTab + '" role="tabpanel">';
            content +='<div class="form-group"><input name="faq_title[]" type="text" class="form-control" placeholder="Question" aria-required="true"></div>';
            content += '<div class="form-group"><textarea name="faq_content[]"  rows="4" cols="25" placeholder="Answer" class="form-control"></textarea></div>';
        content +'</div>';

        // insert tab
        navTab.append(tab); 
        navTabContent.append(content);
        $('#tab_'+ countTab).click();
      });
    }


    /*--------------------------------------------
    Image Uploader
    --------------------------------------------*/    

    $('div').on('click', '.closeDiv', function () {
      $(this).parents('.divThumbnail').remove();
      $('#upload-input').val("");
    });

    var fileInput = document.getElementById("upload-input");

    if(fileInput) {
      fileInput.addEventListener("change", function (e) {
        var filesVAR = this.files;
        showThumbnail(filesVAR);
      }, false);
    }
    

    function showThumbnail(files) {
      var file = files[0]
      var $thumbnail = $('#thumbnail').get(0);

      var $image = $("<img>", {
          class: "imgThumbnail img-fluid"
      });
      var $pDiv = $("<div>", {
          class: "divThumbnail"
      });
      var $div = $("<div>", {
          class: "closeDiv"
      }).html('<span class="ti-close"></span>');

      $pDiv.append($image, $div).appendTo($thumbnail);
      var reader = new FileReader()
      reader.onload = function (e) {
          $image[0].src = e.target.result;
      }
      var ret = reader.readAsDataURL(file);
      var canvas = document.createElement("canvas");
      $image.on('load', function () {
          // ctx.drawImage($image[0], 100, 100);
      })
    }

    /*--------------------------------------------
    Add Socail Field
    --------------------------------------------*/

    $(document).on('click', '.social-network-block .delete', function() {
      $(this).parents('.form-group').remove();
    })

    $('.social-networks .add-field-button').on('click', function(e) {
      e.preventDefault();

      var formGroup = document.createElement('div');
      formGroup.setAttribute('class', 'form-group');
      var slct = '<select class="form-control add-social-link"><option value="">Select Network</option><option value="">Facebook</option><option value="">Twitter</option><option value="">Linkedin</option><option value="">Google Plus</option><option value="">Instagram</option><option value="">Pinterest</option><option value="">Reddit</option></select>';
      var npt = '<input type="text" placeholder="Enter Link" class="form-control social-link-input">';
      var dlt = '<div class="delete"><i class="far fa-trash-alt"></i></div>';

      var cln = slct + npt + dlt;

      $(formGroup).append(cln);
      $('.social-network-block').append(formGroup);
      $(".social-network-block .add-social-link").select2({
        theme: "bootstrap"
      });
    })

    
   
    /*-----------------------------------
    CountTo 
    -----------------------------------*/
    function animateCountTo(ct) {
      if ($.fn.visible && $(ct).visible() && !$(ct).hasClass('animated')) {
        $(ct).countTo({
          speed: 1000,
          refreshInterval: 1
        });
        $(ct).addClass('animated');
      }
    }

    function initCountTo() {
      var counter = $('.count');
      counter.each(function () {
        animateCountTo(this);
      });
    }

    initCountTo();

    /*--------------------------------------------
    Person Control
    --------------------------------------------*/

    var personAmount = $('.person-amount').html();

    $('.control.plus').on('click', function() {
      personAmount++;
      $('.person-amount').html(personAmount);
      $('.control.minus').css({
        cursor: 'pointer'
      })
    })

    $('.control.minus').on('click', function() {
      personAmount--;
      $('.person-amount').html(personAmount);
      if(personAmount <= 0) {
        personAmount = 0;
        $('.person-amount').html(personAmount);
        $('.control.minus').css({
          cursor: 'not-allowed' 
        })
      }
    })


		/*--------------------------------------------
		Owl Carousel
		--------------------------------------------
    $('.header-category-slider-wrap').owlCarousel({
      autoplay: 2000,
      items:12,
      responsive:{
       
        480:{
          items:1
        },
        768:{ 
          items:6
        },
        992: {
          items:9
        },
        1200:{
          items:12
        }
      }
    })
 
   
   

    
    /*-----------------------------------
    Contact Form
    -----------------------------------*/
    // Function for email address validation
    function isValidEmail(emailAddress) {
        var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);

        return pattern.test(emailAddress);

    }
    $("#contactForm").on('submit', function (e) {
        e.preventDefault();
        var data = {
            name: $("#name").val(),
            email: $("#email").val(),
            phone: $("#phone").val(),
            subject: $("#subject").val(),
            message: $("#message").val()
        };

        if (isValidEmail(data['email']) && (data['message'].length > 1) && (data['name'].length > 1) && (data['phone'].length > 1)) {
          $.ajax({
            type: "POST",
            url: "sendmail.php",
            data: data,
            success: function () {
              $('#contactForm .input-success').delay(100).fadeIn(1000);
              $('#contactForm .input-error').fadeOut(100);
            }
          });
        } else {
          $('#contactForm .input-error').delay(100).fadeIn(1000);
          $('#contactForm .input-success').fadeOut(100);

        }
        return false;
    });

    /*-----------------------------------
    Back to Top
    -----------------------------------*/
    $('.backtotop').on('click', function() {
      $("html, body").animate({
        scrollTop: 0
      }, 600);
      return false;
    })

    /*-------------------------------------
    Window Scroll
    -------------------------------------*/
    $(window).on('scroll', function () {
      initCountTo();
    });

    $(window).on('resize orientationchange', function () {
      selectFun();
    });

})

$(window).on('load', function () {
    $(".preloader").delay(200).fadeOut();
});