/**
 * Created by liuchen on 2018/5/11.
 */
;(function($){
    $.fn.notice_alert = function(opts){
        $(this).empty();

        var defaults = {
            notice_class: 'alert-warning',
            head: '信息',
            msg : 'info',
            close_event:null,
            closed_event:null
        };

        var settings = $.extend({},defaults,opts);

        var _box = '<div class=\'alert '+settings.notice_class+' alert-dismissable\'>';
        _box += '<button type=\'button\' class=\'close\' data-dismiss=\'alert\' aria-label=\'Close\'>&times;</button>';
        _box += '<strong>' + settings.head + '：</strong><span>' + settings.msg + '</span>';
        _box += '</div>';
        $(this).append(_box);

        if(settings.close_event != null){
            $(this).find('div[class=\'alert '+settings.notice_class+'\']').bind('close.bs.alert',settings.close_event);
        }

        if(settings.closed_event != null){
            $(this).find('div[class=\'alert '+settings.notice_class+'\']').bind('closed.bs.alert',settings.closed_event);
        }
    }
})(jQuery);