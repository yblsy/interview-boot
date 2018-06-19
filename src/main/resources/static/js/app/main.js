/**
 * Created by liuchen on 2018/6/19.
 */
$(document).ready(function() {
    $.ajax({
        url : '/class/queryClass',
        type: 'post',
        success : function (data) {
            $('#sideNav>li:gt(0)').remove();
            var htmlStr = sideMenuData(data.data);
            $('#sideNav').append(htmlStr);
        },
        complete:function(){
            //平铺body
            $('body').sprFlat({
                sidebar: {
                    fixed: true,//fixed sidebar
                    rememberToggle: true, //remember if sidebar is hided
                    offCanvas: false //make sidebar offcanvas in tablet and small screens
                },
                sideNav: {
                    hover: false, //shows subs on hover or click
                    showNotificationNumbers: 'onhover',//show how many elements menu have with notifcation style values - always, onhover, never
                    showArrows: true,//show arrow to indicate sub
                    sideNavArrowIcon: 'en-arrow-down5', //arrow icon for navigation
                    showIndicator: false,//show indicator when hover links
                    notificationColor: 'red', //green, red
                    subOpenSpeed: 300,//animation speed for open subs
                    subCloseSpeed: 400,//animation speed for close subs
                    animationEasing: 'linear',//animation easing
                    absoluteUrl: false, //put true if use absolute path links. example http://www.host.com/dashboard instead of /dashboard
                    subDir: '' //if you put template in sub dir you need to fill here. example '/html'
                },
                customScroll: {
                    color: '#999', //color of custom scroll
                    railColor: '#eee', //color of rail
                    size: '5px', //size in pixels
                    opacity: '0.5', //opacity
                    alwaysVisible: false //disable hide in
                }
            });
        }
    })

});

function sideMenuData(data){
    var htmlStr = '';
    $(data).each(function(index,value){
        htmlStr += '<li>';
        if(value.t.level == 1){
            htmlStr += '<a href=\'' + ((value.t.url == null || value.t.url == '' || value.t.url == undefined)?'#':value.t.url) + '\' >' + value.text;
        }else{
            htmlStr += '<a href=\'' + ((value.t.url == null || value.t.url == '' || value.t.url == undefined)?'#':value.t.url) + '\' style=\'padding-left:'+((value.t.level - 1) * 10)+'%\'>' + value.text;
        }

        if(value.t.icon != '' && value.t.icon != null){
            htmlStr += '<i class=\'' + value.t.icon + '\'></i>';
        }
        htmlStr += '</a>';

        if(value.nodes != null && value.nodes.length > 0){
            htmlStr += '<ul class=\'nav sub\'>';
            htmlStr += sideMenuData(value.nodes);
            htmlStr += '</ul>'
        }
        htmlStr += '</li>';
    });

    return htmlStr;
}