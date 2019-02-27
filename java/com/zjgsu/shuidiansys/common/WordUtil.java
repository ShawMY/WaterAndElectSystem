package com.zjgsu.shuidiansys.common;

import com.zjgsu.shuidiansys.pojo.FeeInfoFtl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class WordUtil {
    private static final Logger logger = LoggerFactory.getLogger(WordUtil.class);
    public static void createWord(OutputStream out, String templatePath, Object props) {
        Writer wrter = null;
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        // 模板加载路径
        configuration.setClassForTemplateLoading(WordUtil.class, "/wordTemplate");
        Template t = null;
        try {
            t = configuration.getTemplate(templatePath);
            wrter = new OutputStreamWriter(out, "utf-8");
            t.process(props, wrter);
            wrter.flush();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            if (null != wrter) {
                try {
                    wrter.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public static Map<String,Object> matchFtl(FeeInfoFtl ftl){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nian1", ftl.nian1);
        map.put("yue1", ftl.yue1);
        map.put("ri1", ftl.ri1);
        map.put("nian2", ftl.nian2);
        map.put("yue2", ftl.yue2);
        map.put("ri2", ftl.ri2);
        map.put("yue3", ftl.yue3);
        map.put("ri3", ftl.ri3);
        map.put("huming", ftl.huming);
        map.put("dizhi", ftl.dizhi);
        map.put("biao1", ftl.biao1);
        map.put("biao2", ftl.biao2);
        map.put("biao3", ftl.biao3);
        map.put("shangqi1", ftl.shangqi1);
        map.put("shangqi2", ftl.shangqi2);
        map.put("shangqi3", ftl.shangqi3);
        map.put("benqi1", ftl.benqi1);
        map.put("benqi2", ftl.benqi2);
        map.put("benqi3",ftl.benqi3);
        map.put("shiyong1",ftl.shiyong1);
        map.put("shiyong2",ftl.shiyong2);
        map.put("shiyong3",ftl.shiyong3);
        map.put("danjia",ftl.danjia);
        map.put("jine",ftl.jine);
        map.put("beizhu",ftl.beizhu);
        return map;
    }
}
