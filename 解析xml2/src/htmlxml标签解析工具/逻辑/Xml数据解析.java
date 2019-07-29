package htmlxml标签解析工具.逻辑;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import htmlxml标签解析工具.Object.Xml;

public class Xml数据解析 {
    private String url, 主机;
    private List<String> 标签嵌套位置 = new ArrayList<String>();
    static String 当前嵌套 = "";
    private Xml同级解析 html解析 = new Xml同级解析();
    private StringBuilder html_xml测试信息 = new StringBuilder();

    public Xml数据解析() {
        html_xml测试信息.append("----当前html解析----\r\n___________________________________________\r\n");
    }

    public void Xml解析(解析回调 back, String xmlstring) {
//        if (back == null) return;
        back.开始解析();
        List<Xml> list = html解析.解析HTML(xmlstring);
        for (int i = 0; i < list.size(); i++) {
            String t = 当前嵌套;
            Xml xml = list.get(i);
            当前嵌套 = 当前嵌套 + "." +xml.get名称();
            back.正在解析的标签(当前嵌套);
            back.解析属性和属性值(xml.get名称(), xml.get属性属性值(), xml.get内容(), html解析.内容提取(xml),xml);
//            back.解析属性和属性值(xml);
            if(!xml.get内容().equals("")) back.解析里面(当前嵌套);
            Xml解析(back, list.get(i).get内容(), 当前嵌套);
            back.解析完当前(当前嵌套);
            当前嵌套 = t;
        }
        back.解析完毕();
    }

    //	private 解析回调 back;
    private void Xml解析(解析回调 back, String xmlstring, String 嵌套数量) {
        if (back == null) return;
        List<Xml> list = html解析.解析HTML(xmlstring);
//        back.解析下一级(嵌套数量);
        String t = 嵌套数量;
        for (int i = 0; i < list.size(); i++) {
            Xml xml = list.get(i);
            if (xml.is为实体内容) {
                t = t + "." + xml.get名称();
                back.正在解析的标签(t);
            }else {
                back.正在解析的标签("没有结束标记："+xml.get名称());
            }
            back.解析属性和属性值(xml.get名称(), xml.get属性属性值(), xml.get内容(), html解析.内容提取(xml),xml);
            if(!xml.get内容().equals("")) back.解析里面(当前嵌套);
            Xml解析(back, list.get(i).get内容(), t);
            back.解析完当前(t);
            t = 嵌套数量;
        }
//        back.解析这一级(嵌套数量);
    }

    int 解析级别 = 0;

    public interface 解析回调 {
        void 开始解析();
        void 正在解析的标签(String bq);
        void 解析完当前(String bq);
        void 解析里面(String s);
        void 解析属性和属性值(String 标签名称, HashMap<String, String> 属性属性值, String 内容, String 实体内容,Xml xml);
//        void 解析属性和属性值();

        void 解析完毕();
    }
    @Deprecated
    private String html测试(String html) {
        /**伪代码：   List<aaa> list=xxx(html);
         *            for(int i=0;i<list.size();i++){
         *            aaa a=list.get(i);
         *     为了避免重复，就用变量互换
         *            String t=当前嵌套;
         *            当前嵌套=当前嵌套+"."+a.get标签名称();
         *            标签嵌套层次结构.add(当前嵌套);
         *            String cc=a.get内容();
         *            html分析(cc);
         *            执行完下一层循环前把变量换回来,这样就知道层次结构了；
         *            当前嵌套=t;
         *            }*/
        List<Xml> list = html解析.解析HTML(html);
        for (int i = 0; i < list.size(); i++) {
            String t = 当前嵌套;
            当前嵌套 = 当前嵌套 + "." + list.get(i).get名称();
            html_xml测试信息.append("\n当前标签名称；" + list.get(i).get名称() + "\r\n");
            html_xml测试信息.append(list.get(i).get名称() + "的位置；" + 当前嵌套 + "\r\n");
            标签嵌套位置.add(当前嵌套);
            String html2 = list.get(i).get内容();
            HashMap<String, String> map = list.get(i).get属性属性值();
            if (map.size() == 0) {
                html_xml测试信息.append(list.get(i).get名称() + "无属性和属性值\r\n");
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                html_xml测试信息.append("属性：" + entry.getKey() + "\t属性值为：" + entry.getValue() + "\r\n");
            }
            List<Integer> list2 = 静态工具包.get字符串匹配(html2, "</");
            List<Integer> list3 = 静态工具包.get字符串匹配(html2, "/>");
            if (list2.size() > 0 || list3.size() > 0) {
                html_xml测试信息.append(list.get(i).get名称() + "标签内还嵌套有标签，不当作内容显示。\r\n");
                html_xml测试信息.append("___________________________________________________________\r\n");
                html测试(html2);
            } else {
                if (!list.get(i).is为实体内容) {
                    html_xml测试信息.append(list.get(i).get名称() + "标签没有结束标记\r\n");
                    html_xml测试信息.append("___________________________________________________________\r\n");
                } else {
                    html_xml测试信息.append(list.get(i).get名称() + "的内容为；" + html2 + "\r\n");
                    html_xml测试信息.append("___________________________________________________________\r\n");
                }
            }
            当前嵌套 = t;
        }
        return html_xml测试信息.toString();
    }
}
