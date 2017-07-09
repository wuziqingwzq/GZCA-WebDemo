package gzca.test;


import cn.com.syan.spark.app.sdk.classified.SparkClient;
import cn.com.syan.spark.app.sdk.classified.SparkClients;
import cn.com.syan.spark.app.sdk.classified.entity.Oid;
import cn.com.syan.spark.app.sdk.classified.entity.Response;
import cn.com.syan.spark.app.sdk.classified.entity.User;
import com.syan.netonej.exception.NetonejExcepption;
import com.syan.netonej.http.client.PCSClient;
import com.syan.netonej.http.client.SVSClient;
import com.syan.netonej.http.client.TSAClient;
import gzca.ca.GZCA;
import gzca.config.PropertiesConfig;

import java.io.IOException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuziqing on 2016-12-8.
 */
public class Test {
    public static void main(String arg[]) throws CertificateException, InvalidKeySpecException, IOException, SignatureException {
        SVSClient svsClient = new SVSClient();
        TSAClient tsaClient = new TSAClient();
        PCSClient pcsClient = new PCSClient();
        tsaClient.initClient("111.85.176.62", "443");
        pcsClient.initClient("111.85.176.62", "443");
        String cert = "MIIDXTCCAkWgAwIBAgIPBwAFIBQQMREAAAAAAHGYMA0GCSqGSIb3DQEBBQUAME8xCzAJBgNVBAYTAkNOMTEwLwYDVQQKDChHVUlaSE9VIEVMRUNUUk9OSUMgQ0VSVElGSUNBVEVTIENPLixMVEQuMQ0wCwYDVQQDDARHWkNBMB4XDTE0MTAzMDE2MDAwMFoXDTE5MTAzMDE2MDAwMFowdTELMAkGA1UEBhMCQ04xEjAQBgNVBAgMCei0teW3nuecgTESMBAGA1UEBwwJ6LS16Ziz5biCMSowKAYDVQQKDCHotLXlt57nnIHnlLXlrZDor4HkuabmnInpmZDlhazlj7gxEjAQBgNVBAMMCeWQtOWtkOa4hTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAn2pbx+UGZos35bnj4+ztXFcznluHews9x3iFsvXcKJ+6DYkC7bykm2CumIXscr7g28+F3tX3VAzCjFWumcU9M9GbW7Tvg8HBex/8ab61E0vWJo8gjBkQnBB60a5xbaJ2aIpSQQ6dApIc2Ain1mS/lwZhGRdkaoOnX24Zh6NWSosCAwEAAaOBkzCBkDAfBgNVHSMEGDAWgBTFU3ElMj8KAnNe2vTGVm153XVGYTAdBgNVHQ4EFgQUARIRFjKlS9okNLfGakgPL32qrnEwCwYDVR0PBAQDAgbAMCIGCCqBHNAUBAEBBBYxFIASNTIyMTAxMTk5MjA0MjEwNDEwMB0GBSpWFQEDBBQMEjAzOTExMjAxNDEwMzEzMTc3MDANBgkqhkiG9w0BAQUFAAOCAQEAEEv6DcZReMCZQsNUhKF/WgR9etHW7pP40jCNnST9vO6Ym3nMDVv00Qcd+bLQWlc/J6OFxJKZuZXS1ksveThydjxYbHPulD2+gAIpSMEhMHYlGSPKXcD42952Lmpwe4w7dktvWh686ucRvApM4UXCehZzJtnXIMuYAbz+HiGdk82Cp32yFZmQgnu2+GNTprJobwa7cMarFDb3kgUPUQJGMxWJUNWQ2ciUAlG8xmaPSQ/pspWlKJNKTqgWfswbjTcHiGXCIQPu786v6KYQ7MUJqiVssHIxkow1EV5CukMRRgYJAdakJyfUqLqOw+JQOcb95346T8CVOQqTnhwytuA3zA==";
        String data = "test value";
        String datab64 = "dGVzdCB2YWx1ZQ==";
        String signture = "Sn5tN94/MGBNRZASRE+Lfj2J725qQIhP9DBv7R6Kkl/2Eu2+cD9BMZ69HQgZVPLxENjf0ayGClI21mt0D7XVKcMsbWy3Z3DpOBjMMTCUnq+NJQHd2uVDXL802/rPDnmf++ARkRtLTYcBouTnm6lAdhDomJ3Q42VLUmChfu5AghI=";
        String tsaTimestamp = "MIIEoTADAgEAMIIEmAYKKoEcz1UGAQQCAqCCBIgwggSEAgEDMQ4wDAYIKoEcz1UBg3UFADCBzgYLKoZIhvcNAQkQAQSggb4EgbswgbgCAQEGBCoDBAEwITAJBgUrDgMCGgUABBTVbHU+D4zoS6PTqyhGKM9llP2qdAIJAJ9BJ0FHmXDRGA8yMDE3MDQyNzAxMTgwN1oCCQCdE05+iL/3+qBjpGEwXzELMAkGA1UEBhMCQ04xDzANBgNVBAgeBo01Xd53ATEPMA0GA1UEBx4GjTWWM14CMQ0wCwYDVQQKEwRHWkNBMQwwCgYDVQQLEwNKU0IxETAPBgNVBAMTCEdaQ0EtSlNCoIICHjCCAhowggHAoAMCAQICDQCnzXcRmRvKQrbzGXAwCgYIKoEcz1UBg3UwXzELMAkGA1UEBhMCQ04xDzANBgNVBAgeBo01Xd53ATEPMA0GA1UEBx4GjTWWM14CMQ0wCwYDVQQKEwRHWkNBMQwwCgYDVQQLEwNKU0IxETAPBgNVBAMTCEdaQ0EtSlNCMCIYDzIwMTcwNDIxMTYwMDAwWhgPMjAyNzA0MTkxNjAwMDBaMF8xCzAJBgNVBAYTAkNOMQ8wDQYDVQQIHgaNNV3edwExDzANBgNVBAceBo01ljNeAjENMAsGA1UEChMER1pDQTEMMAoGA1UECxMDSlNCMREwDwYDVQQDEwhHWkNBLUpTQjBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABOoUiMQkg2dV0mVrnvnygEGzULrv/AZXDY7GYBkIJqvnxyIdHsBP6b60SSGDVzzYvhJvficc4ka6QcVbn75N1eCjXTBbMAwGA1UdEwQFMAMBAf8wHQYDVR0OBBYEFO4mZxUiHxkHBTbIxfFY0cgut/LyMB8GA1UdIwQYMBaAFO4mZxUiHxkHBTbIxfFY0cgut/LyMAsGA1UdDwQEAwIBhjAKBggqgRzPVQGDdQNIADBFAiEAia0E6/pBXpVByu0hQ5IPqDK8VanCPngwhdfYTxqdD0sCIATbzX/OLXR5ddVnJBrprFWZOSjLpOoYzN4Glc7qufcnMYIBejCCAXYCAQEwcDBfMQswCQYDVQQGEwJDTjEPMA0GA1UECB4GjTVd3ncBMQ8wDQYDVQQHHgaNNZYzXgIxDTALBgNVBAoTBEdaQ0ExDDAKBgNVBAsTA0pTQjERMA8GA1UEAxMIR1pDQS1KU0ICDQCnzXcRmRvKQrbzGXAwDAYIKoEcz1UBg3UFAKCBmDAaBgkqhkiG9w0BCQMxDQYLKoZIhvcNAQkQAQQwHAYJKoZIhvcNAQkFMQ8XDTE3MDQyNzAxMTgwN1owKwYLKoZIhvcNAQkQAgwxHDAaMBgwFgQU+2ny0Ska2dAzGcd8bGsbWUlIAk8wLwYJKoZIhvcNAQkEMSIEIHFHIAEgHN8IXKKmLMO+VzVYikW8f9KpCziIuZkl98jnMA0GCSqBHM9VAYItAQUABEcwRQIgZplSs6cAq39qDzoBHOUI92Xzr5+gTLG8/Iia3g4JpwACIQCDkYsLahJCXBY3EEMpvxRuZCSWM7CQOVU77Ir8I/RSeA==";
        String type = "7";


        switch (type) {
            case "0": {
                try {
                    int result;
                    System.out.println(cert);
                    svsClient.initClient("111.85.176.62", "443");
                    result = svsClient.verifyCertificate(cert);
                    if (result == 200) {
                        System.out.println(result + ":验签成功");
                    } else {
                        System.out.println("验签失败");
                    }
                } catch (NetonejExcepption netonejExcepption) {
                    System.out.println("验签错误");
                    netonejExcepption.printStackTrace();
                }
                break;
            }
            case "1": {
                int result;
                try {
                    svsClient.initClient("111.85.176.62", "443");
                    result = svsClient.verifyPKCS1(data, signture, cert);
                    if (result == 200) {
                        System.out.println("验签成功：" + result);
                    } else {
                        System.out.println("验签失败：" + result);
                    }
                } catch (NetonejExcepption netonejExcepption) {
                    System.out.println("验签错误");
                    netonejExcepption.printStackTrace();
                }
                break;
            }
            case "2": {
                try {
                    String tsaResult = tsaClient.createTimestamp(data, "sha1");
                    if (tsaResult != null) {
                        System.out.println("时间戳请求成功：");
                        System.out.println(tsaResult);
                    } else {
                        System.out.println("时间戳请求失败");
                    }
                } catch (NetonejExcepption netonejExcepption) {
                    System.out.println("时间戳请求错误");
                    netonejExcepption.printStackTrace();
                }
                tsaClient.releaseClient();
                break;
            }
            case "3": {
                try {
                    System.out.println(tsaClient.verifyTimestamp(tsaTimestamp));
                } catch (NetonejExcepption netonejExcepption) {
                    System.out.println("验签错误");
                    netonejExcepption.printStackTrace();
                }
                tsaClient.releaseClient();
                break;
            }
            case "4": {
                try {
                    String certList[] = pcsClient.getPcsIds();
                    for (int i = 0; i < certList.length; i++) {
                        System.out.println(certList[i]);
                    }
                    String encryptData = pcsClient.pubKeyEncrypt(certList[0], "123456", "kid", data);
                    System.out.println(encryptData);
                    System.out.println(pcsClient.priKeyDecrypt(certList[0], "123456", "kid", encryptData));
                } catch (NetonejExcepption netonejExcepption) {
                    netonejExcepption.printStackTrace();
                }
                break;
            }
            case "5": {
                pushdata();
                break;
            }

            case "6": {
                String serialnumber="070005201702250100000000143456";
//                String
                GZCA ca = new GZCA("https://58.42.231.108:5001/","Test_W");
                System.out.println(ca.VerifyCertificate(serialnumber));
            }

            case "7": {
                PropertiesConfig pc = new PropertiesConfig();

//                System.out.println(pc.setConfig("gzcaverifyIP","58.42.231.108"));
                System.out.println("获取到"+"gzcaverifyIP"+"的值为："+pc.getConfig("gzcaverifyIP"));
            }
        }
    }

