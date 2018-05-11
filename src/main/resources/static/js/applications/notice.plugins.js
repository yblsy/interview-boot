/**
 * Created by liuchen on 2018/5/11.
 */
;(function($){
    $.fn.notice = function(opts){
        $(this).empty();

        var settings = _method.init(opts);
        var _model = _method._box(settings.id,settings.title,settings.msg);
        if(settings.isClose){
            var close = _method._close(settings.closeText,settings.closeEvent);
            $(_model).find('.modal-footer').append(close);
        }

        if(settings.isRequire){
            var require = _method._button(settings.requireText,settings.requireEvent);
            $(_model).find('.modal-footer').append(require);
        }
        $(this).append(_model);
    }

    var _method = {
        init : function(options){
            return $.extend({},{
                id: 'notice',
                title: 'Notice Title',
                msg: 'Hello World',
                closeText: 'close',
                closeEvent:function(){},
                requireText: 'ok',
                requireEvent:function(){},
                isClose: true,
                isRequire: true
            },options);
        },
        _box : function(_id,_title,_msg){
            var box = '<div class=\'modal fade\' id=\''+ _id +'\' tabindex=\'-1\' role=\'dialog\' aria-labelledby=\'toolModel\' aria-hidden=\'true\'>';
            box += '    <div class=\'modal-dialog\'>';
            box += '        <div class=\'modal-content\'>';
            box += '            <div class=\'modal-header\'>';
            box += '                <h4 class=\'modal-title\' id=\'toolModel\'>'+_title+'</h4>';
            box += '            </div>';
            box += '            <div class=\'modal-body\'>';
            box += _msg;
            box += '            </div>';
            box += '            <div class=\'modal-footer\'>';
            box += '            </div>';
            box += '        </div>';
            box += '    </div>';
            box += '</div>';
            return box;
        },
        _close: function(close_text,close_event){
            var close = '<button type=\'button\' class=\'btn btn-default\' data-dismiss=\'modal\'>' + close_text + '</button>';
            $(close).on('click',close_event);
            return $(close);
        },
        _button: function(button_text,button_event){
            var btn = '<button type=\'button\' class=\'btn btn-primary\'>' + button_text + '</button>';
            $(btn).on('click',button_event);
            return $(btn);
        }
    }

}(jQuery));

