//测试函数
function test() {


}

//==========================================================================================================
//先安控件操作函数
var fingerprint;
//加载证书选择框
function loadCertificate(colx) {
    //colx.CF_Issuer_Contains="TESTCA";
    //colx.CF_Issuer_Contains = 'GZCA';
    var crtx = null;
    var crtxNumber = colx.Load();
    if (crtxNumber == 0) {
        alert("浏览器中没有发现数字证书！");
    } else if (crtxNumber == 1) {
        crtx = colx.GetAt(0);
    } else {
        crtx = colx.SelectCertificateDialog();
    }
    //crtx.DEBUG=1;
    fingerprint = crtx.ThumbprintSHA1;

    var serial = crtx.SerialNumberHex;
    $("#cert_sign_serial").val(serial);

    var subject = crtx.Subject;
    $("#cert_subject").val(subject);

    var certValue = crtx.Content;
    $("#cert_sign_value").val(certValue);
    return crtx;
}

//初始化证书
function selectCertificate(isSign) {
    var singleton = Singleton.getInstance();
    var netonex = singleton.getNetOneX();
    var colx = netonex.getCertificateCollectionX();
    if (isSign) {
        colx.CF_KeyUsage = 32;//通过密钥用法过滤证书，32表示只列出签名证书
    } else {
        colx.CF_KeyUsage = 16;//通过密钥用法过滤证书，32表示只列出签名证书
    }
    //colx.CF_KeyUsage=1;
    colx.CryptoInterface = 3;
    var crtx = loadCertificate(colx);
    crtx.Quiet = 1;
}

//PKCS1签名函数
function pkcs1(sign) {
    //初始化控件
    var singleton = Singleton.getInstance();
    var netonex = singleton.getNetOneX();
    var b64x = netonex.getBase64X();
    var colx = netonex.getCertificateCollectionX();
    //设置证书用法
    colx.CF_KeyUsage = 32;//通过密钥用法过滤证书，32表示只列出签名证书
    colx.CryptoInterface = 3;
    //判断是签名还是本地验签
    if (sign) {
        var crtx = loadCertificate(colx);
        crtx.Quiet = 1;
        //获取签名原文、和证书B64
        var data = $("#data").val();
        var certValue = crtx.Content;
        //获取签名值
        var signature = crtx.PKCS1String(data);
        //回显文本框中的数据
        $("#cert_sign_value").val(certValue);
        $("#signature").val(signature);
        $("#cert_sign_serial").val(crtx.SerialNumberHex);
        $("#cert_subject").val(crtx.Subject);
    } else {
        var certValue = $("#cert_sign_value").val();
        var signature = $("#signature").val();
        var data = $("#data").val();
        var datab64 = b64x.EncodeString(data);
        var crtx = colx.CreateCertificateBase64(certValue);
        if(crtx==""){
            alert("请先选择证书");
        }
        var result = crtx.PKCS1Verify(signature,datab64);
        if (result == 0) {
            alert("PKCS1签名验证成功！");
        } else {
            alert("PKCS1签名验证失败！");
        }
    }
}

//通过证书B64解析证书信息
function CreateCertByB64() {
    var singleton = Singleton.getInstance();
    var netonex = singleton.getNetOneX();
    var colx = netonex.getCertificateCollectionX();
    var content = $("#cert_sign_value").val();
    var crtx = colx.CreateCertificateBase64(content);
    $("#cert_sign_serial").val(crtx.SerialNumberHex);
    $("#cert_subject").val(crtx.Subject);
    $("#cert_sign_value").val(crtx.Content);
    $("#user_id").val(getUserID(crtx));
    $("#FriendlyName").val(getUserName(crtx));
    $("#cert_NotBeforeSystemTime").val(gettime(crtx.NotBeforeSystemTime));
    $("#cert_NotAfterSystemTime").val(gettime(crtx.NotAfterSystemTime));
    return crtx;
}

//通过文件进行解析证书信息,先安控件1.3.9以上可以使用
function CreateCertByFile() {
    var singleton = Singleton.getInstance();
    var netonex = singleton.getNetOneX();
    var colx = netonex.getCertificateCollectionX();
    var crtx = colx.CreateCertificateFile("","","");
    $("#cert_sign_serial").val(crtx.SerialNumberHex);
    $("#cert_subject").val(crtx.Subject);
    $("#cert_sign_value").val(crtx.Content);
    $("#user_id").val(getUserID(crtx));
    $("#FriendlyName").val(getUserName(crtx));
    $("#cert_NotBeforeSystemTime").val(gettime(crtx.NotBeforeSystemTime));
    $("#cert_NotAfterSystemTime").val(gettime(crtx.NotAfterSystemTime));
    return crtx;
}

