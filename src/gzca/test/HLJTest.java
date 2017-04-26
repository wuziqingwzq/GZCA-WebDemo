package gzca.test;/*
 * Project: android-base sdk
 *
 * @(#) Test.java 
  * Created on  202015/6/17 13:13 
 * @author  lyb
 * Copyright 2014 Jiangsu Syan Technology Co.,Ltd. All rights reserved.
 * Jiangsu Syan PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import cn.com.syan.jcee.common.impl.utils.CertificateConverter;
import cn.com.syan.spark.app.sdk.classified.NullValueException;
import cn.com.syan.spark.app.sdk.classified.SparkClient;
import cn.com.syan.spark.app.sdk.classified.SparkClients;
import cn.com.syan.spark.app.sdk.classified.entity.AuthUserpswd;
import cn.com.syan.spark.app.sdk.classified.entity.Oid;
import cn.com.syan.spark.app.sdk.classified.entity.Response;
import cn.com.syan.spark.app.sdk.classified.entity.User;
import cn.com.syan.spark.app.sdk.classified.util.Conventer;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This class provides...
 * </p>
 * User: lyb
 * Date: 2015/6/17 13:13
 */
public class HLJTest {

    /**
     * 测试返回 可以使用的应用组信息
     * @throws CertificateException
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws SignatureException
     */
    public static  void  testQueryGroup() throws CertificateException, IOException, InvalidKeySpecException, SignatureException {
        SparkClient sc=SparkClients.getDefaultClient();
        Response r=sc.queryGroup();
        System.out.println("testQueryGroup>"+r.getMsg()+ r.getData());
    }

    /**
     * 测试 用户开通应用组接口
     * @throws CertificateException
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws SignatureException
     */
    public static  void  testJoinGroup() throws CertificateException, IOException, InvalidKeySpecException, SignatureException {


        String     certificate="";


        certificate  ="MIIDmjCCAoKgAwIBAgIPBwAFIBURJhEAAAAABRJgMA0GCSqGSIb3DQEBBQUAME8xCzAJBgNVBAYTAkNOMTEwLwYDVQQKDChHVUlaSE9VIEVMRUNUUk9OSUMgQ0VSVElGSUNBVEVTIENPLixMVEQuMQ0wCwYDVQQDDARHWkNBMB4XDTE1MTEyNTE2MDAwMFoXDTE2MTEyNTE2MDAwMFowgbwxCzAJBgNVBAYTAkNOMRIwEAYDVQQIDAnotLXlt57nnIExEjAQBgNVBAcMCei0temYs+W4gjE2MDQGA1UECgwt6LS15bee55yB55S15a2Q6K+B5Lmm5pyJ6ZmQ5YWs5Y+477yI5rWL6K+V77yJMRUwEwYDVQQLDAzmtYvor5Xms5XkuroxNjA0BgNVBAMMLei0teW3nuecgeeUteWtkOivgeS5puaciemZkOWFrOWPuO+8iOa1i+ivle+8iTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEArbqGsuTbZBO/5V5yS5ow919czK0o+cq92HS510CCc512TlhUSkSKC1T07XDQhgY3FhLdS46ErQVMBcdzfBO2kPh7UikHJHdpi66Ga7BNB2KNVVRKPIWx8JZC22V0L5hsgKyTPHs8H6Yug0NVRR6zN7WujETvvj6+p224yYt6RUUCAwEAAaOBiDCBhTAfBgNVHSMEGDAWgBTFU3ElMj8KAnNe2vTGVm153XVGYTAdBgNVHQ4EFgQUNdbpvp8Hip/gRoU0WH7DLT3bCoMwCwYDVR0PBAQDAgbAMBcGCCqBHNAUBAEEBAsTCTMxMDE4ODU4MTAdBgUqVhUBAwQUDBIwMzkyMDIwMTUxMTI2MTg2NDMwDQYJKoZIhvcNAQEFBQADggEBAE5AKh63YkqnRYpafcJCg8eftReDmkn0Iw6h2wXeIT7T1PTVhhO844pRPX7Eg5GNkH+L+7E+wgHfFTTfw+20LhHgneVd5mZp3IkCeZnr3XqfWzhQAc2zqoCW048dt4ACyRSVu/iN5zRfwksTAr0vI1pMPwKjt+eLMIXFq7L8zdHUXyI7LZn43vbD1G2xMEFAEdY5uIL/l7RMB4+oUiwyKSjC/59AFppr7STiYZKkugosa+6RvIRPuNephVF7oKFqnb+7q7BYVoNyfmrOtvu9D1gkFh/NlMSoJNb842tONU7OJwIDQyoAC87r99Jjy3i1M3BfpvSzrnT/uDG39iJgda8=";
        /****
         * 以上是测试代码获取证书base64编码字符串
         * 以下代码为调用接口示例代码
         */

        SparkClient sc=SparkClients.getDefaultClient();
        User user=new User();
        X509Certificate x=  CertificateConverter.fromBase64(certificate);

        user.setCertificate(certificate);
//        user.setCertificate(CertificateConverter.toBase64String(x));

        user.setName("森赫电梯股份有限公司");       //设置用户名称 或者单位名称     如果不设置 系统为默认使用证书CN项作为名称
        user.setIdno("669165499");               //证件号
//        user.setPhone("18285107141");        //联系电话

        List<Oid> oidList=new ArrayList<Oid>();
        Oid oid=new Oid();
        oid.setOidMask("gz.public.uuid");             //组扩展 标识
        oid.setOidValue("297ebc2d5595ea52015595f35510019c");           //组上扩展值
        oidList.add(oid);
//        oid=new Oid();
//        oid.setOidMask("test.test.test");             //组扩展 标识
//        oid.setOidValue("test.test.test55555");           //组上扩展值
//        oidList.add(oid);


        /**
         * 如果应用组没有oid扩展  oidList 可以为null
         * 如   sc.joinGroup(user,1,null);
         */
        Response r= sc.joinGroup(user,1,oidList);
        System.out.println("testJoinGroup>"+ r.getRet() +r.getMsg());

    }

