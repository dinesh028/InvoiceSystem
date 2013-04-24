<!DOCTYPE html>
<!-- saved from url=(0051)http://jsfiddle.net/jonathansampson/VpDUG/170/show/ -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <script type="text/javascript" src="/InvoiceSystem/js/jquery.min.js"></script>
  <script type="text/javascript" src="/InvoiceSystem/js/jquery.mockjax.js"></script>
  <style type="text/css">
 
.modal {
    display:    none;
    position:   fixed;
    z-index:    1000;
    top:        0;
    left:       0;
    height:     100%;
    width:      100%;
    background: rgba( 255, 255, 255, .8 ) 
                url('http://localhost:8080/InvoiceSystem/images/i@i.jpg') 
                50% 50% 
                no-repeat;
}


body.loading {
    overflow: hidden;   
}

body.loading .modal {
    display: block;
}
  </style>
  


<script type="text/javascript">//<![CDATA[ 
$(window).load(function(){
$("body").on({
    // When ajaxStart is fired, add 'loading' to body class
    ajaxStart: function() { 
        $(this).addClass("loading"); 
    },
    // When ajaxStop is fired, rmeove 'loading' from body class
    ajaxStop: function() { 
        $(this).removeClass("loading"); 
    }    
});

// Initiates an AJAX request on click
$(document).on("click", function(){
    $.post("/mockjax");        
});


// http://code.appendto.com/plugins/jquery-mockjax
$.mockjax({ url: '/mockjax', responseTime: 2000 });

});//]]>  

</script>


</head>
<body class="">
  <h1>Click anywhere to start some AJAX</h1>
           
<p><strong>Pellentesque habitant morbi tristique</strong> senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. <em>Aenean ultricies mi vitae est.</em> Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, <code>commodo vitae</code>, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. <a href="http://jsfiddle.net/jonathansampson/VpDUG/170/show/#">Donec non enim</a> in turpis pulvinar facilisis. Ut felis.</p>

<h2>Header Level 2</h2>
           
<ol>
   <li>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</li>
   <li>Aliquam tincidunt mauris eu risus.</li>
</ol>

<blockquote><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue. Ut a est eget ligula molestie gravida. Curabitur massa. Donec eleifend, libero at sagittis mollis, tellus est malesuada tellus, at luctus turpis elit sit amet quam. Vivamus pretium ornare est.</p></blockquote>

<h3>Header Level 3</h3>

<ul>
   <li>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</li>
   <li>Aliquam tincidunt mauris eu risus.</li>
</ul>

<pre><code>
#header h1 a { 
    display: block; 
    width: 300px; 
    height: 80px; 
}
</code></pre>
<div class="modal"></div>
  





</body></html>