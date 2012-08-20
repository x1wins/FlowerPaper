/* Copyright (c) 2008 Kean Loong Tan http://www.gimiti.com/kltan
 * Licensed under the MIT (http://www.opensource.org/licenses/mit-license.php)
 * jFlow
 * Version: 1.2 (July 7, 2008)
 * Requires: jQuery 1.2+
 */
 
(function($) {

	$.fn.jFlow = function(options) {
		var opts = $.extend({}, $.fn.jFlow.defaults, options);
		var randNum = Math.floor(Math.random()*11);
		var jFC = opts.controller;
		var jFS =  opts.slideWrapper;
		var jSel = opts.selectedWrapper;

		var cur = 0;
		var maxi = $(jFC).length;
		// sliding function
		var slide = function (dur, i) {
			$(opts.slides).children().css({
				overflow:"hidden"
			});
			$(opts.slides + " iframe").hide().addClass("temp_hide");
			$(opts.slides).animate({
				marginLeft: "-" + (i * $(opts.slides).find(":first-child").width() + "px")}, 
				opts.duration*(dur),
				opts.easing,
				function(){
					$(opts.slides).children().css({
						overflow:"auto"
					});
					$(".temp_hide").show();
				}
			);
			
		}
		$(this).find(jFC).each(function(i){
			$(this).click(function(){
				if ($(opts.slides).is(":not(:animated)")) {
					$(jFC).removeClass(jSel);
					$(this).addClass(jSel);
					var dur = Math.abs(cur-i);
					slide(dur,i);
					cur = i;
				}
			});
		});	
		
		$(opts.slides).before('<div id="'+jFS.substring(1, jFS.length)+'"></div>').appendTo(jFS);
		
		$(opts.slides).find("div").each(function(){
			$(this).before('<div class="jFlowSlideContainer"></div>').appendTo($(this).prev());
		});
		
		//initialize the controller
		$(jFC).eq(cur).addClass(jSel);
		
		var resize = function (x){
			$(jFS).css({
				position:"relative",
				width: opts.width,
				height: opts.height,
				overflow: "hidden"
			});
			//opts.slides or #mySlides container
			$(opts.slides).css({
				position:"relative",
				width: $(jFS).width()*$(jFC).length+"px",
				height: $(jFS).height()+"px",
				overflow: "hidden"
			});
			// jFlowSlideContainer
			$(opts.slides).children().css({
				position:"relative",
				width: $(jFS).width()+"px",
				height: $(jFS).height()+"px",
				"float":"left",
				overflow:"auto"
			});
			
			$(opts.slides).css({
				marginLeft: "-" + (cur * $(opts.slides).find(":eq(0)").width() + "px")
			});
		}
		
		// sets initial size
		resize();

		// resets size
		$(window).resize(function(){
			resize();						  
		});
		
		$(opts.prev).click(function(){
			if ($(opts.slides).is(":not(:animated)")) {
				var dur = 1;
				if (cur > 0)
					cur--;
				else {
					cur = maxi -1;
					dur = cur;
				}
				$(jFC).removeClass(jSel);
				slide(dur,cur);
				$(jFC).eq(cur).addClass(jSel);
			}
		});
		
		$(opts.next).click(function(){
			if ($(opts.slides).is(":not(:animated)")) {
				var dur = 1;
				if (cur < maxi - 1)
					cur++;
				else {
					cur = 0;
					dur = maxi -1;
				}
				$(jFC).removeClass(jSel);
				slide(dur, cur);
				$(jFC).eq(cur).addClass(jSel);
			}
		});
	};
	
	$.fn.jFlow.defaults = {
		controller: ".jFlowControl", // must be class, use . sign
		slideWrapper : "#jFlowSlide", // must be id, use # sign
		selectedWrapper: "jFlowSelected",  // just pure text, no sign
		easing: "swing",
		duration: 400,
		width: "100%",
		prev: ".jFlowPrev", // must be class, use . sign
		next: ".jFlowNext" // must be class, use . sign
	};
	
})(jQuery);