    public static void pushdata() throws CertificateException, InvalidKeySpecException, SignatureException, IOException {
        SparkClient sparkClient = SparkClients.getDefaultClient();
        String cert ="MIIDgDCCAmigAwIBAgIPBwAFIBYSMBEAAAAAElNYMA0GCSqGSIb3DQEBBQUAME8xCzAJBgNVBAYTAkNOMTEwLwYDVQQKDChHVUlaSE9VIEVMRUNUUk9OSUMgQ0VSVElGSUNBVEVTIENPLixMVEQuMQ0wCwYDVQQDDARHWkNBMB4XDTE2MTIyOTE2MDAwMFoXDTE3MTIyOTE2MDAwMFowgaExCzAJBgNVBAYTAkNOMRIwEAYDVQQIDAnotLXlt57nnIExEjAQBgNVBAcMCei0temYs+W4gjEqMCgGA1UECgwh6LS15bee55yB5rWL6K+V56eR5oqA5pyJ6ZmQ5YWs5Y+4MRIwEAYDVQQLDAnmtYvor5XkuroxKjAoBgNVBAMMIei0teW3nuecgea1i+ivleenkeaKgOaciemZkOWFrOWPuDCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAiIuTG4eZHloo1pPyH8o7gAau+N7jx2vwvs0YqanQ1+N1yHglHvAKQ3QpWkvzaVr94HNatmftrHPzrv+SumLpHke02As8+Y4VCDsT2wz6epNWpUbVf2fGsf3ojvOwa4wQVVdlq/EjMz7DZQ/M+h5/rgLFHP0yRaCWW+tf0twltm0CAwEAAaOBiTCBhjAfBgNVHSMEGDAWgBTFU3ElMj8KAnNe2vTGVm153XVGYTAdBgNVHQ4EFgQUW7ncepVAIMEVQlsSUjpK84OVzLIwCwYDVR0PBAQDAgbAMBgGCCqBHNAUBAEEBAwTCjEyMzQ0NDk4LTAwHQYFKlYVAQMEFAwSMDM5MjAyMDE2MTIzMDM3ODc4MA0GCSqGSIb3DQEBBQUAA4IBAQCHE8BXz7YMPdUu2S4KO088T9Q0JhkmqgGImoEO6d0nHPo7beJR1VHSCjDNYtVMekToCUnYyppERdAwp7NEmUDJNUV+88O/FqOA9D8izorF00G+2xdAU66L4U5p+ZW8wqd0y9czNWgpBODvMQFaGvEc6lBKXVU6OO+Q/dEKoINvu65OscpJwFTIGTvlKVh5ygXtEPdTMfNadWJ1KCC7nUmcshcI8y3ASvYkmPaqVGhSnT9cw79QYmbF3uv5CZJmzmLC9aL5HM5JP/93Wy3an9txLsJ/RXiVhfu5ynGfS7Xd/AUnMjinqvsvZFKVYLxroGmN3GF6NMVaLs85Qpljz83v";
        String userIdNo="123123123123";
        String userOid="test20170520";
        User user = new User();
        user.setCertificate(cert);
        user.setName("lkjfaldsjf");       //设置用户名称 或者单位名称     如果不设置 系统为默认使用证书CN项作为名称
        user.setIdno(userIdNo);               //证件号
        List<Oid> oidList = new ArrayList<Oid>();
        Oid oid = new Oid();
        oid.setOidMask("Test_Group_OID");             //组扩展 标识
        oid.setOidValue(userOid);           //组上扩展值
        oidList.add(oid);
        Response r = null;
        r = sparkClient.joinGroup(user, 1, oidList);
        System.out.println(sparkClient.queryGroup().getMsg());
        System.out.println(r.getMsg());
    }
}
