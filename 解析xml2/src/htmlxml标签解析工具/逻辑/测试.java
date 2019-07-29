package htmlxml标签解析工具.逻辑;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import htmlxml标签解析工具.Object.Xml;
public class 测试 {
    public static void main(String[] arge) {
        new 测试();
    }

    测试() {
        解析xml();
    }
    private void 解析xml() {
//        String s1 = "E:\\开发\\Eclipse\\安卓工程\\记事本实例\\res\\layout\\notes_list_cell.xml",
//                s2 = "E:\\开发\\Eclipse\\javaEE\\javaee2\\MarsHTML5kecheng\\WebContent\\anzhuohehtmlhunhekaifa\\index.html";
//        FileInputStream stream = null;
//        try {
//            stream = new FileInputStream(s1);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        String s = 静态工具包.输入流转字符串(stream, "utf-8");
    	String xmlstring="<FrameLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\r\n" + 
    			"    android:layout_width=\"match_parent\"\r\n" + 
    			"    android:layout_height=\"match_parent\">\r\n" + 
    			"    <!--android:divider=\"@null\"去掉分隔线-->\r\n" + 
    			"\r\n" + 
    			"    <ListView\r\n" + 
    			"        android:id=\"@+id/lv_组图\"\r\n" + 
    			"        android:layout_width=\"match_parent\"\r\n" + 
    			"        android:layout_height=\"match_parent\"\r\n" + 
    			"        android:background=\"#00CCCCCC\"\r\n" + 
    			"        android:divider=\"@null\" />\r\n" + 
    			"\r\n" + 
    			"    <GridView\r\n" + 
    			"        android:id=\"@+id/gv_组图\"\r\n" + 
    			"        android:layout_width=\"match_parent\"\r\n" + 
    			"        android:layout_height=\"match_parent\"\r\n" + 
    			"        android:background=\"#00CCCCCC\"\r\n" + 
    			"        android:divider=\"@null\"\r\n" + 
    			"        android:numColumns=\"2\"\r\n" + 
    			"        android:visibility=\"gone\" />\r\n" + 
    			"</FrameLayout>";
    	String html="<html>"
    			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"
    			
    			+ "<title>妮可妮可妮</title>"
    			
    			+ "<style type=\"text/css\">"
    			+ " #first{ background:red;}"
    			+ "</style>"
    			+ "<p id=\"first\">红色代表激情代表革命 </p>"
    			+ "<a href=\"https://www.bilibili.com/video/av13094734?from=search&seid=6652555122435605300\">超级链接</a>"
    			
    			+ " </html>";
    	String s=xmlstring;
        Xml数据解析 xml数据解析 = new Xml数据解析();
        xml数据解析.Xml解析(new Xml数据解析.解析回调() {

			@Override
			public void 开始解析() {
				sc("----------开始解析------------");
				
			}

			@Override
			public void 正在解析的标签(String bq) {
				
				sc("正在解析的标签:"+bq+"\n");
				
			}

			@Override
			public void 解析完当前(String bq) {
				sc("解析完当前:"+bq);
				sc("--------------------");
			}

			@Override
			public void 解析里面(String s) {
				sc("--------------------");
				sc("解析里面:"+s);
				
			}


			@Override
			public void 解析属性和属性值(String 标签名称, HashMap<String, String> 属性属性值, String 内容, String 实体内容, Xml xml) {
				sc("标签名称:"+标签名称);
                for(Map.Entry<String,String> entry:属性属性值.entrySet()){
                    sc("属性："+entry.getKey()+"\t属性值："+entry.getValue());
                }
                sc("内容："+实体内容);
                
				
			}

			@Override
			public void 解析完毕() {
				sc("解析完毕！");
				
			}
        	
        }, s);
    }

    static void sc(Object o) {
        System.out.println(o);
    }
}
