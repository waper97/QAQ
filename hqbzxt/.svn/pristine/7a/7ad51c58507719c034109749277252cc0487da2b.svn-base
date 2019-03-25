//时间选择器，依赖于jquery.easyui  
(function ($) {
    $.fn.extend({
        timepicker: function () {
            $(this).each(function () {
                var $this = $(this);
                $(this).click(function () {
                    if (!document.getElementById("time_container")) {
                        $("body").append(createDiv($this.val()));
                        $('#timeSlider_Hour,#timeSlider_Minute').slider();
                        $('#time_timespinner').timespinner({ onSpinUp: $.fn.timepicker.timepicker_showTime2, onSpinDown: $.fn.timepicker.timepicker_showTime2 });
                        $("#time_container table,#time_container table tr,#time_container table td").css({
                            "background-color": "transparent",
                            margin: '0px'
                        })
                    } else {
                        var HHmm = $this.val();
                        if (HHmm == '') HHmm = '00:00';
                        var HH = HHmm.split(':')[0] * 1;
                        var mm = HHmm.split(':')[1] * 1;
                        $('#time_timespinner').timespinner('setValue', HHmm);
                        $.fn.timepicker.timepicker_showTime2();
                    }
                    $('body').bind("mousedown", $.fn.timepicker.timepicker_onMousedown);
 
                    $("input#time_btnOK").unbind('click').click(function () {
                        $this.val($('#time_timespinner').timespinner('getValue')).change();
                        $("#time_container").hide();
                    });
                    var pos = getPosition($this);
                    $("#time_container").css({
                        position: "absolute",
                        "background-color": "#E3E3E3",
                        border: "1px solid #777777",
                        top: pos.top,   // $this.offset().top + $this.outerHeight(),
                        left: pos.left  //$this.offset().left
                    }).show();
                })
            });
 
            //私有函数,返回元素距离窗口各边距离
            function getDistance(obj) {
                if (!obj instanceof jQuery) {
                    obj = $(obj);
                }
                var distance = {};
                distance.top = (obj.offset().top - $(document).scrollTop());
                distance.bottom = ($(window).height() - distance.top - obj.outerHeight());
                distance.left = (obj.offset().left - $(document).scrollLeft());
                distance.right = ($(window).width() - distance.left - obj.outerWidth());
                return distance;
            }
 
            function getPosition(obj) {
                if (!obj instanceof jQuery) {
                    obj = $(obj);
                }
                var pos = {};
                var containerH = $('#time_container').outerHeight();
                var containerW = $('#time_container').outerWidth();
                var distance = getDistance(obj);
                if (distance.bottom < containerH && distance.top > containerH) {
                    pos.top = obj.offset().top - containerH;
                } else {
                    pos.top = obj.offset().top + obj.outerHeight();
                }
                if (distance.right < containerW && distance.left > containerW) {
                    pos.left = obj.offset().left - containerW + obj.outerWidth();
                } else {
                    pos.left = obj.offset().left;
                }
                return pos;
            }
            //私有函数，用于创建选择器html  
            function createDiv(HHmm) {
                if (HHmm == '') HHmm = '00:00';     //如果当前时间为空则取00:00  
                var HH = HHmm.split(':')[0] * 1;
                var mm = HHmm.split(':')[1] * 1;
 
                var sb = "<div  id=\"time_container\" style=\"font-size: 14px; z-index:9999; position: relative; width: 260px; height: 200px; background-color: bisque; border: 1px green solid; -moz-box-shadow: 4px 4px 3px rgba(20%,20%,40%,0.5); -webkit-box-shadow: 4px 4px 3px rgba(20%,20%,40%,0.5); box-shadow: 4px 4px 3px rgba(20%,20%,40%,0.5); -ms-filter: 'progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color=#000000)'; filter: progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#000000');\">";
                sb = sb + "        <iframe src=\"about:blank\" style=\"top: 0px; left: 0px; width: 100%; height: 100%; border: 0px;\"></iframe>";
                sb = sb + "        <div style=\"position: absolute; top: 0px; left: 0px;\">";
                sb = sb + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
                sb = sb + "                <tr style=\"height: 70px; line-height: 20px;\">";
                sb = sb + "                    <td style=\"width: 50px; text-align: center;\">小时:</td>";
                sb = sb + "                    <td>";
                sb = sb + "                        <input id=\"timeSlider_Hour\" value=\"" + HH + "\" class=\"easyui-slider\" style=\"width: 180px\" data-options=\"showTip:true,max:23,rule: [0,'|',6,'|',12,'|',18,'|',23],onChange:$.fn.timepicker.timepicker_showTime\" /></td>";
                sb = sb + "                </tr>";
                sb = sb + "                <tr style=\"height: 70px; line-height: 20px;\">";
                sb = sb + "                    <td style=\"width: 50px; text-align: center;\">分钟:</td>";
                sb = sb + "                    <td>";
                sb = sb + "                        <input id=\"timeSlider_Minute\" value=\"" + mm + "\" class=\"easyui-slider\" style=\"width: 180px\" data-options=\"showTip:true,max:59,rule: [0,'|',9,'|',19,'|',29,'|',39,'|',49,'|',59],onChange:$.fn.timepicker.timepicker_showTime\" /></td>";
                sb = sb + "                </tr>";
                sb = sb + "            </table>";
                sb = sb + "            <div style=\"width: 250px; text-align: right; margin: 10px; line-height: 30px;\">";
                sb = sb + "                <input value=\"" + HHmm + "\" style=\"width: 100px; font-size: 24px; height: 24px; line-height: 24px;\" id=\"time_timespinner\" readonly=\"readonly\" onchange=\"$.fn.timepicker.timepicker_showTime2();\" />";
                sb = sb + "                <input id=\"time_btnOK\" type=\"button\" value=\"确定\" style=\"margin-left: 20px; margin-right: 20px;\" />";
                sb = sb + "            </div>";
                sb = sb + "        </div>";
                sb = sb + "    </div>";
                return sb;
            }
            //公开函数，当小时或者分钟的Slider改变时更新timspinner  
            $.fn.timepicker.timepicker_showTime = function () {
                var h = parseInt($('#timeSlider_Hour').slider('getValue'));
                var m = parseInt($('#timeSlider_Minute').slider('getValue'));
                if (h < 10) h = '0' + h;
                if (m < 10) m = '0' + m;
                $('#time_timespinner').timespinner('setValue', h + ":" + m);
            }
            //公开函数，当单击文档时检测是否隐藏当前选择框  
            $.fn.timepicker.timepicker_onMousedown = function (event) {
                var target = event.target || event.srcElement;
                if (!(target.id == "time_container" || $(target).parents("#time_container").length > 0)) {
                    $("#time_container").hide();
                    $('body').unbind("mousedown", $.fn.timepicker.timepicker_onMousedown);
                }
            }
            //公开函数，当timespinner改变时设置小时和分钟Slider  
            $.fn.timepicker.timepicker_showTime2 = function () {
                var HHmm = $('#time_timespinner').timespinner('getValue');
                var HH = HHmm.split(':')[0] * 1;
                var mm = HHmm.split(':')[1] * 1;
                $('#timeSlider_Hour').slider('setValue', HH);
                $('#timeSlider_Minute').slider('setValue', mm);
            }
        }
    })
})(jQuery);  