function envseal() {
    
}

function envopen(){

}

//获取用户证书管理系统直接录入的时间
function gettime(timeString) {
    var time;
    var String = timeString.split(" ");
    time = String[0];
    time = time.replace(new RegExp("/","g"),"-")
    return time;
}

//获取SHA1摘要值
function doSHA1(str) {
    var singleton = Singleton.getInstance();
    var netonex = singleton.getNetOneX();
    var hashx = netonex.getHashX();
    var teststr = hashx.HashString(str);
    return(teststr);
}


//页面显示部分js=======================================================================================================
//页面加载初始化
$(document).ready(function () {
    $("#do-not-say-1").collapse('close');
});

//页面与后台交互Ajax函数
function sendVerify(url,parameter) {
    //原生AJAX传递参数，参考代码
    changemessage('连接中......请稍候',0);

    //创建xmlhttp对象，IE6以下创建ActiveXObject对象。
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //准备发送请求
    var URL = url;
    var METHOD = "POST";
    var type = "verifycertifacate";

    //发送请求
    xmlhttp.open(METHOD, URL);
    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
    xmlhttp.send(parameter);
    // xmlhttp.send("cert_sign_serial=1231231231231231");

    //Ajax状态改变函数
    xmlhttp.onreadystatechange = function () {
        if(xmlhttp.readyState !== 4){
            changemessage('连接中......请稍候',"");
        }
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            changemessage(xmlhttp.responseText,"success");
//                    document.getElementById("serverResult").innerHTML = xmlhttp.responseText;
        }
    }

}

function verifycert() {
    var type = "VerifyCertificate"
    var url = "/WebDemo/gzcaverifylogin";
    var param = "cert_sign_serial=" + $("#cert_sign_serial").val()
        + "&type=" + type;
    sendVerify(url,param);
}

function verifysign() {
    var type = "VerifySign";
    var url = "/WebDemo/gzcaverifylogin";
    var param = "cert_sign_serial=" + $("#cert_sign_serial").val()
            + "&data=" + $("#data").val()
            + "&signature=" + $("#signature").val()
            + "&type=" + type;
    // param = encodeURIComponent(param);
    sendVerify(url,param);
}

function changemessage(message,type) {
    $("#serverResult").show();
    $("#serverResult p").empty();
    $("#serverResult p").append('<i class="am-icon-spinner am-icon-spin"></i>');
    $("#serverResult p").append(message);
    if(type=="success"){
        $("#serverResult").addClass("am-alert-success");
        $("#serverResult").show();
        $("#serverResult p").empty();
        $("#serverResult p").append(message);
    }
}

function closealert() {
    $("#serverResult").toggle();
}


//Jquery 形式的Ajax代码
//        $(document).ready(function () {
//            $("#verifyCert").click(function () {
//                var str_cert_sign_serial = $("#cert_sign_serial").val();
//                $.post(
//                        "/WebDemo/gzcaverifylogin",
//                        {cert_sign_serial: str_cert_sign_serial},
//                        function (data, status, xhr) {
//                            alert("数据：" + data + "\n状态：" + status + "\n连接状态：");
//                            if (status == "success")
//                                alert("外部内容加载成功！" + xhr.status + ": " + xhr.statusText);
//                            if (status == "error")
//                                alert("Error: " + xhr.status + ": " + xhr.statusText);
//                        }
//                );
//            });
//        });

/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 *以下内容为GZCA张吉权增加
 *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

//获取证件类型
function getDocumentType(crtx) {
    if (crtx.Issuer.indexOf("CN=ZJCA", 0) >= 0) {
        //浙江CA证书"
        return "机构代码/身份证号";//机构代码/身份证号
    } else if (crtx.Issuer.indexOf("CN=GZCA", 0) >= 0) {
        //组织机构:1.2.156.10260.4.1.4  个人:1.2.156.10260.4.1.1
        var oid = ['1.2.156.10260.4.1.4', '1.2.156.10260.4.1.1'];
        var d;
        for (var i in oid) {
            d = crtx.GetExtensionString(oid[i], 0);
            if (d) {
                if (oid[i] == '1.2.156.10260.4.1.4') {
                    return "机构代码";//机构代码
                } else if (oid[i] == '1.2.156.10260.4.1.1') {
                    return "身份证号";//身份证号
                }
            }
        }
    } else {
        return "";
    }
}

