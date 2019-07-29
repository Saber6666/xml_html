
<h2>这是我第一次上传代码到github，好吧废话不多说</h2>
<i style="background:red">这个是我自己写的解析xml框架，它不但可以解析xml，还可以解析html,怎么用它呢？</i>
<br/>首先下载jar包，导入到工程就可以使用了
<br/>
<br/>
怎么使用呢？首先new这个类出来<br/>
Xml数据解析 xml数据解析 = new Xml数据解析();
<br/>
然后调用这个方法：<br/>
xml数据解析.Xml解(...)<br/>
第一个参数传递的是接口回调，第二个参数传递的是要解析的xml字符串,如：<br/>
<fieldset>
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
static void sc(Object o) {
        System.out.println(o);
    }
</fieldset>
