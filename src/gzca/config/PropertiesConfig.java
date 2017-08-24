package gzca.config;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wuziqing on 2017-5-11.
 */
public class PropertiesConfig {
    private String filepath = "/Config.properties";

    /**
     * 设置文件路径
     *
     * @return
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * 获取文件路径
     *
     * @param filepath
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * 通过文件路径读取到配置类Properties
     *
     * @return
     */
    public Properties readPropertyFile() {
        Properties prop = new Properties();
        try {
            InputStream inputStream = getClass().getResourceAsStream(filepath);
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println("配置文件读取失败");
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * 读取配置文件中Key所对应的Value值
     *
     * @param propertyName 传入Key值
     * @return 返回K-V对中的Value值
     */
    public String getConfig(String propertyName) {
        String result = "";
        Properties prop = this.readPropertyFile();
        result = prop.getProperty(propertyName);
        return result;
    }

//    public PrintStream getConfigJson(){
//        int result = 0;
//        Properties prop = this.readPropertyFile();
//        PrintStream ps = new OutputStream();
//        prop.list(ps);
//        return ps ;
//    }

    public int setConfig(String propertyName, String propertyValue) {
        int result = 0;
        Properties prop = this.readPropertyFile();
        try {
            if (prop.getProperty(propertyName) != null) {
                Object setResult = prop.setProperty(propertyName, propertyValue);
                String fullFilepath = getClass().getResource(this.filepath).getPath();
                FileOutputStream fileOutputStream = new FileOutputStream(fullFilepath);
                if (setResult != null) {
                    prop.store(fileOutputStream, "");
                    result = 1;
                } else {
                    System.out.println("推送失败");
                    return 0;
                }
                fileOutputStream.close();
            } else return 0;
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到，可能是路径错误");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("写入文件失败");
            e.printStackTrace();
        }
        return result;
    }

    public int addConfig(String propertyName, String propertyValue) {
        int result = 0;

        return result;
    }

    /**
     * 获取配置文件中的所有配置项
     * @return Map配置项集合
     */
    public Map getAllConfig() {
        Map mapProperties = new HashMap<String ,String >();
        String key;
        String value;
        Properties prop = this.readPropertyFile();
        Enumeration en = prop.propertyNames();
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            value = this.getConfig(key);
            mapProperties.put(key,value);
        }
        return mapProperties;
    }
}
