//封装好的ajax方法
var App={};
    App.Ajax = {
        _defConfig: {url: '', dataType: "json", async: true, isAutoTip: true},
        request: function (config) {
            config = $.extend({}, this._defConfig, config || {});
            var succFn = config.success;
            var failFn = config.error;
            var isAutoTip = config.isAutoTip;
            $.ajax({
                url: config.url,
                type: config.method || 'post',
                data: config.data || {},
                dataType: config.dataType,
                async: config.async,
                success: function (resp) {
                    if (resp.success == null)resp.success = true;
                    App.Ajax.succOper(resp, succFn, failFn, isAutoTip)
                },
                error: function (resp) {
                    if (resp.status == 200) {
                        App.Ajax.succOper(resp, succFn, failFn, isAutoTip)
                    } else {
                        App.Ajax.errorHandler(resp, this.url)
                    }
                }
            })
        }, succOper: function (resp, succFn, failFn, isAutoTip) {
            if (resp) {
                if (resp.success) {
                    if ($.isFunction(succFn)) {
                        succFn(resp)
                    }
                    App.Ajax.showTip(resp.msg, isAutoTip, true)
                } else {
                    if ($.isFunction(failFn)) {
                        failFn(resp)
                    } else {
                        isAutoTip = true
                    }
                    App.Ajax.showTip(resp.error || resp.msg, isAutoTip, false)
                }
            } else {
                App.Ajax.showTip('响应数据为空!!', true, false)
            }
        }, showTip: function (msg, isAutoTip, success) {
            if (isAutoTip == true) {
                alert(msg || (success ? '操作成功!' : '操作失败!'))
            }
        }, errorHandler: function (resp, url) {
            var status = resp.status;
            if (status == '0') {
                App.Ajax.showTip('无法与服务器建立连接', true, false)
            } else if (status == '1') {
                App.Ajax.showTip('向服务端发生请求失败', true, false)
            } else if (status == '404') {
                App.Ajax.showTip('url "' + url + '" 不存在', true, false)
            } else if (status == '408') {
                App.Ajax.showTip('服务器等候请求时发生超时', true, false)
            } else if (status == '405') {
                App.Ajax.showTip('请求不被允许', true, false)
            } else if (status == '500') {
                App.Ajax.showTip('服务器内部错误', true, false)
            } else if (status == '503') {
                App.Ajax.showTip('服务不可用', true, false)
            } else {
                App.Ajax.showTip('请求失败, 状态代码 : ' + status, true, false)
            }
        }
    }
