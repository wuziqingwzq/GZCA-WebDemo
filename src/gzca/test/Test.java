package gzca.test;


import cn.com.syan.spark.app.sdk.classified.SparkClient;
import cn.com.syan.spark.app.sdk.classified.SparkClients;
import cn.com.syan.spark.app.sdk.classified.entity.Response;

import java.io.IOException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by wuziqing on 2016-12-8.
 */
public class Test {
    public static void main(String arg[]) throws CertificateException, InvalidKeySpecException, IOException, SignatureException {
        Test.pushdata();
    }

    public static void pushdata() throws CertificateException, InvalidKeySpecException, SignatureException, IOException {
//        SparkClient sparkClient = SparkClients.getDefaultClient();
//        //查询权限组
//        Response rs = sparkClient.queryGroup();
//        System.out.println("testQueryGroup>"+rs.getMsg()+ rs.getData());
//        System.out.println("-------------------------------------------------");
//

        //组用户查询
//        String     certificate="";
//        certificate  ="MIID0TCCA3WgAwIBAgIPBwAFIBcEIREAAAAAFgKYMAwGCCqBHM9VAYN1BQAwgYYxCzAJBgNVBAYTAkNOMQ8wDQYDVQQIDAbotLXlt54xDzANBgNVBAcMBui0temYszEqMCgGA1UECgwh6LS15bee55yB55S15a2Q6K+B5Lmm5pyJ6ZmQ5YWs5Y+4MRIwEAYDVQQLDAnov5DokKXpg6gxFTATBgNVBAMMDEdaQ0FPUEVSUk9PVDAeFw0xNzA0MjAxNjAwMDBaFw0xODA0MjAxNjAwMDBaMIGNMQswCQYDVQQGEwJDTjESMBAGA1UECAwJ6LS15bee55yBMRIwEAYDVQQHDAnotLXpmLPluIIxIDAeBgNVBAoMF+i0teW3nkNB5rWL6K+V6K+B5LmmU00yMRIwEAYDVQQLDAnlkLTlrZDmuIUxIDAeBgNVBAMMF+i0teW3nkNB5rWL6K+V6K+B5LmmU00yMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAELLBsH6IKnVECikni/Vh8QDWTebbS9Dors2ToJcgo4m+AQ24AfZuV2DoS7PrqZUXgoV1JsBaI5AUp6xRgjEL7YqOCAbkwggG1MB8GA1UdIwQYMBaAFO70/TLhGRiTSghuZIVHUstDcGoVMB0GA1UdDgQWBBStweCT/lrFSRIeDmriXYStZJpnnzBYBgNVHSAEUTBPME0GCyqBHIbwFAICAQEBMD4wPAYIKwYBBQUHAgEWMGh0dHBzOi8vd3d3Lmd6Y2EuY2MvZG93bi9DUFMvR1pDQSUyMENQU19WMS4yLnBkZjALBgNVHQ8EBAMCBsAwTgYDVR0fBEcwRTBDoEGgP4Y9aHR0cDovL2NybC5nemNhLmNjOjgwODAvR1pDQURvd25sb2FkQ2VudGVyL2Rvd25sb2FkU20yQ3JsLmpzcDCBggYIKwYBBQUHAQEEdjB0MDYGCCsGAQUFBzABhipodHRwOi8vb2NzcC5nemNhLmNjOjgwMTMvb2NzcC9vY3NwY2dpYy5jZ2kwOgYIKwYBBQUHMAKGLmh0dHBzOi8vd3d3Lmd6Y2EuY2MvZG93bi9jZXJ0L0daQ0FPUEVSUk9PVC5jZXIwGAYIKoEc0BQEAQQEDBMKMDAwMDAwMDAtMTAdBgUqVhUBAwQUDBIwMzkyMDIwMTcwNDIxNDkwNTgwDAYIKoEcz1UBg3UFAANIADBFAiEA16BSc1C5ewN/HOKnNvCX8Dc50ztWRYaP95TDHTcVi5wCIEAxAQ2e3+YtENC4UckZQ3c45uDpi1iv3P9CG+I0QPbT";
//        /****
//         * 以上是测试代码获取证书base64编码字符串
//         * 以下代码为调用接口示例代码
//         */
//        SparkClient sc=SparkClients.getDefaultClient();
//        User user=new User();
//        X509Certificate x=  CertificateConverter.fromBase64(certificate);
//        user.setCertificate(certificate);
////        user.setCertificate(CertificateConverter.toBase64String(x));
//        user.setName("贵州CA测试证书SM2");       //设置用户名称 或者单位名称     如果不设置 系统为默认使用证书CN项作为名称
//        user.setIdno("00000000-1");               //证件号
////        user.setPhone("18285107141");        //联系电话
//        List<Oid> oidList=new ArrayList<Oid>();
//        Oid oid=new Oid();
//        oid.setOidMask("Test_Group_OID");             //组扩展 标识
//        oid.setOidValue("TEST1");           //组上扩展值
//        oidList.add(oid);
//        /**
//         * 如果应用组没有oid扩展  oidList 可以为null
//         * 如   sc.joinGroup(user,1,null);
//         */
//        Response r= sc.joinGroup(user,1,oidList);
//        System.out.println("testJoinGroup>"+ r.getRet() +r.getMsg());
//
        String newCertificate= "MIID0TCCA3WgAwIBAgIPBwAFIBcEIREAAAAAFgKYMAwGCCqBHM9VAYN1BQAwgYYxCzAJBgNVBAYTAkNOMQ8wDQYDVQQIDAbotLXlt54xDzANBgNVBAcMBui0temYszEqMCgGA1UECgwh6LS15bee55yB55S15a2Q6K+B5Lmm5pyJ6ZmQ5YWs5Y+4MRIwEAYDVQQLDAnov5DokKXpg6gxFTATBgNVBAMMDEdaQ0FPUEVSUk9PVDAeFw0xNzA0MjAxNjAwMDBaFw0xODA0MjAxNjAwMDBaMIGNMQswCQYDVQQGEwJDTjESMBAGA1UECAwJ6LS15bee55yBMRIwEAYDVQQHDAnotLXpmLPluIIxIDAeBgNVBAoMF+i0teW3nkNB5rWL6K+V6K+B5LmmU00yMRIwEAYDVQQLDAnlkLTlrZDmuIUxIDAeBgNVBAMMF+i0teW3nkNB5rWL6K+V6K+B5LmmU00yMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAELLBsH6IKnVECikni/Vh8QDWTebbS9Dors2ToJcgo4m+AQ24AfZuV2DoS7PrqZUXgoV1JsBaI5AUp6xRgjEL7YqOCAbkwggG1MB8GA1UdIwQYMBaAFO70/TLhGRiTSghuZIVHUstDcGoVMB0GA1UdDgQWBBStweCT/lrFSRIeDmriXYStZJpnnzBYBgNVHSAEUTBPME0GCyqBHIbwFAICAQEBMD4wPAYIKwYBBQUHAgEWMGh0dHBzOi8vd3d3Lmd6Y2EuY2MvZG93bi9DUFMvR1pDQSUyMENQU19WMS4yLnBkZjALBgNVHQ8EBAMCBsAwTgYDVR0fBEcwRTBDoEGgP4Y9aHR0cDovL2NybC5nemNhLmNjOjgwODAvR1pDQURvd25sb2FkQ2VudGVyL2Rvd25sb2FkU20yQ3JsLmpzcDCBggYIKwYBBQUHAQEEdjB0MDYGCCsGAQUFBzABhipodHRwOi8vb2NzcC5nemNhLmNjOjgwMTMvb2NzcC9vY3NwY2dpYy5jZ2kwOgYIKwYBBQUHMAKGLmh0dHBzOi8vd3d3Lmd6Y2EuY2MvZG93bi9jZXJ0L0daQ0FPUEVSUk9PVC5jZXIwGAYIKoEc0BQEAQQEDBMKMDAwMDAwMDAtMTAdBgUqVhUBAwQUDBIwMzkyMDIwMTcwNDIxNDkwNTgwDAYIKoEcz1UBg3UFAANIADBFAiEA16BSc1C5ewN/HOKnNvCX8Dc50ztWRYaP95TDHTcVi5wCIEAxAQ2e3+YtENC4UckZQ3c45uDpi1iv3P9CG+I0QPbT";
        String oldCertificate=  "MIIDgTCCAmmgAwIBAgIPBwAFIBcCJQEAAAAAFDRWMA0GCSqGSIb3DQEBBQUAME8xCzAJBgNVBAYTAkNOMTEwLwYDVQQKDChHVUlaSE9VIEVMRUNUUk9OSUMgQ0VSVElGSUNBVEVTIENPLixMVEQuMQ0wCwYDVQQDDARHWkNBMB4XDTE3MDIyNDE2MDAwMFoXDTIwMDQwNTE2MDAwMFowgaUxCzAJBgNVBAYTAkNOMRIwEAYDVQQIDAnotLXlt57nnIExEjAQBgNVBAcMCei0temYs+W4gjEtMCsGA1UECgwk55yB5Lit5b+DZ3pjYeWQjOaZuuS8n+S4mnJzYea1i+ivlTIzMRAwDgYDVQQLDAfms5XkuroyMS0wKwYDVQQDDCTnnIHkuK3lv4NnemNh5ZCM5pm65Lyf5LiacnNh5rWL6K+VMjMwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAOvdpYcF3rk8jyomJB6+A4F51sMCFwUQqoqbCw9KbuDEljDiby+SPtXubgazoOjzG2Pa68xd5M3sb1MQ1OhTYKnl6lNVcGjpsZZJEmYf6qeO1C2A77nCRj/6WgM48R5ZQRyjWQNH/ha+HyXpdd67MGTEqU851iXaf/yu2BviUqBtAgMBAAGjgYYwgYMwHwYDVR0jBBgwFoAUxVNxJTI/CgJzXtr0xlZted11RmEwHQYDVR0OBBYEFH2jkl50v54szRSz3ZFYNqMMkmsTMAsGA1UdDwQEAwIGwDAVBggqgRzQFAQBBAQJEwc0MzI1MzQ1MB0GBSpWFQEDBBQMEjAzOTIwMjAxNjA0MDYyNTkwNTANBgkqhkiG9w0BAQUFAAOCAQEAOBuAeg73b8qzzw/iREGCJ+J5aRTD1snR4isPO9RUUjBqnI0Hwth51lLSzxmirL+pS5RDDFUI8I3wASzzWLitXOfw2wSU/3fSZpjc111nZyC8rpxf/gMmMGNUaO53h+AE27gPQcJJ5FM8ROlta/bKBbU3m4GmYWPwPQo5Sg1SwxioyKo1d1x1snFdlmrQ+0NhqutBzNmmq69rukg8JzMkhs/oWX1VRMv4w7C2iVh/X4kD2icxYzrrgvklTWd6Db6GZs4/ELmFTfWdVvOHunkUAOGRye0m8czeukgTFZj26F8CKZRMowiE7o9zEWn42vvAxqckQKi8y2BP6KOc+RX1nw==";
        SparkClient sc= SparkClients.getDefaultClient();
        Response r= sc.updateCertificate(oldCertificate,newCertificate);
        System.out.println("testUpdateCertificate>"+ r.getRet()+r.getMsg());



    }
}