    /**
     * 测试 证书更新
     * @throws CertificateException
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws SignatureException
     */
    public static  void  testUpdateCertificate() throws CertificateException, IOException, InvalidKeySpecException, SignatureException {
        String path="e:\\3.cer";
        System.out.println(">>>>"+path);
        FileInputStream fis = new FileInputStream(path);
        byte[] content=new byte[fis.available()];
        fis.read(content);
        byte[] tno = new byte[2];
        System.arraycopy(content, 0, tno, 0, 2);

        String     oldCertificate="";
        if ("3082".equals(Conventer.bytesToHexString(tno))) {
            oldCertificate = new String(Base64.encodeBase64(content));
        } else {
            oldCertificate = new String(content);
        }

        String newpath="e:\\4.cer";

        FileInputStream newfis = new FileInputStream(newpath);
        byte[] newcontent=new byte[newfis.available()];
        newfis.read(newcontent);
        byte[] newtno = new byte[2];
        System.arraycopy(newcontent, 0, newtno, 0, 2);
        String     newCertificate="";
        if ("3082".equals(Conventer.bytesToHexString(newtno))) {
            newCertificate = new String(Base64.encodeBase64(newcontent));
        } else {
            newCertificate = new String(newcontent);
        }
        /****
         *    以上是测试代码用于获取用户旧证书 和新证书的base64编码字符串
         *    以下为接口调用代码  目的是用新证书 更新旧证书
         */

        SparkClient sc=SparkClients.getDefaultClient();
        Response r= sc.updateCertificate(oldCertificate,newCertificate);
        System.out.println("testUpdateCertificate>"+ r.getRet()+r.getMsg());

    }


    public static  void  testDisableCertififcate() throws CertificateException, IOException, InvalidKeySpecException, SignatureException {
        String path="e:\\4.cer";;
        System.out.println(">>>>"+path);
        FileInputStream fis = new FileInputStream(path);
        byte[] content=new byte[fis.available()];
        fis.read(content);
        byte[] tno = new byte[2];
        System.arraycopy(content, 0, tno, 0, 2);

        String     certificate="";
        if ("3082".equals(Conventer.bytesToHexString(tno))) {
            certificate = new String(Base64.encodeBase64(content));
        } else {
            certificate = new String(content);
        }

        /****
         *    以上是测试代码用于获取用户禁用证书的base64编码字符串
         *    以下为接口调用代码  禁用指定证书
         */
        SparkClient sc=SparkClients.getDefaultClient();
        Response r= sc.disableCertificate(certificate);
        System.out.println("testDisableCertififcate>"+ r.getRet()+r.getMsg());

    }

    public static  void  testEnableCertififcate() throws CertificateException, IOException, InvalidKeySpecException, SignatureException {
        String path="e:\\4.cer";;
        System.out.println(">>>>"+path);
        FileInputStream fis = new FileInputStream(path);
        byte[] content=new byte[fis.available()];
        fis.read(content);
        byte[] tno = new byte[2];
        System.arraycopy(content, 0, tno, 0, 2);

        String     certificate="";
        if ("3082".equals(Conventer.bytesToHexString(tno))) {
            certificate = new String(Base64.encodeBase64(content));
        } else {
            certificate = new String(content);
        }
        /****
         *    以上是测试代码用于获取用户启动证书的base64编码字符串
         *    以下为接口调用代码  启动指定证书
         */

        SparkClient sc=SparkClients.getDefaultClient();
        Response r= sc.enableCertificate(certificate);
        System.out.println("testEnableCertififcate>"+ r.getRet()+r.getMsg());

    }

    /**
     * 测试注册用户名
     * @param authUserpswd
     * @throws CertificateException
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws SignatureException
     * @throws NullValueException
     * @throws NoSuchAlgorithmException
     */
    public static  void  testRegisterUsername(AuthUserpswd authUserpswd) throws CertificateException, IOException, InvalidKeySpecException, SignatureException, NullValueException, NoSuchAlgorithmException {
        SparkClient sc=SparkClients.getDefaultClient();
        Response r=sc.registerUsername(authUserpswd);
        System.out.println("testRegisterUsername>"+r.getRet() +" " +r.getMsg()+ r.getData());
    }


    public static void main(String [] arg){

        try {


            HLJTest.testJoinGroup();

        } catch (Exception  e1) {
            e1.printStackTrace();
        }



    }



}
