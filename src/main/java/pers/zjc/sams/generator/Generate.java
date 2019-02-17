package pers.zjc.sams.generator;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generate {
    public static void generator() throws Exception{
           List<String> warnings = new ArrayList<String>();
           boolean overwrite = true;
           //项目根路径不要有中文,有中文的使用绝对路径
           File configFile = new File("D:\\workspace\\sams\\src\\main\\resources\\generatorConfig.xml");
           ConfigurationParser cp = new ConfigurationParser(warnings);
           Configuration config = cp.parseConfiguration(configFile);
           DefaultShellCallback callback = new DefaultShellCallback(overwrite);
           MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
           myBatisGenerator.generate(null);
    }
    public static void main(String[] args) {
        try {
            generator();
            System.out.println("生成成功！！！！！！！！！！");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}