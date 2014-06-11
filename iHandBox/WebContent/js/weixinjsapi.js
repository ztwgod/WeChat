/**!
 * ΢�������������Javascript API�����ܰ�����
 *
 * 1������΢������Ȧ
 * 2�������΢�ź���
 * 3��������Ѷ΢��
 * 4������/��ʾ���ϽǵĲ˵����
 * 5������/��ʾ�ײ������������
 * 6����ȡ��ǰ������״̬
 * 7������΢�ſͻ��˵�ͼƬ�������
 * 8���رչ���ƽ̨Webҳ��
 *
 */
var WeixinApi = (function () {

    "use strict";

    /**
     * ����΢������Ȧ
     * @param       {Object}    data       ���������Ϣ
     * @p-config    {String}    appId      ����ƽ̨��appId������ſ��ã�
     * @p-config    {String}    imageUrl   ͼƬ��ַ
     * @p-config    {String}    link       ���ӵ�ַ
     * @p-config    {String}    desc       ����
     * @p-config    {String}    title      ����ı���
     *
     * @param       {Object}    callbacks  ��ػص�����
     * @p-config    {Boolean}   async                   ready�����Ƿ���Ҫ�첽ִ�У�Ĭ��false
     * @p-config    {Function}  ready(argv)             ����״̬
     * @p-config    {Function}  dataLoaded(data)        ���ݼ�����ɺ���ã�asyncΪtrueʱ���ã�Ҳ����Ϊ��
     * @p-config    {Function}  cancel(resp)    ȡ��
     * @p-config    {Function}  fail(resp)      ʧ��
     * @p-config    {Function}  confirm(resp)   �ɹ�
     * @p-config    {Function}  all(resp)       ���۳ɹ�ʧ�ܶ���ִ�еĻص�
     */
    function weixinShareTimeline(data, callbacks) {
        callbacks = callbacks || {};
        var shareTimeline = function (theData) {
            WeixinJSBridge.invoke('shareTimeline', {
                "appid":theData.appId ? theData.appId : '',
                "img_url":theData.imgUrl,
                "link":theData.link,
                "desc":theData.title,
                "title":theData.desc, // ע������Ҫ�����ȥ��������desc
                "img_width":"120",
                "img_height":"120"
            }, function (resp) {
                switch (resp.err_msg) {
                    // share_timeline:cancel �û�ȡ��
                    case 'share_timeline:cancel':
                        callbacks.cancel && callbacks.cancel(resp);
                        break;
                    // share_timeline:fail������ʧ��
                    case 'share_timeline:fail':
                        callbacks.fail && callbacks.fail(resp);
                        break;
                    // share_timeline:confirm ���ͳɹ�
                    case 'share_timeline:confirm':
                    case 'share_timeline:ok':
                        callbacks.confirm && callbacks.confirm(resp);
                        break;
                }
                // ���۳ɹ�ʧ�ܶ���ִ�еĻص�
                callbacks.all && callbacks.all(resp);
            });
        };
        WeixinJSBridge.on('menu:share:timeline', function (argv) {
            if (callbacks.async && callbacks.ready) {
                window["_wx_loadedCb_"] = callbacks.dataLoaded || new Function();
                if(window["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_") > 0) {
                    window["_wx_loadedCb_"] = new Function();
                }
                callbacks.dataLoaded = function (newData) {
                    window["_wx_loadedCb_"](newData);
                    shareTimeline(newData);
                };
                // Ȼ�����
                callbacks.ready && callbacks.ready(argv);
            } else {
                // ����״̬
                callbacks.ready && callbacks.ready(argv);
                shareTimeline(data);
            }
        });
    }

    /**
     * ���͸�΢���ϵĺ���
     * @param       {Object}    data       ���������Ϣ
     * @p-config    {String}    appId      ����ƽ̨��appId������ſ��ã�
     * @p-config    {String}    imageUrl   ͼƬ��ַ
     * @p-config    {String}    link       ���ӵ�ַ
     * @p-config    {String}    desc       ����
     * @p-config    {String}    title      ����ı���
     *
     * @param       {Object}    callbacks  ��ػص�����
     * @p-config    {Boolean}   async                   ready�����Ƿ���Ҫ�첽ִ�У�Ĭ��false
     * @p-config    {Function}  ready(argv)             ����״̬
     * @p-config    {Function}  dataLoaded(data)        ���ݼ�����ɺ���ã�asyncΪtrueʱ���ã�Ҳ����Ϊ��
     * @p-config    {Function}  cancel(resp)    ȡ��
     * @p-config    {Function}  fail(resp)      ʧ��
     * @p-config    {Function}  confirm(resp)   �ɹ�
     * @p-config    {Function}  all(resp)       ���۳ɹ�ʧ�ܶ���ִ�еĻص�
     */
    function weixinSendAppMessage(data, callbacks) {
        callbacks = callbacks || {};
        var sendAppMessage = function (theData) {
            WeixinJSBridge.invoke('sendAppMessage', {
                "appid":theData.appId ? theData.appId : '',
                "img_url":theData.imgUrl,
                "link":theData.link,
                "desc":theData.desc,
                "title":theData.title,
                "img_width":"120",
                "img_height":"120"
            }, function (resp) {
                switch (resp.err_msg) {
                    // send_app_msg:cancel �û�ȡ��
                    case 'send_app_msg:cancel':
                        callbacks.cancel && callbacks.cancel(resp);
                        break;
                    // send_app_msg:fail������ʧ��
                    case 'send_app_msg:fail':
                        callbacks.fail && callbacks.fail(resp);
                        break;
                    // send_app_msg:confirm ���ͳɹ�
                    case 'send_app_msg:confirm':
                    case 'send_app_msg:ok':
                        callbacks.confirm && callbacks.confirm(resp);
                        break;
                }
                // ���۳ɹ�ʧ�ܶ���ִ�еĻص�
                callbacks.all && callbacks.all(resp);
            });
        };
        WeixinJSBridge.on('menu:share:appmessage', function (argv) {
            if (callbacks.async && callbacks.ready) {
                window["_wx_loadedCb_"] = callbacks.dataLoaded || new Function();
                if(window["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_") > 0) {
                    window["_wx_loadedCb_"] = new Function();
                }
                callbacks.dataLoaded = function (newData) {
                    window["_wx_loadedCb_"](newData);
                    sendAppMessage(newData);
                };
                // Ȼ�����
                callbacks.ready && callbacks.ready(argv);
            } else {
                // ����״̬
                callbacks.ready && callbacks.ready(argv);
                sendAppMessage(data);
            }
        });
    }

    /**
     * ������Ѷ΢��
     * @param       {Object}    data       ���������Ϣ
     * @p-config    {String}    imageUrl   ͼƬ��ַ
     * @p-config    {String}    link       ���ӵ�ַ
     * @p-config    {String}    desc       ����
     * @p-config    {String}    title      ����ı���
     *
     * @param       {Object}    callbacks  ��ػص�����
     * @p-config    {Boolean}   async                   ready�����Ƿ���Ҫ�첽ִ�У�Ĭ��false
     * @p-config    {Function}  ready(argv)             ����״̬
     * @p-config    {Function}  dataLoaded(data)        ���ݼ�����ɺ���ã�asyncΪtrueʱ���ã�Ҳ����Ϊ��
     * @p-config    {Function}  cancel(resp)    ȡ��
     * @p-config    {Function}  fail(resp)      ʧ��
     * @p-config    {Function}  confirm(resp)   �ɹ�
     * @p-config    {Function}  all(resp)       ���۳ɹ�ʧ�ܶ���ִ�еĻص�
     */
    function weixinShareWeibo(data, callbacks) {
        callbacks = callbacks || {};
        var shareWeibo = function (theData) {
            WeixinJSBridge.invoke('shareWeibo', {
                "content":theData.desc,
                "link":theData.link,
                "img_url":theData.imgUrl,
                "title":theData.title,
                "img_width":"120",
                "img_height":"120"
            }, function (resp) {
                switch (resp.err_msg) {
                    // share_weibo:cancel �û�ȡ��
                    case 'share_weibo:cancel':
                        callbacks.cancel && callbacks.cancel(resp);
                        break;
                    // share_weibo:fail������ʧ��
                    case 'share_weibo:fail':
                        callbacks.fail && callbacks.fail(resp);
                        break;
                    // share_weibo:confirm ���ͳɹ�
                    case 'share_weibo:confirm':
                    case 'share_weibo:ok':
                        callbacks.confirm && callbacks.confirm(resp);
                        break;
                }
                // ���۳ɹ�ʧ�ܶ���ִ�еĻص�
                callbacks.all && callbacks.all(resp);
            });
        };
        WeixinJSBridge.on('menu:share:weibo', function (argv) {
            if (callbacks.async && callbacks.ready) {
                window["_wx_loadedCb_"] = callbacks.dataLoaded || new Function();
                if(window["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_") > 0) {
                    window["_wx_loadedCb_"] = new Function();
                }
                callbacks.dataLoaded = function (newData) {
                    window["_wx_loadedCb_"](newData);
                    shareWeibo(newData);
                };
                // Ȼ�����
                callbacks.ready && callbacks.ready(argv);
            } else {
                // ����״̬
                callbacks.ready && callbacks.ready(argv);
                shareWeibo(data);
            }
        });
    }

    /**
     * ����΢��Native��ͼƬ���������
     * �������Բ�������ǿ��⣬����������Ϸ���ֱ�ӻᵼ��΢�ſͻ���crash
     *
     * @param {String} curSrc ��ǰ���ŵ�ͼƬ��ַ
     * @param {Array} srcList ͼƬ��ַ�б�
     */
    function imagePreview(curSrc,srcList) {
        if(!curSrc || !srcList || srcList.length == 0) {
            return;
        }
        WeixinJSBridge.invoke('imagePreview', {
            'current' : curSrc,
            'urls' : srcList
        });
    }

    /**
     * ��ʾ��ҳ���Ͻǵİ�ť
     */
    function showOptionMenu() {
        WeixinJSBridge.call('showOptionMenu');
    }


    /**
     * ������ҳ���Ͻǵİ�ť
     */
    function hideOptionMenu() {
        WeixinJSBridge.call('hideOptionMenu');
    }

    /**
     * ��ʾ�ײ�������
     */
    function showToolbar() {
        WeixinJSBridge.call('showToolbar');
    }

    /**
     * ���صײ�������
     */
    function hideToolbar() {
        WeixinJSBridge.call('hideToolbar');
    }

    /**
     * �������¼������ͣ�
     *
     * network_type:wifi     wifi����
     * network_type:edge     ��wifi,����3G/2G
     * network_type:fail     ����Ͽ�����
     * network_type:wwan     2g����3g
     *
     * ʹ�÷�����
     * WeixinApi.getNetworkType(function(networkType){
     *
     * });
     *
     * @param callback
     */
    function getNetworkType(callback) {
        if (callback && typeof callback == 'function') {
            WeixinJSBridge.invoke('getNetworkType', {}, function (e) {
                // �������õ�e.err_msg��������Ͱ��������е���������
                callback(e.err_msg);
            });
        }
    }

    /**
     * �رյ�ǰ΢�Ź���ƽ̨ҳ��
     */
    function closeWindow() {
        WeixinJSBridge.call("closeWindow");
    }

    /**
     * ��ҳ�������Ϻ�ִ�У�ʹ�÷�����
     * WeixinApi.ready(function(Api){
     *     // ������ֻ��Api����WeixinApi
     * });
     * @param readyCallback
     */
    function wxJsBridgeReady(readyCallback) {
        if (readyCallback && typeof readyCallback == 'function') {
            var Api = this;
            var wxReadyFunc = function () {
                readyCallback(Api);
            };
            if (typeof window.WeixinJSBridge == "undefined"){
                if (document.addEventListener) {
                    document.addEventListener('WeixinJSBridgeReady', wxReadyFunc, false);
                } else if (document.attachEvent) {
                    document.attachEvent('WeixinJSBridgeReady', wxReadyFunc);
                    document.attachEvent('onWeixinJSBridgeReady', wxReadyFunc);
                }
            }else{
                wxReadyFunc();
            }
        }
    }

    return {
        version         :"1.6",
        ready           :wxJsBridgeReady,
        shareToTimeline :weixinShareTimeline,
        shareToWeibo    :weixinShareWeibo,
        shareToFriend   :weixinSendAppMessage,
        showOptionMenu  :showOptionMenu,
        hideOptionMenu  :hideOptionMenu,
        showToolbar     :showToolbar,
        hideToolbar     :hideToolbar,
        getNetworkType  :getNetworkType,
        imagePreview    :imagePreview,
        closeWindow     :closeWindow
    };
})();