//-------------------------------------------------
//获取用户名称（机构名称/姓名）
function getUserName(crtx) {
    if (crtx.Issuer.indexOf("CN=ZJCA", 0) >= 0) {
        //浙江CA证书
        return this.analysisDN(crtx.Subject, "G");
    } else if (crtx.Issuer.indexOf("CN=GZCA", 0) >= 0) {
        //GZCA证书
        return this.analysisDN(crtx.Subject, "CN");
    } else {
        return "";
    }
}


//-------------------------------------------------
//获取用户ID(组织机构代码/身份证号)
function getUserID(crtx) {
    if (crtx.Issuer.indexOf("CN=ZJCA", 0) >= 0) {
        //浙江CA证书
        return this.analysisDN(crtx.Subject, "OID.2.5.4.1");
    } else if (crtx.Issuer.indexOf("CN=GZCA", 0) >= 0) {
        //组织机构:1.2.156.10260.4.1.4  个人:1.2.156.10260.4.1.1
        var oid = ['1.2.156.10260.4.1.4', '1.2.156.10260.4.1.1'];
        var d;
        for (var i in oid) {
            d = crtx.GetExtensionString(oid[i], 0);
            if (d) {
                if (oid[i] == '1.2.156.10260.4.1.4') {
                    return this.hexToString(d, 2);
                } else if (oid[i] == '1.2.156.10260.4.1.1') {
                    return this.hexToString(d, 4);
                }
            }
        }
    } else {
        return "";
    }
}


//-------------------------------------------------
//获取信任服务号
function getTrustNO(crtx) {
    if (crtx.Issuer.indexOf("CN=GZCA", 0) >= 0) {
        var d = crtx.GetExtensionString('1.2.86.21.1.3', 0);
        if (d) {
            return this.hexToString(d, 2);
        }
    }
    return "";
}

/*-------------------------------------------------
 功能：解析证书的DN信息项，入口参数为所有DN信息及需要解析的具体项名称，返回对应项的值；
 入参：DN 为全部DN信息内容，DNname 为具体项名称，如CN、O、L等；
 出参：完成解析的具体信息项值；
 */
function analysisDN(DN, DNname) {
    var dnarr = DN.split(",");
    var dnvalue = "";
    DNname += "=";

    for (var i = 0; i < dnarr.length; i++) {
        if (dnarr[i].indexOf(DNname) >= 0) {
            //去掉左边的空格
            dnarr[i] = dnarr[i].replace(/(^\s*)|(\s*$)/g, "");
            dnvalue = dnarr[i].substring(DNname.length, dnarr[i].length);
            return dnvalue;
        }
    }
}


/*-------------------------------------------------
 功能：对解析出的证书扩展信息项数据进行处理，转换成实际数据内容，去掉内容中的类型定义和数据长度定义；
 入参：从CAPIcom中读取的扩展信息项
 出参：完成处理的实际数据内容；
 */
function hexToString(pStr, pType) {
    var arr = ("" + pStr).split(" ");
    var str = "";
    for (var j = pType; j < arr.length; j++) {
        str += String.fromCharCode(parseInt(arr[j].toUpperCase(), 16));
    }
    return str;
}


/*-------------------------------------------------
 功能：对解析出的证书扩展信息项数据进行处理，转换成实际数据内容，去掉内容中的类型定义和数据长度定义；
 入参：从CAPIcom中读取的扩展信息项
 出参：完成处理的实际数据内容；
 */
function getGZCAErr() {
    var ms = "未能成功加载数字证书，可能是如下原因：";
    ms += "\n1、证书介质USBKEY未插入电脑USB口;";
    ms += "\n2、未安装证书介质驱动（数字证书客户端）;";
    ms += "\n3、数字证书客户端未启动;";
    ms += "\n4、当前USB口故障，请换其他USB口;";
    ms += "\n5、插入的证书不是当前登录用户的证书。";
    ms += "\n\n如有疑问，请拨打贵州省电子证书有限公司客服电话：0851-86402317,4007000813。";
    return ms;
}
/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
 *以上内容为GZCA张吉权增加
 *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/